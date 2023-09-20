package com.gussoft.customers.integration.expose;

import com.gussoft.customers.core.business.CustomerService;
import com.gussoft.customers.integration.transfer.request.CustomerRequest;
import com.gussoft.customers.integration.transfer.response.CustomerResponse;
import java.net.URI;
import java.util.Date;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class CustomerController {

  @Autowired
  private CustomerService service;

  @GetMapping("/customers")
  public Mono<ResponseEntity<Flux<CustomerResponse>>> findAllCustomer() {
    return Mono.just(
      ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(service.findAllCustomer()));
  }

  @GetMapping("/customers/{id}")
  public Mono<ResponseEntity<CustomerResponse>> findById(@PathVariable String id) {
    return service.findById(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping(path = "/customers")
  public Mono<ResponseEntity<CustomerResponse>> create(@RequestBody Mono<CustomerRequest> request) {
    return request.flatMap(customer -> {
      if (customer.getCreateAt() == null) {
        customer.setCreateAt(new Date());
      }
      return service.saveCustomer(Mono.just(customer)).map(cus -> ResponseEntity
        .created(URI.create("/api/v1/customers/".concat(cus.getId()))).body(cus));
    }).onErrorResume(throwable -> {
      return Mono.just(throwable).cast(WebExchangeBindException.class)
        .flatMap(e -> Mono.just(e.getFieldErrors()))
        .flatMapMany(Flux::fromIterable)
        .map(field -> "El campo ".concat(field.getField()).concat(" ").concat(
          Objects.requireNonNull(field.getDefaultMessage())))
        .collectList()
        .flatMap(list -> {
          log.error(list.toString());
          return Mono.just(ResponseEntity.badRequest().build());
        });
    });
  }

  @PutMapping("/customers/{id}")
  public Mono<ResponseEntity<CustomerResponse>> edit(@PathVariable String id,
                                                     @RequestBody Mono<CustomerRequest> request) {
    return service.updateCustomer(request, id)
      .map(ResponseEntity::ok)
      .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/customers/{id}")
  public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
    return service.deleteCustomer(id).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
  }

}
