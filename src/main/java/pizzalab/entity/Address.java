package pizzalab.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "address_key_sequence_generator")
    @SequenceGenerator(name = "address_key_sequence_generator", sequenceName = "address_sequence", allocationSize = 1)
    private Long id;

    @ManyToMany(mappedBy = "addresses")
    private List<Customer> customer;

    String street;
}
