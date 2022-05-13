package pizzalab.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

  @Id
  private Long id;

  @ManyToMany
  @JoinTable(
    name = "menu_to_menu_items",
    joinColumns = @JoinColumn(name = "menu_item_id"),
    inverseJoinColumns = @JoinColumn(name = "menu_id"))
  private List<MenuItem> items;
  private Date createdAt;
  private Date validUntil;
  private Date lastUpdatedAt;


}

