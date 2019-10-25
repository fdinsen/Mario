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
    
    public Pizza(int menuIndex, int pizzaSize) {
        //Informationen om pizzaen hentes fra menu-kortet
        pizzaName = Menu.getPizzaName(menuIndex);
        pizzaPrice = Menu.getPizzaPrice(menuIndex);
        this.pizzaNumber = menuIndex;
        
        if(pizzaSize>=3 || pizzaSize < 0){
            this.pizzaSize = 0;
        }else{
            this.pizzaSize = pizzaSize;
        }
        switch (pizzaSize){
            case 1:
                this.pizzaPrice *= 1.85;
                break;
            case 2: 
                this.pizzaPrice *= 1.15;
                break;
            default:
                break;
        }
        this.totalPizzaPrice = pizzaPrice;
        listOfToppingsAdded = new ArrayList<>();  
    }
    public void addExtraTopping(ExtraTopping topping){
        
            for(ExtraTopping toppingAdded : listOfToppingsAdded){
                if(toppingAdded.getExtraToppingName().equals(topping.getExtraToppingName())){
                    toppingAdded.addExtraTopping(topping.getExtraToppingQuantity());
                    return;
                }
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
    public double getToppingsAddedTotalPrice() {
        double sum = 0;
        for (ExtraTopping extraTopping : listOfToppingsAdded) {
            sum += extraTopping.getExtraToppingPrice() * extraTopping.getExtraToppingQuantity();
        }
        return sum;
    }

    public int getPizzaNumber() {
        return pizzaNumber;
    }
    
}