package mariopizzaria;

import java.util.ArrayList;

/**
 *
 * @author <Oliver Vang>
 */
public class Orderlist {
    private static Orderlist Order_List_Instance = null;
    private ArrayList<Order> listOfOrders = new ArrayList<>();
    private Statistics statistics = Statistics.getInstance();
    
    private Orderlist() {
    }
    
    public static Orderlist getInstance() 
    { 
        return OrderlistHolder.INSTANCE;
    }



    private static class OrderlistHolder {
        private static final Orderlist INSTANCE = new Orderlist();
    }
    
    
    int createOrder(boolean orderByPhone) {
        //Laver ny order og tilføjer til orders array
        listOfOrders.add(new Order(orderByPhone));
        return listOfOrders.size();

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

    void deleteOrder(int index) {
        this.listOfOrders.remove(index);
    }

    Order getOrder(int index) {
        return listOfOrders.get(index-1);
    }

    
    int getPizzaCountInOrder(int orderNumberInArray){
        return listOfOrders.get(orderNumberInArray-1).getNumberOfPizzas();
    }
    
    void deletePizzaFromOrder(int orderNumberInArray, int pizzaNumberInOrder){
        listOfOrders.get(orderNumberInArray-1).removePizzaFromOrder(pizzaNumberInOrder-1);
    }

    int getOrdersListSize() {
        return listOfOrders.size();
    }

    ArrayList<Order> getActiveOrders(){
        return listOfOrders;
    }

    String getOrderTimeMinutes(int index){
        return getOrder(index).getOrderTimeMinute();
    }

    String getPickUpTimeHour(int index){
        return getOrder(index).getPickupTimeHour();
    }

    String getPickUpTimeMinutes(int index){
        return getOrder(index).getPickupTimeMinute();
    }

    String getOrderTimeHour(int index){
        return getOrder(index).getOrderTimeHour();
    }

    String getCustomerName(int index){
        return getOrder(index).getCostumerName();
    }

    String getCustomerPhone(int index){
        return getOrder(index).getCostumerName();
    }

    Boolean isOrderedByPhone(int index){
        return getOrder(index).isOrderedByPhone();
    }

    ArrayList<Pizza> getAllPizzasInOrder(int index){
        return getOrder(index).getAllPizzasInOrder();
    }

    public double getTotalPrice(int index) {
        return getOrder(index).getTotalPrice();
    }
}
