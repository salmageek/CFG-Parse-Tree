package Rules;

//Expression''-> Expression' Expression'' | E

public class Expression2_R {

    Expression1_R expression1;
    Expression2_R expression2;

    public Expression2_R(Expression1_R expression1, Expression2_R expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }
    
    public void printNode(){
        
        if(expression1 != null){
            expression1.printNode(); 
        }
        
        if(expression2 != null){
            expression2.printNode();
        }
    }
}
