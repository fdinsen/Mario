
package mariopizzaria;

public class StatisticsUI {

    private static StatisticsUI staticstics_UI_instance = null; 
    
    
    private StatisticsUI(){
        
    }
    
    public static StatisticsUI getInstance() 
    { 
        if (staticstics_UI_instance == null) 
            staticstics_UI_instance = new StatisticsUI(); 
  
        return staticstics_UI_instance; 
    } 
}
