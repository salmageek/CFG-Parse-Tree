package Rules;

import LexicalAnalyzer.Match;

import java.util.ArrayList;

//ConstructorDeclaration::= Identifier "(" ( Type Identifier ( "," Type Identifier )*)? ")""{" ( VarDeclaration )* ( Statement )* "}"
public class ConstructorDeclaration_R {
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




}
