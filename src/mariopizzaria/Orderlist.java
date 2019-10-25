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
            orderInString += "Ordre Nr. " + (index + 1) + ". "
                    + " - Bestilingstidspunkt: " + orders.get(index).getOrderTime().getHour() + ":" + orders.get(index).getOrderTime().getMinute()
                    + "\n";
            //Tjek om navn og telefon nr er tilstede og tilføj det til stringen hvis det er
            if(orders.get(index).getCostumerName() != null){
                orderInString += "Kunde Navn: " + orders.get(index).getCostumerName() + "\n";
            }
            if (orders.get(index).isOrderedByPhone()) {
                orderInString += "Kunde Tlf: " + orders.get(index).getCostumerPhoneNumber() + "\n";
            }

            //Udskriver en linje for hver pizza i orderen
            for (int i = 0; i < orders.get(index).getOrderSize(); i++) {
                orderInString += "-----\n" + orders.get(index).getPizzaAt(i).getPizzaName();

                //Udskriver prisen for pizzaen
                orderInString += "\t" + orders.get(index).getPizzaAt(i).getPizzaPrice() + " kr.";

                //Tjekker om der er tilføjet toppings
                if (!orders.get(index).getPizzaAt(i).getToppingsAdded().isEmpty()) {
                    //Udskriver ekstra toppings på pizzaen
                    ToppingsAddedTotalPrice = orders.get(index).getPizzaAt(i).getToppingsAddedTotalPrice();
                    orderInString += "\nEkstra Toppings("+ ToppingsAddedTotalPrice + " kr.)\t";
                    for (ExtraTopping extraTopping : orders.get(index).getPizzaAt(i).getToppingsAdded()) {
                        //For hver topping indsæt antal og navn i stringen
                        orderInString += "x" + extraTopping.getExtraToppingQuantity();
                        orderInString += " " + extraTopping.getExtraToppingName();

                        //Hvis der er mere end en topping, tilføj komma
                        if (orders.get(index).getPizzaAt(i).getToppingsAdded().size() > 1) {
                            orderInString += ", ";
                        }
                    }
                }
                orderInString += "\n";
            }
            orderInString += "-----\n";

            //Tilføjer afheningstidspunkt samt totalpris
            orderInString += "Afhentnings tidspunkt: " + orders.get(index).getPickupTime().getHour() + ":"
                    + orders.get(index).getPickupTime().getMinute() + " "
                    + "\nTotal Pris: " + orders.get(index).getTotalPrice();

            counter++;
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
