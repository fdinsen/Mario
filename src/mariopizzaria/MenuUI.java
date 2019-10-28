package mariopizzaria;

public class MenuUI {

    private static MenuUI menu_Ui_Instance = null; 
    
    
    private MenuUI(){
    }
    
    public static MenuUI getInstance() 
    { 
        if (menu_Ui_Instance == null) 
            menu_Ui_Instance = new MenuUI(); 
  
        return menu_Ui_Instance; 
    } 
}
