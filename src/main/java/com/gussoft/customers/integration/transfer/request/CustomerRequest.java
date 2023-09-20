package com.gussoft.customers.integration.transfer.request;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {

  private String name;
  private String lastName;
  private String lastName2;
  private String address;
  private String phone;
  private Date createAt;
  private TypeCustomerRequest type;
  private String document;

}
