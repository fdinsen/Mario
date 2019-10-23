package mariopizzaria;


import java.util.ArrayList;
import java.util.Date;

public class Order {

    private ArrayList<Pizza> Pizzas;
    private Costumer costumer;
    private Date pickUpTime;
    private boolean orderByPhone;
    private Date orderTime;
    private double totalPrice;
    private int procentDiscount;

    
    //-------------//
    // CONSTRUCTOR //
    //-------------//
    public Order(Date pickupTime, boolean orderByPhone, Date orderTime) {
        this.pickUpTime = pickupTime;
        this.orderByPhone = orderByPhone;
        this.orderTime = orderTime;
        
    }
    
    public void discount() {
    }

    public void addPizza(int menuIndex) {
    }

    public void addExtraTopping(int toppingIndex, int quantity) {
    }
}
