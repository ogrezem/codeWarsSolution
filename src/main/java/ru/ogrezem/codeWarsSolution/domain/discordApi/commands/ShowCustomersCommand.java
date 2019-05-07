package ru.ogrezem.codeWarsSolution.domain.discordApi.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import ru.ogrezem.codeWarsSolution.domain.jts.Customer;
import ru.ogrezem.codeWarsSolution.domain.jts.CustomerRepository;

import java.util.List;

public class ShowCustomersCommand extends Command {

    private CustomerRepository customerRepository;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ShowCustomersCommand(CustomerRepository customerRepository) {
        this.name = "show";
        this.help = "shows some customers";
        this.arguments = "<args>";
        this.guildOnly = false;
        this.customerRepository = customerRepository;
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
                responseStringBuilder.append(gson.toJson(customer))
                        .append("\n");
            }
            event.reply(responseStringBuilder.toString());
        } else {
            System.out.println("АРГУМЕНТЫ НЕ СОВПАЛИ");
        }
    }
}
