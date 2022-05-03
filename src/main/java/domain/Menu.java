package domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class Menu {

  private List<MenuItem> items;
  private Date createdAt;
  private Date validUntil;
  private Date lastUpdatedAt;


  @Builder
  @ToString
  public static final class MenuItem implements Comparable {

    private String description;
    private Long price;

    @Override
    public int compareTo(Object o) {
      return description.compareTo(((MenuItem) o).description);
    }
  }


}

