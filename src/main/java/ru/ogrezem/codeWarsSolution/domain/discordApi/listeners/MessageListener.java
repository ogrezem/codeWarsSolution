package ru.ogrezem.codeWarsSolution.domain.discordApi.listeners;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        switch (channel.getType()) {
            case TEXT:
                channel.sendMessage(gson.toJson(event.getGuild()))
                        .queue();
                break;
            case PRIVATE:
                channel.sendMessage(gson.toJson(event.getAuthor()))
                        .queue();
                break;
        }
    }
}
