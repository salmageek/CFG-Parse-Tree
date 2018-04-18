package Rules;

//ElseStatement -> else Statement | E

import LexicalAnalyzer.Match;

public class ElseStatement_R {
    Match else_;
    Statement_R statement;
}
