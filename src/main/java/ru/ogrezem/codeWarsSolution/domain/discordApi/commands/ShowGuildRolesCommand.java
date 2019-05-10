package ru.ogrezem.codeWarsSolution.domain.discordApi.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.Iterator;
import java.util.List;

public class ShowGuildRolesCommand extends Command {

    public ShowGuildRolesCommand() {
        this.name = "show-roles";
        this.help = "shows all roles of the server";
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        User author = event.getAuthor();
        if (author.isBot())
            return;
        TextChannel channel = event.getTextChannel();
        if (!author.getId().equals("277457628517629952")) {
            channel.sendMessage("Недостаточно прав").queue();
        }
        for (Role role : event.getGuild().getRoles()) {
            channel.sendMessage("Имя роли: " + role.getName() + "\nПрава:").complete();
            List<Permission> permissions = role.getPermissions();
            for (int i = 0; i < permissions.size(); i++) {
                channel.sendMessage(i + ": " + permissions.get(i).getName()).complete();
            }
        }
    }
}
