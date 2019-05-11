package ru.ogrezem.codeWarsSolution.domain.discordApi.listeners;

import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import ru.ogrezem.codeWarsSolution.domain.discordApi.gamesManagement.mafia.MafiaStateMachine;

import java.util.HashMap;
import java.util.Map;

public class MafiaGameMessageListener extends ListenerAdapter {

    private final Map<Long, MafiaStateMachine> stateMachinesMap = new HashMap<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User author = event.getAuthor();
        if (author.isBot()) {
            return;
        }

    }
}
