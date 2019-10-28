package mariopizzaria;
/*

    @Author Simon Kjems Jørgensen
*/

public class ExtraTopping {
    
    Menu menu = Menu.getInstance();
    
    private String name;

    private double price;

    private int quantity;
    
    public ExtraTopping(String name, double price){
        
        this.name = name;
        this.price = price;
        this.quantity = 0;
    }
    
    public ExtraTopping(int itemNumber, int quantity){
        
        this.quantity = Math.abs(quantity);
        this.name = menu.getExtratoppingName(itemNumber);
        this.price = menu.getExtratoppingPrice(itemNumber);
        
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
        
        quantity = Math.abs(qtr);
    }
    public void addExtraTopping(int qtr){
        quantity += Math.abs(qtr);
    }
     
}
