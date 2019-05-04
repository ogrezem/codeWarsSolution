package ru.ogrezem.codeWarsSolution;

import ru.ogrezem.codeWarsSolution.domain.vkApi.VkBotManager;
import ru.ogrezem.codeWarsSolution.other.BirthDay;
import ru.ogrezem.codeWarsSolution.other.DayOfWeek;
import ru.ogrezem.codeWarsSolution.other.Person;
import ru.ogrezem.codeWarsSolution.other.Sex;

public class Main {

    public static void main(String[] args) {
        VkBotManager botManager = VkBotManager.getInstance();
        botManager.runBot();
    }
}
