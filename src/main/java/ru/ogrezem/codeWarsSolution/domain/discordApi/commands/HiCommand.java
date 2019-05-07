package ru.ogrezem.codeWarsSolution.domain.discordApi.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class HiCommand extends Command {

    public HiCommand() {
        this.name = "hi";
        this.arguments = "<name> <location>";
        this.help = "say me your name and location";
        this.guildOnly = false;

    }

    @Override
    protected void execute(CommandEvent event) {
        boolean argsPatternMatches = event.getArgs().matches("^[^\\s]+\\s[^\\s]+$");
        System.out.println("Arguments pattern matches: " + argsPatternMatches);
        if (!argsPatternMatches) {
            event.replyError("Ты должен назвать своё имя и местонахождение, пидорасина!");
        } else {
            String[] eventArgs = event.getArgs().split("\\s+");
            event.reply("Здравствуй, " + eventArgs[0] + " из " + eventArgs[1]);
        }

    }
}
