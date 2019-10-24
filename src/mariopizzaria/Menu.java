package mariopizzaria;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author <Simon Kjems Jørgensen>
 */

public final class Menu {
    //Setup for toppings file
    private static File toppingFile;
    private static final String TOPPING_FILE_NAME = "Toppings.txt";
    
    //Setup for pizza file
    private static File pizzaFile;
    private static final String PIZZA_FILE_NAME = "Pizzas.txt";

    private static Scanner in;
    
    //Future proof. If the amount of pizzas changes in file
    private static ArrayList<String> pizzaName;

    private static ArrayList<Double> pizzaPrice;

    private static ArrayList<String> pizzaDescription;

    private static ArrayList<String> ExtratoppingName;

    private static ArrayList<Double> ExtratoppingPrice;
    
    static{
        pizzaFile = new File(PIZZA_FILE_NAME);
        toppingFile = new File(TOPPING_FILE_NAME);
        
        pizzaName = new ArrayList<>();
        pizzaPrice = new ArrayList<>();
        pizzaDescription = new ArrayList<>();
        
        ExtratoppingName = new ArrayList<>();
        ExtratoppingPrice = new ArrayList<>();
        
        readInPizzas();
        readInToppings();
    }
    
    public Menu() {
    }

    public static String printMenu() {
        String returnString = "";
        
        for(int i = 0; i < pizzaName.size(); i++){
            returnString += i+1+ " " + pizzaName.get(i) + " " + pizzaPrice.get(i) +  " " + pizzaDescription.get(i)+"\n";
        }
        for(int i = 0; i < ExtratoppingName.size();i++){
            returnString += ExtratoppingName.get(i) + " " + ExtratoppingPrice.get(i)+"\n";
        }
        
        return returnString;
    }

    private static void readInPizzas() {
        String[] temp = new String[3];
        String nextLine;
        
        try (Scanner in = new Scanner(pizzaFile)) {
            while (in.hasNextLine()) {
                nextLine = in.nextLine();
                
                temp = nextLine.split(":");
                
                pizzaName.add(temp[0]);
                pizzaPrice.add(Double.parseDouble(temp[1]));
                pizzaDescription.add(temp[2]);
            }
        } catch (FileNotFoundException ex) {
            //TODO email the developers(us)
        }
    }

    private static void readInToppings() {
        String[] temp = new String[2];
        String nextLine; 
        
        try (Scanner in = new Scanner(toppingFile)) {
            while (in.hasNextLine()) {

                nextLine = in.nextLine();
                temp = nextLine.split(":");
                
                ExtratoppingName.add(temp[0]);
                ExtratoppingPrice.add(Double.parseDouble(temp[1]));
                    
            }
        } catch (FileNotFoundException ex) {
            //TODO email the developers(us)
        }
    }
    public static String getPizzaName(int index){
        
        return pizzaName.get(index);
    }
    public static double getPizzaPrice(int index){
        
        return pizzaPrice.get(index);
    }
    
    public static ArrayList<String> getListOfPizzaName() {
        return pizzaName;
    }

    public static ArrayList<Double> getListOfPizzaPrice() {
        return pizzaPrice;
    }

    public static ArrayList<String> getPizzaDescription() {
        return pizzaDescription;
    }

    public static ArrayList<String> getExtratoppingName() {
        return ExtratoppingName;
    }

    public static ArrayList<Double> getExtratoppingPrice() {
        return ExtratoppingPrice;
    }
}
