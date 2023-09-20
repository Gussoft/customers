package com.gussoft.customers.integration.mappers;

import com.gussoft.customers.core.models.TypeCustomer;
import com.gussoft.customers.integration.transfer.request.TypeCustomerRequest;
import com.gussoft.customers.integration.transfer.response.TypeCustomerResponse;
import org.springframework.beans.BeanUtils;

public class TypeCustomerMapper {

  public TypeCustomerMapper() {
  }

  public static TypeCustomer toTypeCustomerRequest(TypeCustomerRequest request) {
    TypeCustomer customer = new TypeCustomer();
    BeanUtils.copyProperties(request, customer);
    return customer;
  }

  public static TypeCustomerResponse toTypeCustomerResponse(TypeCustomer customer) {
    TypeCustomerResponse response = new TypeCustomerResponse();
    BeanUtils.copyProperties(customer, response);
    return response;
  }

}
