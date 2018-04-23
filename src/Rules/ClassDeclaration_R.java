package Rules;

//ClassDeclaration ::= "class" Identifier ( "extends" Identifier )? "{" ( VarDeclaration )* ( ConstructorDeclaration )* ( MethodDeclaration )* "}"

import LexicalAnalyzer.Match;

import java.util.ArrayList;

public class ClassDeclaration_R implements Node {
    Match class_;
    Identifier_R identifier1;
    Match extend;
    Identifier_R identifier2;
    Match LEFT_CURLY_B;
    ArrayList<VarDeclaration_R> varDeclarations = new ArrayList<>();
    ArrayList<ConstructorDeclaration_R> constructorDeclarations = new ArrayList<>();
    ArrayList<MethodDeclaration_R> methodDeclarations = new ArrayList<>();
    Match RIGHT_CURLY_B;

    public ClassDeclaration_R(Match class_, Identifier_R identifier1, Match extend, Identifier_R identifier2, Match LEFT_CURLY_B, ArrayList<VarDeclaration_R> varDeclarations, ArrayList<ConstructorDeclaration_R> constructorDeclarations, ArrayList<MethodDeclaration_R> methodDeclarations, Match RIGHT_CURLY_B) {
        this.class_ = class_;
        this.identifier1 = identifier1;
        this.extend = extend;
        this.identifier2 = identifier2;
        this.LEFT_CURLY_B = LEFT_CURLY_B;
        this.varDeclarations = varDeclarations;
        this.constructorDeclarations = constructorDeclarations;
        this.methodDeclarations = methodDeclarations;
        this.RIGHT_CURLY_B = RIGHT_CURLY_B;
    }

    //    ClassDeclaration ::= "class" Identifier ( "extends" Identifier )? "{" ( VarDeclaration )*
//            ( ConstructorDeclaration )* ( MethodDeclaration )* "}"
    @Override
    public void printNode() {
        System.out.print(class_.value + " ");
        identifier1.printNode();
        if (extend != null) {
            System.out.print(" " + extend.value + " ");
            identifier2.printNode();
        }
        System.out.println(" " + LEFT_CURLY_B.value);
        for (int i = 0; i < varDeclarations.size(); i++) {
            varDeclarations.get(i).printNode();
        }
        for (int i =0; i < constructorDeclarations.size(); i++){
            constructorDeclarations.get(i).printNode();
        }
        for (int i = 0; i < methodDeclarations.size(); i++){
            methodDeclarations.get(i).printNode();
        }
        System.out.println(RIGHT_CURLY_B.value);
    }

}
