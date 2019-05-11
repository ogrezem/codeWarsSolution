package ru.ogrezem.codeWarsSolution.domain.discordApi.gamesManagement.mafia;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import ru.ogrezem.codeWarsSolution.domain.discordApi.StateMachine;

public class MafiaStateMachine implements StateMachine {

    private int state = 0;

    MafiaStateMachine(MessageChannel channel) {
        channel.sendMessage("Начинается игра \"мафия\"").queue();
    }

    @Override
    public boolean updateState(MessageReceivedEvent event) {

        return false;
    }
}
