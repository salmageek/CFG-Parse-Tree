package Rules;

import LexicalAnalyzer.Match;

// MainClass ::= "class" Identifier "{" "public" "static" "void" "main" "(" "String" "[" "]" Identifier ")" "{" Statement "}" "}"

public class MainClass_R implements Node {
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

    public MainClass_R(Match class_, Identifier_R identifier1, Match LEFT_CURLY_B1, Match public_,
                       Match static_, Match void_, Match main_, Match LEFT_ROUND_B,
                       Match string_, Match LEFT_SQUARE_B, Match RIGHT_SQUARE_B, Identifier_R identifier2,
                       Match RIGHT_ROUND_B, Match LEFT_CURLY_B2, Statement_R statement,
                       Match RIGHT_CURLY_B2, Match RIGHT_CURLY_B1) {

        this.class_ = class_;
        this.identifier1 = identifier1;
        this.LEFT_CURLY_B1 = LEFT_CURLY_B1;
        this.public_ = public_;
        this.static_ = static_;
        this.void_ = void_;
        this.main_ = main_;
        this.LEFT_ROUND_B = LEFT_ROUND_B;
        this.string_ = string_;
        this.LEFT_SQUARE_B = LEFT_SQUARE_B;
        this.RIGHT_SQUARE_B = RIGHT_SQUARE_B;
        this.identifier2 = identifier2;
        this.RIGHT_ROUND_B = RIGHT_ROUND_B;
        this.LEFT_CURLY_B2 = LEFT_CURLY_B2;
        this.statement = statement;
        this.RIGHT_CURLY_B2 = RIGHT_CURLY_B2;
        this.RIGHT_CURLY_B1 = RIGHT_CURLY_B1;

    }

    //    MainClass ::= "class" Identifier "{" "public" "static" "void" "main" "(" "String" "[" "]" Identifier ")" "{" Statement "}" "}"
    @Override
    public void printNode() {
        System.out.print(class_.value + " ");
        identifier1.printNode();
        System.out.println(" " + LEFT_CURLY_B1.value);
        System.out.print(public_.value + " " + static_.value + " " + void_.value + " " + main_.value + LEFT_ROUND_B.value);
        System.out.print(string_.value + LEFT_SQUARE_B.value + RIGHT_SQUARE_B.value + " ");
        identifier2.printNode();
        System.out.println(RIGHT_ROUND_B.value + " " + LEFT_CURLY_B2.value);
        statement.printNode();
        System.out.println(RIGHT_CURLY_B2.value);
    }


}