package Rules;

import LexicalAnalyzer.Match;

public class Array_R implements Node {
    Match token1;
    Match token2;

    public Array_R(Match token1, Match token2) {
        this.token1 = token1;
        this.token2 = token2;
    }

    @Override
    public void printNode() {
        System.out.print(token1.value + token2.value);

    }
}
