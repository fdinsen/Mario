package mariopizzaria;


import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;

public class Order {

    private ArrayList<Pizza> Pizzas;
    private Costumer costumer;
    private LocalDateTime pickUpTime;
    private LocalDateTime orderTime;
    private boolean orderByPhone;
    private double totalPrice;
    private int procentDiscount;

    
    //-------------//
    // CONSTRUCTOR //
    //-------------//
    public Order(LocalDateTime pickupTime, boolean orderByPhone) {
        this.pickUpTime = pickupTime;
        this.orderByPhone = orderByPhone;
        orderTime = LocalDateTime.now();
    }
    public Order() {
        orderTime = LocalDateTime.now();
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
    
    
    public void discount() {
    }

    public void addPizza(int menuIndex) {
    }

    public void addExtraTopping(int toppingIndex, int quantity) {
    }
}
