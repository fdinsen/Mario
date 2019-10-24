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
        pizzaStats = new String[2][arraySize];
        strBuilder = new StringBuilder();
        //TODO: Add a check for if stats file exists
        //TODO: Automatically import information to Array if it does
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
        File file = new File(fileName);

        //Checks if file with the given name exists
        if (!file.exists()) {
            //if it does not, create it and run this method again
            createFile(fileName, array);
            updateArray(order, fileName, array);
        } else if (array[0][0] == null) {
            //if it does exist, but the array is empty, fill the array 
            // and run this method again
            createArray(array);
            updateArray(order, fileName, array);
        } else {
            //otherwise loop through every pizza in the order 
            //and add one to the given pizza type in the array
            for (int i = 0; i < order.getOrderSize(); i++) {
                pizzaNumber = order.getPizzaAt(i).getPizzaNumber();

                //Takes previous sales, converts it to an int and adds 1 before 
                //placing it back in the array as a String
                previousPizzaSales = Integer.parseInt(array[1][pizzaNumber]) + 1;
                updatedPizzaSales = String.valueOf(previousPizzaSales);
                array[1][pizzaNumber] = updatedPizzaSales;
            }
            //then update the actual file
            updateFile(fileName, array);

        }
    }

    //This method should only run once EVER. Otherwise it clears the statistics
    public static void createFile(String statsFileName, String[][] array) {
        //If the given array is empty, fill it
        if (array[0][0] == null) {
            createArray(array);
        }

        try {
            File file = new File(statsFileName);
            bw = new BufferedWriter(new FileWriter(file));

            //If the file does not already exist
            if (!file.exists()) {
                //loops through every entry in the array, and creates a 
                // line for each pizza-type
                for (int i = 0; i < arraySize; i++) {
                    //Appends the pizza name and the sales to each line in the string
                    strBuilder.append(array[0][i] + " " + array[1][i]);
                    //As long as this is not the last row
                    if (i < arraySize - 1) {
                        //then add a newline
                        strBuilder.append("\n");
                    }
                }
                bw.write(strBuilder.toString());
                bw.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Statistic.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void readFile() {
        //TODO: read through file, line by line, and add each piece
        //of information to the Array
    }

    private static void updateFile(String statsFileName, String[][] array) {
        //loops through every entry in the array, and creates a 
        for (int i = 0; i < arraySize; i++) {
            try {
                File tempFile = new File(statsFileName);
                bw = new BufferedWriter(new FileWriter(tempFile));
                //Appends the pizza name and the sales to the string
                strBuilder.append(array[0][i] + " " + array[1][i]);
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
