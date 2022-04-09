package com.javarush.task.task34.task3408.mathexpression;

public class  Lexeme {
    private String mathChar;
    private LexemeType type;


    public Lexeme(String mathChar, LexemeType type) {
        this.mathChar = mathChar;
        this.type = type;
    }

    public String getMathChar() {
        return mathChar;
    }

    public LexemeType getType() {
        return type;
    }
}
