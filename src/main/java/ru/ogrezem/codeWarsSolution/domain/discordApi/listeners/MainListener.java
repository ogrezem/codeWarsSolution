package ru.ogrezem.codeWarsSolution.domain.discordApi.listeners;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MainListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equals("hi")) {
            event.getChannel()
                    .sendMessage("Здравствуй!")
                    .queue();
        }
    }
}
