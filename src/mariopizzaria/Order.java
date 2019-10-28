package mariopizzaria;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public String getCostumerName() {
        return costumer.getCostumerName();
    }

    public int getCostumerPhoneNumber() {
        return costumer.getCostumerPhoneNumber();
    }

    public int getNumberOfPizzas() {
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
        menuIndex -=1;
        int pizzaSize = 0; //Default værdi

        pizzas.add(new Pizza(menuIndex, pizzaSize));

        calculateTotalPrice();
    }

    public void addPizza(int menuIndex, int pizzaSize) {
        menuIndex = Math.abs(menuIndex);
        pizzaSize = Math.abs(pizzaSize);
        menuIndex -= 1;

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

    public void removePizzaFromOrder(int index) {
        index = Math.abs(index);

        pizzas.remove(index);
        calculateTotalPrice();
    }

    @Override
    public String toString() {
        int counter = 1;
        String stringOrder = "";
        stringOrder += "Bestilingstidspunkt: " + getOrderTime().getHour() + ":" + getOrderTime().getMinute()
                + "\n";
        //Tjek om navn og telefon nr er tilstede og tilføj det til stringen hvis det er
        if (getCostumerName() != null) {
            stringOrder += "Kunde Navn: " + getCostumerName() + "\n";
        }
        if (isOrderedByPhone()) {
            stringOrder += "Kunde Tlf: " + getCostumerPhoneNumber() + "\n";
        }
            stringOrder += "-----\n";
        for (Pizza pizza : pizzas) {
            stringOrder += counter + ". " + pizza.getPizzaName() + " -- " + pizza.getPizzaSizeString();
            
            counter++;

            //Udskriver prisen for pizzaen
            stringOrder += "\t" + pizza.getPizzaPrice() + " kr.";

            //Tjekker om der er toppings på pizzaewn
            if (!pizza.getToppingsAdded().isEmpty()) {
                //Udskriver prisen for pizzaen
                stringOrder += "\nEkstra Toppings";
                for (ExtraTopping extraTopping : pizza.getToppingsAdded()) {
                    //For hver topping indsæt antal og navn i stringen
                    stringOrder += "x" + extraTopping.getExtraToppingQuantity();
                    stringOrder += " " + extraTopping.getExtraToppingName();

                    //Hvis der er mere end en topping, tilføj komma
                    if (pizza.getToppingsAdded().size() > 1) {
                        stringOrder += ", ";
                    }
                }

            }
            stringOrder += "\n-----\n";
        }
       stringOrder += "Afhentnings tidspunkt: " + getPickupTime().getHour() + ":"
                + getPickupTime().getMinute() + " "
                + "\nTotal Pris: " + getTotalPrice();
        return stringOrder;
    }

}
