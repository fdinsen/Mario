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
        
        if(pizzaSize>3 || pizzaSize < 0){
            this.pizzaSize = 0;
        }else{
            this.pizzaSize = pizzaSize;
        }
        
        this.totalPizzaPrice = pizzaPrice;
        listOfToppingsAdded = new ArrayList<>();  
    }
    public void addExtraTopping(ExtraTopping topping){
        
        if(listOfToppingsAdded.contains(topping)){
            int index = listOfToppingsAdded.indexOf(topping);
            listOfToppingsAdded.get(index).addExtraTopping(topping.getExtraToppingQuantity());
        }
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
