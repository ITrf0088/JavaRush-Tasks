package com.javarush.task.task34.task3404;

import java.util.List;

public class LexemeBuffer {

    int position  = 0;
    int countOfOperand = 0;

    public void incrCountOfOper() {
        countOfOperand++;
    }

    List<Lexeme> lexemes ;

    public LexemeBuffer(List<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    public Lexeme next(){
        if(position < lexemes.size()) {
            return lexemes.get(position++);
        }
        return null;
    }

    public void back(){
        position--;
    }

    public int getPosition() {
        return position;
    }

}
