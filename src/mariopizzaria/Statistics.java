package mariopizzaria;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author <Frederik Keis Dinsen>
 */
public final class Statistics {

    private static String[][] pizzaStats;
    private static File statsFile;
    private static final String STATISTICS_FILE_NAME = "statistics.txt";
    private static StringBuilder strBuilder;
    private static BufferedWriter bw;
    private static int arraySize;
    public static int arrayListSize;
    private static ArrayList<IndividualStatistics> statisticsList;
    private static String seperatorCharacter;

    static {
        arraySize = Menu.getListOfPizzaName().size();
        statsFile = new File(STATISTICS_FILE_NAME);
        pizzaStats = new String[2][arraySize];
        strBuilder = new StringBuilder();
        statisticsList = new ArrayList<>();
        seperatorCharacter = ":";
        arrayListSize = statisticsList.size();
        //TODO: Add a check for if stats file exists
        //TODO: Automatically import information to Array if it does
    }

    //---------//
    // GETTERS //
    //---------//
    public static String[][] getPizzaStats() {
        return pizzaStats;
    }
    public static int getPizzaSalesOfPizzaAt(int index) {
        return statisticsList.get(index).getAmountOfSales();
    }
    
    public static String getPizzaNameOfPizzaAt(int index) {
        return statisticsList.get(index).getPizzaName();
    }
    

    //---------//
    // METHODS //
    //---------//
    public static void updateStats(Order order) {
        updateArray(order, STATISTICS_FILE_NAME, pizzaStats);
    }
    //This one should only be called directly in tests, use updateStats() instead
    public static void updateArray(Order order, String fileName, ArrayList<IndividualStatistics> arrayList) {
        int pizzaNumber;
        int previousPizzaSales;
        int updatedPizzaSales;
        File file = new File(fileName);
        
        //Checks if file with the given name exists
        if(!file.exists()) {
            //if it does not, create it and run this method again
            createFile(fileName, arrayList);
            updateArray(order, fileName, arrayList);
        } else if (arrayList.get(0) == null) {
            //if it does exist, but the array is empty, fill the array 
            // and run this method again
            createArray(arrayList);
            updateArray(order,fileName, arrayList);
        }else {
            //otherwise loop through every pizza in the order 
            //and add one to the given pizza type in the array
            for (int i = 0; i < order.getOrderSize(); i++) {
                //gets the pizza and adds 1 to the orders
                pizzaNumber = order.getPizzaAt(i).getPizzaNumber();
                arrayList.get(pizzaNumber).updatePizzaSales();
            }
            //then update the actual file
            updateFile(fileName, arrayList);
        }
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
    public static void createFile(String fileName, ArrayList<IndividualStatistics> arrayList) {
        //If the given array is empty, fill it
        if (arrayList.get(0) == null) {
            createArray(arrayList);
        }
        
        arrayListSize = arrayList.size();
        String currentPizzaName;
        int currentPizzaSales;
        
        try {
            File file = new File(fileName);
            bw = new BufferedWriter(new FileWriter(file));
            strBuilder.delete(0, strBuilder.length());
            
            //If the file does not already exist
            if (!file.exists()) {
                //loops through every entry in the array, and creates a 
                // line for each pizza-type
                for (int i = 0; i < arrayListSize; i++) {
                    currentPizzaName = arrayList.get(i).getPizzaName();
                    currentPizzaSales = arrayList.get(i).getAmountOfSales();
                    //Appends the pizza name and the sales to each line in the string
                    strBuilder.append(currentPizzaName + seperatorCharacter + 
                            currentPizzaSales);
                    //As long as this is not the last row
                    if (i < arrayListSize -1) {
                        //then add a newline
                        strBuilder.append("\n");
                    }
                }
                bw.write(strBuilder.toString());
                bw.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
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
            strBuilder.delete(0, strBuilder.length());
            
            //If the file does not already exist
            if (!file.exists()) {
                //loops through every entry in the array, and creates a 
                // line for each pizza-type
                for (int i = 0; i < arraySize; i++) {
                    //Appends the pizza name and the sales to each line in the string
                    strBuilder.append(array[0][i] + seperatorCharacter + array[1][i]);
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
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static void readFile(ArrayList<IndividualStatistics> arrayList, File file) {
        //TODO: read through file, line by line, and add each piece
        //of information to the Array

        String[] temp = new String[2];
        String nextLine;
        int count = 0;
        try (Scanner in = new Scanner(file)) {
            while (in.hasNextLine()) {

                nextLine = in.nextLine();
                temp = nextLine.split(seperatorCharacter);

                arrayList.add(new IndividualStatistics(temp[0], temp[1]));
                //pizzaStats[0][count] = temp[0];
                //pizzaStats[1][count] = temp[1];
            }

        } catch (FileNotFoundException ex) {

        }

    }

    private static void updateFile(String fileName, ArrayList<IndividualStatistics> arrayList) {
        //loops through every entry in the array, and creates a 
        arrayListSize = arrayList.size();
        strBuilder.delete(0, strBuilder.length());
        for (int i = 0; i < arrayListSize; i++) {
            try {
                File file = new File(fileName);
                bw = new BufferedWriter(new FileWriter(file));
                //Appends the pizza name and the sales to the string
                strBuilder.append(arrayList.get(i).getPizzaName() + 
                        seperatorCharacter + arrayList.get(i).getAmountOfSales());
                //If this is not the last row
                if( i < arrayListSize - 1) {
                    strBuilder.append("\n");
                }
                bw.write(strBuilder.toString());
                bw.close();
            }catch (IOException ex) {
                Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static void updateFile(String statsFileName, String[][] array) {
        //loops through every entry in the array, and creates a 
        for (int i = 0; i < arraySize; i++) {
            try {
                File tempFile = new File(statsFileName);
                bw = new BufferedWriter(new FileWriter(tempFile));
                //Appends the pizza name and the sales to the string
                strBuilder.append(array[0][i] + seperatorCharacter + array[1][i]);
                //If this is not the last row
                if (i < arraySize - 1) {
                    //then add a newline
                    strBuilder.append("\n");
                }
                bw.write(strBuilder.toString());
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public static void createArray(ArrayList<IndividualStatistics> arrayList) {
        //Creates array with all pizza names, and zero in every sale
        arrayList.clear();
        for (int i = 0; i < arrayListSize; i++) {
            arrayList.add(new IndividualStatistics(Menu.getListOfPizzaName().get(i), 0));
        }
    }
    
    public static void createArray(String[][] arrayToFill) {
        //Creates array with all pizza names, and zero in every sale
        for (int i = 0; i < arraySize; i++) {
            arrayToFill[0][i] = Menu.getListOfPizzaName().get(i);
            arrayToFill[1][i] = "0";
        }
    }

    public static String getStatistics() {
        //TODO build a String that contains everything, using StringBuilder
        statisticsList.sort(new AmountSorter());

        StringBuilder strbuilder = new StringBuilder();
        
        for(IndividualStatistics stats : statisticsList){
            strBuilder.append(stats);
            if(statisticsList.indexOf(stats) < statisticsList.size() -1){
                strBuilder.append("\n");
            }         
        }
        return strbuilder.toString();
    }
}
