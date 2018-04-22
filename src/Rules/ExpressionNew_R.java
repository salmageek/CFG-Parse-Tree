package Rules;
//
//ExpressionNew -> ("int" | "float" | "String" | "char" | "boolean" ) "[" Expression "]" Expression''
//        | Identifier "(" (Expression ( "," Expression)*)? ")" Expression''

import LexicalAnalyzer.Match;

import java.util.ArrayList;

public class ExpressionNew_R implements Node {
    Match token1;
    Match token2;
    Match token3;
    Expression2_R expression2_1;
    Identifier_R identifier;
    Expression_R expression1;
    ArrayList<ExpressionNew_R> commaExpression;
    String type;

    public ExpressionNew_R(Match token, Match token2, Expression_R expr, Match token3, Expression2_R expr2, String type) {
        this.token1 = token;
        this.token2 = token2;
        this.token3 = token3;
        this.expression2_1 = expr2;
        this.expression1 = expr;
        this.type = type;
    }

    public ExpressionNew_R(Identifier_R id, Match token2, Match token4, Expression2_R expression2, String type) {
        this.token1 = token2;
        this.token2 = token4;
        this.expression2_1 = expression2;
        this.identifier = id;
        this.type = type;
    }


    public ExpressionNew_R(Identifier_R id, Match token2, Expression_R expr, Match token4, Expression2_R expression2, String type) {
        this.token1 = token2;
        this.token2 = token4;
        this.expression2_1 = expression2;
        this.identifier = id;
        this.expression1 = expr;
        this.type = type;
    }

    public ExpressionNew_R(Match token3, Expression_R expr1) {
        this.token1 = token3;
        this.expression1 = expr1;
    }



    public ExpressionNew_R(Identifier_R id, Match token, Expression_R expr, ArrayList<ExpressionNew_R> exprNews, Match token4, Expression2_R expression2, String type) {
        this.token1 = token;
        this.token2 = token4;
        this.expression2_1 = expression2;
        this.identifier = id;
        this.expression1 = expr;
        this.commaExpression = exprNews;
        this.type = type;
    }

    @Override
    public void printNode(){
        if (type.equals("expr1")){

        }
    }
}
