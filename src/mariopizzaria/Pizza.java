package mariopizzaria;


import java.util.ArrayList;

public class Pizza {

    private String pizzaName;

    private double pizzaPrice;

    private int pizzaSize;

    private double totalPizzaPrice;

    private ArrayList<ExtraTopping> toppingsAdded;

    private int number;
    
    public Pizza(String pizzaName, double pizzaPrice) {
        this.pizzaName = pizzaName;
        this.pizzaPrice = pizzaPrice;
    }
}
