package mariopizzaria;

class Costumer {
    //--------------------//
    // INSTANCE VARIABLES //
    //--------------------//
    private String costumerName;
    private int costumerPhoneNumber;
    
    //---------//
    // GETTERS //
    //---------//
    String getCostumerName() {
        return costumerName;
    }
    
    int getCostumerPhoneNumber() {
        return costumerPhoneNumber;
    }
    
    //---------//
    // SETTERS //
    //---------//
    void setCostumerName(String name) {
        costumerName = name;
    }
    
    void setCostumerPhoneNumber(int phone) {
        costumerPhoneNumber = phone;
    }
}