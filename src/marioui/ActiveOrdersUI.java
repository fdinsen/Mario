package marioui;

import mariopizzaria.ExtraTopping;
import mariopizzaria.Order;
import mariopizzaria.Orderlist;
import mariopizzaria.Pizza;
import mariopizzaria.inputValidation;

public class ActiveOrdersUI {

    private static ActiveOrdersUI Order_Ui_Instance = null;
    private inputValidation inputVal = inputValidation.getInstance();
    private Orderlist orderlist = Orderlist.getInstance();
    private NewOrderUI newOrderUI = NewOrderUI.getInstance();

    private ActiveOrdersUI() {

    }

    public static ActiveOrdersUI getInstance() {
        return ActiveOrdersUIHolder.INSTANCE;
    }

    private static class ActiveOrdersUIHolder {

        private static final ActiveOrdersUI INSTANCE = new ActiveOrdersUI();
    }

    void showActiveOrdersDialog() {
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
                        //Færdigør en ordre
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

    private void seeOrderDialog() {
        boolean exit = false;
        boolean seeOtherOrder = false;
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
                System.out.println("5 - Marker ordre som gået tabt");
                if (orderlist.getPizzaCountInOrder(orderNumber) > 1) {
                    System.out.println("6 - Fjern pizza fra ordre");
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
                        completeOrder(orderNumber,true);
                        seeOtherOrder = false;
                        exit = true;
                        break;
                    case 6:
                        if (orderlist.getPizzaCountInOrder(orderNumber) > 1) {
                            //Fjern pizza til ordre
                            deletePizzaFromOrderDialog(orderNumber);
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
        orderlist.deleteOrder(orderNumber);
    }

    private void deletePizzaFromOrderDialog(int orderNumber) {
        boolean correctNumber;
        int pizzaNumber;

        do {
            System.out.println("Mario's Pizzaria - Fjern Pizza fra Ordre");
            System.out.println("-------------------------");
            showAllPizzasInOrder(orderNumber);
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
        orderlist.deletePizzaFromOrder(orderNumber, pizzaNumber);
    }

    private void completeOrder(int orderNumber) {
        orderlist.completeOrder(orderNumber);
    }
    private void completeOrder(int orderNumber, boolean lostOrder) {
        orderlist.completeOrder(orderNumber -1, lostOrder);
    }

    private void completeOrderDialog() {
        System.out.println("Mario's Pizzaria - Færdiggør en ordre");
        System.out.println("-------------------------");
        showAllCurrentOrders();
        System.out.println("-------------------------");
        System.out.println("Indtast ordre nummeret på ordren du vil færdiggøre");

        //Fjerne ordren fra ordre arrayet og går tilbage igen
        //Minus en for at få det til at passe til listen
        orderlist.completeOrder(getOrderNumber());
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

    private void showAllCurrentOrders() {
        int counter = 1;
        StringBuilder ordersInString = new StringBuilder();

        //Bygger en tekst String med alle ordrene, hvis der er 1 eller flere ordre i arrayet
        if (orderlist.getOrdersListSize() > 0) {
            for (Order order : orderlist.getActiveOrders()) {
                ordersInString.append("Order Nr. ").append(counter).append(". ");
                ordersInString.append("Antal pizzaer: ").append(order.getOrderSize()).append("\n");
                ordersInString.append("Afhentnings tidspunkt: ").append(order.getPickupTimeHour()).append(":").append(order.getPickupTimeMinute());
                ordersInString.append(", Total Pris: ").append(order.getTotalPrice());
                if (orderlist.getOrdersListSize() != counter) {
                    ordersInString.append("\n-----\n");
                }
                counter++;
            }
        } else {
            ordersInString = new StringBuilder("Der er ikke oprettet en ordre endnu");
        }
        System.out.println(ordersInString);
    }

    private void showOrder(int index) {
        //Trækker en da Orderlisten for brugeren starter på 1
        String orderInString = "";

        //Bygger en tekst String for ordreren, hvis der valgt en ordre
        if (orderlist.getOrdersListSize() > 0) {
            printOrder(index);
        } else {
            orderInString = "Der er ikke oprettet en ordre endnu";
        }
        System.out.println(orderInString);
    }

    private void printOrder(int index) {
        int counter = 1;
        StringBuilder stringOrder = new StringBuilder();
        stringOrder.append("Bestilingstidspunkt: ").append(orderlist.getOrderTimeHour(index)).append(":").append(orderlist.getOrderTimeMinutes(index)).append("\n");
        //Tjek om navn og telefon nr er tilstede og tilføj det til stringen hvis det er
        if (orderlist.getCustomerName(index) != null) {
            stringOrder.append("Kunde Navn: ").append(orderlist.getCustomerName(index)).append("\n");
        }
        if (orderlist.isOrderedByPhone(index)) {
            stringOrder.append("Kunde Tlf: ").append(orderlist.getCustomerPhone(index)).append("\n");
        }
        stringOrder.append("-----\n");
        for (Pizza pizza : orderlist.getAllPizzasInOrder(index)) {
            stringOrder.append(counter).append(". ").append(pizza.getPizzaName()).append(" -- ").append(pizza.getPizzaSizeString());

            counter++;

            //Udskriver prisen for pizzaen
            stringOrder.append("\t").append(pizza.getPizzaPrice()).append(" kr.");

            //Tjekker om der er toppings på pizzaewn
            if (!pizza.getToppingsAdded().isEmpty()) {
                //Udskriver prisen for pizzaen
                stringOrder.append("\nEkstra Toppings: ");
                for (ExtraTopping extraTopping : pizza.getToppingsAdded()) {
                    //For hver topping indsæt antal og navn i stringen
                    stringOrder.append("x").append(extraTopping.getExtraToppingQuantity());
                    stringOrder.append(" ").append(extraTopping.getExtraToppingName());

                    //Hvis der er mere end en topping, tilføj komma
                    if (pizza.getToppingsAdded().size() > 1) {
                        stringOrder.append(", ");
                    }
                }

            }
            stringOrder.append("\n-----\n");
        }
        stringOrder.append("Afhentnings tidspunkt: ").append(orderlist.getPickUpTimeHour(index)).append(":").append(orderlist.getPickUpTimeMinutes(index)).append(" ").append("\nTotal Pris: ").append(orderlist.getTotalPrice(index));
        System.out.println(stringOrder);
    }

    private void showAllPizzasInOrder(int index) {
        StringBuilder stringOrder = new StringBuilder();
        int counter = 1;
        for (Pizza pizza : orderlist.getAllPizzasInOrder(index)) {
            stringOrder.append(counter).append(". ").append(pizza.getPizzaName()).append(" -- ").append(pizza.getPizzaSizeString());

            counter++;

            //Udskriver prisen for pizzaen
            stringOrder.append("\t").append(pizza.getPizzaPrice()).append(" kr.");

            //Tjekker om der er toppings på pizzaewn
            if (!pizza.getToppingsAdded().isEmpty()) {
                //Udskriver prisen for pizzaen
                stringOrder.append("\nEkstra Toppings: ");
                for (ExtraTopping extraTopping : pizza.getToppingsAdded()) {
                    //For hver topping indsæt antal og navn i stringen
                    stringOrder.append("x").append(extraTopping.getExtraToppingQuantity());
                    stringOrder.append(" ").append(extraTopping.getExtraToppingName());

                    //Hvis der er mere end en topping, tilføj komma
                    if (pizza.getToppingsAdded().size() > 1) {
                        stringOrder.append(", ");
                    }
                }

            }
            if (orderlist.getPizzaCountInOrder(index) >= counter) {
                stringOrder.append("\n-----\n");
            }
        }
        System.out.println(stringOrder);
    }
}
