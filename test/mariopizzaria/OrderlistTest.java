/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariopizzaria;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author <Oliver Vang>
 */
public class OrderlistTest {

    Orderlist orderlist = Orderlist.getInstance();
    /**
     * Test of createOrder method, of class Orderlist.
     */
    
    @Before
    public void clearArraylistBeforeEachTest() {
        orderlist.clearOrderlist();
    }
    
    @Test
    public void testCreateOrderArrayLength() {
        
        int expectedSize = 1;

        //Laver en orderlist og kalder createOrder med orderByPhone sat til true
        
        orderlist.createOrder(true);

        //Assert
        assertEquals(expectedSize, orderlist.getOrdersListSize());

    }

    /**
     * Test of completeOrder method, of class Orderlist.
     */
    @Test
    public void testCompleteOrder_int() {
        //TODO add test af statistic
        int exspectedLength = 1;
        
        orderlist.createOrder(true);
        orderlist.createOrder(true);

        //Act
        //Fjerner orderen fra arrayet
        orderlist.completeOrder(0);
        int actualLength = orderlist.getOrdersListSize();
        //Assert
        //Tjekker om listen indenholder orderen der er blevet fjernet
        assertEquals(exspectedLength, actualLength);

    }

    /**
     * Test of completeOrder method, of class Orderlist.
     */
    @Test
    public void testCompleteOrder_int_boolean() {
        //TODO add test af statistic
        int exspectedLength = 1;
        
        orderlist.createOrder(true);
        orderlist.createOrder(true);

        //Act
        //Fjerner orderen fra arrayet
        orderlist.completeOrder(0,true);
        int actualLength = orderlist.getOrdersListSize();
        //Assert
        //Tjekker om listen indenholder orderen der er blevet fjernet
        assertEquals(exspectedLength, actualLength);
    }
    /**
     * Test of deleteOrder method, of class Orderlist.
     */
    @Test
    public void testDeleteOrder() {
        //TODO add test af statistic
        int exspectedLength = 1;
        
        
        orderlist.createOrder(true);
        orderlist.createOrder(true);

        //Act
        //Fjerner orderen fra arrayet
        orderlist.deleteOrder(0);
        int actualLength = orderlist.getOrdersListSize();
        //Assert
        //Tjekker om listen indenholder orderen der er blevet fjernet
        assertEquals(exspectedLength, actualLength);
    }
}
