package Rules;

//ExpressionDot -> "length"
//        | Identifier "(" ( Expression ( "," Expression )* )? ")"


import LexicalAnalyzer.Match;

public class ExpressionDot_R {
    Identifier_R identifier;
    Expression_R expression;
    Match token1;
    Match token2;
    Match token3;

}
