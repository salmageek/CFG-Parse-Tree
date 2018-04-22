package Rules;

//ElseStatement -> else Statement | E

import LexicalAnalyzer.Match;

public class ElseStatement_R implements Node {
    Match token;
    Statement_R statement;

    public ElseStatement_R(Match token, Statement_R statement) {
        this.token = token;
        this.statement = statement;
    }

    @Override
    public void printNode() {
        System.out.print(token.value);
        statement.printNode();
    }
}
