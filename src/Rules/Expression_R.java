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
    
    Match token1;
    Match token2;
    Expression2_R expression2;
    Expression_R expression;
    ExpressionNew_R expressionNew;
    Identifier_R identifier;
    String type;
    
    
    public Expression_R(Match token1, ExpressionNew_R expressionNew, String type) {
        this.token1 = token1;
        this.expressionNew = expressionNew;
        this.type = type;
    }

    public Expression_R(Match token1, Expression2_R expression2, String type) {
        this.token1 = token1;
        this.expression2 = expression2;
        this.type = type;
    }

    public Expression_R(Identifier_R identifier, Expression2_R expression2, String type) {
        this.identifier = identifier;
        this.expression2 = expression2;
        this.type = type;

    }


    public Expression_R(Match token1, Expression_R expression, Expression2_R expression2, String type) {
        this.token1 = token1;
        this.expression2 = expression2;
        this.expression = expression;
        this.type = type;
                
    }

    public Expression_R(Match token1, Expression_R expression, Match token2, Expression2_R expression2, String type) {
        this.token1 = token1;
        this.token2 = token2;
        this.expression2 = expression2;
        this.expression = expression;
        this.type = type;
    }   
    
//Expression -> <INTEGRAL_LITERAL> Expression''
//        | <FLOAT_LITERAL> Expression''
//        | "true" Expression''
//        | "false" Expression''
//        | Identifier Expression''
//        | "this" Expression''
    
//        | "new" ExpressionNew
    
//        | "!" Expression Expression''
//        | "(" Expression ")" Expression''

    
    public void printNode(){
        
        if(type.equals("print reserved words in expression")){//int float true false this
                System.out.print(token1.value);
                if(expression2 != null)
                   expression2.printNode();
            
        }
        
        else if(type.equals("print new in expression")){ //| "new" ExpressionNew
                      
                System.out.print(token1.value + " ");
                
                if(expressionNew != null)
                    expressionNew.printNode();
        }
        
        else if(type.equals("print identifier in expression")){
            if(identifier != null)
               identifier.printNode();
            if(expression2 != null)
               expression2.printNode();
             
        }
        
        else if(type.equals("print ! in expression")){
            
            System.out.print(token1.value);
            
            if(expression != null)
                expression.printNode();
            
            if(expression2 != null)
                expression2.printNode();
            
        }
        
        else if(type.equals("print (expression) in expression")){
            
                System.out.print(token1.value);

                if(expression != null)
                    expression.printNode();

                System.out.print(token1.value);

                 if(expression2 != null)
                    expression2.printNode();
        }
        
   }  
    
}
  
