package Rules;

import LexicalAnalyzer.Match;

import java.util.ArrayList;

//ConstructorDeclaration::= Identifier "(" ( Type Identifier ( "," Type Identifier )*)? ")""{" ( VarDeclaration )* ( Statement )* "}"
public class ConstructorDeclaration_R implements Node {
    Identifier_R identifier1;
    Match LEFT_ROUND_B;
    ArrayList<Paramter> paramters = new ArrayList<>();
    Match COMMA;
    int numberOfCommas;
    Match RIGHT_ROUND_B;
    Match LEFT_CURLY_B;
    ArrayList<VarDeclaration_R> varDeclarations = new ArrayList<>();
    ArrayList<Statement_R> statements = new ArrayList<>();
    Match RIGHT_CURLY_B;

    public ConstructorDeclaration_R(Identifier_R identifier1, Match LEFT_ROUND_B, ArrayList<Paramter> paramters, int numberOfCommas, Match RIGHT_ROUND_B, Match LEFT_CURLY_B, ArrayList<VarDeclaration_R> varDeclarations, ArrayList<Statement_R> statements, Match RIGHT_CURLY_B) {
        this.identifier1 = identifier1;
        this.LEFT_ROUND_B = LEFT_ROUND_B;
        this.paramters = paramters;
        this.numberOfCommas = numberOfCommas;
        this.RIGHT_ROUND_B = RIGHT_ROUND_B;
        this.LEFT_CURLY_B = LEFT_CURLY_B;
        this.varDeclarations = varDeclarations;
        this.statements = statements;
        this.RIGHT_CURLY_B = RIGHT_CURLY_B;
    }
//    ConstructorDeclaration::= Identifier "(" ( Type Identifier ( "," Type Identifier )*)? ")"
//            "{" ( VarDeclaration )* ( Statement )* "}"

    @Override
    public void printNode() {
        identifier1.printNode();
        System.out.print(" " + LEFT_ROUND_B.value);
        for (int i = 0; i < paramters.size(); i++) {
            paramters.get(i).type.printNode();
            System.out.print(" ");
            paramters.get(i).id.printNode();
            if (i != paramters.size() - 1)
                System.out.print(", ");

        }
        System.out.println(RIGHT_ROUND_B.value + " " + LEFT_CURLY_B.value);
        for (int i = 0; i < varDeclarations.size(); i++){
            varDeclarations.get(i).printNode();
        }
        for (int i = 0; i < statements.size(); i++){
            statements.get(i).printNode();
        }
        System.out.println(RIGHT_CURLY_B.value);

    }


}
