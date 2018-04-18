package Rules;

import LexicalAnalyzer.Match;

//ConstructorDeclaration::= Identifier "(" ( Type Identifier ( "," Type Identifier )*)? ")""{" ( VarDeclaration )* ( Statement )* "}"
public class ConstructorDeclaration_R {
    Identifier_R identifier1;
    Match LEFT_ROUND_B;
    Type_R type1;
    Identifier_R identifier2;
    Match COMMA;
    Type_R type2;
    Identifier_R identifier3;
    Match RIGHT_ROUND_B;
    Match LEFT_CURLY_B;
    VarDeclaration_R varDeclaration_r;
    Statement_R statement;
    Match RIGHT_CURLY_B;




}
