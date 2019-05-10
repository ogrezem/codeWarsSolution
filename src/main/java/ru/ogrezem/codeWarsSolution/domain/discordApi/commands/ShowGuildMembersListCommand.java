package ru.ogrezem.codeWarsSolution.domain.discordApi.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.List;

public class ShowGuildMembersListCommand extends Command {

    public ShowGuildMembersListCommand() {
        this.name = "show-members";
        this.help = "shows all members in the server";
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
        List<Member> members = event.getGuild().getMembers();
        for (Member member : members) {
            User memberUser = member.getUser();
            channel.sendMessage(memberUser.getName() + " (" + "id: " + memberUser.getId() + ")").queue();
        }
    }
}
