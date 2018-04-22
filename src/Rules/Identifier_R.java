package Rules;

import LexicalAnalyzer.Match;

public class Identifier_R implements Node {
    public Identifier_R() {
    }
    public Identifier_R(Match token) {
        this.token = token;
    }

    Match token;

    @Override
    public void printNode() {
        System.out.print("Identifier:- ");
        if (token.value == null)
            System.out.println("null");
        else
            System.out.println(token.value);
    }
}
