package ru.ogrezem.codeWarsSolution.domain.discordApi.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class StartMafiaGameCommand extends Command {

    public StartMafiaGameCommand() {
        this.name = "mafia";
        this.help = "starts mafia game";
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

    }
}
