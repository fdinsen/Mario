package mariopizzaria;

import java.util.ArrayList;

/**
 *
 * @author <Oliver Vang>
 */
public class Orderlist {

    private ArrayList<Order> orders;

    public Orderlist() {
        orders = new ArrayList<Order>();
    }

    public int createOrder(boolean orderByPhone) {
        //Laver ny order og tilføjer til orders array
        orders.add(new Order(orderByPhone));
        return orders.size() - 1;

    }

    public void completeOrder(int index) {
        //Kalder Statistics
        Statistics.updateStats(orders.get(index));
        //Fjerner orderen fra listen
        this.orders.remove(index);
    }
    
    //Bruges ikke endnu
    public void completeOrder(int index, boolean lostOrder) {
        //Kalder Statistics - Statistics tager lige nu ikke lostOrder med
        //Statistics.updateStats(orders.get(index),lostOrder);
        Statistics.updateStats(orders.get(index));
        
        //Fjerner orderen fra listen
        this.orders.remove(index);
    }

    //Retunere en String der viser alle ordre på en pæn måde
    public String ShowAllCurrentOrders() {
        int counter = 1;
        String ordersInString = "";

        //Bygger en tekst String med alle ordrene, hvis der er 1 eller flere ordre i arrayet
        if (orders.size() > 0) {
            for (Order order : orders) {
                ordersInString
                        += "Order Nr. " + counter + ". "
                        + "Antal pizzaer: " + order.getOrderSize() + "\n"
                        + "Afhentnings tidspunkt: " + order.getPickupTime().getHour() + ":" + order.getPickupTime().getMinute()
                        + ", Total Pris: " + order.getTotalPrice() + "\n";

                counter++;
            }
        } else {
            ordersInString = "Der er ikke oprettet en ordre endnu";
        }
        //System.out.println(ordersInString);
        return ordersInString;
    }

    public String showOrder(int index) {
        //Trækker en da Orderlisten for brugeren starter på 1
        index--;
        int counter = 1;
        double ToppingsAddedTotalPrice;
        String orderInString = "";

        //Bygger en tekst String for ordreren, hvis der valgt en ordre
        if (orders.size() > 0) {
            System.out.println(orders.get(index).toString());
        } else {
            orderInString = "Der er ikke oprettet en ordre endnu";
        }
        return orderInString;
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
    
    public String showAllPizzasInOrder(int orderNumberInArray){
        return orders.get(orderNumberInArray).toString();
    }
    
    public int getPizzaCountInOrder(int orderNumberInArray){
        return orders.get(orderNumberInArray).getNumberOfPizzas();
    }
    
    public void deletePizzaFromOrder(int orderNumberInArray, int pizzaNumberInOrder){
        orders.get(orderNumberInArray).removePizzaFromOrder(pizzaNumberInOrder);
    }

}
