package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {

    private String currencyCode;
    private Map<Integer, Integer> denominations = new TreeMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }

    }

    public int getTotalAmount() {
        return denominations
                .entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey() * entry.getValue())
                .sum();
    }

    public boolean hasMoney() {
        return denominations.size() > 0 && getTotalAmount() > 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }


    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        final int FIND = 0;
        TreeMap<Integer, Integer> copiedMainMapDenominations = new TreeMap<>((x, y) -> y - x);
        copiedMainMapDenominations.putAll(denominations);

        NavigableMap<Integer, Integer> tailMap = new TreeMap<>(copiedMainMapDenominations.tailMap(expectedAmount, true));


        for (Map.Entry<Integer, Integer> entry : tailMap.entrySet()) {

            TreeMap<Integer, Integer> extractedBanknotes = new TreeMap<>();

            int find = expectedAmount;
            Integer key = entry.getKey();
            while (find > FIND) {
                find -= key;

                extractedBanknotes.merge(key, 1, Integer::sum);
                int val = copiedMainMapDenominations.get(key) - 1;
                if (val == 0) copiedMainMapDenominations.remove(key);
                else copiedMainMapDenominations.put(key, val);

                SortedMap<Integer, Integer> map = copiedMainMapDenominations.tailMap(find);
                if (map.size() == 0) break;
                key = map.firstKey();

            }

            if (find == FIND) {
                denominations = copiedMainMapDenominations;
                return extractedBanknotes;
            }

            copiedMainMapDenominations.clear();
            copiedMainMapDenominations.putAll(denominations);
        }

        throw new NotEnoughMoneyException();
    }


}
