/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariopizzaria;

import com.sun.org.glassfish.external.statistics.Statistic;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author <Frederik Keis Dinsen>
 */
public class StatisticTest {

    String testFileName = "testStats.txt";
    File testFile = new File(testFileName);

    public StatisticTest() {

    }

    @Test
    public void testCreateFileExists() {
        //Arrange
        int arraySize = Menu.getListOfPizzaName().size();
        String[][] pizzaStatsTest = new String[2][arraySize];
        ArrayList<IndividualStatistics> arrayList = new ArrayList<>();

        //Act
        Statistics.createFile(testFile, arrayList);

        //Assert
        assertTrue(testFile.exists());
    }

    @Test
    public void testCreateFileByContent() {
        //Arrange
        //String[][] pizzaStatsTest = new String[2][arraySize];
        ArrayList<IndividualStatistics> arrayList = new ArrayList<>();
        String actualFirstLine = "";
        String expectedFirstLine = "Margherita";
        //Act
        Statistics.createFile(testFile, arrayList);
        try (Scanner in = new Scanner(testFile)) {
            actualFirstLine = in.nextLine();
        } catch (FileNotFoundException ex) {

        }
        actualFirstLine = actualFirstLine.substring(0, 10);

        //Assert
        assertEquals(expectedFirstLine, actualFirstLine);
    }

    @Test
    public void testUpdateArray() {
        //Arrange
        int arraySize = Menu.getListOfPizzaName().size();
        //String[][] pizzaStatsTest = new String[2][arraySize];
        ArrayList<IndividualStatistics> arrayList = new ArrayList<>();
        int expectedAmountOfSales;
        int actualAmountOfSales;

        //Act
        expectedAmountOfSales = 3;
        Order order = new Order(true);
        order.addPizza(1);
        order.addPizza(1);
        order.addPizza(1);

        Statistics.updateArray(order, testFile, arrayList);
        actualAmountOfSales = arrayList.get(0).getAmountOfSales();

        //Assert
        assertEquals(expectedAmountOfSales, actualAmountOfSales);
    }

    @Test
    public void testReadFileToIndividualStatisticsObject() {
        //Arrange
        ArrayList<IndividualStatistics> testArrayList
                = new ArrayList<IndividualStatistics>();
        Statistics.createFile(testFile, testArrayList);
        String expectedName = "Marinara";
        int expectedNumber = 0;
        String actualName;
        int actualNumber;

        //Act
        Statistics.readFile(testArrayList, testFile);
        actualName = testArrayList.get(1).getPizzaName();
        actualNumber = testArrayList.get(1).getAmountOfSales();

        //Assert
        assertEquals(expectedName, actualName);
        assertEquals(expectedNumber, actualNumber);

    }

    @Test
    public void testCreateArrayByLength() {
        //Arrange
        int expectedLength = 30;
        int actualLength;
        ArrayList<IndividualStatistics> arrayList
                = new ArrayList<>();

        //Act
        Statistics.createArray(arrayList);
        actualLength = arrayList.size();

        //Assert
        assertEquals(expectedLength, actualLength);
    }

    @Test
    public void testCreateArrayByContent() {
        //Arrrange
        String expectedPizzaName = "Margherita";
        String actualPizzaName;
        int expectedPizzaSales = 0;
        int actualPizzaSales;
        ArrayList<IndividualStatistics> arrayList
                = new ArrayList<>();

        //Act
        Statistics.createArray(arrayList);
        actualPizzaName = arrayList.get(0).getPizzaName();
        actualPizzaSales = arrayList.get(0).getAmountOfSales();

        //Assert
        assertEquals(expectedPizzaName, actualPizzaName);
        assertEquals(expectedPizzaSales, actualPizzaSales);
    }
    
    @Test
    public void testGetStatisticsByLength() {
        //Arrange
        int expMinimumLength = 450;
        int actualLength;
        boolean isLongerThan;
        ArrayList<IndividualStatistics> arrayList
                = new ArrayList<>();
        
        //Act
        Statistics.createArray(arrayList);
        actualLength = Statistics.getStatistics().length();
        isLongerThan = (actualLength > expMinimumLength);
        
        //Assert
        assertTrue(isLongerThan);
    }

}
