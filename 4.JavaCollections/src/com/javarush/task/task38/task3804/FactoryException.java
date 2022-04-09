package com.javarush.task.task38.task3804;

public class FactoryException {

    public static Throwable getThrowable(Enum e){
        if(e == null) return new IllegalArgumentException();
        String s = e.name().replaceAll("_"," ")
                .toLowerCase();
        s = s.replaceFirst(String.valueOf(s.charAt(0)),
                String.valueOf(s.charAt(0)).toUpperCase());

        if(e instanceof ApplicationExceptionMessage){

            return new Exception(s);
        }else if(e instanceof DatabaseExceptionMessage){

            return new RuntimeException(s);
        }else if(e instanceof UserExceptionMessage){
            return new Error(s);
        }else return new IllegalArgumentException();
    }


}
