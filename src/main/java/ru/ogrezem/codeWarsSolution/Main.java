package ru.ogrezem.codeWarsSolution;

import com.google.gson.Gson;
import ru.ogrezem.codeWarsSolution.domain.User;
import ru.ogrezem.codeWarsSolution.domain.vkApi.VKConnection;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        var gson = new Gson();
        var user1 = new User("nigosh", "nigosh@mail.ru", "gogs");
//        int sleepTime = (int) (2000 + Math.random() * 5000);
//        System.out.println("sleepTime: " + sleepTime);
//        Thread.sleep(sleepTime);
        var user2 = new User("goga", "goga@gmail.com", "gogog");

        var vkConnection = new VKConnection();

    }
}
