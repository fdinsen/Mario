package mariopizzaria;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Orderlist {

    private ArrayList<Order> Orders;

    public Orderlist() {
        Orders = new ArrayList<Order>();
    }
    
    

    public void createOrder(LocalDateTime pickupTime, boolean orderByPhone) {
        Order order = new Order(pickupTime, orderByPhone);
        Orders.add(order);
        System.out.println(Orders.get(0).toString());
        
    }

    public void completeOrder(int index) {
    }

    public void completeOrder(int index, boolean lostOrder) {
    }

    public ArrayList<Order> ShowAllCurrentOrders() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Order showOrder(int index) {
        return null;
    }

    public void deleteOrder(int index) {
    }

    public Order getOrder(int index) {
        return Orders.get(index);
    }

    public ArrayList<Order> getOrdersList() {
        return Orders;
    }

    
    
}
