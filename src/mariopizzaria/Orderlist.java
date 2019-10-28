package mariopizzaria;

import java.util.ArrayList;

/**
 *
 * @author <Oliver Vang>
 */
public class Orderlist {
    private static Orderlist Order_List_Instance = null; 
    private ArrayList<Order> ListOfOrders;

    private Orderlist() {
        ListOfOrders = new ArrayList<>();
    }
    
    public static Orderlist getInstance() 
    { 
        if (Order_List_Instance == null) 
            Order_List_Instance = new Orderlist(); 
  
        return Order_List_Instance; 
    } 
    
    int createOrder(boolean orderByPhone) {
        //Laver ny order og tilføjer til orders array
        ListOfOrders.add(new Order(orderByPhone));
        return ListOfOrders.size() - 1;

    }

    void completeOrder(int index) {
        //Kalder Statistics
        Statistics.updateStats(ListOfOrders.get(index));
        //Fjerner orderen fra listen
        this.ListOfOrders.remove(index);
    }
    
    //Bruges ikke endnu
    public void completeOrder(int index, boolean lostOrder) {
        //Kalder Statistics - Statistics tager lige nu ikke lostOrder med
        //Statistics.updateStats(orders.get(index),lostOrder);
        Statistics.updateStats(ListOfOrders.get(index));
        
        //Fjerner orderen fra listen
        this.ListOfOrders.remove(index);
    }

    //Retunere en String der viser alle ordre på en pæn måde
    public String ShowAllCurrentOrders() {
        int counter = 1;
        String ordersInString = "";

        //Bygger en tekst String med alle ordrene, hvis der er 1 eller flere ordre i arrayet
        if (ListOfOrders.size() > 0) {
            for (Order order : ListOfOrders) {
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
        if (ListOfOrders.size() > 0) {
            System.out.println(ListOfOrders.get(index).toString());
        } else {
            orderInString = "Der er ikke oprettet en ordre endnu";
        }
        return orderInString;
    }

    void deleteOrder(int index) {
        this.ListOfOrders.remove(index);
    }

    Order getOrder(int index) {
        return ListOfOrders.get(index);
    }
    
    String showAllPizzasInOrder(int orderNumberInArray){
        return "" + ListOfOrders.get(orderNumberInArray);
    }
    
    int getPizzaCountInOrder(int orderNumberInArray){
        return ListOfOrders.get(orderNumberInArray).getNumberOfPizzas();
    }
    
    void deletePizzaFromOrder(int orderNumberInArray, int pizzaNumberInOrder){
        ListOfOrders.get(orderNumberInArray).removePizzaFromOrder(pizzaNumberInOrder);
    }

    int getOrdersListSize() {
        return ListOfOrders.size();
    }
}
