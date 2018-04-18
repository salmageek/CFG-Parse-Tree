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
}
