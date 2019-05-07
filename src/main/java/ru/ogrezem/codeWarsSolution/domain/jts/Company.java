package ru.ogrezem.codeWarsSolution.domain.jts;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("companies")
public class Company {

    @Id
    private String id;
    private String name;

    public Company() {

    }

    public Company(String name) {
        this.name = name;
    }

    public Company(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return '{' +
                "id: '" + id + '\'' +
                ", name: '" + name + '\'' +
                '}';
    }
}
