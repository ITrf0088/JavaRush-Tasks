package com.javarush.task.task39.task3913;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogLine {

    static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    static List<String> lineData = Arrays.asList("ip","user","date","event","status");
    public HashMap<String,String> fields = new HashMap<>();


    String ip;
    String user;
    Date date;
    String dateAsString;
    Event event;
    int task;
    Status status;

    public LogLine(String ip, String user, String date, Event event, int task, Status status) {
        this.ip = ip;
        fields.put("ip",ip);

        this.user = user;
        fields.put("user",user);

        this.date = toParseDate(date);
        this.dateAsString = date;
        fields.put("date",dateAsString);

        this.event = event;
        fields.put("event",event.toString());

        this.task = task;

        this.status = status;
        fields.put("status",status.toString());

    }


    public static Date toParseDate(String date){
        try {
            return format.parse(date);
        } catch (ParseException e) {
            return null;
        }

    }

    public static List<LogLine> parsedData(Path logDir) {
        List<LogLine> parsedData = new ArrayList<>();

        try {
            parsedData = Files.list(logDir)
                    .filter(path -> path.toString().endsWith(".log"))
                    .flatMap(LogLine::getFileDataAsStream)
                    .map(line -> getLogLine(line.split("\t")))
                    .collect(Collectors.toList());


        } catch (IOException e) { e.printStackTrace(); }


        return parsedData;
    }

    public static LogLine getLogLine(String[] line){
        String ip = line[0].trim();
        String user = line[1].trim();
        String dateAsString = line[2];
        Event event = Event.valueOf(line[3].split(" ")[0]);
        int task = (line[3].split(" ").length > 1) ? Integer.parseInt(line[3].split(" ")[1]) :0;
        Status status = Status.valueOf(line[4]);

        return new LogLine(ip,user,dateAsString,event,task,status);

    }

    public static Stream<String> getFileDataAsStream(Path path){
        try {
            return Files.lines(path);
        } catch (IOException e) {
            throw new RuntimeException(e.getCause());
        }
    }

}
