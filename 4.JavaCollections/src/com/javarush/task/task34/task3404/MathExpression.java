package com.javarush.task.task34.task3404;


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class MathExpression {



    //TODO toSolve method
    public static String toSolve(String expr){
        List<Lexeme> lexemes = getLexemes(expr);

        LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
        String num = String.valueOf(expr(lexemeBuffer));
        Number number;
        if(num.matches("(-)?\\d+\\.0")){
            number = ((int) Double.parseDouble(num));
        }else {
            number = Double.parseDouble(num);
        }
        return (number)+" "+getCountofOperation(expr);
    }



    //TODO expr method
    private static double expr(LexemeBuffer lexemeBuf){
        double value = formatDouble(plusMinus(lexemeBuf));
        return value;
    }

    //TODO plusMinus method
    private static double plusMinus(LexemeBuffer lexemeBuf){
        double value = formatDouble(multDiv(lexemeBuf));
        Lexeme lexeme;
        LexemeType type;
        while (true) {
            lexeme = lexemeBuf.next();
            type = lexeme.getType();

            if (type == LexemeType.OP_PLUS) {
                value += formatDouble(multDiv(lexemeBuf));
                lexemeBuf.incrCountOfOper();
            } else if (type == LexemeType.OP_MINUS) {
                value -= formatDouble(multDiv(lexemeBuf));
                lexemeBuf.incrCountOfOper();
            } else {
                lexemeBuf.back();
                break;
            }
        }

        return value;
    }

    //TODO multDiv method
    private static double multDiv(LexemeBuffer lexemeBuf) {

        double value = formatDouble(pow(lexemeBuf));
        Lexeme lexeme;
        LexemeType type;
        while (true) {
            lexeme = lexemeBuf.next();
            type = lexeme.getType();

            if (type == LexemeType.OP_MUL) {
                value *= formatDouble(pow(lexemeBuf));
                lexemeBuf.incrCountOfOper();
            } else if (type == LexemeType.OP_DIV) {
                value /= formatDouble(pow(lexemeBuf));
                lexemeBuf.incrCountOfOper();
            } else {
                lexemeBuf.back();
                break;
            }

        }
            return value;
    }

    //TODO pow method
    private static double pow(LexemeBuffer lexemeBuf) {
        double value = formatDouble(factor(lexemeBuf));

        Lexeme lexeme;
        LexemeType type;
        while (true) {
            lexeme = lexemeBuf.next();
            type = lexeme.getType();

           if (type == LexemeType.POW) {
                value = formatDouble(Math.pow(value,formatDouble(factor(lexemeBuf))));
               lexemeBuf.incrCountOfOper();
            } else {
                lexemeBuf.back();
                break;
            }
        }
        return value;
    }


    //TODO factor method
    private static double factor(LexemeBuffer lexemeBuf){
        Lexeme lexeme = lexemeBuf.next();
        double value = 0;
        if(lexeme.getType() == LexemeType.NUMBER){

            value = formatDouble(Double.parseDouble(lexeme.getMathChar()));

        }else if (lexeme.getType() == LexemeType.LEFT_BRACKET){
            value = formatDouble(expr(lexemeBuf));
            if(lexemeBuf.next().getType() != LexemeType.RIGHT_BRACKET){
                throw new RuntimeException("unexpected token");
            }

        }else if(lexeme.getType() == LexemeType.SIN){
            lexemeBuf.next();
            value = formatDouble(Math.sin(Math.toRadians(expr(lexemeBuf))));
            lexemeBuf.incrCountOfOper();
            if(lexemeBuf.next().getType() != LexemeType.RIGHT_BRACKET){
                throw new RuntimeException("unexpected token");
            }

        }else if(lexeme.getType() == LexemeType.COS){
            lexemeBuf.next();
            value = formatDouble(Math.cos(Math.toRadians(expr(lexemeBuf))));
            lexemeBuf.incrCountOfOper();
            if(lexemeBuf.next().getType() != LexemeType.RIGHT_BRACKET){
                throw new RuntimeException("unexpected token");
            }

        }else if(lexeme.getType() == LexemeType.TAN){
            lexemeBuf.next();
            value = formatDouble(Math.tan(Math.toRadians(expr(lexemeBuf))));
            lexemeBuf.incrCountOfOper();
            if(lexemeBuf.next().getType() != LexemeType.RIGHT_BRACKET){
                throw new RuntimeException("unexpected token");
            }

        }else {
            throw new RuntimeException("unexpected token");
        }

        return value;
    }


    //TODO getLexemes method
    private static List<Lexeme> getLexemes(String expr) {
        boolean isMinusBeforeNumberOrBracket = false;
        List<Lexeme> lexemes = new ArrayList<>();
        int pos = 0;
        char ch;

        while (pos < expr.length()) {
            ch = expr.charAt(pos);
            switch (ch){
                case '+':
                    lexemes.add(new Lexeme(String.valueOf(ch), LexemeType.OP_PLUS));
                    pos++;
                    break;
                case '-':
                    if(pos == 0 || expr.charAt(pos-1) == '('){
                        isMinusBeforeNumberOrBracket = true;
                        pos++;
                        break;
                    }
                    lexemes.add(new Lexeme(String.valueOf(ch), LexemeType.OP_MINUS));
                    pos++;
                    break;
                case '*':
                    lexemes.add(new Lexeme(String.valueOf(ch), LexemeType.OP_MUL));
                    pos++;
                    break;
                case '/':
                    lexemes.add(new Lexeme(String.valueOf(ch), LexemeType.OP_DIV));
                    pos++;
                    break;
                case '(':
                    if(isMinusBeforeNumberOrBracket){
                        lexemes.add(new Lexeme("0", LexemeType.NUMBER));
                        lexemes.add(new Lexeme("-", LexemeType.OP_MINUS));
                        isMinusBeforeNumberOrBracket = false;
                    }
                    lexemes.add(new Lexeme(String.valueOf(ch), LexemeType.LEFT_BRACKET));
                    pos++;
                    break;
                case ')':
                    lexemes.add(new Lexeme(String.valueOf(ch), LexemeType.RIGHT_BRACKET));
                    pos++;
                    break;
                case '^':
                    lexemes.add(new Lexeme(String.valueOf(ch), LexemeType.POW));
                    pos++;
                    break;
                case 't':
                    if(isMinusBeforeNumberOrBracket){
                        lexemes.add(new Lexeme("0", LexemeType.NUMBER));
                        lexemes.add(new Lexeme("-", LexemeType.OP_MINUS));
                        isMinusBeforeNumberOrBracket = false;
                    }
                    lexemes.add(new Lexeme("tan", LexemeType.TAN));
                    pos+=3;
                    break;
                case 's':
                    if(isMinusBeforeNumberOrBracket){
                        lexemes.add(new Lexeme("0", LexemeType.NUMBER));
                        lexemes.add(new Lexeme("-", LexemeType.OP_MINUS));
                        isMinusBeforeNumberOrBracket = false;
                    }
                    lexemes.add(new Lexeme("sin", LexemeType.SIN));
                    pos+=3;
                    break;
                case 'c':
                    if(isMinusBeforeNumberOrBracket){
                        lexemes.add(new Lexeme("0", LexemeType.NUMBER));
                        lexemes.add(new Lexeme("-", LexemeType.OP_MINUS));
                        isMinusBeforeNumberOrBracket = false;
                    }
                    lexemes.add(new Lexeme("cos", LexemeType.COS));
                    pos+=3;
                    break;

                default:

                    StringBuilder numbreBuilder = new StringBuilder();
                    if(isMinusBeforeNumberOrBracket) {
                        numbreBuilder.append('-');
                        isMinusBeforeNumberOrBracket = false;
                    }

                    if(ch >= '0' && ch <= '9') {

                        while ((ch >= '0' && ch <= '9') || ch == '.') {
                            numbreBuilder.append(ch);

                            pos++;
                            if (pos >= expr.length()) break;
                            ch = expr.charAt(pos);

                        }

                        lexemes.add(new Lexeme(numbreBuilder.toString(), LexemeType.NUMBER));
                    }
                    else {
                        if(ch != ' ') {
                            throw new RuntimeException("Unexpected token");
                        }
                        pos++;
                    }

            }
        }

        lexemes.add(new Lexeme("", LexemeType.EOF));
        return lexemes;
    }

    private static double formatDouble(double number){
        DecimalFormat df = new DecimalFormat("#.##");
        DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        String output = df.format(number);
        return Double.parseDouble(output);
    }

    private static int getCountofOperation(String expr) {
        String oper  = expr.replaceAll("(cos|sin|tan)","o");

        oper = oper.replaceAll("[^o*/^+-]","");

        return oper.length();
    }

}
