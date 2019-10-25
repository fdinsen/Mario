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
public class PizzaTest {
    ExtraTopping eggs = new ExtraTopping("Egg", 6.00);
    ExtraTopping egg = new ExtraTopping("Egg", 6.00);
    Pizza Quattro = new Pizza(5 ,0);
    Pizza Quattro1 = new Pizza(5, 1);
    Pizza Quattro2 = new Pizza(5, 2);
    
    public PizzaTest() {
    }

    @Test
    public void testGetTotalPizzaPriceWithoutToppings() {
        //Arrange
        double exspectedPrice = 64.00;
        //Act
        double actualPrice = Quattro.getTotalPizzaPrice();
        //Assert
        assertEquals(exspectedPrice, actualPrice,0.005);
    }
    @Test
    public void testGetTotalPizzaPriceWithToppings(){
        //Arrange
        double exspectedPrice = 112.00;
        egg.setExtraToppingQuntity(8);
        Quattro.addExtraTopping(egg);
        //Act
        double actualPrice = Quattro.getTotalPizzaPrice();
        //Assert
        assertEquals(exspectedPrice, actualPrice,0.005);
    }
    @Test
    public void testGetTotalPizzaPriceWithMinusToppings(){
        //Arrange
        double exspectedPrice = 112.00;
        egg.setExtraToppingQuntity(-8);
        Quattro.addExtraTopping(egg);
        //Act
        double actualPrice = Quattro.getTotalPizzaPrice();
        //Assert
        assertEquals(exspectedPrice, actualPrice,0.005);
    }
    @Test
    public void testGetTotalPizzaPriceAddingMoreEggs(){
        //Arrange
        int exspectedLenght = 1;
        eggs.setExtraToppingQuntity(3);
        egg.setExtraToppingQuntity(2);
        Quattro.addExtraTopping(egg);
        Quattro.addExtraTopping(eggs);
        
        //Act
        int actualLength = Quattro.getToppingsAdded().size();
        //Assert
        assertEquals(exspectedLenght, actualLength);
    }
    @Test 
    public void testPizzaSize1(){
        
        double exspected = 118.4;
        
        double actual = Quattro1.getPizzaPrice();
        
        assertEquals(exspected, actual, 0.0);
    }
    @Test
    public void testPizzaSize2(){
        
        double exspected = 73.6;
        
        double actual = Quattro2.getPizzaPrice();
        
        assertEquals(exspected, actual, 0.0);
    }
    @Test
    public void testGetgetToppingsAddedTotalPrice(){
        //Arrange
        egg.addExtraTopping(4);
        Quattro.addExtraTopping(egg);
        double expected = 24.0;
        //Act
        double actual = Quattro.getToppingsAddedTotalPrice();
        //Assert
        assertEquals(expected, actual, 0.0);
    }
}
