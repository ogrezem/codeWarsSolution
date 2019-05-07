package ru.ogrezem.codeWarsSolution.domain.discordApi.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ogrezem.codeWarsSolution.domain.jts.Customer;
import ru.ogrezem.codeWarsSolution.domain.jts.CustomerRepository;

import java.util.List;

@Service
public class ShowAllCustomersCommand extends Command {

    @Autowired
    private CustomerRepository customerRepository;

    public ShowAllCustomersCommand() {
        this.name = "show all";
        this.help = "shows all customers";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {
        var responseStringBuilder = new StringBuilder()
                .append("Список всех customer: \n");
        if (customerRepository.count() == 0) {
            event.reply("База customers пуста");
            return;
        }
        List<Customer> customersList = customerRepository.findAll();
        for (Customer customer : customersList) {
            responseStringBuilder.append(customer.toString())
                    .append("\n");
        }
        event.reply(responseStringBuilder.toString());
    }
}
