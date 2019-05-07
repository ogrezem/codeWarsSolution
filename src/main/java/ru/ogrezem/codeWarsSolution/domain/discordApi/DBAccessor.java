package ru.ogrezem.codeWarsSolution.domain.discordApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ogrezem.codeWarsSolution.domain.jts.CustomerRepository;

@Service
public class DBAccessor {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }
}
