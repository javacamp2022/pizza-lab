public class Main {

  public static void main(String[] args) {

    System.out.println("Welcome to Pizza Delivery Service");

    PantryRepository pantryRepository = new PantryRepository();

    pantryRepository.listAvailablePizzas();

    //pantryRepository.addPizza();
  }

}
