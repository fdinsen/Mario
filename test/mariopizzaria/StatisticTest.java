/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariopizzaria;

import com.sun.org.glassfish.external.statistics.Statistic;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public void testCreateFileExists() {
        //Arrange
        String testFileName = "testStats.txt";
        File file = new File(testFileName);
        int arraySize = Menu.getListOfPizzaName().size();
        String[][] pizzaStatsTest = new String[2][arraySize];
        
        //Act
        Statistics.createFile(testFileName, pizzaStatsTest);
        
        
        //Assert
        assertTrue(file.exists());
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

        Statistics.updateArray(order, testFileName, pizzaStatsTest);
        actualAmountOfSales = Integer.parseInt(pizzaStatsTest[1][1]);
        
        //Assert
        assertEquals(expectedAmountOfSales, actualAmountOfSales);
    }
    
    /*
    @Test
    public void testReadFileToIndividualStatisticsObject() {
        //Arrange
        String testFileName = "testStats.txt";
        File file = new File(testFileName);
        ArrayList<IndividualStatistics> testArrayList
                = new ArrayList<>();
        int indexOfPizzaToTest = 0;
        String expectedName = "Marinara";
        int expectedNumber = 3;
        String actualName;
        int actualNumber;
        
        //Act
        Statistics.readFile(testArrayList, file);
        actualName = testArrayList.get(0).getPizzaName();
        actualNumber = testArrayList.get(0).getAmountOfSales();
        
        //Assert
        assertEquals(expectedName, actualName);
        assertEquals(expectedNumber, actualNumber);
        
    }*/
    
}
