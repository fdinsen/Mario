package mariopizzaria;
/*

    @Author Simon Kjems Jørgensen
*/

public class ExtraTopping {

    //--------------------//
    // INSTANCE VARIABLES //
    //--------------------//
    private String name;

    private double price;

    private int quantity;
    
    //--------------//
    // CONSTRUCTERS //
    //--------------//
    ExtraTopping(String name, double price){
        
        this.name = name;
        this.price = price;
        this.quantity = 0;
    }
    
    ExtraTopping(int itemNumber, int quantity){
        
        this.quantity = Math.abs(quantity);
        //--------------------//
        // INSTANCE VARIABLES //
        //--------------------//
        Menu menu = Menu.getInstance();
        this.name = menu.getExtratoppingName(itemNumber);
        this.price = menu.getExtratoppingPrice(itemNumber);
        
    }
    
    //---------//
    // GETTERS //
    //---------//
    public String getExtraToppingName() {
        return name;
    }

    public double getExtraToppingPrice() {
        return price;
    }

    public int getExtraToppingQuantity() {
        return quantity;
    }
    
    //---------//
    // METHODS //
    //---------//
    void addExtraTopping(int qtr){
        quantity += Math.abs(qtr);
    }
     
}
