package ru.ogrezem.codeWarsSolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.ogrezem.codeWarsSolution.domain.discordApi.DiscordBotManager;
import ru.ogrezem.codeWarsSolution.domain.vkApi.VkBotManager;
import ru.ogrezem.codeWarsSolution.domain.vkApi.VkBotServicesConfiguration;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context2 = SpringApplication.run(Main.class, args);
//        var context1 = new AnnotationConfigApplicationContext(VkBotServicesConfiguration.class);
//        context1.setParent(context1);
        VkBotManager vkBotManager = context2.getBean(VkBotManager.class);
        DiscordBotManager discordBotManager = context2.getBean(DiscordBotManager.class);
        vkBotManager.runBot();
    }
}
