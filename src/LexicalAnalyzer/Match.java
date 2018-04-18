package LexicalAnalyzer;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Match {

    public String token;
    public String value;
    int startPosition;
    int endPosition;
    int range;



    public Match(String token, String value, int startPosition, int endPosition, int range) {

        this.token = token;
        this.value = value;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.range = range;

    }

    public Match(String token, String value, int startPosition, int endPosition) {

        this.token = token;
        this.value = value;
        this.startPosition = startPosition;
        this.endPosition = endPosition;

    }

}
