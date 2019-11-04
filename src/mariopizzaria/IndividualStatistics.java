
package mariopizzaria;

/**
 *
 * @author <Frederik Keis Dinsen>
 */
public class IndividualStatistics {
    //--------------------//
    // INSTANCE VARIABLES //
    //--------------------//
    private String pizzaName;
    private int amountOfSales;
    
    //--------------//
    // CONSTRUCTERS //
    //--------------//
    IndividualStatistics(String pizzaName, int amountOfSales) {
        this.pizzaName = pizzaName;
        this.amountOfSales = amountOfSales;
    }
    
    IndividualStatistics(String pizzaName, String amountOfSales) {
        this.pizzaName = pizzaName;
        this.amountOfSales = Integer.parseInt(amountOfSales);
    }
 
    //---------//
    // GETTERS //
    //---------//
    String getPizzaName() {
        return pizzaName;
    }
    
    int getAmountOfSales() {
        return amountOfSales;
    }
    
    @Override
    public String toString(){
        return pizzaName + " : "+ amountOfSales;
    }
    
    //---------//
    // METHODS //
    //---------//
    void updatePizzaSales() {
        amountOfSales++;
    }
}
