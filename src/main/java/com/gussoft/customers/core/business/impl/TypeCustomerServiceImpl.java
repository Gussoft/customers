package com.gussoft.customers.core.business.impl;

import com.gussoft.customers.core.business.TypeCustomerService;
import com.gussoft.customers.core.repository.TypeCustomerRepository;
import com.gussoft.customers.integration.mappers.TypeCustomerMapper;
import com.gussoft.customers.integration.transfer.request.TypeCustomerRequest;
import com.gussoft.customers.integration.transfer.response.TypeCustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TypeCustomerServiceImpl implements TypeCustomerService {

  @Autowired
  private TypeCustomerRepository repo;

  @Override
  public Flux<TypeCustomerResponse> findAllTypeCustomer() {
    return repo.findAll().map(TypeCustomerMapper::toTypeCustomerResponse);
  }

  @Override
  public Mono<TypeCustomerResponse> findById(String id) {
    return repo.findById(id).map(TypeCustomerMapper::toTypeCustomerResponse);
  }

  @Override
  public Mono<TypeCustomerResponse> findByName(String name) {
    return repo.findByName(name).map(TypeCustomerMapper::toTypeCustomerResponse);
  }

  @Override
  public Mono<TypeCustomerResponse> save(Mono<TypeCustomerRequest> request) {
    return request.map(TypeCustomerMapper::toTypeCustomerRequest)
      .flatMap(repo::save)
      .map(TypeCustomerMapper::toTypeCustomerResponse);
  }

  @Override
  public Mono<Void> delete(String id) {
    return repo.deleteById(id);
  }

}
