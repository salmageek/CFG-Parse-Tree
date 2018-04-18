package Rules;

//Statement ->  "{" ( Statement )* "}"
//        | if "(" Expression ")" Statement ElseStatement
//        | "while" "(" Expression ")" Statement
//        | "System.out.println" "(" Expression ")" ";"
//        | Identifier StatementDash_R

import LexicalAnalyzer.Match;

public class Statement_R {
    Match LEFT_CURLY_B;
    Statement_R statement;
    Match RIGHT_CURLY_B;

    Match if_;
    Match LEFT_ROUND_B;
    Expression_R expression;
    Match RIGHT_ROUND_B;
    ElseStatement_R elseStatement;

    Match while_;

    Match sout;
    Match semicolon;

    Identifier_R identifier;
    StatementDash_R statementDash;
}

