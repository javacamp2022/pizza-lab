package pizzalab.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Builder
@ToString
@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class MenuItem implements Comparable {

  @Id
  private Long id;

  private String description;
  private Long price;

  @ManyToMany
  @JoinTable(
    name = "menu_to_menu_items",
    joinColumns = @JoinColumn(name = "menu_id"),
    inverseJoinColumns = @JoinColumn(name = "menu_item_id"))
  private List<Menu> menus;

  @Override
  public int compareTo(Object o) {
    return description.compareTo(((MenuItem) o).description);
  }
}
