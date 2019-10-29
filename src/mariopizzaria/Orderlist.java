package mariopizzaria;

import java.util.ArrayList;

/**
 *
 * @author <Oliver Vang>
 */
public class Orderlist {
    private static Orderlist Order_List_Instance = null; 
    private ArrayList<Order> listOfOrders;
    private Statistics statistics = Statistics.getInstance();
    
    private Orderlist() {
        listOfOrders = new ArrayList<>();
    }
    
    public static Orderlist getInstance() 
    { 
        return OrderlistHolder.INSTANCE;
    } 
    
    private static class OrderlistHolder {
        private static final Orderlist INSTANCE = new Orderlist();
    }
    
    public ArrayList<Order> getCurrentOrders() {
        return listOfOrders;
    } 
    
    
    int createOrder(boolean orderByPhone) {
        //Laver ny order og tilføjer til orders array
        listOfOrders.add(new Order(orderByPhone));
        return listOfOrders.size() - 1;

    }

    void completeOrder(int index) {
        //Kalder Statistics
        statistics.updateStats(listOfOrders.get(index));
        //Fjerner orderen fra listen
        this.listOfOrders.remove(index);
    }
    
    //Bruges ikke endnu
    public void completeOrder(int index, boolean lostOrder) {
        //Kalder Statistics - Statistics tager lige nu ikke lostOrder med
        //Statistics.updateStats(orders.get(index),lostOrder);
        statistics.updateStats(listOfOrders.get(index));
        
        //Fjerner orderen fra listen
        this.listOfOrders.remove(index);
    }

    //Retunere en String der viser alle ordre på en pæn måde
    public String ShowAllCurrentOrders() {
        int counter = 1;
        String ordersInString = "";

        //Bygger en tekst String med alle ordrene, hvis der er 1 eller flere ordre i arrayet
        if (listOfOrders.size() > 0) {
            for (Order order : listOfOrders) {
                ordersInString
                        += "Order Nr. " + counter + ". "
                        + "Antal pizzaer: " + order.getOrderSize() + "\n"
                        + "Afhentnings tidspunkt: " + order.getPickupTimeHour() + ":" + order.getPickupTimeMinute()
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
        String orderInString = "";

        //Bygger en tekst String for ordreren, hvis der valgt en ordre
        if (listOfOrders.size() > 0) {
            System.out.println(listOfOrders.get(index).toString());
        } else {
            orderInString = "Der er ikke oprettet en ordre endnu";
        }
        return orderInString;
    }

    void deleteOrder(int index) {
        this.listOfOrders.remove(index);
    }

    Order getOrder(int index) {
        return listOfOrders.get(index);
    }
    
    String showAllPizzasInOrder(int orderNumberInArray){
        return "" + listOfOrders.get(orderNumberInArray);
    }
    
    int getPizzaCountInOrder(int orderNumberInArray){
        return listOfOrders.get(orderNumberInArray).getNumberOfPizzas();
    }
    
    void deletePizzaFromOrder(int orderNumberInArray, int pizzaNumberInOrder){
        listOfOrders.get(orderNumberInArray).removePizzaFromOrder(pizzaNumberInOrder);
    }

    int getOrdersListSize() {
        return listOfOrders.size();
    }
}
