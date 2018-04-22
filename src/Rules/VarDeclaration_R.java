package Rules;

import LexicalAnalyzer.Match;

//VarDeclaration ::= Type Identifier ";"
public class VarDeclaration_R implements Node {
    Type_R type;
    Identifier_R identifier;
    Match semicolon;

    public VarDeclaration_R(Type_R type, Identifier_R identifier, Match semicolon) {
        this.type = type;
        this.identifier = identifier;
        this.semicolon = semicolon;
    }

    @Override
    public void printNode() {
        type.printNode();
        System.out.print(" ");
        identifier.printNode();
        System.out.println(semicolon.value);
    }
}
