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

    //-------------//
    // CONSTRUCTOR //
    //-------------//
    public Order(LocalDateTime pickupTime, boolean orderByPhone) {
        this.pickupTime = pickupTime;
        this.orderByPhone = orderByPhone;
        orderTime = LocalDateTime.now();
        totalPrice = 0;
    }

    public Order(boolean orderByPhone) {
        this.orderByPhone = orderByPhone;

        orderTime = LocalDateTime.now();
        pickupTime = LocalDateTime.now().plusMinutes(15);
        
        totalPrice = 0;
    }

    //---------//
    // GETTERS //
    //---------//
    public Pizza getPizza(int index) {
        return pizzas.get(index);
    }

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

    //---------//
    // METHODS //
    //---------//
    public void discount(int percentToDiscount) {
        double percent = 1.0 - (percentToDiscount / 100.0);
        totalPrice *= percent; 
    }

    public void addPizza(int menuIndex) {
        //String pizzaName = Menu.getPizzaName(menuIndex);
        //Double pizzaPrice = Menu.getPizzaPrice(menuIndex);
        //pizzas.add(new Pizza(pizzaName, pizzaPrice));
        
        calculateTotalPrice();
    }

    public void addExtraTopping(int itemNumber, int toppingIndex, int quantity) {
        //pizzas.get(itemNumber).addExtraTopping(toppingIndex, quantity);
        
        calculateTotalPrice();
    }
    
    public void calculateTotalPrice() {
        totalPrice = 0;
        for(Pizza pizza : pizzas) {
            //totalPrice += pizza.getTotalPrice();
        }
    }
    
    
    //--------//
    //  TEMP  //
    //--------//
    public void setTotalPrice(double price) {
        totalPrice = price;
    }
}
