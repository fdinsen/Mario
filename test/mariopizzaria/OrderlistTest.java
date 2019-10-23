/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariopizzaria;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author <Oliver Vang>
 */
public class OrderlistTest {

    /**
     * Test of createOrder method, of class Orderlist.
     */
    @Test
    public void testCreateOrderArrayLength() {
        //Opretter en ordre 
        ArrayList<Order> expectedList = new ArrayList<Order>();
        String str = "2019-10-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime pickupTime = LocalDateTime.parse(str, formatter);
        Order order = new Order(pickupTime, true);
        expectedList.add(order);
        
        //Laver en orderlist og kalder createOrder med orderByPhone sat til true
        Orderlist actual = new Orderlist();
        actual.createOrder(pickupTime, true);

        //Assert
        Assert.assertEquals(expectedList.size(), actual.getOrdersList().size());
        
    }

    /**
     * Test of completeOrder method, of class Orderlist.
     */
    @Test
    public void testCompleteOrder_int() {
    }

    /**
     * Test of completeOrder method, of class Orderlist.
     */
    @Test
    public void testCompleteOrder_int_boolean() {
    }

    /**
     * Test of ShowAllCurrentOrders method, of class Orderlist.
     */
    @Test
    public void testShowAllCurrentOrders() {
    }

    /**
     * Test of showOrder method, of class Orderlist.
     */
    @Test
    public void testShowOrder() {
    }

    /**
     * Test of deleteOrder method, of class Orderlist.
     */
    @Test
    public void testDeleteOrder() {
    }

    /**
     * Test of getOrder method, of class Orderlist.
     */
    @Test
    public void testGetOrder() {
    }

}
