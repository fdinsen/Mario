
package mariopizzaria;

public class ActiveOrdersUI {

    private static ActiveOrdersUI Order_Ui_Instance = null; 
    private inputValidation inputVal = inputValidation.getInstance();
    private Orderlist orderlist = Orderlist.getInstance();
    private NewOrderUI newOrderUI = NewOrderUI.getInstance();
    
    private ActiveOrdersUI(){
        
    }
    
    public static ActiveOrdersUI getInstance() 
    { 
        return ActiveOrdersUIHolder.INSTANCE;
    } 
    
    private static class ActiveOrdersUIHolder {
        private static final ActiveOrdersUI INSTANCE = new ActiveOrdersUI();
    }
    
     public void showActiveOrdersDialog() {
        boolean exit = false;
        int selection;

        do {
            System.out.println("Mario's Pizzaria - Aktive Ordre");
            System.out.println("-------------------------");
            showAllCurrentOrders();
            System.out.println("-------------------------");
            System.out.println("1. - Tilbage til hovedmenu");
            System.out.println("2. - Lav ny order");
            if (orderlist.getOrdersListSize() > 0) {
                System.out.println("3. - Fjern en ordre");
            }
            if (orderlist.getOrdersListSize() > 0) {
                System.out.println("4. - Se en ordre");
            }
            if (orderlist.getOrdersListSize() > 0) {
                System.out.println("5. - Færdiggør en ordre");
            }
            selection = inputVal.getUserInput();

            if (selection != -1) {
                switch (selection) {
                    case 1:
                        //Gå tilbage til hoved menuen
                        exit = true;
                        break;
                    case 2:
                        //lav ny ordre
                        newOrderUI.makeNewOrderDialog();
                        break;
                    case 3:
                        //Fjern en ordre
                        //Se en ordre
                        if (orderlist.getOrdersListSize() > 0) {
                            deleteOrderDialog();
                        } else {
                            System.err.println(selection + " Er ikke en mulighed i menuen 'Aktive Ordre', prøv igen");
                        }
                        break;
                    case 4:
                        //Se en ordre
                        if (orderlist.getOrdersListSize() > 0) {
                            seeOrderDialog();
                        } else {
                            System.err.println(selection + " Er ikke en mulighed i menuen 'Aktive Ordre', prøv igen");
                        }
                        break;
                    case 5:
                        //Se en ordre
                        if (orderlist.getOrdersListSize() > 0) {
                            completeOrderDialog();
                        } else {
                            System.err.println(selection + " Er ikke en mulighed i menuen 'Aktive Ordre', prøv igen");
                        }
                        break;

                    default:
                        System.err.println(selection + " fejl i dit input til menuen 'Aktive Ordre', prøv igen");
                }
            }
        } while (!exit);
    }
     
     public void seeOrderDialog() {
        boolean exit = false;
        boolean seeOtherOrder = false;
        boolean correctNumber;
        int orderNumber;
        int selection;
        do {
            System.out.println("Mario's Pizzaria - Order");
            System.out.println("-------------------------");
            showAllCurrentOrders();
            System.out.println("-------------------------");
            System.out.println("Indtast ordre nummeret på ordren du vil se");

            //Får ordrenummeret af brugeren
            orderNumber = getOrderNumber();

            //Udskriver orderen
            System.out.println("Mario's Pizzaria - Order " + orderNumber);
            System.out.println("-------------------------");
            showOrder(orderNumber);
            System.out.println("-------------------------");
            do {
                System.out.println("1 - For at gå tilbage");
                System.out.println("2 - Se anden ordre");
                System.out.println("3 - Tilføj pizza til ordre");
                System.out.println("4 - Færdigør ordre");
                if (orderlist.getPizzaCountInOrder(orderNumber) > 1) {
                    System.out.println("5 - Fjern pizza fra ordre");
                }

                selection = inputVal.getUserInput();

                switch (selection) {
                    case 1:
                        //Gå tilbage
                        seeOtherOrder = false;
                        exit = true;
                        break;
                    case 2:
                        //Se en anden ordre
                        seeOtherOrder = false;
                        break;
                    case 3:
                        //Tilføj pizza til ordre
                        newOrderUI.addPizzaDialog(orderNumber);
                        seeOtherOrder = false;
                        exit = true;
                        break;
                    case 4:
                        completeOrder(orderNumber);
                        seeOtherOrder = false;
                        exit = true;
                        break;
                    case 5:
                        if (orderlist.getPizzaCountInOrder(orderNumber) > 1) {
                            //Fjern pizza til ordre
                            deletePizzaFromOrderDialog(orderNumber - 1);
                            seeOtherOrder = false;
                            exit = true;
                        } else {
                            System.err.println(selection + " Er ikke en mulighed, prøv igen");
                        }
                        break;

                    default:
                        System.err.println(selection + " Er ikke en mulighed, prøv igen");
                }
            } while (seeOtherOrder);

        } while (!exit);
    }
     
     private void deleteOrderDialog() {
        boolean correctNumber;
        int orderNumber;
        do {
            System.out.println("Mario's Pizzaria - Fjern Ordre");
            System.out.println("-------------------------");
            showAllCurrentOrders();
            System.out.println("-------------------------");
            System.out.println("Indtast ordre nummeret på ordren du vil fjerne");

            orderNumber = inputVal.getUserInput();
            if (orderNumber > 0 && orderNumber < orderlist.getOrdersListSize() + 1) {
                //Er et korrekt ordre nr
                correctNumber = true;
            } else {
                //Er ikke et korrekt ordre nr
                correctNumber = false;
                System.out.println(orderNumber + " er ikke mellem 1 og antallet af ordre (" + orderlist.getOrdersListSize() + ")");
            }

            //Bliver ved indtil korrekt svar
        } while (!correctNumber);

        //Fjerne ordren fra ordre arrayet og går tilbage igen
        orderlist.deleteOrder(orderNumber - 1);
    }

    private void deletePizzaFromOrderDialog(int orderNumber) {
        boolean correctNumber;
        int pizzaNumber;

        do {
            System.out.println("Mario's Pizzaria - Fjern Pizza fra Ordre");
            System.out.println("-------------------------");
            System.out.println(orderlist.showAllPizzasInOrder(orderNumber));
            System.out.println("-------------------------");
            System.out.println("Indtast nummeret ud for pizzaen du vil fjerne fra orderen");

            pizzaNumber = inputVal.getUserInput();
            if (pizzaNumber > 0 && pizzaNumber < orderlist.getPizzaCountInOrder(orderNumber) + 1) {
                //Er et korrekt ordre nr
                correctNumber = true;
            } else {
                //Er ikke et korrekt ordre nr
                correctNumber = false;
                System.out.println(pizzaNumber + " er ikke mellem 1 og antallet af ordre (" + orderlist.getOrdersListSize() + ")");
            }

            //Bliver ved indtil korrekt svar
        } while (!correctNumber);

        //Fjerne pizza fra orderen og går tilbage
        orderlist.deletePizzaFromOrder(orderNumber, pizzaNumber - 1);
    }
    
    private void completeOrder(int orderNumber) {
        orderlist.completeOrder(orderNumber - 1);
    }
    
    private void completeOrderDialog() {
        System.out.println("Mario's Pizzaria - Færdiggør en ordre");
        System.out.println("-------------------------");
        showAllCurrentOrders();
        System.out.println("-------------------------");
        System.out.println("Indtast ordre nummeret på ordren du vil færdiggøre");

        //Fjerne ordren fra ordre arrayet og går tilbage igen
        //Minus en for at få det til at passe til listen
        orderlist.completeOrder(getOrderNumber() - 1);
    }
    
    private int getOrderNumber() {
        int orderNumber;
        boolean correctNumber;
        do {
            //
            orderNumber = inputVal.getUserInput();
            if (orderNumber > 0 && orderNumber < orderlist.getOrdersListSize() + 1) {
                //Er et korrekt ordre nr
                correctNumber = true;
            } else {
                //Er ikke et korrekt ordre nr
                correctNumber = false;
                System.out.println(orderNumber + " er ikke mellem 1 og antallet af ordre (" + orderlist.getOrdersListSize() + ")");
            }

            //Bliver ved indtil korrekt svar
        } while (!correctNumber);
        return orderNumber;
    }

    public void showAllCurrentOrders() {
        int counter = 1;
        String ordersInString = "";

        //Bygger en tekst String med alle ordrene, hvis der er 1 eller flere ordre i arrayet
        if (orderlist.getOrdersListSize() > 0) {
            for (Order order : orderlist.getActiveOrders()) {
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
        System.out.println(ordersInString);
    }

    public void showOrder(int index) {
        //Trækker en da Orderlisten for brugeren starter på 1
        index--;
        String orderInString = "";

        //Bygger en tekst String for ordreren, hvis der valgt en ordre
        if (orderlist.getOrdersListSize() > 0) {
            printOrder(index);
        } else {
            orderInString = "Der er ikke oprettet en ordre endnu";
        }
        System.out.println(orderInString);
    }

    public void printOrder(int index){
        int counter = 1;
        String stringOrder = "";
        stringOrder += "Bestilingstidspunkt: " + orderlist.getOrderTimeHour(index) + ":" + orderlist.getOrderTimeMinutes(index)
                + "\n";
        //Tjek om navn og telefon nr er tilstede og tilføj det til stringen hvis det er
        if (orderlist.getCustomerName(index) != null) {
            stringOrder += "Kunde Navn: " + orderlist.getCustomerName(index) + "\n";
        }
        if (orderlist.isOrderedByPhone(index)) {
            stringOrder += "Kunde Tlf: " + orderlist.getCustomerPhone(index) + "\n";
        }
        stringOrder += "-----\n";
        for (Pizza pizza : orderlist.getAllPizzasInOrder(index)) {
            stringOrder += counter + ". " + pizza.getPizzaName() + " -- " + pizza.getPizzaSizeString();

            counter++;

            //Udskriver prisen for pizzaen
            stringOrder += "\t" + pizza.getPizzaPrice() + " kr.";

            //Tjekker om der er toppings på pizzaewn
            if (!pizza.getToppingsAdded().isEmpty()) {
                //Udskriver prisen for pizzaen
                stringOrder += "\nEkstra Toppings: ";
                for (ExtraTopping extraTopping : pizza.getToppingsAdded()) {
                    //For hver topping indsæt antal og navn i stringen
                    stringOrder += "x" + extraTopping.getExtraToppingQuantity();
                    stringOrder += " " + extraTopping.getExtraToppingName();

                    //Hvis der er mere end en topping, tilføj komma
                    if (pizza.getToppingsAdded().size() > 1) {
                        stringOrder += ", ";
                    }
                }

            }
            stringOrder += "\n-----\n";
        }
        stringOrder += "Afhentnings tidspunkt: " + orderlist.getPickUpTimeHour(index) + ":"
                + orderlist.getPickUpTimeMinutes(index) + " "
                + "\nTotal Pris: " + orderlist.getTotalPrice(index);
        System.out.println(stringOrder);
    }
}
