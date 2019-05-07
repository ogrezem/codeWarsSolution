package ru.ogrezem.codeWarsSolution.domain.vkApi;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan ({
        "ru.ogrezem.codeWarsSolution.domain.jts",
        "ru.ogrezem.codeWarsSolution.domain.vkApi",
        "ru.ogrezem.codeWarsSolution.domain.discordApi.commands"
})
public class VkBotServicesConfiguration {


}
