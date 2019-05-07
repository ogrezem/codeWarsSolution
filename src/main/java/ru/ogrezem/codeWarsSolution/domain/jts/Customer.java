package ru.ogrezem.codeWarsSolution.domain.jts;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("customers")
public class Customer {

    @Id
    private String id;
    @DBRef
    private Company company;
    private String firstName;
    private String lastName;

    public Customer() {

    }

    public Customer(Company company, String firstName, String lastName) {
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
                ", company: " + company +
                ", firstName: '" + firstName + '\'' +
                ", lastName: '" + lastName + '\'' +
                '}';
    }
}
