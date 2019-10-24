package mariopizzaria;

/*

    @Author Simon Kjems Jørgensen
*/
import java.util.ArrayList;

public class Pizza {

    private String pizzaName;

    private double pizzaPrice;

    private int pizzaSize;

    private double totalPizzaPrice;

    private ArrayList<ExtraTopping> listOfToppingsAdded;

    private int pizzaNumber;
    
    public Pizza(String pizzaName, double pizzaPrice, int pizzaSize) {
        this.pizzaName = pizzaName;
        this.pizzaPrice = pizzaPrice;
        this.pizzaSize = pizzaSize;
        this.pizzaNumber = pizzaNumber;
        
        listOfToppingsAdded = new ArrayList<>();  
    }
    public void addExtraTopping(ExtraTopping topping){
        listOfToppingsAdded.add(topping);
        
        totalPizzaPrice += topping.getExtraToppingPrice() * topping.getExtraToppingQuantity();
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public int getPizzaSize() {
        return pizzaSize;
    }

    public double getTotalPizzaPrice() {
        return totalPizzaPrice;
    }

    public ArrayList<ExtraTopping> getToppingsAdded() {
        return listOfToppingsAdded;
    }

    public int getPizzaNumber() {
        return pizzaNumber;
    }
    
}
