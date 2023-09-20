package com.gussoft.customers.core.repository;

import com.gussoft.customers.core.models.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

  Mono<Customer> findByName(String name);

  Mono<Customer> findByDocument(String document);

}
