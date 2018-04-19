package Rules;

import LexicalAnalyzer.Match;
import LexicalAnalyzer.Tokenizer;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

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

        Scanner scanner = new Scanner(new File("C:\\Users\\Lenovo\\IdeaProjects\\Compilers Phase 2\\src\\output.txt"));
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
            System.out.println("Syntax Error! type null");
            return null;
        }
        Identifier_R id = identifier();
        if (id == null) {
            System.out.println("Syntax Error! no id");
            return null;
        }
        Match token = queue.peek();
        if (token == null && token.token != "SEMICOLON") {
            System.out.println("Syntax Error! semicolon");
            return null;
        }
        queue.poll();
        return new VarDeclaration_R(t, id, token);
    }

    public Type_R type() {
        DataType_R d = datatype();
        if (d == null) {
            System.out.println("Sytnax Error! datatype null");
            return null;
        }
        Array_R a = array();
        return new Type_R(d, a);
    }

    public DataType_R datatype() {
        Match token = queue.peek();
        if (token.token == null) {
            System.out.println("Syntax Error! datatype null");
            return null;
        }
        if (!token.token.equals("BOOLEAN") && !(token.token.equals("INT")) && !token.token.equals("FLOAT") && !token.token.equals("STRING") && !token.token.equals("CHAR")) {
            System.out.println("Syntax Error! wrong datatype");
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
            System.out.println("Synatx Error! id null");
            return null;
        }
        if (!token.token.equals("ID")) {
            System.out.println("Synatx Error! wrong id");
            return null;
        }
        queue.poll();
        return new Identifier_R(token);
    }

    // ================== Expression ======================== //
//    public Expression_R Expression() {
//        Match token = queue.peek();
//        if (token == null) {
//            System.out.println("Synatx Error!");
//            return null;
//        }
//        if (token.token.equals("INTEGRAL_LITERAL") || token.token.equals("FLOAT_LITERAL") || token.token.equals("TRUE") || token.token.equals("FALSE") || token.token.equals("THIS")) {
//            queue.poll();
//            Expression2_R exp2 = Expression2();
//            return new Expression_R(token, exp2);
//        }
//        Identifier_R id = identifier();
//        if (id != null) {
//            Expression2_R exp2 = Expression2();
//            if (exp2 == null) {
//                System.out.println("Synax Error!");
//                return null;
//            }
//            return new Expression_R(id, exp2);
//
//        }
//        if (token.token.equals("new")) {
//            queue.poll();
//            ExpressionNew_R exprNew = ExpressionNew();
//            if (exprNew == null) {
//                return null;
//            }
//            return new Expression_R(token, exprNew);
//
//        }
//        if (token.token.equals("!")) {
//            queue.poll();
//            Expression_R expr = Expression();
//            if (expr == null) {
//                System.out.println("Synatx Error! exp null");
//                return null;
//            }
//            Expression2_R expr2 = Expression2();
//            if (expr2 == null) {
//                System.out.println("Synatx Error! exp2 null");
//                return null;
//            }
//            return new Expression_R(token, expr, expr2);
//        }
//        if (token.token.equals("LEFT_ROUND_B")) {
//            queue.poll();
//            Expression_R expr = Expression();
//            if (expr == null) {
//                System.out.println("Syntax Error! Expression is null");
//                return null;
//            }
//            Match token2 = queue.peek();
//            if (token2.token.equals("RIGHT_ROUND_B")) {
//                queue.poll();
//                Expression2_R expr2 = Expression2();
//                if (expr2 == null) {
//                    System.out.println("Synatx Error! Expression2 is null");
//                    return null;
//                }
//                return new Expression_R(token, expr, token2, expr2);
//
//            } else {
//                System.out.println("Syntax Error! no )");
//                return null;
//            }
//        }
//        return null;
//    }
//
//    //    Expression''-> Expression' Expression'' | E
////
////    Expression' -> ( "&&" | "||" | "==" | "!=" | ">" | "<" | "<=" | ">="| "+" | "-" | "*" | "/" ) Expression
////            -> "[" Expression "]"
////            -> "." ExpressionDot
////
////    ExpressionDot -> "length"
////            | Identifier "(" ( Expression ( "," Expression )* )? ")"
////
////    ExpressionNew -> ("int" | "float" | "String" | "char" | "boolean" ) "[" Expression "]" Expression''
////            | Identifier "(" (Expression ( "," Expression)*)? ")" Expression''
////
//
//    public Expression2_R Expression2() {
//        Expression1_R expr1 = Expression1();
//        if (expr1 != null) {
//            Expression2_R expr2 = Expression2();
//            if (expr2 != null) {
//                return new Expression2_R(expr1, expr2);
//            } else {
//                System.out.println("Syntax Error! Expression2 is null");
//                return null;
//            }
//        }
//        return null;
//
//    }
//
//    //    ExpressionNew -> ("int" | "float" | "String" | "char" | "boolean" ) "[" Expression "]" Expression''
////            | Identifier "(" (Expression ("," Expression)*)? ")" Expression''
//
//    public ExpressionNew_R ExpressionNew() {
//        Match token = queue.peek();
//        if (token == null) {
//            System.out.println("Syntax Error! data type in Expression new is null");
//            return null;
//        }
//
//        if (token.token.equals("INT") || token.token.equals("FLOAT") || token.token.equals("STRING") || token.token.equals("CHAR") || token.token.equals("BOOLEAN")) {
//            queue.poll();
//            Match token2 = queue.peek();
//            if (token2 == null) {
//                System.out.println("Syntax Error! [  in Expression new is null");
//                return null;
//            }
//
//            if (token2.token.equals("LEFT_SQUARE_B")) {
//                queue.poll();
//                Expression_R expr = Expression();
//                if (expr == null) {
//                    System.out.println("Syntax Error! Expression  in Expression new is null");
//                    return null;
//                }
//                Match token3 = queue.peek();
//                if (token3 == null) {
//                    System.out.println("Syntax Error! ]  in Expression new is null");
//                    return null;
//                }
//                if (token3.token.equals("RIGHT_SQUARE_B")) {
//                    queue.poll();
//                    Expression2_R expr2 = Expression2();
//                    if (expr2 == null) {
//                        System.out.println("Syntax Error! Expression2  in Expression new is null");
//                        return null;
//                    }
//                }
//
//            }
//        }
//    }
//

    // ====================== Statement ====================== //

//    public Statement_R statement() {
//        Match token = queue.peek();
//        if (token.token.equals("LEFT_CURLY_B")) {
//            queue.poll();
//
//            ArrayList<Statement_R> statements = new ArrayList<>();
//
//            Statement_R statement = statement();
//            while(statement != null){
//                statements.add(statement);
//                statement = statement();
//            }
//
//            Match token2 = queue.peek();
//            if (token2.token.equals("}")){
//                queue.poll();
//                return new Statement_R(token, statements, token2);
//            } else {
//                System.out.println("Syntax Error! Statement has no }");
//                return null;
//            }
//
//        } else if (token.token.equals("IF") || token.token.equals("WHILE") || token.token.equals("SYSTEM.OUT.PRINTLN")) {
//            queue.poll();
//            Match token2 = queue.peek();
//
//            if (token2.token.equals("LEFT_ROUND_B")) {
//                queue.poll();
//                Expression_R expression = Expression();
//
//                if (expression != null) {
//                    Match token3 = queue.peek();
//
//                    if (token3.token.equals("RIGHT_ROUND_B")) {
//                        queue.poll();
//
//                        if (token.token.equals("SYSTEM.OUT.PRINTLN")) {
//                            Match token4 = queue.peek();
//
//                            if (token4.token.equals("SEMICOLON")) {
//                                queue.poll();
//                                return new Statement_R(token, token2, expression, token3, token4);
//                            } else {
//                                System.out.println("Syntax Error! Statement->SOUT no ;");
//                                return null;
//                            }
//
//                        } else {
//                            Statement_R statement = statement();
//
//                            if (statement != null) {
//                                if (token.token.equals("IF")) {
//                                    ElseStatement_R elseStatement = elseStatement();
//                                    return new Statement_R(token, token2, expression, token3, statement, elseStatement); // if not null but error?
//                                } else if (token.token.equals("WHILE")) {
//                                    return new Statement_R(token, token2, expression, token3, statement);
//                                }
//
//
//                            } else {
//                                System.out.println("Syntax Error! statement->if/while no Statement");
//                                return null;
//                            }
//                        }
//                    } else {
//                        System.out.println("Syntax Error! statement no )");
//                        return null;
//                    }
//                } else {
//                    System.out.println("Syntax Error! statement no Expression");
//                    return null;
//                }
//            } else {
//                System.out.println("Syntax Error! statement no (");
//                return null;
//            }
//
//
//        } else {
//            Identifier_R id = identifier();
//            if (id == null){
//                System.out.println("Syntax Error! no Statement!");
//                return null;
//            }
//            StatementExpression_R statementExpr = statementExpr();
//            if (statementExpr == null){
//                System.out.println("Statement->Identifier has no statementDash");
//                return null;
//            }
//            return new Statement_R(id, statementExpr);
//        }
//        return null;
//    }
//
//    private StatementExpression_R statementExpr() {
//        Match token = queue.peek();
//        if (token.token.equals("=")){
//            queue.poll();
//
//            Expression_R expression = Expression();
//            if (expression != null){
//                Match token2 = queue.peek();
//                if (token2.token.equals(";")){
//                    queue.poll();
//                    return new StatementExpression_R(token, expression, token2);
//                } else {
//                    System.out.println("StatementExpression has no ;");
//                    return null;
//                }
//            } else {
//                System.out.println("Syntax Error! StatementExpr has no Expression");
//                return null;
//            }
//
//        } else if (token.token.equals("LEFT_SQUARE_B")) {
//            queue.poll();
//            Expression_R expression = Expression();
//
//            if (expression != null){
//                Match token2 = queue.peek();
//
//                if (token2.token.equals("RIGHT_SQUARE")){
//                    queue.poll();
//                    Match token3 = queue.peek();
//
//                    if (token3.token.equals("EQUAL")){
//                        queue.poll();
//
//                        Expression_R expression2 = Expression();
//                        if (expression2 != null){
//
//                            Match token4 = queue.peek();
//                            if (token2.token.equals("SEMICOLON")){
//                                queue.poll();
//                                return new StatementExpression_R(token, expression, token2, token3, expression2, token4);
//
//                            } else {
//                                System.out.println("StatementExpression has no ;");
//                                return null;
//                            }
//                        } else {
//                            System.out.println("Syntax Error! StatementExpr has no Expression2");
//                            return null;
//                        }
//                    } else {
//                        System.out.println("StatementExpression has no =");
//                        return null;
//                    }
//                } else {
//                    System.out.println("StatementExpression has no ]");
//                    return null;
//                }
//            } else {
//                System.out.println("Syntax Error! StatementExpr has no Expression");
//                return null;
//            }
//
//        }
//        System.out.println("Syntax Error! no StatementExpression");
//        return null;
//    }
//
//    private ElseStatement_R elseStatement() {
//        Match token = queue.peek();
//        if (token.token.equals("ELSE")) {
//            queue.poll();
//            Statement_R statement = statement();
//            if (statement == null) {
//                System.out.println("Syntax Error! ElseStatement has no Statement");
//                return null;
//            }
//            return new ElseStatement_R(token, statement);
//        }
//        return null;
//    }
//

    public static void main(String[] args) throws FileNotFoundException {
        Parser p = new Parser();
        p.loadTokens();

        VarDeclaration_R root = p.varDeclaration();

        root.printNode();
    }
}
