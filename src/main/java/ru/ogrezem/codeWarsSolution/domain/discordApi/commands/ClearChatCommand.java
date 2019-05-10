package ru.ogrezem.codeWarsSolution.domain.discordApi.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;

public class ClearChatCommand extends Command {

    public ClearChatCommand() {
        this.name = "clear";
        this.help = "clears the chat";
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        if (event.getAuthor().isBot())
            return;
        MessageChannel channel = event.getChannel();
        if (event.getAuthor().getId().equals("277457628517629952")) {
            for (Message message : channel.getIterableHistory().cache(false)) {
                message.delete().queue();
            }
            channel.sendMessage("Чат был успешно очищен").queue();
        } else
            channel.sendMessage("Недостаточно прав").queue();
    }
}
