package com.gussoft.customers.core.repository;

import com.gussoft.customers.core.models.TypeCustomer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TypeCustomerRepository extends ReactiveMongoRepository<TypeCustomer, String> {

  Mono<TypeCustomer> findByName(String name);

}
