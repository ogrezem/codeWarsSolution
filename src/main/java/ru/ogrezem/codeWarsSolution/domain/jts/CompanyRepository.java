package ru.ogrezem.codeWarsSolution.domain.jts;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CompanyRepository extends MongoRepository<Company, String> {

    Optional<Company> findByName(String name);
}
