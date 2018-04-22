package Rules;

import LexicalAnalyzer.Match;

public class DataType_R implements Node {
    Match token;

    public DataType_R(Match token) {
        this.token = token;
    }


    @Override
    public void printNode() {
        System.out.print(token.value);
    }
}
