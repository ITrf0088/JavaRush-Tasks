package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {

    private static final String URL_FORMAT = "https://hh.ru/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();

        int page = 0;

        while(true) {
            Document document = getDocument(searchString, page++);

            Elements elements = document.getElementsByAttributeValueStarting("data-qa", "vacancy-serp__vacancy vacancy-serp__vacancy_");
            if(elements.html().isEmpty()) break;
            for (Element element : elements) {
                Vacancy v = new Vacancy();

                v.setTitle(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());
                v.setSalary(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text());
                v.setCity(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text());
                v.setCompanyName(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                v.setUrl(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href"));
                v.setSiteName("hh.ru");
                vacancies.add(v);
            }
        }
        
        return vacancies;
    }

    protected Document getDocument(String searchString,int page) {
//        Path path = Paths.get("C:\\Users\\it0088\\IdeaProjects\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task28\\task2810\\hh.html");
//
        try {
//            if(path.toFile().length() < 10) {
                Document document = Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36")
                        .referrer("https://hh.ua")
                        .get();

                //Files.write(path, document.html().getBytes());
                return document;
//            }else {
//                return Jsoup.parse(path.toFile(), Charset.defaultCharset().name());
//            }
//
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
