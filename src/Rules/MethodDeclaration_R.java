package Rules;

import LexicalAnalyzer.Match;

import java.util.ArrayList;

//MethodDeclaration ::= ("public" | "private" | "protected") Type Identifier"(" ( Type Identifier ( "," Type Identifier )*)? ")"
//"{" ( VarDeclaration )* ( Statement )* "return" Expression ";" "}"
public class MethodDeclaration_R {
    Match methodType;
    Type_R type1;
    Identifier_R identifier1;
    Match LEFT_ROUND_B;
    ArrayList <Paramter> paramters = new ArrayList<>();
    Paramter paramter;
    int numberOfCommas;
    Match RIGHT_ROUND_B;
    Match LEFT_CURLY_B;
    ArrayList <VarDeclaration_R> varDeclarations =new ArrayList<>();
    ArrayList <Statement_R> statements = new ArrayList<>();
    Match return_;
    Match semiColon;
    Match RIGHT_CURLY_B;

    //params + vars+ stmts
    public MethodDeclaration_R(Match methodType, Type_R type1, Identifier_R identifier1, Match LEFT_ROUND_B, ArrayList<Paramter> paramters, int numberOfCommas, Match RIGHT_ROUND_B, Match LEFT_CURLY_B, ArrayList<VarDeclaration_R> varDeclarations, ArrayList<Statement_R> statements, Match return_, Match semiColon, Match RIGHT_CURLY_B) {
        this.methodType = methodType;
        this.type1 = type1;
        this.identifier1 = identifier1;
        this.LEFT_ROUND_B = LEFT_ROUND_B;
        this.paramters = paramters;
        this.numberOfCommas = numberOfCommas;
        this.RIGHT_ROUND_B = RIGHT_ROUND_B;
        this.LEFT_CURLY_B = LEFT_CURLY_B;
        this.varDeclarations = varDeclarations;
        this.statements = statements;
        this.return_ = return_;
        this.semiColon = semiColon;
        this.RIGHT_CURLY_B = RIGHT_CURLY_B;
    }


}
