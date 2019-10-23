package mariopizzaria;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;

public class Order {

    private ArrayList<Pizza> Pizzas;
    private Costumer costumer;
    private LocalDateTime pickupTime;
    private LocalDateTime orderTime;
    private boolean orderByPhone;
    private double totalPrice;
    private int percentDiscount;

    //-------------//
    // CONSTRUCTOR //
    //-------------//
    public Order(LocalDateTime pickupTime, boolean orderByPhone) {
        this.pickupTime = pickupTime;
        this.orderByPhone = orderByPhone;
        orderTime = LocalDateTime.now();
    }

    public Order(boolean orderByPhone) {
        this.orderByPhone = orderByPhone;

        orderTime = LocalDateTime.now();
        pickupTime = LocalDateTime.now().plusMinutes(15);
        

    }

    //---------//
    // GETTERS //
    //---------//
    public Pizza getPizza(int index) {
        return Pizzas.get(index);
    }

    LocalDateTime getOrderTime() {
        return orderTime;
    }
    
    LocalDateTime getPickupTime() {
        return pickupTime;
    }

    //---------//
    // METHODS //
    //---------//
    public void discount() {
    }

    public void addPizza(int menuIndex) {
        String pizzaName = Menu.getPizzaName(menuIndex);
        Double pizzaPrice = Menu.getPizzaPrice(menuIndex);
        Pizzas.add(new Pizza(pizzaName, pizzaPrice));
    }

    public void addExtraTopping(int toppingIndex, int quantity) {
        
    }
}
