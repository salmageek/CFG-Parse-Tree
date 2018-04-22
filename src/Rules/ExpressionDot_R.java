package Rules;

import LexicalAnalyzer.Match;

import java.util.ArrayList;

//ExpressionDot -> "length"
//        | Identifier "(" ( Expression ( "," Expression )* )? ")"


public class ExpressionDot_R implements Node {
    Identifier_R identifier;
    Expression_R expression;
    ArrayList<ExpressionDot_R> exprDots = new ArrayList();
    Match token1;
    Match token2;
    Match token3;
    String type;

    public ExpressionDot_R(Match token1, String type) {
        this.token1 = token1;
        this.type = type;
    }

    public ExpressionDot_R(Match token1, Expression_R expression) {
        this.token1 = token1;
        this.expression = expression;
    }


    public ExpressionDot_R(Identifier_R identifier, Match token1, Match token2, String type) {
        this.identifier = identifier;
        this.token1 = token1;
        this.token2 = token2;
        this.type = type;
    }

    public ExpressionDot_R(Identifier_R identifier, Match token1, Expression_R expression, Match token2, String type) {
        this.identifier = identifier;
        this.token1 = token1;
        this.expression = expression;
        this.token2 = token2;
        this.type = type;
    }

    public ExpressionDot_R(Identifier_R identifier, Match token1, Expression_R expression, ArrayList<ExpressionDot_R> exprDots, Match token2, String type) {
        this.identifier = identifier;
        this.token1 = token1;
        this.expression = expression;
        this.exprDots = exprDots;
        this.token2 = token2;
        this.type = type;
    }


    @Override
    public void printNode() {
        if (type.equals("length")) {
            System.out.println(token1.value);

        } else if (type.equals("no expression")) {
            identifier.printNode();
            System.out.println(token1.value + token2.value);

        } else if (type.equals("one expression")) {
            identifier.printNode();
            System.out.print(token1.value);
            expression.printNode();
            System.out.print(token2.value);

        } else if (type.equals("many expressions")) {
            identifier.printNode();
            System.out.println(token1.value);
            expression.printNode();
            for (int i = 0; i < exprDots.size(); i++) {
                ExpressionDot_R expr = exprDots.get(i);
                System.out.println(expr.token1 + " ");
                expr.expression.printNode();
            }
            System.out.println(token2.value);
        }
    }
}
