package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {

    public final int SIZE = 5;




    private Path logDir;
    private List<LogLine> parsedData;

    public LogParser(Path logDir) {
        this.logDir = logDir;
        parsedData = LogLine.parsedData(logDir);

    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {

        return getUniqueIPs(after, before).size();

    }



    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {

        return parsedData.stream()
                .filter(log -> isOwnDateBetweenDates(after,before,log.date))
                .map(log->log.ip)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return parsedData.stream()
                .filter(log -> isOwnDateBetweenDates(after,before,log.date))
                .filter(log -> log.user.equals(user.trim()))
                .map(log->log.ip).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return parsedData.stream()
                .filter(log -> isOwnDateBetweenDates(after,before,log.date))
                .filter(log->event.equals(log.event))
                .map(log->log.ip)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return parsedData.stream()
                .filter(log -> isOwnDateBetweenDates(after,before,log.date))
                .filter(log->status.equals(log.status))
                .map(log->log.ip)
                .collect(Collectors.toSet());
    }




    private boolean isOwnDateBetweenDates(Date after, Date before, Date logDate) {
            if(after == null && before == null) {
                return true;
            }
            else if(after != null && before != null) {
                return (logDate.getTime() > after.getTime()
                        && logDate.getTime() < before.getTime());
            }
            else if(before != null ){
                return logDate.getTime() < before.getTime();
            }
            else if(after != null){
                return logDate.getTime() > after.getTime();
            }

        return false;
    }






//      <---------------UserQuery methods implementation------------->



    @Override
    public Set<String> getAllUsers() {
        return parsedData.stream().map(log->log.user).collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return (int) parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .map(log->log.ip).distinct().count();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return  (int) parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log -> log.user.equals(user))
                .map(log->log.event)
                .distinct().count();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log -> log.ip.equals(ip))
                .map(log->log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return   parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log -> log.event.equals(Event.LOGIN))
                .map(log->log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log -> log.event.equals(Event.DOWNLOAD_PLUGIN))
                .map(log->log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log -> log.event.equals(Event.WRITE_MESSAGE))
                .map(log->log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log -> log.event.equals(Event.SOLVE_TASK))
                .map(log->log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log -> log.event.equals(Event.SOLVE_TASK) && log.task == task)
                .map(log->log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log -> log.event.equals(Event.DONE_TASK))
                .map(log->log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log -> log.event.equals(Event.DONE_TASK) && log.task == task)
                .map(log->log.user)
                .collect(Collectors.toSet());
    }






    //      <---------------DateQuery methods implementation------------->



    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log->log.user.equals(user) && log.event.equals(event))
                .map(log-> (log.date))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log -> log.status.equals(Status.FAILED))
                .map(log->(log.date))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log -> log.status.equals(Status.ERROR))
                .map(log->(log.date))
                .collect(Collectors.toSet());
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        return parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log->log.user.equals(user) && log.event.equals(Event.LOGIN))
                .min(Comparator.comparing(log->(log.date)))
                .map(log->(log.date)).orElse(null);
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        return parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log->log.user.equals(user)
                        && log.event.equals(Event.SOLVE_TASK) && log.task ==task)
                .min(Comparator.comparing(log->(log.date)))
                .map(log->(log.date)).orElse(null);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
          return parsedData.stream()
                  .filter(log -> isOwnDateBetweenDates(after, before, log.date))
                  .filter(log->log.user.equals(user)
                          && log.event.equals(Event.DONE_TASK) && log.task ==task)
                  .min(Comparator.comparing(log->(log.date)))
                  .map(log->(log.date)).orElse(null);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return parsedData.stream()
                .filter(log->log.user.equals(user))
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log -> log.event.equals(Event.WRITE_MESSAGE))
                .map(log->(log.date))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
         return parsedData.stream()
                 .filter(log->isOwnDateBetweenDates(after,before,log.date))
                 .filter(log->log.user.equals(user))
                 .filter(log -> log.event.equals(Event.DOWNLOAD_PLUGIN))
                 .map(log->(log.date))
                 .collect(Collectors.toSet());
    }




    //      <---------------EventQuery methods implementation------------->



    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return (int) parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .map(log->log.event)
                .distinct().count();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .map(log->log.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log->log.user.equals(ip))
                .map(log->log.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log->log.user.equals(user))
                .map(log->log.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
          return parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log->log.status.equals(Status.FAILED))
                .map(log->log.event)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
         return parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log->log.status.equals(Status.ERROR))
                .map(log->log.event)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return (int) parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log->log.event.equals(Event.SOLVE_TASK))
                .filter(log->log.task == task)
                .count();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
          return (int) parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                  .filter(log->log.event.equals(Event.DONE_TASK))
                  .filter(log->log.task == task)
                .count();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        return parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log->log.event.equals(Event.SOLVE_TASK))
                .collect(Collectors.toMap(log->log.task,log->1,Integer::sum));
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        return parsedData.stream()
                .filter(log->isOwnDateBetweenDates(after,before,log.date))
                .filter(log->log.event.equals(Event.DONE_TASK))
                .collect(Collectors.toMap(log->log.task,log->1,Integer::sum));
    }



    //      <--------------QLQuery method implementation------------->


    @Override
    public Set<Object> execute(String query) {
        Stream<LogLine> stream = parsedData.stream();

        switch (query) {
            case "get ip":
                return stream.map(log -> log.ip).collect(Collectors.toSet());
            case "get user":
                return stream.map(log -> log.user).collect(Collectors.toSet());
            case "get date":
                return stream.map(log -> (log.date)).collect(Collectors.toSet());
            case "get event":
                return stream.map(log -> log.event).collect(Collectors.toSet());
            case "get status":
                return stream.map(log -> log.status).collect(Collectors.toSet());

                default:{
                    String[] fieldsVal = getFieldsAndValue(query);
                    if (fieldsVal.length > 3) {
                        stream = stream.filter(log->isOwnDateBetweenDates(LogLine.toParseDate(fieldsVal[3]),LogLine.toParseDate(fieldsVal[4]),log.date));
                    }

                    return stream
                                .filter(log -> log.fields.get(fieldsVal[1]).equals(fieldsVal[2]))
                                .map(log -> {
                                    if (fieldsVal[0].equals("date")) return log.date;
                                    else if (fieldsVal[0].equals("event")) return log.event;
                                    else if (fieldsVal[0].equals("status")) return log.status;
                                    else return log.fields.get(fieldsVal[0]);
                                })
                                .collect(Collectors.toSet());

            }
        }
    }

    public String[] getFieldsAndValue(String q) {
        String [] data = new String[3];
        String query = q.replaceAll("[\"=]"," ");
        String [] querySplit = query.split(" ");

        int i = 0;
        for (String s : querySplit) {
            if(LogLine.lineData.contains(s.trim())){
                data[i++] = s.trim();
                if(i == 2) {
                    break;
                }
            }
        }
        if(q.length()>55){
            data = Arrays.copyOf(data,5);

            int index = -1;

            while (true){
               int from = q.indexOf("\"",index+1);
               int to = q.indexOf("\"",from+1);
               if(from == -1) break;
               index = to;
               data[i++] = q.substring(from+1,to);
            }
        }else {
            data[2] = q.substring(q.indexOf("\"")+1,q.length()-1);
        }
        return data;
    }
}