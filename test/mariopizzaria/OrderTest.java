/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariopizzaria;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author <Frederik Keis Dinsen>
 */
public class OrderTest {
    
    Order order;
    
    
    public OrderTest() {
        order = new Order(true);
    }

    /**
     * Test of discount method, of class Order.
     */
    @Test
    public void testDiscount() {
        //Arrange
        int originalPrice;
        int actualDiscountedPrice;
        int expectedDiscountedPrice;
        order.setTotalPrice(252);
        
    }
    
    @Test
    public void testOrderTime() {
        //Arrange
        int expectedHour;
        int actuallyCreatedAtHour;
        int expectedMinute;
        int actuallyCreatedAtMinute;
        int expectedDate;
        int actuallyCreatedAtDate;
        
        //Act
        LocalDateTime orderCreatedAtTime = order.getOrderTime();
        
        expectedHour = LocalDateTime.now().getHour();
        actuallyCreatedAtHour = orderCreatedAtTime.getHour();
        
        expectedMinute = LocalDateTime.now().getMinute();
        actuallyCreatedAtMinute = orderCreatedAtTime.getMinute();
        
        expectedDate = LocalDateTime.now().getDayOfMonth();
        actuallyCreatedAtDate = orderCreatedAtTime.getDayOfMonth();
        
        //Assert
        assertEquals(expectedHour, actuallyCreatedAtHour);
        assertEquals(expectedMinute, actuallyCreatedAtMinute);
        assertEquals(expectedDate, actuallyCreatedAtDate);
    }
    
    @Test
    public void testPickupTime() {
        //Arrange
        int waitTime = 15;
        int expectedPickupHour;
        int actualPickupHour;
        int expectedPickupMinute;
        int actualPickupMinute;
        
        //Act
        LocalDateTime orderPickupAtTime = order.getPickupTime();
        
        expectedPickupHour = LocalDateTime.now().plusMinutes(waitTime).getHour();
        actualPickupHour = orderPickupAtTime.getHour();
        
        expectedPickupMinute = LocalDateTime.now().plusMinutes(waitTime).getMinute();
        actualPickupMinute = orderPickupAtTime.getMinute();
        
        //Assert
        assertEquals(expectedPickupHour, actualPickupHour);
        assertEquals(expectedPickupMinute, actualPickupMinute);
        
    }
    
}
