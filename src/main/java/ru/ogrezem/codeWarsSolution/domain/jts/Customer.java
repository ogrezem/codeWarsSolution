package ru.ogrezem.codeWarsSolution.domain.jts;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("customers")
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    @DBRef
    private Company company;

    public Customer() {

    }

    public Customer(String firstName, String lastName, Company company) {
        this.company = company;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Company getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return '{' +
                "\"id\": '" + id + '\'' +
                ", firstName: '" + firstName + '\'' +
                ", lastName: '" + lastName + '\'' +
                ", company: " + company +
                '}';
    }
}
