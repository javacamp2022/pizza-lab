import domain.Product;

// tot singleton
public class DeliveryService {

    private static final PantryRepository pantry = new PantryRepository();

    public void addOrder(Long customerId, Product product,
                         String dough) {
        //pantry.listAvailablePizzas();
        // validate
        // add order

    }
}
