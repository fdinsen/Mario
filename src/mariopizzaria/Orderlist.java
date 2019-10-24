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
        Order order = new Order(orderByPhone);
        orders.add(order);
        return orders.size()-1;
        
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

    //Retunere en String der viser alle ordre på en pæn måde
    public String ShowAllCurrentOrders() {
        int counter = 1;
        String ordersInString = "";

        //Bygger en tekst String med alle ordrene, hvis der er 1 eller flere ordre i arrayet
        if (orders.size() > 0) {
            for (Order order : orders) {
                ordersInString += 
                        "Order Nr. " + counter + ". " 
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
        String ordersInString = "";

        //Bygger en tekst String for ordreren, hvis der valgt en ordre
        if (orders.size() > 0) {
            ordersInString += "Ordre Nr. " + (index+1) + ". "
            + "Bestilingstidspunkt: " + orders.get(index).getOrderTime().getHour() + ":" + orders.get(index).getOrderTime().getMinute() + "\n";
            
            //Udskriver en linje for hver pizza i orderen
            for (int i = 0; i < orders.get(index).getOrderSize(); i++) {
                ordersInString += orders.get(index).getPizzaAt(i).getPizzaName();
                
                //Tjekker om der er tilføjet toppings
                if(orders.get(index).getPizzaAt(i).getToppingsAdded() != null){
                    //Udskriver ekstra toppings på pizzaen
                    ordersInString += "\nEkstra Toppings:\t";
                    for (ExtraTopping extraTopping : orders.get(index).getPizzaAt(i).getToppingsAdded()) {
                        ordersInString += extraTopping;
                    }
                }
            }
               
            ordersInString += "\nAfhentnings tidspunkt: " + orders.get(index).getPickupTime().getHour() + ":"
                    + orders.get(index).getPickupTime().getMinute() + " "
                    + "\nTotal Pris: " + orders.get(index).getTotalPrice();
            
            counter++;
        } else {
            ordersInString = "Der er ikke oprettet en ordre endnu";
        }
        return ordersInString;
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
