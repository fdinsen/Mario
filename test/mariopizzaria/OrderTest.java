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
        order.addPizza(2);
        
    
        
    }

    /**
     * Test of addExtraTopping method, of class Order.
     */
    @Test
    public void testAddExtraTopping() {
    }
    
    @Test
    public void testSetAndGetCurrentTime() {
        //SimpleDateFormat pizza time
    }
    
}
