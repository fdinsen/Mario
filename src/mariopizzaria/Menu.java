/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariopizzaria;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author simon
 */
public class Menu {
    //--------------------//
    // INSTANCE VARIABLES //
    //--------------------//
    //Setup for toppings file
    private File toppingFile;

    //Setup for pizza file
    private File pizzaFile;
    private final String PIZZA_FILE_NAME = "Pizzas.txt";

    //Future proof. If the amount of pizzas changes in file
    private ArrayList<String> pizzaName;

    private ArrayList<Double> pizzaPrice;

    private ArrayList<String> pizzaDescription;

    private ArrayList<ExtraTopping> listOfExtraTopping;

    //--------------//
    // CONSTRUCTORS //
    //--------------//
    private Menu() {

        pizzaFile = new File(PIZZA_FILE_NAME);
        String TOPPING_FILE_NAME = "Toppings.txt";
        toppingFile = new File(TOPPING_FILE_NAME);

        pizzaName = new ArrayList<>();
        pizzaPrice = new ArrayList<>();
        pizzaDescription = new ArrayList<>();

        listOfExtraTopping = new ArrayList<>();

        readInPizzas();
        readInToppings();

    }

    //------------//
    // SINGLETON  //
    //------------//
    public static Menu getInstance() {
        return MenuHolder.INSTANCE;
    }

    private static class MenuHolder {

        private static final Menu INSTANCE = new Menu();
    }

    //---------//
    // GETTERS //
    //---------//
    public String getPizzaName(int index) {

        return pizzaName.get(index);
    }

    public double getPizzaPrice(int index) {

        return pizzaPrice.get(index);
    }

    public ArrayList<String> getListOfPizzaName() {
        return pizzaName;
    }

    String getExtratoppingName(int index) {
        return listOfExtraTopping.get(index).getExtraToppingName();
    }

    double getExtratoppingPrice(int index) {
        return listOfExtraTopping.get(index).getExtraToppingPrice();
    }

    public ArrayList<ExtraTopping> getToppingList() {
        return listOfExtraTopping;
    }

    public String getPizzaDescription(int index) {
        return pizzaDescription.get(index);
    }

    public int getAmountOfPizzas() {
        return pizzaName.size();
    }

    private void readInPizzas() {
        String[] temp;
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

    private void readInToppings() {
        String[] temp;
        String nextLine;

        try (Scanner in = new Scanner(toppingFile)) {
            while (in.hasNextLine()) {

                nextLine = in.nextLine();
                temp = nextLine.split(":");
                //Created new ExtraTopping with temp[0] and temp[1] and puts into listOfTopping
                listOfExtraTopping.add(new ExtraTopping(temp[0], Double.parseDouble(temp[1])));

            }
        } catch (FileNotFoundException ex) {
            //TODO email the developers(us)
        }

    }

}
