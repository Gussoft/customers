package com.gussoft.customers.core.business;

import com.gussoft.customers.integration.transfer.request.TypeCustomerRequest;
import com.gussoft.customers.integration.transfer.response.TypeCustomerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TypeCustomerService {

  Flux<TypeCustomerResponse> findAllTypeCustomer();

  Mono<TypeCustomerResponse> findById(String id);

  Mono<TypeCustomerResponse> findByName(String name);

  Mono<TypeCustomerResponse> save(Mono<TypeCustomerRequest> request);

  Mono<Void> delete(String id);

}
