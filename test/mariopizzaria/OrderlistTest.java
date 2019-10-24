/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariopizzaria;

import static org.junit.Assert.*;
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
        int expectedSize = 1;

        //Laver en orderlist og kalder createOrder med orderByPhone sat til true
        Orderlist actual = new Orderlist();
        actual.createOrder(true);

        //Assert
        assertEquals(expectedSize, actual.getOrdersList().size());

    }

    /**
     * Test of completeOrder method, of class Orderlist.
     */
    @Test
    public void testCompleteOrder_int() {
        //TODO add test af statistic

        Orderlist orderlist = new Orderlist();
        orderlist.createOrder(true);
        orderlist.createOrder(true);

        //Gemmer orderen, det skal fjernes
        Order orderToBeRemoved = orderlist.getOrdersList().get(1);

        //Act
        //Fjerner orderen fra arrayet
        orderlist.completeOrder(1);

        //Assert
        //Tjekker om listen indenholder orderen der er blevet fjernet
        assertFalse(orderlist.getOrdersList().contains(orderToBeRemoved));

    }

    /**
     * Test of completeOrder method, of class Orderlist.
     */
    @Test
    public void testCompleteOrder_int_boolean() {
        //TODO add test af statistic

        Orderlist orderlist = new Orderlist();
        orderlist.createOrder(true);
        orderlist.createOrder(true);

        //Gemmer orderen, det skal fjernes
        Order orderToBeRemoved = orderlist.getOrdersList().get(1);

        //Act
        //Fjerner orderen fra arrayet
        orderlist.completeOrder(1, true);

        //Assert
        //Tjekker om listen indenholder orderen der er blevet fjernet
        assertFalse(orderlist.getOrdersList().contains(orderToBeRemoved));
    }

    /**
     * Test of ShowAllCurrentOrders method, of class Orderlist.
     */
    @Test
    public void testShowAllCurrentOrders() {
        //TODO Find ud af hvordan denne skal testes
        Orderlist orderlist = new Orderlist();
        orderlist.createOrder(true);
        orderlist.createOrder(true);
        orderlist.createOrder(true);
        //Pizza pizza = new Pizza("Torino", 200);
        //orderlist.getOrder(0).addPizza(0);
        // orderlist.getOrder(0).addPizza(0);

        orderlist.ShowAllCurrentOrders();
    }

    /**
     * Test of showOrder method, of class Orderlist.
     */
    @Test
    public void testShowOrder() {
        //TODO find ud af hvordan det skal testes
        String expected = "Ordre 1. Bestilingstidspunkt: 8:39\n" +
                  "Marinara\n"
                + "Quattro Formaggi\n"
                + "Afhentnings tidspunkt: 8:54 \n"
                + "Total Pris: 0.0";
        //TODO Færigør test når Order klasse er færdig
        Orderlist orderlist = new Orderlist();
        orderlist.createOrder(true);
        orderlist.createOrder(true);
        orderlist.createOrder(true);
        orderlist.getOrder(0).addPizza(1);
        orderlist.getOrder(0).addPizza(5);

        String actual = orderlist.showOrder(1);
        
        //assertEquals(expected, actual);
    }

    /**
     * Test of deleteOrder method, of class Orderlist.
     */
    @Test
    public void testDeleteOrder() {
        Orderlist orderlist = new Orderlist();
        orderlist.createOrder(true);
        orderlist.createOrder(true);

        //Gemmer orderen, det skal fjernes
        Order orderToBeRemoved = orderlist.getOrdersList().get(1);

        //Act
        //Fjerner orderen fra arrayet
        orderlist.deleteOrder(1);

        //Assert
        //Tjekker om listen indenholder orderen der er blevet fjernet
        assertFalse(orderlist.getOrdersList().contains(orderToBeRemoved));
    }

    /**
     * Test of getOrder method, of class Orderlist.
     */
    @Test
    public void testGetOrder() {
        //Skal denne testes?
    }

}
