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
        System.out.print("VarDeclaration:- ");
        if (type != null)
            System.out.print("<Type> ");
        if (identifier != null)
            System.out.print("<Identifier> ");
        if (semicolon != null)
            System.out.println(";");


        if (type == null)
            System.out.println("Type: null");
        else
            type.printNode();

        if (identifier == null)
            System.out.println("Identifier: null");
        else
            identifier.printNode();

        if (semicolon == null)
            System.out.println("Semicolon: null");
        else
            System.out.println(semicolon.value);
    }
}
