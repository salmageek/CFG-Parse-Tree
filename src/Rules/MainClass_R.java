package Rules;

import LexicalAnalyzer.Match;

// MainClass ::= "class" Identifier "{" "public" "static" "void" "main" "(" "String" "[" "]" Identifier ")" "{" Statement "}" "}"

public class MainClass_R {
    Match class_;
    Identifier_R identifier1;
    Match LEFT_CURLY_B1;
    Match public_;
    Match static_;
    Match void_;
    Match main_;
    Match LEFT_ROUND_B;
    Match string_;
    Match LEFT_SQUARE_B;
    Match RIGHT_SQUARE_B;
    Identifier_R identifier2;
    Match RIGHT_ROUND_B;
    Match LEFT_CURLY_B2;
    Statement_R statement;
    Match RIGHT_CURLY_B2;
    Match RIGHT_CURLY_B1;
}
