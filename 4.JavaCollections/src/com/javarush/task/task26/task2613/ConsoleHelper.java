package com.javarush.task.task26.task2613;


import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.util.ResourceBundle;

public class ConsoleHelper {

    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.common_en");

    private static final BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("the.end"));
        ConsoleHelper.writeMessage(res.getString("choose.operation"));
        ConsoleHelper.writeMessage(res.getString("operation.INFO"));
        ConsoleHelper.writeMessage(res.getString("operation.DEPOSIT"));
        ConsoleHelper.writeMessage(res.getString("operation.WITHDRAW"));
        ConsoleHelper.writeMessage(res.getString("operation.EXIT"));
        ConsoleHelper.writeMessage(res.getString("invalid.data"));
        ConsoleHelper.writeMessage(res.getString("choose.currency.code"));
        ConsoleHelper.writeMessage(res.getString("choose.denomination.and.count.format"));
        String s = null;
        try {
            s = bis.readLine();
        } catch (IOException ignore) {
        }
        if(s != null && s.toLowerCase().equals("exit"))
            throw new InterruptOperationException();
        return s;

    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage("Введите код валюты:");

        String readCurrency = readString();

        while (readCurrency == null || readCurrency.length() != 3) {
            writeMessage("Введите код валюты:");
            writeMessage("Нужно ввести три символа!");
            readCurrency = readString();
        }

        return readCurrency.trim().toUpperCase();
    }


    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            try {
                writeMessage("Выберите действие: 1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT");

                String s = readString();

                if (s == null) continue;

                return Operation.getAllowableOperationByOrdinal(Integer.valueOf(s.trim()));

            } catch (IllegalArgumentException e) {
                writeMessage("Выберите дейтвие!");
            }
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {

        while (true) {
            writeMessage("Введите два целых положителных числа [номинал] [кол. банкнот]");
            String s = readString();
            if (s == null) continue;
            String[] twoDigits = s.split(" ");

            if (twoDigits.length == 2) {
                try {
                    if (Integer.parseInt(twoDigits[0]) > 0 && Integer.parseInt(twoDigits[1]) > 0)
                        return twoDigits;
                } catch (NumberFormatException e) {
                    writeMessage("Данные некорректны!");
                    continue;
                }
            }
            writeMessage("Данные некорректны!");

        }
    }
    public static void  printExitMessage(){
        ConsoleHelper.writeMessage("До встречи!");
    }
}
