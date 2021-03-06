package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.view.View;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Model {
    private View view;
    private Provider[] providers;

    public Model(View view, Provider...providers) {
        if(providers==null ||providers.length == 0 || view ==null)
            throw new IllegalArgumentException();
        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String city){
        view.update(Arrays.stream(providers)
                .flatMap(provider -> provider.getJavaVacancies(city).stream())
                .collect(Collectors.toList()));


    }
}
