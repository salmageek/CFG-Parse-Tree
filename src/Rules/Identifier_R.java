package Rules;

import LexicalAnalyzer.Match;

public class Identifier_R implements Node {

    Match token;

    public Identifier_R() {
    }

    public Identifier_R(Match token) {
        this.token = token;
    }

    @Override
    public void printNode() {
        if (token != null) {
            System.out.print(token.value);
        }
    }
}
