package mariopizzaria;
/*

    @Author Simon Kjems Jørgensen
*/

public class ExtraTopping {

    private String name;

    private double price;

    private int quantity;
    
    public ExtraTopping(String name, double price, int quantity){
        
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getExtraToppingName() {
        return name;
    }

    public double getExtraToppingPrice() {
        return price;
    }

    public int getExtraToppingQuantity() {
        return quantity;
    }
    
    public void setExtraToppingQuntity(int qtr){
        quantity = qtr;
    }
}
