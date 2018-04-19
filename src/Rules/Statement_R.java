package Rules;

//Statement ->  "{" ( Statement )* "}"
//        | if "(" Expression ")" Statement ElseStatement
//        | "while" "(" Expression ")" Statement
//        | "System.out.println" "(" Expression ")" ";"
//        | Identifier StatementExpression_R

import LexicalAnalyzer.Match;

import java.util.ArrayList;

public class Statement_R {

    Match token1;
    Match token2;
    Match token3;
    Match token4;
    Expression_R expression;
    ElseStatement_R elseStatement;
    StatementExpression_R statementExpr;
    Identifier_R identifier;
    Statement_R statement;
    ArrayList<Statement_R> statements;


    public Statement_R(Match token, Match token2, Expression_R expression, Match token3, Statement_R statement, ElseStatement_R elseStatement) {
        this.token1 = token;
        this.token2 = token2;
        this.token3 = token3;
        this.expression = expression;
        this.elseStatement = elseStatement;
        this.statement = statement;
    }

    public Statement_R(Match token, Match token2, Expression_R expression, Match token3, Statement_R statement) {
        this.token1 = token;
        this.token2 = token2;
        this.token3 = token3;
        this.expression = expression;
        this.statement = statement;
    }

    public Statement_R(Match token, Match token2, Expression_R expression, Match token3, Match token4) {
        this.token1 = token;
        this.token2 = token2;
        this.token3 = token3;
        this.token4 = token4;
        this.expression = expression;
        this.statement = statement;
    }

    public Statement_R(Identifier_R id, StatementExpression_R statementExpr) {
        this.identifier = id;
        this.statementExpr = statementExpr;
    }

    public Statement_R(Match token, ArrayList<Statement_R> statements, Match token2) {
        this.token1 = token;
        this.statements = statements;
        this.token2 = token2;
    }
}

