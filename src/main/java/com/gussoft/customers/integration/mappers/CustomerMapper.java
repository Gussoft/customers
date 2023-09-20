package com.gussoft.customers.integration.mappers;

import com.gussoft.customers.core.models.Customer;
import com.gussoft.customers.core.models.TypeCustomer;
import com.gussoft.customers.integration.transfer.request.CustomerRequest;
import com.gussoft.customers.integration.transfer.response.CustomerResponse;
import com.gussoft.customers.integration.transfer.response.TypeCustomerResponse;
import org.springframework.beans.BeanUtils;

public class CustomerMapper {

  public CustomerMapper() {
  }

  public static Customer toCustomerRequest(CustomerRequest request) {
    Customer customer = new Customer();
    BeanUtils.copyProperties(request, customer);
    if (request.getType() != null) {
      customer.setType(new TypeCustomer(request.getType().getId(), request.getType().getName()));
    }
    return customer;
  }

  public static CustomerResponse toCustomerResponse(Customer customer) {
    CustomerResponse response = new CustomerResponse();
    BeanUtils.copyProperties(customer, response);
    if (customer.getType() != null) {
      response.setType(new TypeCustomerResponse(customer.getType().getId(), customer.getType().getName()));
    }
    return response;
  }

}
