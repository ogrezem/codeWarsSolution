package ru.ogrezem.codeWarsSolution.domain.discordApi;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ogrezem.codeWarsSolution.domain.discordApi.commands.HiCommand;
import ru.ogrezem.codeWarsSolution.domain.discordApi.commands.ShowCustomersCommand;
import ru.ogrezem.codeWarsSolution.domain.discordApi.listeners.MessageListener;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;

@Service
public class DiscordBotManager {

    private static final String TOKEN = "NDExOTc4NDE2NjE1NzE4OTIz.XNBB9w.n5HTkgnxRMyMVN-1eNDD_hZVgQI";
    private boolean isInitialised = false;
    private JDA jdaBuilder;
    @Autowired
    private DBAccessor dbAccessor;

    @PostConstruct
    private void init() {
        try {
            var commandClient = new CommandClientBuilder()
                    .setPrefix("//@")
                    .setOwnerId("ogrezem#2891")
                    .addCommands(
                            new HiCommand(), new ShowCustomersCommand(dbAccessor.getCustomerRepository())
                    ).setGame(Game.playing("Казаки: Снова Война"))
                    .build();
            jdaBuilder = new JDABuilder(AccountType.BOT)
                    .setToken(TOKEN)
                    .addEventListener (
                            new MessageListener(), commandClient
                    )
                    .buildAsync();
            isInitialised = true;
        } catch (LoginException e) {
            System.err.println("DISCORD BOT LOGIN ERROR");
            return;
        }


    }
}

//https://discordapp.com/oauth2/authorize?client_id=411978416615718923&scope=bot&identify%20email%20connections%20guilds%20guilds.join%20gdm.join%20webhook.incoming%20rpc.notifications.read%20rpc.api%20rpc%20messages.read%20applications.builds.upload%20applications.builds.read%20applications.entitlements%20applications.store.update&permissions=1341644225&redirect_uri=https%3A%2F%2Fdiscordapp.com%2Fapi%2Foauth2%2Fauthorize
//https://discordapp.com/oauth2/authorize?client_id=411978416615718923&scope=bot&identify%20email%20connections%20guilds%20guilds.join%20gdm.join%20webhook.incoming%20rpc.notifications.read%20rpc.api%20rpc%20messages.read%20applications.builds.upload%20applications.builds.read%20applications.entitlements%20applications.store.update&permissions=2146958847&redirect_uri=https%3A%2F%2Fdiscordapp.com%2Fapi%2Foauth2%2Fauthorize
//https://discordapp.com/oauth2/authorize?client_id=411978416615718923&scope=bot&identify%20email%20connections%20guilds%20guilds.join%20gdm.join%20webhook.incoming%20rpc.notifications.read%20rpc.api%20rpc%20messages.read%20applications.builds.upload%20applications.builds.read%20applications.entitlements%20applications.store.update&permissions=1341644225&redirect_uri=https%3A%2F%2Fdiscordapp.com%2Fapi%2Foauth2%2Fauthorize&response_type=code
//https://discordapp.com/oauth2/authorize?client_id=411978416615718923&scope=bot&identify%20email%20connections%20guilds%20guilds.join%20gdm.join%20webhook.incoming%20rpc.notifications.read%20rpc.api%20rpc%20messages.read%20applications.builds.upload%20applications.builds.read%20applications.store.update%20applications.entitlements&permissions=2146958847&redirect_uri=https%3A%2F%2Fdiscordapp.com%2Fapi%2Foauth2%2Fauthorize
//https://discordapp.com/oauth2/authorize?client_id=411978416615718923&scope=bot&permissions=8&redirect_uri=https%3A%2F%2Fdiscordapp.com%2Fapi%2Foauth2%2Fauthorize
/*

identify%20
email%20
connections%20
guilds%20
guilds.join%20
gdm.join%20
webhook.incoming%20
bot%20
rpc.notifications.read%20
rpc.api%20
rpc%20
messages.read%20
applications.builds.upload%20
applications.builds.read%20
applications.store.update%20
applications.entitlements

*/