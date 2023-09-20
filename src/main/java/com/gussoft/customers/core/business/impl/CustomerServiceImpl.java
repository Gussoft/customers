package com.gussoft.customers.core.business.impl;

import com.gussoft.customers.core.business.CustomerService;
import com.gussoft.customers.core.repository.CustomerRepository;
import com.gussoft.customers.integration.mappers.CustomerMapper;
import com.gussoft.customers.integration.transfer.request.CustomerRequest;
import com.gussoft.customers.integration.transfer.response.CustomerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository repo;

  @Override
  public Flux<CustomerResponse> findAllCustomer() {
    return repo.findAll().map(CustomerMapper::toCustomerResponse);
  }

  @Override
  public Mono<CustomerResponse> findById(String id) {
    return repo.findById(id).map(CustomerMapper::toCustomerResponse);
  }

  @Override
  public Mono<CustomerResponse> findByDocument(String doc) {
    return repo.findByDocument(doc).map(CustomerMapper::toCustomerResponse);
  }

  @Override
  public Mono<CustomerResponse> saveCustomer(Mono<CustomerRequest> request) {
    return request.map(CustomerMapper::toCustomerRequest)
      .flatMap(repo::save)
      .map(CustomerMapper::toCustomerResponse);
  }

  @Override
  public Mono<CustomerResponse> updateCustomer(Mono<CustomerRequest> customer, String id) {
    return repo.findById(id)
      .flatMap(c -> customer.map(CustomerMapper::toCustomerRequest)
        .doOnNext(e -> {
          e.setId(id);
          log.info(e.toString());
        }))
      .flatMap(repo::save)
      .map(CustomerMapper::toCustomerResponse);
  }

  @Override
  public Mono<Void> deleteCustomer(String id) {
    return repo.deleteById(id);
  }

}
