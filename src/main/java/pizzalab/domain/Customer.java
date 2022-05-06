package pizzalab.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  private String name;
  private String phone;
  private String hashedPassword;
  private String creditCard;
  private List<Address> addresses;

}
