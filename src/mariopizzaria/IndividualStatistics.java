
package mariopizzaria;

/**
 *
 * @author <Frederik Keis Dinsen>
 */
public class IndividualStatistics {
    private String pizzaName;
    private int amountOfSales;
    
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
}