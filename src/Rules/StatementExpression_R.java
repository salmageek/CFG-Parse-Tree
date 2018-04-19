package Rules;


//StatementDash -> "=" Expression ";" |
//                  "[" Expression "]" "=" Expression ";"

import LexicalAnalyzer.Match;

public class StatementExpression_R {

    Match token;
    Match token2;
    Match token3;
    Match token4;
    Expression_R expression1;
    Expression_R expression2;

    public StatementExpression_R(Match token, Expression_R expression, Match token2) {
        this.token = token;
        this.token2 = token2;
        this.expression1 = expression;
    }


    public StatementExpression_R(Match token, Expression_R expression, Match token2, Match token3, Expression_R expression2, Match token4) {
        this.token = token;
        this.token2 = token2;
        this.token3 = token3;
        this.token4 = token4;
        this.expression1 = expression;
        this.expression2 = expression2;
    }
}
