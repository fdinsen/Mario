package mariopizzaria;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;

/**
 *
 * @author <Frederik Keis Dinsen>
 */
public class Order {

    private ArrayList<Pizza> pizzas;
    private Costumer costumer;
    private LocalDateTime pickupTime;
    private LocalDateTime orderTime;
    private boolean orderByPhone;
    private double totalPrice;
    private int percentDiscount;
    private Menu menu;

    //-------------//
    // CONSTRUCTOR //
    //-------------//
    public Order(LocalDateTime pickupTime, boolean orderByPhone) {
        this.pickupTime = pickupTime;
        this.orderByPhone = orderByPhone;
        pizzas = new ArrayList<Pizza>();
        costumer = new Costumer();
        
        orderTime = LocalDateTime.now();
        
        totalPrice = 0;
        
        //menu = new Menu();
    }

    public Order(boolean orderByPhone) {
        this.orderByPhone = orderByPhone;
        pizzas = new ArrayList<Pizza>();
        costumer = new Costumer();

        orderTime = LocalDateTime.now();
        pickupTime = LocalDateTime.now().plusMinutes(15);
        
        totalPrice = 0;
        
        //menu = new Menu();
    }

    //---------//
    // GETTERS //
    //---------//
//    public Pizza getPizza(int index) {
//        return pizzas.get(index);
//    }
    LocalDateTime getOrderTime() {
        return orderTime;
    }
    LocalDateTime getPickupTime() {
        return pickupTime;
    }
    public boolean isOrderedByPhone() {
        return orderByPhone;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public Pizza getPizzaAt(int index) {
        index = Math.abs(index);
        return pizzas.get(index);
    }
    public int getOrderSize() {
        return pizzas.size();
    }
    
    public String getCostumerName(){
       return costumer.getCostumerName();
    }
    public int getCostumerPhoneNumber(){
       return costumer.getCostumerPhoneNumber();
    }
    public int getNumberOfPizzas(){
       return pizzas.size();
    }
    

    
    //---------//
    // SETTERS //
    //---------//
    public void setCostumerName(String name) {
        costumer.setCostumerName(name);
    }
    public void setCostumerPhoneNumber(int phone) {
        costumer.setCostumerPhoneNumber(phone);
    }

    //---------//
    // METHODS //
    //---------//
    public void discount(int percentToDiscount) {
        percentToDiscount = Math.abs(percentToDiscount);
        
        double percent = 1.0 - (percentToDiscount / 100.0);
        totalPrice *= percent; 
    }

    public void addPizza(int menuIndex) {
        menuIndex = Math.abs(menuIndex);
        //Informationen om pizzaen hentes fra menu-kortet
        String pizzaName = Menu.getPizzaName(menuIndex);
        Double pizzaPrice = Menu.getPizzaPrice(menuIndex);
        int pizzaSize = 0; //Default værdi
        
        pizzas.add(new Pizza(menuIndex, pizzaSize));
        
        calculateTotalPrice();
    }
    public void addPizza(int menuIndex, int pizzaSize) {
        menuIndex = Math.abs(menuIndex);
        pizzaSize = Math.abs(pizzaSize);
        
        pizzas.add(new Pizza(menuIndex, pizzaSize));
        
        calculateTotalPrice();
    }

    public void addExtraTopping(int itemNumber, int toppingIndex, int quantity) {
        itemNumber = Math.abs(itemNumber);
        toppingIndex = Math.abs(toppingIndex);
        quantity = Math.abs(quantity);
        
        pizzas.get(itemNumber).addExtraTopping(new ExtraTopping(toppingIndex, quantity));
        
        calculateTotalPrice();
    }
    
    private void calculateTotalPrice() {
        totalPrice = 0;
        for(Pizza pizza : pizzas) {
            totalPrice += pizza.getTotalPizzaPrice();
        }
    }
    
    public void removePizzaFromOrder(int index) {
        index = Math.abs(index);
        
        pizzas.remove(index);
        calculateTotalPrice();
    }

    @Override
    public String toString() {
        int counter = 1;
        String stringPizzas = "";
        for (Pizza pizza : pizzas) {
            stringPizzas += counter + ". " + pizza.getPizzaName() + "\n";
            counter++;
            //Tjekker om der er toppings på pizzaewn
                    if (!pizza.getToppingsAdded().isEmpty()) {
                    //Udskriver ekstra toppings på pizzaen
                    stringPizzas += "Ekstra Toppings";
                    for (ExtraTopping extraTopping : pizza.getToppingsAdded()) {
                        //For hver topping indsæt antal og navn i stringen
                        stringPizzas += "x" + extraTopping.getExtraToppingQuantity();
                        stringPizzas += " " + extraTopping.getExtraToppingName();

                        //Hvis der er mere end en topping, tilføj komma
                        if (pizza.getToppingsAdded().size() > 1) {
                            stringPizzas += ", ";
                        }
                }
                stringPizzas = "\n";
            }
        }
        return stringPizzas;
    }
    
}
