package ru.ogrezem.codeWarsSolution.domain.discordApi.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.managers.AudioManager;

public class PlayCommand extends Command {

    public PlayCommand() {
        this.name = "p";
        this.arguments = "<url>";
        this.help = "say me url of which video sound you want to play";
        this.guildOnly = true;
    }
    @Override
    protected void execute(CommandEvent event) {
        if (event.getAuthor().isBot())
            return;
        Guild guild = event.getGuild();
        AudioManager audioManager = guild.getAudioManager();
    }
}
