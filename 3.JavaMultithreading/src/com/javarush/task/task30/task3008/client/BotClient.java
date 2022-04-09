package com.javarush.task.task30.task3008.client;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BotClient extends Client {
    @Override
    protected String getUserName() {
        return "date_bot_"+(int) (Math.random()*100);
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    public class BotSocketThread extends SocketThread{
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {

            System.out.println(message);
            String[] parse = message.split(": ");
            if(message==null||parse.length!=2)return;
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat ;

            switch (parse[1]){
                case "дата":dateFormat = new SimpleDateFormat("d.MM.YYYY");break;
                case "день":dateFormat = new SimpleDateFormat("d");break;
                case "месяц":dateFormat = new SimpleDateFormat("MMMM");break;
                case "год":dateFormat = new SimpleDateFormat("YYYY");break;
                case "время":dateFormat = new SimpleDateFormat("H:mm:ss");break;
                case "час":dateFormat = new SimpleDateFormat("H");break;
                case "минуты":dateFormat = new SimpleDateFormat("m");break;
                case "секунды":dateFormat = new SimpleDateFormat("s");break;
                default:
                    return;
            }

            sendTextMessage("Информация для "+parse[0]+": "+dateFormat.format(calendar.getTime()));
        }
    }

    public static void main(String[] args) {
        new BotClient().run();
    }
}
