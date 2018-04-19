package Rules;

//Expression' -> ( "&&" | "||" | "==" | "!=" | ">" | "<" | "<=" | ">="| "+" | "-" | "*" | "/" ) Expression
//        -> "[" Expression "]"
//        -> "." ExpressionDot

import LexicalAnalyzer.Match;

public class Expression1_R {
    Match token1;
    Match token2;
    Expression_R expression;
    ExpressionDot_R expressionDot;
    
    public Expression1_R(Match token1, Expression_R expression) {
        this.token1 = token1;
        this.expression = expression;
    }
    
    public Expression1_R(Match token1, Expression_R expression, Match token2) {
        this.token1 = token1;
        this.expression = expression;
        this.token2 = token2;
    }
    
    public Expression1_R(Match token1, ExpressionDot_R expression) {
        this.token1 = token1;
        this.expressionDot = expression;
       
    }
}
