package domain;

import java.util.Arrays;
import java.util.List;

public enum PizzaType {
    MARGARITA("margarita", Arrays.asList("pufos")),
    CAPRICIOSA("integrala", Arrays.asList("pufos", "integral"));

    String name;
    List<String> doughTypes;

    PizzaType(String name, List<String> doughTypes) {
        this.name = name;
        this.doughTypes = doughTypes;
    }
}
