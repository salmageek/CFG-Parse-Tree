package Rules;

import LexicalAnalyzer.Match;
import LexicalAnalyzer.Tokenizer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Parser {
    public Queue<Match> queue;

    public Parser(Queue<Match> queue) {
        this.queue = queue;
    }

    public Parser() {

        this.queue = new LinkedList<>();
    }


    public void loadTokens() throws FileNotFoundException {
        Tokenizer t = new Tokenizer();
        t.runTokenizer();

        Scanner scanner = new Scanner(new File("E:\\FOURTH YEAR\\second semester\\compilers\\project1\\CFG-Parse-Tree-1\\src\\output.txt"));
        while (scanner.hasNext()) {
            String token = scanner.next();
            scanner.next();
            String value = scanner.next();
            Match m = new Match(token, value, 0, 0, 0);
            this.queue.add(m);
        }
    }

    // ==================== VarDeclaration ===================== //

    public VarDeclaration_R varDeclaration() {
        Type_R t = type();
        if (t == null) {
            //System.out.println("Syntax Error! type null");
            return null;
        }
        Identifier_R id = identifier();
        if (id == null) {
            System.out.println("Syntax Error! no id");
            return null;
        }
        Match token = queue.peek();
        if (token == null )  {
            System.out.println("Syntax Error! semicolon null");
            return null;
        }
        if(!token.token.equals("SEMICOLON")){
            return null;
        }
        queue.poll();
        return new VarDeclaration_R(t, id, token);
    }

    public Type_R type() {
        DataType_R d = datatype();
        if (d == null) {
            //System.out.println("Sytnax Error! datatype null");
            return null;
        }
        Array_R a = array();
        return new Type_R(d, a);
    }

    public DataType_R datatype() {
        Match token = queue.peek();
        if (token.token == null) {
            //System.out.println("Syntax Error! datatype null");
            return null;
        }
        if (!token.token.equals("BOOLEAN") && !(token.token.equals("INT")) && !token.token.equals("FLOAT") && !token.token.equals("STRING") && !token.token.equals("CHAR")) {
            //System.out.println("Syntax Error! wrong datatype");
            return null;
        }
        queue.poll();
        return new DataType_R(token);
    }

    public Array_R array() {
        Match token = queue.peek();

        if (token != null) {
            if (token.token.equals("LEFT_SQUARE_B")) {
                queue.poll();
                Match token2 = queue.peek();
                if (token2 == null) {
                    System.out.println("Syntax Error! right null");
                    return null;
                }

                if (!token2.token.equals("RIGHT_SQUARE_B")) {
                    System.out.println("Syntax Error! no right ]");
                    return null;
                }
                queue.poll();
                return new Array_R(token, token2);
            }
        }
        return null;
    }

    public Identifier_R identifier() {
        Match token = queue.peek();
        if (token == null) {
            System.out.println("Synatx Error! Identifier-> <id> is null");
            return null;
        }
        if (token.token.equals("ID")){
            queue.poll();
            return new Identifier_R(token);
        }
        return null;
    }

    // ================== Expression ======================== //

    public Expression_R expression() {
        Match token = queue.peek();
        if (token == null){
            // System.out.println("Syntax Error! no tokens in Expression");
            /* Salma->expression can be called in a place where it can be null*/
            return null;
        }
        if (token.token.equals("INTEGRAL_LITERAL")){
            queue.poll();
            Expression2_R expression2 = Expression2();
            return new Expression_R(token, expression2);


        } else if (token.token.equals("FLOAT_LITERAL")){
            queue.poll();
            Expression2_R expression2 = Expression2();
            return new Expression_R(token, expression2);

        } else if (token.token.equals("TRUE")){
            queue.poll();
            Expression2_R expression2 = Expression2();
            return new Expression_R(token, expression2);

        } else if (token.token.equals("FALSE")) {
            queue.poll();
            Expression2_R expression2 = Expression2();
            return new Expression_R(token, expression2);


        } else if (token.token.equals("THIS")) {
            queue.poll();
            Expression2_R expression2 = Expression2();
            return new Expression_R(token, expression2);

        } else if (token.token.equals("NEW")) {
            // TODO: check ExpressionNew function
            queue.poll();
            ExpressionNew_R expressionNew = ExpressionNew();
            if (expressionNew == null){
                System.out.println("Syntax Error! expression->new expressionNew is null");
                return null;
            }
            return new Expression_R(token, expressionNew);

        } else if (token.token.equals("NOT")) {
            queue.poll();
            Expression_R expression = expression();
            if (expression == null){
                System.out.println("Syntax Error! expression->! expression is null");
                return null;
            }
            Expression2_R expression2 = Expression2();
            return new Expression_R(token, expression, expression2);

        } else if (token.token.equals("LEFT_ROUND_B")) {
            queue.poll();
            Expression_R expression = expression();
            if (expression == null){
                System.out.println("Syntax Error! expression->( expression is null");
                return null;
            }

            Match token2 = queue.peek();
            if (token2 == null){
                System.out.println("Syntax Error! expression->( ) is null");
                return null;
            }

            if (token2.token.equals("RIGHT_ROUND_B")){
                queue.poll();
                Expression2_R expression2 = Expression2();
                return new Expression_R(token, expression, token2, expression2);
            } else {
                System.out.println("Syntax Error! expression->( ) is missing");
                return null;
            }


        } else {
            Identifier_R id = identifier();
            if (id != null){
                Expression2_R expression2 = Expression2();
                return new Expression_R(id, expression2);
            }
        }
        System.out.println("no valid rule was found in expression");
        return null;
    }

    public Expression1_R Expression1() {
        Match token = queue.peek();

        if (token == null) {
            //System.out.println("Syntax Error!");
            /* Salma->because expression1 is called sometimes where it can be null but it is not an error! */
            return null;
        }

        if (token.token.equals("AND") || token.token.equals("OR") || token.token.equals("EQUAL") ||
            token.token.equals("NOT_EQUAL") || token.token.equals("GREATERTHAN") || token.token.equals("LESSTHAN") ||
            token.token.equals("LESS_EQ") || token.token.equals("GREATER_EQ") || token.token.equals("PLUS") ||
            token.token.equals("MINUS") || token.token.equals("MULTIPLY") || token.token.equals("DIV")) {

            queue.poll();
            Expression_R expr = expression();

            if (expr == null) {
                System.out.println("Syntax Error! Expression1->op Expression is null");
                return null;
            }
            return new Expression1_R(token, expr);


        } else if (token.token.equals("LEFT_SQUARE_B")) {
            queue.poll();

            Expression_R expr = expression();
            if (expr == null) {
                System.out.println("Syntax Error! Expression1->[ Expression is null");
                return null;
            }

            Match token2 = queue.peek();
            if (token2 == null) {
                System.out.println("Syntax Error! Expression1->[ ] is null");
                return null;
            }

            if (token2.token.equals("RIGHT_SQUARE_B")) {
                queue.poll();
                return new Expression1_R(token, expr, token2);
            } else {
                System.out.println("Syntax Error! Expression1->[ ] is missing");
                return null;
            }

        } else if (token.token.equals("DOT")) {
            queue.poll();
            ExpressionDot_R expr = ExpressionDot();
            if (expr == null) {
                System.out.println("Syntax Error! Expression1->. ExpressionDot is null");
                return null;
            }
            return new Expression1_R(token, expr);
        }

        return null;
    }

    public Expression2_R Expression2() {

        Expression1_R expr1 = Expression1();
        if (expr1 != null) {
            Expression2_R expr2 = Expression2();
            return new Expression2_R(expr1, expr2);
        }
        return null;
    }

    public ExpressionDot_R ExpressionDot() {
        Match token = queue.peek();

        if (token == null) {
            System.out.println("Syntax Error! ExpressionDot-> no tokens left!");
            return null;
        }

        if (token.token.equals("LENGTH")) {
            queue.poll();
            return new ExpressionDot_R(token, "length");

        } else {

            Identifier_R id = identifier();
            if (id == null) {
                System.out.println("Syntax Error! ExpressionDot->id Identifier is null");
                return null;
            }

            Match token2 = queue.peek();
            if (token2 == null) {
                System.out.println("Syntax Error! ExpressionDot->id ( is null");
                return null;
            }

            if (token2.token.equals("LEFT_ROUND_B")) {
                queue.poll();

                Expression_R expr = expression();

                // identifier ()
                if (expr == null) {

                    Match token4 = queue.peek();
                    if (token4 == null) {
                        System.out.println("Syntax Error! ExpressionDot->id( ) is null");
                        return null;

                    }

                    if (token4.token.equals("RIGHT_ROUND_B")) {
                        queue.poll();
                        return new ExpressionDot_R(id, token2, token4, "no expressions");
                    } else {
                        System.out.println("Syntax Error! ExpressionDot->id( ) is missing");
                        return null;
                    }

                }

                // identifier (Expression)
                Match token3 = queue.peek();
                if (token3 == null) {   //no Comma
                    Match token4 = queue.peek();
                    if (token4 == null) {
                        System.out.println("Syntax Error! ExpressionDot->id(Expression ) is null");
                        return null;

                    }

                    if (token4.token.equals("RIGHT_ROUND_B")) {
                        queue.poll();
                        return new ExpressionDot_R(id, token2, expr, token4, "one expression");
                    } else {
                        System.out.println("Syntax Error! ExpressionDot->id(Expression ) is missing");
                        return null;
                    }
                }

                // identifier(Expression, Expression, ..etc)
                ArrayList<ExpressionDot_R> exprDots = new ArrayList<>();

                while (token3.token.equals("COMMA")) {
                    queue.poll();

                    Expression_R expr1 = expression();
                    if (expr1 == null) {
                        System.out.println("Syntax Error! ExpressionDot->id(Expression, Expression is null");
                        return null;
                    }

                    ExpressionDot_R exprDot = new ExpressionDot_R(token3, expr1);
                    exprDots.add(exprDot);

                    token3 = queue.peek();
                    if (token3 == null)
                        break;
                }

                Match token4 = queue.peek();
                if (token4 == null) { // right round bracket not found
                    System.out.println("Syntax Error! ExpressionDot->id(Expression,Expression ) is null");
                    return null;
                }

                if (token4.token.equals("RIGHT_ROUND_B")) {
                    queue.poll();
                    return new ExpressionDot_R(id, token, expr, exprDots, token4, "many expressions");
                } else {
                    System.out.println("Syntax Error! ExpressionDot->id(Expression,Expression ) is null");
                    return null;
                }

            }
        }

        return null;
    }

    public ExpressionNew_R ExpressionNew() {

        Match token = queue.peek();
        if (token == null) {
            System.out.println("Syntax Error! data type in Expression new is null");
            return null;
        }

        if (token.token.equals("INT") || token.token.equals("FLOAT") || token.token.equals("STRING") || token.token.equals("CHAR") || token.token.equals("BOOLEAN")) {
            queue.poll();
            Match token2 = queue.peek();
            if (token2 == null) {
                System.out.println("Syntax Error! ExpressionNew->type [ is null");
                return null;
            }

            if (token2.token.equals("LEFT_SQUARE_B")) {
                queue.poll();

                Expression_R expr = expression();
                if (expr == null) {
                    System.out.println("Syntax Error! ExpressionNew->type[ Expression is null");
                    return null;
                }

                Match token3 = queue.peek();
                if (token3 == null) {
                    System.out.println("Syntax Error! ExpressionNew->type[Expression ] is null");
                    return null;
                }
                if (token3.token.equals("RIGHT_SQUARE_B")) {
                    queue.poll();

                    Expression2_R expr2 = Expression2(); // can be null
                    return new ExpressionNew_R(token, token2, expr, token3, expr2, "expr1");

                } else {
                    System.out.println("Syntax Error! ExpressionNew->type[Expression ] is missing");
                    return null;
                }
            } else {
                System.out.println("Syntax Error! ExpressionNew->type [ is missing");
                return null;
            }

        } else {

            Identifier_R id = identifier();
            if (id == null) {
                System.out.println("Syntax Error! ExpressionNew->id Identifier is null");
                return null;
            }

            Match token2 = queue.peek();
            if (token2 == null) {
                System.out.println("Syntax Error! ExpressionNew->id ( is null");
                return null;
            }

            if (token2.token.equals("LEFT_ROUND_B")) {
                queue.poll();

                Expression_R expr = expression();

                // identifier ()
                if (expr == null) {

                    Match token4 = queue.peek();
                    if (token4 == null) {
                        System.out.println("Syntax Error! ExpressionNew->id( ) is null");
                        return null;
                    }

                    if (token4.token.equals("RIGHT_ROUND_B")) {
                        queue.poll();

                        Expression2_R expression2 = Expression2();
                        return new ExpressionNew_R(id, token2, token4, expression2, "no expression");
                    } else {
                        System.out.println("Syntax Error! ExpressionNew->id( ) is missing");
                        return null;
                    }

                }

                // identifier (Expression)
                Match token3 = queue.peek();
                if (token3 == null) {   //no Comma
                    Match token4 = queue.peek();
                    if (token4 == null) {
                        System.out.println("Syntax Error! ExpressionNew->id(Expression ) is null");
                        return null;
                    }

                    if (token4.token.equals("RIGHT_ROUND_B")) {
                        queue.poll();
                        Expression2_R expression2 = Expression2();
                        return new ExpressionNew_R(id, token2, expr, token4, expression2, "one expression");
                    } else {
                        System.out.println("Syntax Error! ExpressionNew->id(Expression ) is missing");
                        return null;
                    }
                }

                // identifier(Expression, Expression, ..etc)
                ArrayList<ExpressionNew_R> exprNews = new ArrayList<>();

                while (token3.token.equals("COMMA")) {
                    queue.poll();

                    Expression_R expr1 = expression();
                    if (expr1 == null) {
                        System.out.println("Syntax Error! ExpressionNew->id(Expression, Expression is null");
                        return null;
                    }

                    ExpressionNew_R exprNew = new ExpressionNew_R(token3, expr1);
                    exprNews.add(exprNew);

                    token3 = queue.peek();
                    if (token3 == null)
                        break;
                }

                Match token4 = queue.peek();
                if (token4 == null) { // right round bracket not found
                    System.out.println("Syntax Error! ExpressionNew->id(Expression,Expression ) is null");
                    return null;
                }

                if (token4.token.equals("RIGHT_ROUND_B")) {
                    queue.poll();
                    Expression2_R expression2 = Expression2();
                    return new ExpressionNew_R(id, token, expr, exprNews, token4, expression2, "many expressions");
                } else {
                    System.out.println("Syntax Error! ExpressionNew->id(Expression,Expression ) is null");
                    return null;
                }

            }
        }
        return null;
    }


    // ====================== Statement ====================== //

    public Statement_R statement() {
        Match token = queue.peek();
        if (token == null){
            System.out.println("no tokens left in Statement");
            return null;
        }

        if (token.token.equals("LEFT_CURLY_B")) {
            queue.poll();
            ArrayList<Statement_R> statements = new ArrayList<>();

            Statement_R statement = statement();
            while (statement != null) {
                statements.add(statement);
                statement = statement();
            }

            Match token2 = queue.peek();
            if (token2 == null){
                System.out.println("Syntax Error! Statement->{statements* } is null");
                return null;
            }
            if (token2.token.equals("RIGHT_CURLY_B")) {
                queue.poll();
                return new Statement_R(token, statements, token2, "statements");
            } else {
                System.out.println(token2.value);
                System.out.println("Syntax Error! Statement->{statements* } is missing");
                return null;
            }

        } else if (token.token.equals("IF") || token.token.equals("WHILE") || token.token.equals("SYSTEM.OUT.PRINTLN")) {
            queue.poll();
            Match token2 = queue.peek();
            if (token2 == null){
                System.out.println("Syntax Error! Statement->if/while/sout ( is null");
                return null;
            }

            if (token2.token.equals("LEFT_ROUND_B")) {
                queue.poll();
                Expression_R expression = expression();

                if (expression != null) {
                    Match token3 = queue.peek();

                    if (token3 == null){
                        System.out.println("Syntax Error! Statement->if/while/sout(Expression ) is null");
                        return null;
                    }
                    if (token3.token.equals("RIGHT_ROUND_B")) {
                        queue.poll();

                        if (token.token.equals("SYSTEM.OUT.PRINTLN")) {
                            Match token4 = queue.peek();

                            if (token4 == null){
                                System.out.println("Syntax Error! Statement->SOUT(Expression) ; is null");
                                return null;
                            }
                            if (token4.token.equals("SEMICOLON")) {
                                queue.poll();
                                return new Statement_R(token, token2, expression, token3, token4, "print statement");
                            } else {
                                System.out.println("Syntax Error! SOUT(Expression) ; is missing");
                                return null;
                            }

                        } else {
                            Statement_R statement = statement();

                            if (statement != null) {
                                if (token.token.equals("IF")) {
                                    ElseStatement_R elseStatement = elseStatement();
                                    return new Statement_R(token, token2, expression, token3, statement, elseStatement, "if statement");
                                } else if (token.token.equals("WHILE")) {
                                    return new Statement_R(token, token2, expression, token3, statement, "while statement");
                                }


                            } else {
                                System.out.println("Syntax Error! statement->if/while(Expression) Statement is null");
                                return null;
                            }
                        }
                    } else {
                        System.out.println("Syntax Error! if/while/sout(Expression ) is missing");
                        return null;
                    }
                } else {
                    System.out.println("Syntax Error! Statement->if/while/sout( Expression is null");
                    return null;
                }
            } else {
                System.out.println("Syntax Error! Statement->if/while/sout ( is missing");
                return null;
            }


        } else {
            Identifier_R id = identifier();
            if (id != null) {
                StatementExpression_R statementExpr = statementExpr();
                if (statementExpr == null) {
                    System.out.println("Statement->Identifier StatementExpression is null");
                    return null;
                }
                return new Statement_R(id, statementExpr, "id statement");
            }
        }
        System.out.println("no valid rule was found in Statement");
        return null;
    }

    private StatementExpression_R statementExpr() {
        Match token = queue.peek();
        if (token == null){
            System.out.println("Syntax Error! no tokens left in StatementExpression");
            return null;
        }
        if (token.token.equals("ASSIGNMENT")) {
            queue.poll();

            Expression_R expression = expression();
            if (expression != null) {
                Match token2 = queue.peek();
                if (token2 == null){
                    System.out.println("Syntax Error! StatementExpr->=Expression ; is null");
                    return null;
                }
                if (token2.token.equals("SEMICOLON")) {
                    queue.poll();
                    return new StatementExpression_R(token, expression, token2, "expr1");
                } else {
                    System.out.println("Syntax Error! StatementExpr->=Expression ; is missing");
                    return null;
                }
            } else {
                System.out.println("Syntax Error! StatementExpr->=Expression is null");
                return null;
            }

        } else if (token.token.equals("LEFT_SQUARE_B")) {
            queue.poll();
            Expression_R expression = expression();

            if (expression != null) {
                Match token2 = queue.peek();

                if (token2 == null) {
                    System.out.println("Syntax Error! StatementExpr->=[Expression ] is null");
                    return null;
                }
                if (token2.token.equals("RIGHT_SQUARE_B")) {
                    queue.poll();

                    Match token3 = queue.peek();
                    if (token3 == null){
                        System.out.println("Syntax Error! StatementExpr->=[Expression] = is null");
                        return null;
                    }

                    if (token3.token.equals("ASSIGNMENT")) {
                        queue.poll();

                        Expression_R expression2 = expression();
                        if (expression2 != null) {

                            Match token4 = queue.peek();
                            if (token4 == null){
                                System.out.println("Syntax Error! StatementExpr->=[Expression]=Expression ; is null");
                                return null;
                            }
                            if (token4.token.equals("SEMICOLON")) {
                                queue.poll();
                                return new StatementExpression_R(token, expression, token2, token3, expression2, token4, "expr2");

                            } else {
                                System.out.println("Syntax Error! StatementExpr->=[Expression]=Expression ; is missing");
                                return null;
                            }
                        } else {
                            System.out.println("Syntax Error! StatementExpr->=[Expression]=Expression is null");
                            return null;
                        }
                    } else {
                        System.out.println("Syntax Error! StatementExpr->=[Expression] = is missing");
                        return null;
                    }
                } else {
                    System.out.println("Syntax Error! StatementExpr->=[Expression ] is missing");
                    return null;
                }
            } else {
                System.out.println("Syntax Error! StatementExpr->[ Expression is null");
                return null;
            }

        }
        System.out.println("no valid rule was found in StatementExpression");
        return null;
    }

    private ElseStatement_R elseStatement() {
        Match token = queue.peek();
        if (token == null){
            return null;
        }
        if (token.token.equals("ELSE")) {
            queue.poll();
            Statement_R statement = statement();
            if (statement == null) {
                System.out.println("Syntax Error! ElseStatement->else Statement is null");
                return null;
            }
            return new ElseStatement_R(token, statement);
        }
        return null;
    }
    //==========================method declaration=====================//
    public MethodDeclaration_R methodDeclaration() {
        ArrayList<Paramter> paramters = new ArrayList<>();
        Match token = queue.peek();
        if (token == null) {
            System.out.println("Synatx Error! token null");
            return null;
        }
        //System.out.println("public "+token.token +"  "+token.value);

        if (!token.token.equals("PUBLIC") && !token.token.equals("PRIVATE") && !token.token.equals("PROTECTED")) {
            System.out.println("Syntax Error! public or private or protected is wrong");
            return null;
        }
        queue.poll();
        Type_R type = type();
        if (type == null) {
            System.out.println("Synatx Error! type null inside method dec");
            return null;
        }
        Identifier_R id = identifier();
        if (id == null) {
            System.out.println("Syntax Error! id null");
            return null;
        }
        Match token2 = queue.peek();
        if (token2 == null) {
            System.out.println("Syntax Error! ( null ");
            return null;
        }
        if (!token2.token.equals("LEFT_ROUND_B")) {
            System.out.println("Syntax Error! ( wrong ");
            return null;
        }
        Match token4 = new Match();

        if (token2.token.equals("LEFT_ROUND_B")) {
            queue.poll();
            Type_R type2 = type();

            System.out.println("in method dec after return from type");
            while (type2 != null) {
                Identifier_R id2 = identifier();
                if (id2 == null) {
                    System.out.println("Synatx Error! id inside paramter null");
                    return null;
                }
                Match token3 = queue.peek();
                if (token3 == null) {
                    System.out.println("Synatx Error! )is null");
                    return null;
                }
                Paramter paramter = new Paramter(type2, id2);
                paramters.add(paramter);
                if (!token3.token.equals("COMMA")) {
                    break;
                }
                if (token3.token.equals("COMMA")) {
                    queue.poll();
                    type2 = type();
                    if(type2 == null){
                        System.out.println("Syntax Error! extra comma");
                        return null;
                    }

                }

            }
            token4 = queue.peek();
            if (token4 == null) {
                System.out.println("Synatx Error! ) null ");
                return null;
            }
            if (!token4.token.equals("RIGHT_ROUND_B")) {
                System.out.println("Synatx Error!  wrong )");
                return null;
            }
            queue.poll();
        }

        Match token5 = queue.peek();
        if (token5 == null) {
            System.out.println("Synatx Error! { null ");
            return null;
        }
        if (!token5.token.equals("LEFT_CURLY_B")) {
            System.out.println("Synatx Error! wrong { ");
            return null;
        }
        queue.poll();
        ArrayList<VarDeclaration_R> varDeclarations = new ArrayList<>();
        VarDeclaration_R varDeclaration = varDeclaration();
        while (varDeclaration != null) {
            varDeclarations.add(varDeclaration);
            varDeclaration = varDeclaration();
        }
        ArrayList<Statement_R> statements = new ArrayList<>();
        Statement_R stmt = statement();
        System.out.println("return from stmt  " +stmt);
        while (stmt != null) {
            statements.add(stmt);
            stmt = statement();
        }
        Match token6 = queue.peek();
        if (token6 == null) {
            System.out.println("Synatx Error! return is  null");
            return null;
        }
        //System.out.println("token 6 "+token6.token+"  "+token6.value);
        if (!token6.token.equals("RETURN")) {
            System.out.println("Synatx Error! return wrong ");
            return null;
        }
        queue.poll();
        Expression_R expr = expression();
        if (expr == null) {
            System.out.println("Synatx Error! expr null ");
            return null;
        }
        Match token7 = queue.peek();
        if (token7 == null) {
            System.out.println("Synatx Error! ; null ");
            return null;
        }
        if (!token7.token.equals("SEMICOLON")) {
            System.out.println("Synatx Error! wrong ; ");
            return null;
        }
        queue.poll();
        Match token8 = queue.peek();
        if (token8 == null) {
            System.out.println("Synatx Error! } null ");
            return null;
        }
        if (!token8.token.equals("RIGHT_CURLY_B")) {
            System.out.println("Synatx Error! { wrong ");
            return null;
        }
        queue.poll();

        return new MethodDeclaration_R(token, type, id, token2, paramters, paramters.size() - 1, token4, token5, varDeclarations, statements, token6,expr, token7, token8);


    }
    public ConstructorDeclaration_R constructorDeclartion() {
        ArrayList <Paramter> paramters = new ArrayList<>();
        ArrayList <Statement_R> statements = new ArrayList<>();
        ArrayList <VarDeclaration_R> varDeclarations = new ArrayList<>();

        Identifier_R id = identifier();
        if (id == null){
            System.out.println("Synatx Error! id null");
            return null;
        }
        Match token = queue.peek();
        if(token == null){
            System.out.println("Synatx Error! left ( null");
            return null;
        }
        if (!token.token.equals("LEFT_ROUND_B")){
            System.out.println("Synatx Error! ( wrong");
            return null;
        }
        queue.poll();
        Type_R type = type();
        while (type != null){
            Identifier_R id2 =identifier();
            if (id2 == null){
                System.out.println("Synatx Error! id inside paramter null");
                return null;
            }
            Paramter paramter = new Paramter(type, id2);
            paramters.add(paramter);
            Match comma = queue.peek();
            if(comma == null){
                System.out.println("Synatx Error!  right ) null");
                return null;
            }
            if (!comma.token.equals("COMMA")){
                break;
            }
            if (comma.token.equals("COMMA")){
                queue.poll();
                //System.out.println(" comma found");
                type = type();
                if (type == null){
                    System.out.println("Syntax Error! extra comma ");
                    return null;
                }


            }

        }
        Match token2 = queue.peek();
        if (token2 == null){
            System.out.println("Synax Error! right ) null");
            return null;
        }
        if (!token2.token.equals("RIGHT_ROUND_B")){
            System.out.println("Synax Error! right ) wrong");
            return null;
        }
        queue.poll();
        Match token3 = queue.peek();
        if (token3 == null) {
            System.out.println("Synatx Error! { null ");
            return null;
        }
        if (!token3.token.equals("LEFT_CURLY_B")) {
            System.out.println("Synatx Error! wrong { ");
            return null;
        }
        //System.out.println("token3 " + token3.value);
        queue.poll();
        VarDeclaration_R varDeclaration = varDeclaration();

        while (varDeclaration != null) {
            //System.out.println("ana henaaaaaaa");
            varDeclarations.add(varDeclaration);
            varDeclaration = varDeclaration();
        }

        Statement_R stmt = statement();
        while (stmt != null) {
            statements.add(stmt);
            stmt = statement();
        }
        Match token4 = queue.peek();
        //System.out.println("token 4  : "+token4.value);
        if (token4 == null) {
            System.out.println("Synatx Error! } null ");
            return null;
        }
        if (!token4.token.equals("RIGHT_CURLY_B")) {
            System.out.println("Synatx Error! wrong } ");
            return null;
        }
        queue.poll();
        return new ConstructorDeclaration_R(id,token,paramters,paramters.size()-1,token2,token3,varDeclarations,statements,token4);
    }
    public ClassDeclaration_R classDeclaration(){
        System.out.println("i'm hereeeeeeee");
        ArrayList<VarDeclaration_R> varDeclarations =new ArrayList<>();
        ArrayList<ConstructorDeclaration_R> constructorDeclarations =new ArrayList<>();
        ArrayList<MethodDeclaration_R> methodDeclarations =new ArrayList<>();
        Match token = queue.peek();
        if (token == null){
            System.out.println("Syntax Error! class null");
            return null;
        }
        if (!token.token.equals("CLASS")){
            System.out.println("Syntax Error! class wrong");
            return null;
        }
        queue.poll();
        Identifier_R id = identifier();
        if (id == null){
            System.out.println("Syntax Error! id null");
            return null;
        }
        System.out.println("ana henaaaa");
        Match token2 = queue.peek();
        System.out.println("token 2 "+token2.value);
        if (token2 == null){
            System.out.println("Syntax Error! left { null");
            return null;
        }
        boolean extendFound = false;
        Match extend =new Match();

        Identifier_R id2 = new Identifier_R();
        if (token2.token.equals("EXTENDS")){
            System.out.println("extend found : "+token2.value);
            queue.poll();
            id2 = identifier();
            if (id2 == null){
                System.out.println("Syntax Error! id after extend null");
                return null;
            }
            extendFound = true;
            extend = token2;

        }
        token2 = queue.peek();
        if (!token2.token.equals("LEFT_CURLY_B")){
            System.out.println("Syntax Error! left { wrong");
            return null;
        }
        queue.poll();
        VarDeclaration_R varDeclaration =varDeclaration();
        System.out.println("var dec "+varDeclaration);
        while (varDeclaration != null){
            varDeclarations.add(varDeclaration);
            varDeclaration = varDeclaration();
        }
        ConstructorDeclaration_R constructorDeclaration = constructorDeclartion();
        System.out.println("construction declaration "+constructorDeclaration);
        while (constructorDeclaration != null){
            constructorDeclarations.add(constructorDeclaration);
            constructorDeclaration = constructorDeclartion();
        }
        MethodDeclaration_R methodDeclaration = methodDeclaration();
        System.out.println("method declaration "+methodDeclaration);
        while (methodDeclaration != null){
            methodDeclarations.add(methodDeclaration);
            methodDeclaration = methodDeclaration();
        }
        Match token3 = queue.peek();
        if (token3 == null){
            System.out.println("Syntax Error! right } is null");
            return null;
        }
        if (!token3.token.equals("RIGHT_CURLY_B")){
            System.out.println("Syntax Error! right } wrong");
            return null;
        }
        queue.poll();
        if (extendFound){
            return new ClassDeclaration_R(token,id,extend,id2,token2,varDeclarations,constructorDeclarations,methodDeclarations,token3);
        }
        return new ClassDeclaration_R(token,id,token2,varDeclarations,constructorDeclarations,methodDeclarations,token3);
    }


    public MainClass_R mainClass(){//missing mean that it can be anything :"D

        Match token = queue.peek();//should peek class
        if (token == null){
            System.out.println("Syntax Error! class in MainClass is null!");
            return null;
        }

        if(token.token.equals("CLASS")){
            queue.poll();
            Identifier_R id = identifier();
            if (id == null){
                System.out.println("Syntax Error! identifier in mainClass is null");
                return null;
            }

            Match token1 = queue.peek(); //should peek {
            if (token1 == null){
                System.out.println("Syntax Error! { in mainClass is null");
                return null;
            }

            if (token1.token.equals("LEFT_CURLY_B")){
                queue.poll();
                Match token2 = queue.peek();//PUBLIC
                if (token2 == null){
                    System.out.println("Syntax Error! public in mainClass is null");
                    return null;
                }

                if(token2.token.equals("PUBLIC")){
                    queue.poll();
                    Match token3 = queue.peek();//STATIC
                    if(token3 == null){
                        System.out.println("Syntax Error! static in mainClass is null");
                        return null;
                    }

                    if(token3.token.equals("STATIC")){
                        queue.poll();
                        Match token4 = queue.peek(); //SHOULD PEEK VOID

                        if (token4 == null){
                            System.out.println("Syntax Error! void in mainClass is null");
                            return null;
                        }

                        if(token4.token.equals("VOID")){
                            queue.poll();
                            Match token5 = queue.peek();//Sholud peek main
                            if (token5 == null){
                                System.out.println("Syntax Error! void in mainClass is null");
                                return null;
                            }
                            if (token5.token.equals("MAIN")){
                                queue.poll();
                                Match token6 = queue.peek();//should peek (
                                if (token6 == null){
                                    System.out.println("Syntax Error! main in main class is null");
                                    return null;
                                }

                                if(token6.token.equals("LEFT_ROUND_B")){
                                    queue.poll();
                                    Match token8 = queue.peek();// Should peek string
                                    if(token8 == null){
                                        System.out.println("Syntax Error! string in main class in null");
                                        return null;
                                    }

                                    if(token8.token.equals("STRING")){
                                        queue.poll();
                                        Match token9 = queue.peek();//Should peek [ wa ya rab n5l9 b2a
                                        if(token9 == null){
                                            System.out.println("Syntax Error! [ in main class in null");
                                            return null;
                                        }

                                        if(token9.token.equals("LEFT_SQUARE_B")){
                                            queue.poll();
                                            Match token10 = queue.peek();//SHOULD PEEK ]
                                            if(token10 == null){
                                                System.out.println("Syntax Error! ] in main class is null");
                                                return null;
                                            }
                                            if(token10.token.equals("RIGHT_SQUARE_B")){
                                                queue.poll();
                                                Identifier_R id1 = identifier();
                                                if(id1 == null){
                                                    System.out.println("Syntax Error! identifier after ] in main class is null");
                                                    return null;
                                                }
                                                Match token11 = queue.peek();//Should peek )
                                                if(token11 == null){
                                                    System.out.println("Syntax Error! ) in main class is null");
                                                    return null;
                                                }

                                                if(token11.token.equals("RIGHT_ROUND_B")){
                                                    queue.poll();
                                                    Match token12 = queue.peek();
                                                    if(token12 == null){
                                                        System.out.println("Syntax Error! { in main class is null");
                                                        return null;
                                                    }
                                                    if(token12.token.equals("LEFT_CURLY_B")){
                                                        queue.poll();
                                                        Statement_R stmt = statement();
                                                        if(stmt == null){
                                                            System.out.println("Syntax Error! stmt in main class is null");
                                                            return null;
                                                        }
                                                        Match token13 = queue.peek();//should peek }
                                                        if(token13 == null){
                                                            System.out.println("Syntax Error! } after statement is null");
                                                            return null;

                                                        }
                                                        if(token13.token.equals("RIGHT_CURLY_B")){
                                                            queue.poll();
                                                            Match token14 = queue.peek();//should peek }
                                                            if(token14 == null){
                                                                System.out.println("Syntax Error! } after } is null");
                                                                return null;
                                                            }
                                                            if(token14.token.equals("RIGHT_CURLY_B")){
                                                                queue.poll();
                                                                return new MainClass_R(token, id, token1, token2, token3, token4, token5, token6, token8, token9,
                                                                        token10, id1, token11, token12, stmt, token13, token14);

                                                            }
                                                            else{
                                                                System.out.println("Syntax Error! } after } is missing");
                                                            }
                                                        }

                                                        else{
                                                            System.out.println("Syntax Error! } after stmt is missing");
                                                            return null;
                                                        }

                                                    }
                                                    else{

                                                        System.out.println("Syntax Error! { in main class is missing");
                                                        return null;
                                                    }
                                                }
                                                else {
                                                    System.out.println("Syntax Error! ) in main class is is missing");
                                                    return null;
                                                }

                                            }
                                            else{
                                                System.out.println("Syntax Error! ] in main class is null");
                                                return null;
                                            }


                                        }
                                        else {
                                            System.out.println("Syntax Error! [ in main class is missing");
                                            return null;
                                        }

                                    }

                                    else{
                                        System.out.println("Syntax Errro! String in main class is missing");
                                        return null;
                                    }

                                }

                                else{
                                    System.out.println("Syntax Error! ( is missing");
                                    return null;
                                }
                            }

                            else{
                                System.out.println("Syntax Error! main in main class is missing");
                                return null;
                            }
                        }

                        else{
                            System.out.println("Syntax Error! void in mainClass is missing");
                            return null;
                        }
                    }

                    else {

                        System.out.println("Syntax Error! static in mainClass is missing");
                        return null;
                    }

                }

                else {
                    System.out.println("Syntax ERROR! public in mainClass is missing");
                    return null;
                }


            }

            else {
                System.out.println("Syntax Error! { in mainClass is missing");
                return null;
            }

        }

        System.out.println("Syntax Error! class in main class is missing");
        return null;
    }

    public Goal_R goal(){
        MainClass_R mainClass = mainClass();
        if(mainClass == null){
            System.out.println("Syntax Error! main class in goal is null");
            return null;
        }

        ClassDeclaration_R classD = classDeclaration();
        if(classD == null){
            Match token = queue.peek();
            if(token == null){
                System.out.println("Syntax Error! EOF is null in Goal");
                return null;
            }
            if(token.token.equals("EOF")){
                queue.poll();
                return new Goal_R(mainClass, token);
            }
        }

        ArrayList<Goal_R> classDs;
        classDs = new ArrayList<Goal_R>();

        while(classD != null){

            Goal_R goal = new Goal_R(classD);
            classDs.add(goal);
            classD = classDeclaration();

        }

        Match token = queue.peek();
        if(token == null){
            System.out.println("Syntax Error! EOF is null");
            return null;
        }

        if(token.token.equals("EOF")){
            queue.poll();
            return new Goal_R(mainClass, classDs, token);
        }

        return null;
    }



    public static void main(String[] args) throws FileNotFoundException {
        Parser p = new Parser();
        p.loadTokens();

        VarDeclaration_R root = p.varDeclaration();

        if (root != null)
            root.printNode();

//        Expression_R exp = p.expression();

//        Statement_R stmt = p.statement();
//        stmt.printNode();
        System.out.println("horay");
//        System.out.println("horay");
//        System.out.println("=======================================");
//        MethodDeclaration_R method = p.methodDeclaration();
//        System.out.println(method);
//        System.out.println("number of commas :"+method.numberOfCommas);
//        System.out.println("vars dec "+ method.varDeclarations.size());
//        System.out.println("stat size "+method.statements.size());
//        ConstructorDeclaration_R con = p.constructorDeclartion();
//        System.out.println("constructor result "+con);
        //System.out.println(con.varDeclarations.size());
        //System.out.println(con.statements.size());
//        ClassDeclaration_R c =p.classDeclaration();
//        System.out.println("class declaration : "+ c );
//        System.out.println("ana 3adeet");
//        MainClass_R mClass = p.mainClass();
//        System.out.println("main class : "+mClass);
        Goal_R goal = p.goal();
        System.out.println("goal : "+goal);
        System.out.println(goal.classDs.size());

    }
}
