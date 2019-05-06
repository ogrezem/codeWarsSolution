package ru.ogrezem.codeWarsSolution.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ogrezem.codeWarsSolution.domain.jts.Customer;
import ru.ogrezem.codeWarsSolution.domain.jts.CustomerRepository;
import ru.ogrezem.codeWarsSolution.domain.jts.TestAnswer;

import java.util.List;

@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    public CustomerRepository customerRepository;

    @RequestMapping("/test")
    public TestAnswer testAnswer(@RequestParam(name = "name", defaultValue = "World") String name) {
        return new TestAnswer(1, "Hello, " + name);
    }

    @RequestMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @RequestMapping("/customers/{firstName}")
    public List<Customer> getCustomersByFirstName(@PathVariable String firstName) {
        return customerRepository.findByFirstName(firstName);
    }
}
