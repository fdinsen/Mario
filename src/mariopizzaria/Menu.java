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
class Menu {
    
    private static Menu menu_instance = null; 
    
    //Setup for toppings file
    private  File toppingFile;
    private  final String TOPPING_FILE_NAME = "Toppings.txt";
    
    //Setup for pizza file
    private  File pizzaFile;
    private  final String PIZZA_FILE_NAME = "Pizzas.txt";

    private  Scanner in;
    
    //Future proof. If the amount of pizzas changes in file
    private  ArrayList<String> pizzaName;

    private  ArrayList<Double> pizzaPrice;

    private  ArrayList<String> pizzaDescription;

    private  ArrayList<ExtraTopping> listOfExtraTopping;
    
    private Menu() {
        
        pizzaFile = new File(PIZZA_FILE_NAME);
        toppingFile = new File(TOPPING_FILE_NAME);
        
        pizzaName = new ArrayList<>();
        pizzaPrice = new ArrayList<>();
        pizzaDescription = new ArrayList<>();
        
        listOfExtraTopping = new ArrayList<>();
        
        readInPizzas();
        readInToppings();
        
    }
    
    public static Menu getInstance() 
    { 
        if (menu_instance == null) 
            menu_instance = new Menu(); 
  
        return menu_instance; 
    } 
    
    public  String printMenu() {
        String returnString = "";
        
        for(int i = 0; i < pizzaName.size(); i++){
            returnString += i+1+ " " + pizzaName.get(i) + " " + pizzaPrice.get(i) +  " " + pizzaDescription.get(i)+"\n";
        }
        for(ExtraTopping topping : listOfExtraTopping){
            returnString += topping.getExtraToppingName() + " " + topping.getExtraToppingPrice() + "\n";
        }
        
        return returnString;
    }

    private  void readInPizzas() {
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

    private  void readInToppings() {
        String[] temp = new String[2];
        String nextLine; 
        
        try (Scanner in = new Scanner(toppingFile)) {
            while (in.hasNextLine()) {

                nextLine = in.nextLine();
                temp = nextLine.split(":");
                //Created new ExtraTopping with temp[0] and temp[1] and puts into listOfTopping
                listOfExtraTopping.add(new ExtraTopping(temp[0],Double.parseDouble(temp[1])));
                    
            }
        } catch (FileNotFoundException ex) {
            //TODO email the developers(us)
        }
    }
    public  String getPizzaName(int index){
        
        return pizzaName.get(index);
    }
    public  double getPizzaPrice(int index){
        
        return pizzaPrice.get(index);
    }
    
    public  ArrayList<String> getListOfPizzaName() {
        return pizzaName;
    }

    public  ArrayList<Double> getListOfPizzaPrice() {
        return pizzaPrice;
    }

    public  ArrayList<String> getPizzaDescription() {
        return pizzaDescription;
    }

    public  String getExtratoppingName(int index) {
        return listOfExtraTopping.get(index).getExtraToppingName();
    }

    public  double getExtratoppingPrice(int index) {
        return listOfExtraTopping.get(index).getExtraToppingPrice();
    }
    
    public  ExtraTopping getTopping(int index){
        return listOfExtraTopping.get(index);
    }
    public  ArrayList<ExtraTopping> getToppingList(){
        return listOfExtraTopping;
    }
    public  String getPizzaDescription(int index){
        return pizzaDescription.get(index);
    }
    public int getAmountOfPizzas(){
        return pizzaName.size();
    }
}
