package mariopizzaria;


import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Order {

    private ArrayList<Pizza> Pizzas;
    private Costumer costumer;
    private Date pickUpTime;
    private boolean orderByPhone;
    private double totalPrice;
    private int procentDiscount;
    private SimpleDateFormat orderTime;
    
    //-------------//
    // CONSTRUCTOR //
    //-------------//
    public Order(Date pickupTime, boolean orderByPhone) {
        this.pickUpTime = pickupTime;
        this.orderByPhone = orderByPhone;
        this.orderTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }
    public Order() {
        
    }
    
    //---------//
    // GETTERS //
    //---------//

    public Pizza getPizza(int index) {
        return Pizzas.get(index);
    }
    
    
    
    public void discount() {
    }

    public void addPizza(int menuIndex) {
    }

    public void addExtraTopping(int toppingIndex, int quantity) {
    }
}
