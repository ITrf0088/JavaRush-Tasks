package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
        ConsoleHelper.writeMessage(res.getString("not.enough.money"));


        while (true) {

            int expectedMoney;
            try {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));

                String read = ConsoleHelper.readString();
                if(read != null){
                    expectedMoney = Integer.parseInt(read);
                }else continue;
            } catch (NumberFormatException e) {
                continue;
            }

            if (manipulator.isAmountAvailable(expectedMoney)) {
                try {
                    for (Map.Entry<Integer, Integer> entry : manipulator.withdrawAmount(expectedMoney).entrySet()) {
                        ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
                    }

                    ConsoleHelper.writeMessage(res.getString("success.format"));
                    break;
                } catch (NotEnoughMoneyException e) {
                    ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));

                }
            }
        }


    }
}
