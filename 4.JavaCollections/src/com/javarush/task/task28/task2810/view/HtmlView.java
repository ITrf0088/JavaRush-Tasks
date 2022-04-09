package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class HtmlView implements View {

    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";
    private Controller controller;

    @Override
    public void update(List<Vacancy> vacancies) {

        updateFile(getUpdatedFileContent(vacancies));


    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {

        controller.onCitySelect("Kiev");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        try {
            Document document = getDocument();
            
            Element element = document.getElementsByClass("template").clone()
                    .removeAttr("style").removeClass("template").get(0);
            document.getElementsByAttributeValue("class","vacancy").remove();

            for (Vacancy vacancy : vacancies) {
                Element e = element.clone();
                e.getElementsByClass("city").first().text(vacancy.getCity());
                e.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                e.getElementsByClass("salary").first().text(vacancy.getSalary());
                e.getElementsByTag("a").first() .attr("href",vacancy.getUrl()).text(vacancy.getTitle());
                Element template = document.getElementsByClass("template").first();
                template.before(e.outerHtml());
            }


           return document.html();
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
    }

    private void updateFile(String s) {

        try(OutputStream out = new FileOutputStream(filePath);) {
            
            byte[] bytes = s.getBytes();
            out.write(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(Paths.get(filePath).toFile(),"UTF-8");
    }
}
