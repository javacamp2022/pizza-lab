package domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Customer {

  private String name;
  private String phone;
  private List<Address> addresses;

}
