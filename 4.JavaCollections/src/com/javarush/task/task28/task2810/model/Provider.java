package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Provider {
    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }


    public List<Vacancy> getJavaVacancies(String searchString){

            return strategy.getVacancies(searchString);

    }
}
