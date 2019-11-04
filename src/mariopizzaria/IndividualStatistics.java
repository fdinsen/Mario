
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
    public IndividualStatistics(String pizzaName, int amountOfSales) {
        this.pizzaName = pizzaName;
        this.amountOfSales = amountOfSales;
    }
    
    public IndividualStatistics(String pizzaName, String amountOfSales) {
        this.pizzaName = pizzaName;
        this.amountOfSales = Integer.parseInt(amountOfSales);
    }
 
    //---------//
    // GETTERS //
    //---------//
    public String getPizzaName() {
        return pizzaName;
    }
    
    public int getAmountOfSales() {
        return amountOfSales;
    }
    
    @Override
    public String toString(){
        return pizzaName + " : "+ amountOfSales;
    }
    
    //---------//
    // METHODS //
    //---------//
    public void updatePizzaSales() {
        amountOfSales++;
    }
}
