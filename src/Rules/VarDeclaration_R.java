package Rules;

import LexicalAnalyzer.Match;

//VarDeclaration ::= Type Identifier ";"
public class VarDeclaration_R {
    Type_R type;
    Identifier_R identifier;
    Match semicolon;

    public VarDeclaration_R(Type_R type, Identifier_R identifier, Match semicolon) {
        this.type = type;
        this.identifier = identifier;
        this.semicolon = semicolon;
    }
}
