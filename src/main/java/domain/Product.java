package domain;

import lombok.Getter;

@Getter
public abstract class Product {

  protected int quantity = 0;
  protected long price = -1;

  public abstract String getDescription();
}
