package Rules;

//Statement ->  "{" ( Statement )* "}"
//        | if "(" Expression ")" Statement ElseStatement
//        | "while" "(" Expression ")" Statement
//        | "System.out.println" "(" Expression ")" ";"
//        | Identifier StatementExpression_R

import LexicalAnalyzer.Match;

import java.util.ArrayList;

public class Statement_R implements Node {

    Match token1;
    Match token2;
    Match token3;
    Match token4;
    Expression_R expression;
    ElseStatement_R elseStatement;
    StatementExpression_R statementExpr;
    Identifier_R identifier;
    Statement_R statement;
    ArrayList<Statement_R> statements;
    String type;


    // if "(" Expression ")" Statement ElseStatement
    public Statement_R(Match token, Match token2, Expression_R expression, Match token3, Statement_R statement, ElseStatement_R elseStatement, String type) {
        this.token1 = token;
        this.token2 = token2;
        this.token3 = token3;
        this.expression = expression;
        this.elseStatement = elseStatement;
        this.statement = statement;
        this.type = type;
    }


    // "while" "(" Expression ")" Statement
    public Statement_R(Match token, Match token2, Expression_R expression, Match token3, Statement_R statement, String type) {
        this.token1 = token;
        this.token2 = token2;
        this.token3 = token3;
        this.expression = expression;
        this.statement = statement;
        this.type = type;
    }


    // "System.out.println" "(" Expression ")" ";"
    public Statement_R(Match token, Match token2, Expression_R expression, Match token3, Match token4, String type) {
        this.token1 = token;
        this.token2 = token2;
        this.token3 = token3;
        this.token4 = token4;
        this.expression = expression;
        this.type = type;
    }

    // Identifier StatementExpression
    public Statement_R(Identifier_R id, StatementExpression_R statementExpr, String type) {
        this.identifier = id;
        this.statementExpr = statementExpr;
        this.type = type;
    }

    // "{" ( Statement )* "}"
    public Statement_R(Match token, ArrayList<Statement_R> statements, Match token2, String type) {
        this.token1 = token;
        this.statements = statements;
        this.token2 = token2;
        this.type = type;
    }

    @Override
    public void printNode() {
        
        if (type.equals("statements")) {
            System.out.println(token1.value);
            ////bobo and khadega al if condition msht3'lsh fa3mlna loop 
            while(!statements.isEmpty()) {
                statements.get(0).printNode();
                statements.remove(0);
            }
            System.out.println(token2.value);

        } else if (type.equals("print statement")) {
            System.out.print(token1.value + token2.value + " ");
            if(expression != null)
                expression.printNode();
            System.out.println(token3.value + token4.value);

        } else if (type.equals("if statement")) {
            System.out.print(token1.value + " " + token2.value);
            
            if(expression != null)
                expression.printNode();
            System.out.print(token3.value);
            
            if(statement != null)
              statement.printNode();
            elseStatement.printNode();

        } else if (type.equals("while statement")) {
            System.out.print(token1.value + " " + token2.value);
            
            if(expression != null)
                expression.printNode();
            System.out.print(token3.value);
            
            if(statement != null)
               statement.printNode();

        } else if (type.equals("id statement")) {
            
            identifier.printNode();
            if(statementExpr != null)
               statementExpr.printNode();
        }

    }
}
