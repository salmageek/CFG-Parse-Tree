package Rules;

import LexicalAnalyzer.Match;

//MethodDeclaration ::= ("public" | "private" | "protected") Type Identifier"(" ( Type Identifier ( "," Type Identifier )*)? ")"
//"{" ( VarDeclaration )* ( Statement )* "return" Expression ";" "}"
public class MethodDeclaration_R {
    Match methodType;
    Type_R type1;
    Identifier_R identifier1;
    Match LEFT_ROUND_B;
    Type_R type2;
    Identifier_R identifier2;
    Match COMMA;
    Type_R type3;
    Identifier_R identifier3;
    Match RIGHT_ROUND_B;


}
