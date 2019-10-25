
package mariopizzaria;

/**
 *
 * @author <Frederik Keis Dinsen>
 */
public class IndividialStatistics {
    private String pizzaName;
    private int amountOfSales;
    
    public IndividialStatistics(String pizzaName, int amountOfSales) {
        this.pizzaName = pizzaName;
        this.amountOfSales = amountOfSales;
    }
    
    public IndividialStatistics(String pizzaName, String amountOfSales) {
        this.pizzaName = pizzaName;
        this.amountOfSales = Integer.parseInt(amountOfSales);
    }
        
}
