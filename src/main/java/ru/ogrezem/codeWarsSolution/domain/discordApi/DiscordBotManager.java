package ru.ogrezem.codeWarsSolution.domain.discordApi;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import org.springframework.stereotype.Service;
import ru.ogrezem.codeWarsSolution.domain.discordApi.commands.HiCommand;
import ru.ogrezem.codeWarsSolution.domain.discordApi.commands.ShowAllCustomersCommand;
import ru.ogrezem.codeWarsSolution.domain.discordApi.listeners.MainListener;

import javax.security.auth.login.LoginException;

@Service
public class DiscordBotManager {

    private static final String TOKEN = "NDExOTc4NDE2NjE1NzE4OTIz.XNBB9w.n5HTkgnxRMyMVN-1eNDD_hZVgQI";
    private boolean isInitialised = false;
    private JDA jdaBuilder;

    public DiscordBotManager() {
        try {
            var commandClient = new CommandClientBuilder()
                    .setPrefix("//@")
                    .setOwnerId("ogrezem#2891")
                    .addCommands(
                            new HiCommand(), new ShowAllCustomersCommand()
                    ).setGame(Game.playing("Казаки: снова война"))
                    .build();
            jdaBuilder = new JDABuilder(AccountType.BOT)
                    .setToken(TOKEN)
                    .addEventListener (
                            new MainListener(), commandClient
                    )
                    .buildAsync();
            isInitialised = true;
        } catch (LoginException e) {
            System.err.println("DISCORD BOT LOGIN ERROR");
            return;
        }


    }
}
