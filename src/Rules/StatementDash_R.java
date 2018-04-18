package Rules;


//StatementDash -> "=" Expression ";" |
//                  "[" Expression "]" "=" Expression ";"

import LexicalAnalyzer.Match;

public class StatementDash_R {
    Match equal;
    Expression_R expression1;
    Match semicolon;
    Expression_R expression2;

    Match LEFT_SQUARE_B;
    Match RIGHT_SQUARE_B;
    Match assignment;

}
