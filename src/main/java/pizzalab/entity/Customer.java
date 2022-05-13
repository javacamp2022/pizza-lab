package pizzalab.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "customer_key_sequence_generator")
  @SequenceGenerator(name = "customer_key_sequence_generator", sequenceName = "customer_sequence", allocationSize = 1)
  private Long id;

  private String name;
  private String phone;
  private String hashedPassword;
  private String creditCard;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
    name = "customer_to_address",
    joinColumns = @JoinColumn(name = "customer_id"),
    inverseJoinColumns = @JoinColumn(name = "address_id"))
  private List<Address> addresses;

}
