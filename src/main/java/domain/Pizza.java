package domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Pizza extends Product {

    private String dough;
    private Date expirationDate;
    private PizzaType type;

    @Builder
    public Pizza(String dough, Date expirationDate, PizzaType type,
                 Long price, Long size, int quantity) {
        this.dough = dough;
        this.expirationDate = expirationDate;
        this.type = type;
        this.price = price;
        this.size = size;
        this.quantity = quantity;
    }
}
