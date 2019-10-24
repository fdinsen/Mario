/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariopizzaria;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author <Simon Kjems Jørgensen>
 */
public class MenuTest {
    
    
    public MenuTest() {
        
    }

    /**
     * Test of printMenu method, of class Menu.
     */
    @Test
    public void testPrintMenu() {
        //Don't know how to test
        System.out.println(Menu.printMenu());
    }
    @Test
    public void testReadInPizza(){
        //Arrange
        int exspectedLength = 30;
        
        //Act
        int actualNameLength = Menu.getListOfPizzaName().size();
        
        //Assert
        assertEquals(exspectedLength, actualNameLength);
    }
    @Test
    public void testReadInToppings(){
        int exspectedLength = 26;
        
        //Act
        int actualNameLength = Menu.getExtratoppingName().size();
        
        //Assert
        assertEquals(exspectedLength, actualNameLength);
    }
    @Test
    public void testGetPizzaName(){
        //Arrange
        String exspected = "Braccio di Ferro";
        
        //Act
        String actual = Menu.getPizzaName(17);
        
        //Assert
        assertEquals(exspected, actual);
        
    }
    @Test
    public void testGetPizzaPrice(){
        //Arrange 
        double exspected = 87.00;
        
        //Act
        double actual = Menu.getPizzaPrice(22);
        
        //Assert
        assertEquals(exspected, actual,0.005);
        
    }
}
