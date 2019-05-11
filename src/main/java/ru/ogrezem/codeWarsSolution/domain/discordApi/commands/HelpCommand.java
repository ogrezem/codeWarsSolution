package ru.ogrezem.codeWarsSolution.domain.discordApi.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.List;

public class HelpCommand extends Command {

    private String helpCommandResponse;

    public HelpCommand(List<Command> commands, String prefix) {
        var commandResponseBuilder = new StringBuilder();
        for (Command command : commands) {
            commandResponseBuilder.append(prefix)
                    .append(command.getName()).append(" - ")
                    .append(command.getHelp()).append("\n");
        }
        helpCommandResponse = commandResponseBuilder.toString();
        name = "help";
        guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        User author = event.getAuthor();
        if (author.isBot())
            return;
        TextChannel channel = event.getTextChannel();
        if (!author.getId().equals("277457628517629952")) {
            channel.sendMessage("Недостаточно прав").queue();
            return;
        }
        channel.sendMessage(helpCommandResponse).queue();
    }
}
