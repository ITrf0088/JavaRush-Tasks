package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.HHStrategy;
import com.javarush.task.task28.task2810.model.HabrCareerStrategy;
import com.javarush.task.task28.task2810.model.Model;
import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.view.HtmlView;


public class Aggregator {
    public static void main(String[] args) {

        Provider provider = new Provider(new HabrCareerStrategy());
        Provider provider1 = new Provider(new HHStrategy());

        HtmlView view = new HtmlView();
        Model model = new Model(view,provider,provider1);
        Controller controller = new Controller(model);
        view.setController(controller);
        view.userCitySelectEmulationMethod();

    }
}
