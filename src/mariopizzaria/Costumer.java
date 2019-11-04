package mariopizzaria;

public class Costumer {
    //--------------------//
    // INSTANCE VARIABLES //
    //--------------------//
    private String costumerName;
    private int costumerPhoneNumber;
    
    //---------//
    // GETTERS //
    //---------//
    public String getCostumerName() {
        return costumerName;
    }
    
    public int getCostumerPhoneNumber() {
        return costumerPhoneNumber;
    }
    
    //---------//
    // SETTERS //
    //---------//
    public void setCostumerName(String name) {
        costumerName = name;
    }
    
    public void setCostumerPhoneNumber(int phone) {
        costumerPhoneNumber = phone;
    }
}