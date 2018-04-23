package Rules;

import LexicalAnalyzer.Match;

import java.util.ArrayList;

public class Goal_R implements Node{
    MainClass_R mainClass;
    ClassDeclaration_R classDeclaration;
    Match eof;
    ArrayList<ClassDeclaration_R> classDs = new ArrayList<>();

    public Goal_R(MainClass_R mainClass, Match eof){
        this.mainClass = mainClass;
        this.eof = eof;
    }

    public Goal_R(ClassDeclaration_R classDeclaration){
        this.classDeclaration = classDeclaration;
    }

    public Goal_R(MainClass_R mainClass, ArrayList<ClassDeclaration_R> classDs, Match eof){
        this.mainClass = mainClass;
        this.classDs = classDs;
        this.eof = eof;
    }
    @Override
    public void printNode(){
        if (mainClass != null) {
            mainClass.printNode();
        }

        for (int i = 0; i < classDs.size(); i++) {
            classDs.get(i).printNode();
        }


    }

}
