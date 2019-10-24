package mariopizzaria;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author <Frederik Keis Dinsen>
 */
public final class Statistic {

    private static String[][] pizzaStats;
    private static File statsFile;
    private static final String STATISTICS_FILE_NAME = "statistics.txt";
    private static StringBuilder strBuilder;
    private static BufferedWriter bw;
    private static int arraySize;

    static {
        arraySize = Menu.getListOfPizzaName().size();
        statsFile = new File(STATISTICS_FILE_NAME);
        pizzaStats = new String[arraySize][arraySize];
        strBuilder = new StringBuilder();
    }

    //---------//
    // GETTERS //
    //---------//

    public static String[][] getPizzaStats() {
        return pizzaStats;
    }
    
    //---------//
    // METHODS //
    //---------//
    
    public static void updateStats(Order order) {
        updateArray(order, STATISTICS_FILE_NAME, pizzaStats);
    }
    
    //This one should only be called directly in tests, use updateStats() instead
    public static void updateArray(Order order, String fileName, String[][] array) {
        int pizzaNumber;
        int previousPizzaSales;
        String updatedPizzaSales;

        File statsFile = new File(fileName);
        if (!statsFile.exists()) {
            createFile(fileName);
            updateArray(order, fileName, array);
        } else if (array[0][0] == null) {
            createArray(array);
        } else {
            for (int i = 0; i < order.getOrderSize(); i++) {
                pizzaNumber = order.getPizzaAt(i).getPizzaNumber();

                //Takes previous sales, converts it to an int and adds 1 before 
                //placing it back in the array as a String
                previousPizzaSales = Integer.parseInt(array[1][i]);
                updatedPizzaSales = String.valueOf(previousPizzaSales++);
                array[1][pizzaNumber] = updatedPizzaSales;
            }
            updateFile(fileName);

        }
    }

//    //This one is exactly the same, but uses the acutal statistics file
//    //MUST NOT BE TESTED ON!!!
//    public static void updateStats(Order order) {
//        int pizzaNumber;
//        int previousPizzaSales;
//        String updatedPizzaSales;
//
//        String statsFileName = STATISTICS_FILE_NAME;
//
//        if (!statsFile.exists()) {
//            createFile(statsFileName);
//            updateStats(order);
//        } else if (pizzaStats[0][0] == null) {
//            createArray(pizzaStats);
//        } else {
//            for (int i = 0; i < order.getOrderSize(); i++) {
//                pizzaNumber = order.getPizzaAt(i).getPizzaNumber();
//
//                //Takes previous sales, converts it to an int and adds 1 before 
//                //placing it back in the array as a String
//                previousPizzaSales = Integer.parseInt(pizzaStats[1][i]);
//                updatedPizzaSales = String.valueOf(previousPizzaSales++);
//                pizzaStats[1][pizzaNumber] = updatedPizzaSales;
//            }
//            updateFile(statsFileName);
//
//        }
//    }

    //This method should only run once EVER. Otherwise it clears the statistics
    public static void createFile(String statsFileName) {
        createArray(pizzaStats);

        try {
            File tempFile = new File(statsFileName);
            bw = new BufferedWriter(new FileWriter(tempFile));

            for (int i = 0; i < arraySize; i++) {
                //Appends the pizza name and the sales to the string
                strBuilder.append(pizzaStats[0][i] + " " + pizzaStats[1][i]);
                //If this is not the last row
                if (i < arraySize - 1) {
                    //then add a newline
                    strBuilder.append("\n");
                }
            }
            bw.write(strBuilder.toString());
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(Statistic.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void readFile() {
    }

    private static void updateFile(String statsFileName) {
        for (int i = 0; i < arraySize; i++) {
            try {
                File tempFile = new File(statsFileName);
                bw = new BufferedWriter(new FileWriter(tempFile));
                //Appends the pizza name and the sales to the string
                strBuilder.append(pizzaStats[0][i] + " " + pizzaStats[1][i]);
                //If this is not the last row
                if (i < arraySize - 1) {
                    //then add a newline
                    strBuilder.append("\n");
                }
                bw.write(strBuilder.toString());
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(Statistic.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public static void createArray(String[][] arrayToFill) {
        //Creates array with all pizza names, and zero in every sale
        for (int i = 0; i < arraySize; i++) {
            arrayToFill[0][i] = Menu.getListOfPizzaName().get(i);
            arrayToFill[1][i] = "0";
        }
    }

}
