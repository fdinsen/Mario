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
        order = new Order();
    }

    /**
     * Test of discount method, of class Order.
     */
    @Test
    public void testDiscount() {
    }

    /**
     * Test of addPizza method, of class Order.
     */
    @Test
    public void testAddPizza() {     
    }

    /**
     * Test of addExtraTopping method, of class Order.
     */
    @Test
    public void testAddExtraTopping() {
    }
    
    @Test
    public void testCurrentTime() {
        
        //Arrange
        LocalDateTime orderCreatedAtTime = order.getOrderTime();
        
        //Act
        int expectedHour = LocalDateTime.now().getHour();
        int orderCreatedAtHour = orderCreatedAtTime.getHour();
        
        int expectedMinute = LocalDateTime.now().getMinute();
        int orderCreatedAtMinute = orderCreatedAtTime.getMinute();
        
        int expectedDate = LocalDateTime.now().getDayOfMonth();
        int orderCreatedAtDate = orderCreatedAtTime.getDayOfMonth();
        
        //Assert
        assertEquals(expectedHour, orderCreatedAtHour);
        assertEquals(expectedMinute, orderCreatedAtMinute);
        assertEquals(expectedDate, orderCreatedAtDate);
    }
    
}
