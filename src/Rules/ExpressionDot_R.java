package Rules;
import LexicalAnalyzer.Match;
import java.util.ArrayList;

//ExpressionDot -> "length"
//        | Identifier "(" ( Expression ( "," Expression )* )? ")"


public class ExpressionDot_R {
    Identifier_R identifier;
    Expression_R expression;
    ArrayList<ExpressionDot_R> exprDots = new ArrayList();
    Match token1;
    Match token2;
    Match token3;
    
    public ExpressionDot_R(Match token1){
        this.token1 = token1;
    }
   
    public ExpressionDot_R(Match token1, Expression_R expression){
        this.token1 = token1;
        this.expression = expression;
    }
    
        
    public ExpressionDot_R(Identifier_R identifier, Match token1, Match token2){
        this.identifier = identifier;
        this.token1 = token1;
        this.token2 = token2;
    }
    
   public ExpressionDot_R(Identifier_R identifier, Match token1, Expression_R expression, Match token2){
        this.identifier = identifier;
        this.token1 = token1;
        this.expression = expression;
        this.token2 = token2;
    }
   
      public ExpressionDot_R(Identifier_R identifier, Match token1, Expression_R expression, ArrayList<ExpressionDot_R> exprDots, Match token2){
        this.identifier = identifier;
        this.token1 = token1;
        this.expression = expression;
        this.exprDots = exprDots;
        this.token2 = token2;
    }
      

}
