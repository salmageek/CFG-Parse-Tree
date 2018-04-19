package Rules;

//Expression -> <INTEGRAL_LITERAL> Expression''
//        | <FLOAT_LITERAL> Expression''
//        | "true" Expression''
//        | "false" Expression''
//        | Identifier Expression''
//        | "this" Expression''
//        | "new" ExpressionNew
//        | "!" Expression Expression''
//        | "(" Expression ")" Expression''


import LexicalAnalyzer.Match;

public class Expression_R {
    public Expression_R(Match token1, ExpressionNew_R expressionNew) {
        this.token1 = token1;
        this.expressionNew = expressionNew;
    }

    public Expression_R(Match token1, Expression2_R expression2) {
        this.token1 = token1;
        this.expression2 = expression2;
    }

    public Expression_R(Identifier_R identifier, Expression2_R expression2) {
        this.identifier = identifier;
        this.expression2 = expression2;

    }

    Match token1;

    public Expression_R(Match token1, Expression_R expression, Expression2_R expression2) {
        this.token1 = token1;
        this.expression2 = expression2;
        this.expression = expression;
    }

    Match token2;
    Expression2_R expression2;
    Expression_R expression;
    ExpressionNew_R expressionNew;
    Identifier_R identifier;

    public Expression_R(Match token1, Expression_R expression, Match token2, Expression2_R expression2) {
        this.token1 = token1;
        this.token2 = token2;
        this.expression2 = expression2;
        this.expression = expression;
    }
    
}
