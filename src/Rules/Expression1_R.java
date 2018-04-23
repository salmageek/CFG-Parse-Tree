package Rules;

//Expression' -> ( "&&" | "||" | "==" | "!=" | ">" | "<" | "<=" | ">="| "+" | "-" | "*" | "/" ) Expression
//        -> "[" Expression "]"
//        -> "." ExpressionDot

import LexicalAnalyzer.Match;

public class Expression1_R {
    Match token1;
    Match token2;
    Expression_R expression;
    ExpressionDot_R expressionDot;
    String type;
    
    public Expression1_R(Match token1, Expression_R expression, String type) {
        this.token1 = token1;
        this.expression = expression;
        this.type = type;
    }
    
    public Expression1_R(Match token1, Expression_R expression, Match token2, String type) {
        this.token1 = token1;
        this.expression = expression;
        this.token2 = token2;
        this.type = type;
    }
    
    public Expression1_R(Match token1, ExpressionDot_R expression, String type) {
        this.token1 = token1;
        this.expressionDot = expression;
        this.type = type;
       
    }
    
//Expression' -> ( "&&" | "||" | "==" | "!=" | ">" | "<" | "<=" | ">="| "+" | "-" | "*" | "/" ) Expression
//        -> "[" Expression "]"
//        -> "." ExpressionDot
    
    public void printNode(){
        
        if(type.equals("print operator in expression1")){
           System.out.print(token1.value + " ");
           if(expression != null){
               expression.printNode();
           }
        }
        
        else if(type.equals("print [expression] in expression1")){
            
             System.out.print(token1.value);
            
             if(expression != null){
                expression.printNode();
             }
             
             System.out.print(token2.value);

        }
        
        else if(type.equals("print .expressionDot in expression1")){
            
            System.out.print(token1.value + " ");
             
            if(expressionDot != null){
                expressionDot.printNode();
            }

        }
                 
    }
}
