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
 * @author <Frederik Keis Dinsen>
 */
public class PizzaTest {
    ExtraTopping eggs = new ExtraTopping("Egg", 6.00);
    ExtraTopping egg = new ExtraTopping("Egg", 6.00);
    Pizza Quattro = new Pizza("Quattro",79.00,0);
    
    public PizzaTest() {
    }

    @Test
    public void testGetTotalPizzaPriceWithoutToppings() {
        //Arrange
        double exspectedPrice = 79.00;
        //Act
        double actualPrice = Quattro.getTotalPizzaPrice();
        //Assert
        assertEquals(exspectedPrice, actualPrice,0.005);
    }
    @Test
    public void testGetTotalPizzaPriceWithToppings(){
        //Arrange
        double exspectedPrice = 127.00;
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
        double exspectedPrice = 127.00;
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
}
