package ru.ogrezem.codeWarsSolution.domain.discordApi.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ogrezem.codeWarsSolution.domain.jts.Customer;
import ru.ogrezem.codeWarsSolution.domain.jts.CustomerRepository;

import java.util.List;

@Service
public class ShowCustomersCommand extends Command {

    @Autowired
    public CustomerRepository customerRepository;

    public ShowCustomersCommand() {
        this.name = "show";
        this.help = "shows some customers";
        this.arguments = "<args>";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {
        if (event.getArgs().equals("all")) {
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
        } else {
            System.out.println("АРГУМЕНТЫ НЕ СОВПАЛИ");
        }
    }
}
