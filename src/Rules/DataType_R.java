package Rules;

import LexicalAnalyzer.Match;

public class DataType_R implements Node {
    Match token;

    public DataType_R(Match token) {
        this.token = token;
    }


    @Override
    public void printNode() {
        System.out.print("DataType:- ");
        if (token == null)
            System.out.println("null");
        else
            System.out.println(token.value);
    }
}
