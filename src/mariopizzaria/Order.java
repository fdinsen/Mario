package mariopizzaria;

import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 *
 * @author <Frederik Keis Dinsen>
 */
public class Order {

    //--------------------//
    // INSTANCE VARIABLES //
    //--------------------//
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
        pizzas = new ArrayList<Pizza>();
        costumer = new Costumer();

        orderTime = LocalDateTime.now();

        totalPrice = 0;

        //menu = new Menu();
    }

    Order(boolean orderByPhone) {
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
    boolean isOrderedByPhone() {
        return orderByPhone;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    Pizza getPizzaAt(int index) {
        index = Math.abs(index);
        return pizzas.get(index);
    }

    public int getOrderSize() {
        return pizzas.size();
    }

    String getCostumerName() {
        return costumer.getCostumerName();
    }

    int getCostumerPhoneNumber() {
        return costumer.getCostumerPhoneNumber();
    }

    int getNumberOfPizzas() {
        return pizzas.size();
    }

    public String getPickupTimeHour() {
        return "" + pickupTime.getHour();
    }

    public String getPickupTimeMinute() {
        return "" + pickupTime.getMinute();
    }

    String getOrderTimeHour() {
        return "" + orderTime.getHour();
    }

    String getOrderTimeMinute() {
        return "" + orderTime.getMinute();
    }

    ArrayList<Pizza> getAllPizzasInOrder() {
        return pizzas;
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
        //menuIndex -=1;
        int pizzaSize = 0; //Default værdi

        pizzas.add(new Pizza(menuIndex, pizzaSize));

        calculateTotalPrice();
    }

    public void addPizza(int menuIndex, int pizzaSize) {
        menuIndex = Math.abs(menuIndex);
        pizzaSize = Math.abs(pizzaSize);
        //menuIndex -= 1;

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
        for (Pizza pizza : pizzas) {
            totalPrice += pizza.getTotalPizzaPrice();
        }
    }

    void removePizzaFromOrder(int index) {
        index = Math.abs(index);

        pizzas.remove(index);
        calculateTotalPrice();
    }
}
