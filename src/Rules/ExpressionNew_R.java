package Rules;
//
//ExpressionNew -> ("int" | "float" | "String" | "char" | "boolean" ) "[" Expression "]" Expression''
//        | Identifier "(" (Expression ( "," Expression)*)? ")" Expression''

import LexicalAnalyzer.Match;

public class ExpressionNew_R {
    Match token1;
    Match token2;
    Match token3;
    Expression2_R expression2_1;
    Identifier_R identifier;
    Expression_R expression1;
    Expression_R expression2;
}
