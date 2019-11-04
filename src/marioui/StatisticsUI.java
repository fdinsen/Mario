
package marioui;

import mariopizzaria.Statistics;
import mariopizzaria.InputValidation;

public class StatisticsUI {

    //--------------------//
    // INSTANCE VARIABLES //
    //--------------------//
    private InputValidation inputVal = InputValidation.getInstance();
    private Statistics statistic = Statistics.getInstance();
    
    //--------------//
    // CONSTRUCTORS //
    //--------------//
    private StatisticsUI(){
        
    }
    
    //------------//
    // SINGLETON  //
    //------------//
    public static StatisticsUI getInstance() 
    { 
        return StatisticsUIHolder.INSTANCE;
    } 
    
    private static class StatisticsUIHolder {
        private static final StatisticsUI INSTANCE = new StatisticsUI();
    }
    
    //------------------//
    // METHODS - DIALOGS//
    //------------------//
    void statisticsDialog() {
        boolean exit = false;
        int selection;
        do {
            System.out.println("Mario's Pizzaria - Statistik");
            System.out.println("-------------------------");

            System.out.println(statistic.getStatistics() + "\n");
            System.out.println("1 - Gå tilbage");

            selection = inputVal.getUserInput();
            if (selection == 1) {  //Go back
                exit = true;
            } else {
                System.err.println(selection + " Er ikke en mulighed prøv igen");
            }
        } while (!exit);
    }
}
