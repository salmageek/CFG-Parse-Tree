package Rules;


//StatementDash -> "=" Expression ";" |
//                  "[" Expression "]" "=" Expression ";"

import LexicalAnalyzer.Match;

public class StatementExpression_R implements Node {

    Match token;
    Match token2;
    Match token3;
    Match token4;
    Expression_R expression1;
    Expression_R expression2;
    String type;

    public StatementExpression_R(Match token, Expression_R expression, Match token2, String type) {
        this.token = token;
        this.token2 = token2;
        this.expression1 = expression;
        this.type = type;
    }


    public StatementExpression_R(Match token, Expression_R expression, Match token2, Match token3, Expression_R expression2, Match token4, String type) {
        this.token = token;
        this.token2 = token2;
        this.token3 = token3;
        this.token4 = token4;
        this.expression1 = expression;
        this.expression2 = expression2;
        this.type = type;
    }

    @Override
    public void printNode() {
        if (type.equals("expr1")) {
            
            System.out.print(" " + token.value + " ");
            
            if(expression1 != null)
                expression1.printNode();
            
            System.out.println(token2.value);
            
        } else if (type.equals("expr2")) {
            
            System.out.print(token.value);
            if(expression1 != null)
                 expression1.printNode();
            System.out.print(token2.value + " " + token3.value + " ");
            if(expression2 != null)
                expression2.printNode();
            System.out.println(token4.value);
        }
    }
}
