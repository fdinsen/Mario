package mariopizzaria;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Orderlist {

    private ArrayList<Order> orders;

    public Orderlist() {
        orders = new ArrayList<Order>();
    }

    public void createOrder(boolean orderByPhone) {
        //Laver ny order og tilføjer til orders array
        Order order = new Order(orderByPhone);
        orders.add(order);

    }

    public void completeOrder(int index) {
        //TODO Tilføj statistic kald

        //Fjerner orderen fra listen
        this.orders.remove(index);
    }

    public void completeOrder(int index, boolean lostOrder) {
        //TODO Tilføj statistic kald med lostOrder

        this.orders.remove(index);
    }

    public String ShowAllCurrentOrders() {
        int counter = 1;
        String ordersInString = "";
        if (orders.size() > 0) {
            for (Order order : orders) {
                ordersInString += counter + ". Afhentnings tidspunkt: " + order.getPickupTime() + "Pris:" + order.getTotalPrice() + "\n";
                System.out.println(ordersInString);
                counter++;
            }
        }else{
            ordersInString = "Der er ikke oprettet en ordre endnu";
        }

        return ordersInString;
    }

    public Order showOrder(int index) {
        return null;
    }

    public void deleteOrder(int index) {
        this.orders.remove(index);
    }

    public Order getOrder(int index) {
        return orders.get(index);
    }

    public ArrayList<Order> getOrdersList() {
        return orders;
    }

}
