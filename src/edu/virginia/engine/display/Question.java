package edu.virginia.engine.display;

public class Question {
    private String prompt;
    private String hint;
    private String answer;
    private int correct;

    public Question(String q, String a, String h, int c) {
        this.prompt = q;
        this.answer = a;
        this.hint = h;
        this.correct = c;
    }
    public Question(){
        this.prompt = "";
        this.answer = "";
        this.hint = "";
        this.correct = 0;
    }

    public String getPrompt(){
        return this.prompt;
    }
    public void setPrompt(String s){
        this.prompt = s;
    }

    public String getHint(){
        return this.hint;
    }
    public void setHint(String s){
        this.hint = s;
    }

    public int getCorrect(){
        return this.correct;
    }
    public void setCorrect(int c){
        this.correct = c;
    }

    public String getAnswer(){
        return this.answer;
    }
    public void setAnswer(String s){
        this.answer = s;
    }

}
