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
        
        orderTime = LocalDateTime.now();
        
        totalPrice = 0;
        
        menu = new Menu();
    }

    public Order(boolean orderByPhone) {
        this.orderByPhone = orderByPhone;
        pizzas = new ArrayList<Pizza>();

        orderTime = LocalDateTime.now();
        pickupTime = LocalDateTime.now().plusMinutes(15);
        
        totalPrice = 0;
        
        menu = new Menu();
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
    public Pizza getPizzaAt(int index) {
        return pizzas.get(index);
    }
    public int getOrderSize() {
        return pizzas.size();
    }
    

    //---------//
    // METHODS //
    //---------//
    public void discount(int percentToDiscount) {
        double percent = 1.0 - (percentToDiscount / 100.0);
        totalPrice *= percent; 
    }

    public void addPizza(int menuIndex) {
        //Informationen om pizzaen hentes fra menu-kortet
        String pizzaName = menu.getPizzaName(menuIndex);
        Double pizzaPrice = menu.getPizzaPrice(menuIndex);
        int pizzaSize = 0; //Default værdi
        
        pizzas.add(new Pizza(pizzaName, pizzaPrice, pizzaSize));
        
        calculateTotalPrice();
    }
    public void addPizza(int menuIndex, int pizzaSize) {
        //Informationen om pizzaen hentes fra menu-kortet
        String pizzaName = menu.getPizzaName(menuIndex);
        Double pizzaPrice = menu.getPizzaPrice(menuIndex);
        pizzas.add(new Pizza(pizzaName, pizzaPrice, pizzaSize));
        
        calculateTotalPrice();
    }

    public void addExtraTopping(int itemNumber, int toppingIndex, int quantity) {
        //pizzas.get(itemNumber).addExtraTopping(toppingIndex, quantity);
        
        calculateTotalPrice();
    }
    
    private void calculateTotalPrice() {
        totalPrice = 0;
        for(Pizza pizza : pizzas) {
            //totalPrice += pizza.getTotalPrice();
        }
    }
    
    public void removePizzaFromOrder(int index) {
        pizzas.remove(index);
        calculateTotalPrice();
    }
    
    
    //--------//
    //  TEMP  //
    //--------//
    public void setTotalPrice(double price) {
        totalPrice = price;
    }
}
