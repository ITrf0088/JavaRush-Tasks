package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

class InfoCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");

    @Override
    public void execute() {
        boolean hasAnyMoney = false;
        ConsoleHelper.writeMessage(res.getString("before"));

        for (CurrencyManipulator manipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (manipulator.hasMoney()) {
                hasAnyMoney = true;
                ConsoleHelper.writeMessage(manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
            }
        }
        if (!hasAnyMoney) {
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }

    }
}
