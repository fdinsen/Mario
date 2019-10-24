/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariopizzaria;

import java.io.File;
import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author <Frederik Keis Dinsen>
 */
public class StatisticTest {
    
    public StatisticTest() {
    }

    @Test
    public void testUpdateArray() {
        //Arrange
        String testFileName = "testStats.txt";
        int arraySize = Menu.getListOfPizzaName().size();
        String[][] pizzaStatsTest = new String[2][arraySize];
        int expectedAmountOfSales;
        int actualAmountOfSales;
        
        //Act
        expectedAmountOfSales = 3;
        Order order = new Order(true);
        order.addPizza(1);
        order.addPizza(1);
        order.addPizza(1);

        Statistic.updateArray(order, testFileName, pizzaStatsTest);
        actualAmountOfSales = Integer.parseInt(pizzaStatsTest[1][1]);
        
        //Assert
        assertEquals(expectedAmountOfSales, actualAmountOfSales);
    }
    
    @Test
    public void testCreateFileExists() {
        //Arrange
        String testFileName = "testStats.txt";
        File file = new File(testFileName);
        int arraySize = Menu.getListOfPizzaName().size();
        String[][] pizzaStatsTest = new String[2][arraySize];
        
        //Act
        Statistic.createFile(testFileName, pizzaStatsTest);
        
        
        //Assert
        assertTrue(file.exists());
    }
    
    
}
