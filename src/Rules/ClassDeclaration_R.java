package Rules;

//ClassDeclaration ::= "class" Identifier ( "extends" Identifier )? "{" ( VarDeclaration )* ( ConstructorDeclaration )* ( MethodDeclaration )* "}"

import LexicalAnalyzer.Match;

public class ClassDeclaration_R {
    Match class_;
    Identifier_R identifier1;
    Match extend;
    Identifier_R identifier2;
    Match LEFT_CURLY_B;
    VarDeclaration_R varDeclaration;
    ConstructorDeclaration_R constructorDeclaration;
    MethodDeclaration_R methodDeclaration;
    Match RIGHT_CURLY_B;
}
