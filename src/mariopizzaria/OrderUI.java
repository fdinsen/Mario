
package mariopizzaria;

public class OrderUI {

    private static OrderUI Order_Ui_Instance = null; 
    
    
    private OrderUI(){
        
    }
    
    public static OrderUI getInstance() 
    { 
        if (Order_Ui_Instance == null) 
            Order_Ui_Instance = new OrderUI(); 
  
        return Order_Ui_Instance; 
    } 
}
