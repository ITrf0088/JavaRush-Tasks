package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException {

        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
        ConsoleHelper.writeMessage(res.getString("not.verified.format"));
        ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
        ConsoleHelper.writeMessage(res.getString("before"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String readCreditNumbder = ConsoleHelper.readString();
            if (!isValidData(readCreditNumbder, 12)) continue;
            String readPin = ConsoleHelper.readString();
            if (!isValidData(readPin, 4)) continue;
            if (!isValidUserData(readCreditNumbder, readPin)) continue;

            ConsoleHelper.writeMessage(String.format(res.getString("success.format"),readCreditNumbder));
            break;
        }
    }

    private boolean isValidUserData(String creditNumbder, String pin) {
        if (validCreditCards.containsKey(creditNumbder)) {
            return validCreditCards.getString(creditNumbder).equals(pin);
        }
        return false;
    }

    private boolean isValidData(String data, int requireNumbers) {
        if (data == null) return true;
        return data.matches("\\d{" + requireNumbers + "}");
    }
}
