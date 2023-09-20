package com.gussoft.customers.core.business;

import com.gussoft.customers.integration.transfer.request.CustomerRequest;
import com.gussoft.customers.integration.transfer.response.CustomerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

  Flux<CustomerResponse> findAllCustomer();

  Mono<CustomerResponse> findById(String id);

  Mono<CustomerResponse> findByDocument(String doc);

  Mono<CustomerResponse> saveCustomer(Mono<CustomerRequest> request);

  Mono<CustomerResponse> updateCustomer(Mono<CustomerRequest> request, String id);

  Mono<Void> deleteCustomer(String id);

}
