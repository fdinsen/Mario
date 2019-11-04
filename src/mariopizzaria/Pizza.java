package mariopizzaria;

/*

    @Author Simon Kjems Jørgensen
 */
import java.util.ArrayList;

public class Pizza {

    //--------------------//
    // INSTANCE VARIABLES //
    //--------------------//
    private String pizzaName;

    private double pizzaPrice;

    private int pizzaSize;

    private double totalPizzaPrice;

    private ArrayList<ExtraTopping> listOfToppingsAdded;

    private int pizzaNumber;

    //--------------//
    // CONSTRUCTERS //
    //--------------//
    public Pizza(int menuIndex, int pizzaSize) {
        //Informationen om pizzaen hentes fra menu-kortet
        //--------------------//
        // INSTANCE VARIABLES //
        //--------------------//
        Menu menu = Menu.getInstance();
        pizzaName = menu.getPizzaName(menuIndex);
        pizzaPrice = menu.getPizzaPrice(menuIndex);
        this.pizzaNumber = menuIndex;

        if (pizzaSize >= 3 || pizzaSize < 0) {
            this.pizzaSize = 0;
        } else {
            this.pizzaSize = pizzaSize;
        }
        switch (pizzaSize) {
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

    //---------//
    // GETTERS //
    //---------//
    public String getPizzaName() {
        return pizzaName;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public String getPizzaSizeString() {
        if (pizzaSize == 0) {
            return "Almindelig";
        } else if (pizzaSize == 1) {
            return "Familie";
        } else {
            return "Deep pan";
        }
    }

    double getTotalPizzaPrice() {
        return totalPizzaPrice;
    }

    public ArrayList<ExtraTopping> getToppingsAdded() {
        return listOfToppingsAdded;
    }

    int getPizzaNumber() {
        return pizzaNumber;
    }

    //---------//
    // METHODS //
    //---------//
    void addExtraTopping(ExtraTopping topping) {

        for (ExtraTopping toppingAdded : listOfToppingsAdded) {
            if (toppingAdded.getExtraToppingName().equals(topping.getExtraToppingName())) {
                toppingAdded.addExtraTopping(topping.getExtraToppingQuantity());
                return;
            }
        }
        listOfToppingsAdded.add(topping);

        totalPizzaPrice += topping.getExtraToppingPrice() * topping.getExtraToppingQuantity();

    }

}
