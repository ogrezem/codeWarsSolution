package ru.ogrezem.codeWarsSolution.domain.jts;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// mongodb api private key: f2210cd4-9dbc-437b-8208-0c9956603b62
// public key: wvdatcek

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    List<Customer> findByFirstName(String firstName);
}
