package ru.ogrezem.codeWarsSolution.domain.discordApi;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface StateMachine {

    /**
     * @param event
     * The event this state machine should use as input source
     * @return
     * True if it reaches its final state
     */
    boolean updateState(MessageReceivedEvent event);
}
