package Rules;

//Expression''-> Expression' Expression'' | E

public class Expression2_R {

    Expression1_R expression1;
    Expression2_R expression2;

    public Expression2_R(Expression1_R expression1, Expression2_R expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }



}
