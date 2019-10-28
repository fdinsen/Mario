
package mariopizzaria;

public class StatisticsUI {

    public static StatisticsUI staticstics_UI_instance = null; 
    private inputValidation inputVal = inputValidation.getInstance();
    
    private StatisticsUI(){
        
    }
    
    public static StatisticsUI getInstance() 
    { 
        if (staticstics_UI_instance == null) 
            staticstics_UI_instance = new StatisticsUI(); 
  
        return staticstics_UI_instance; 
    } 
    public void statisticsDialog() {
        boolean exit = false;
        int selection;
        do {
            System.out.println("Mario's Pizzaria - Statistik");
            System.out.println("-------------------------");

            System.out.println(Statistics.getStatistics() + "\n");
            System.out.println("1 - Gå tilbage");

            selection = inputVal.getUserInput();
            if (selection == 1) {  //Gå tilbage
                exit = true;
            } else {
                System.err.println(selection + " Er ikke en mulighed prøv igen");
            }
        } while (!exit);
    }
}
