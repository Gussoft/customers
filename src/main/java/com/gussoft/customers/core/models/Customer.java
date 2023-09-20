package com.gussoft.customers.core.models;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Generated
public class Customer {

  @Id
  @NotEmpty
  private String id;

  @NotBlank
  private String name;
  @NotBlank
  private String lastName;
  private String lastName2;
  private String address;
  private String phone;
  private Date createAt;

  private TypeCustomer type;

  @Size(min = 8, max = 11)
  @Indexed(unique = true)
  private String document;

}
