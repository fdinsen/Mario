package mariopizzaria;

import java.util.Scanner;

/**
 * @author <Oliver Vang>
 */
class UserDialog {

    private Orderlist activeOrderlist = new Orderlist();

    UserDialog() {
        MainMenuDialog();

    }

    

    private void showPizzaMenuDialog() {
        boolean exit = false;
        String pizzaer = "";
        int selection;

        do {
            System.out.println("Mario's Pizzaria - Pizza Menu");
            System.out.println("-------------------------");
            for (int i = 0; i < Menu.getListOfPizzaName().size(); i++) {
                pizzaer += i + 1 + ". " + Menu.getPizzaName(i) + "\t " + Menu.getPizzaPrice(i) + " Kr.\n\t"
                        + Menu.getPizzaDescription(i) + "\n";
            }
            System.out.println(pizzaer);
            System.out.println("-------------------------");
            System.out.println("1. - Tilbage til hovedmenu");
            System.out.println("2. - Lav ny order");

            selection = getUserInput();

            if (selection != -1) {
                switch (selection) {
                    case 1:
                        //Gå tilbage til hoved menuen
                        exit = true;
                        break;
                    case 2:
                        //lav ny ordre
                        makeNewOrderDialog();
                        break;
                    default:
                        System.err.println(selection + " Er ikke en mulighed i menuen 'PizzaMenu', prøv igen");
                }
            }
        } while (!exit);

    }

    private void showActiveOrdersDialog() {
        boolean exit = false;
        int selection;

        do {
            System.out.println("Mario's Pizzaria - Aktive Ordre");
            System.out.println("-------------------------");
            System.out.println(activeOrderlist.ShowAllCurrentOrders());
            System.out.println("-------------------------");
            System.out.println("1. - Tilbage til hovedmenu");
            System.out.println("2. - Lav ny order");
            if (activeOrderlist.getOrdersListSize() > 0) {
                System.out.println("3. - Fjern en ordre");
            }
            if (activeOrderlist.getOrdersListSize() > 0) {
                System.out.println("4. - Se en ordre");
            }
            if (activeOrderlist.getOrdersListSize() > 0) {
                System.out.println("5. - Færdiggør en ordre");
            }
            selection = getUserInput();

            if (selection != -1) {
                switch (selection) {
                    case 1:
                        //Gå tilbage til hoved menuen
                        exit = true;
                        break;
                    case 2:
                        //lav ny ordre
                        makeNewOrderDialog();
                        break;
                    case 3:
                        //Fjern en ordre
                        //Se en ordre
                        if (activeOrderlist.getOrdersListSize() > 0) {
                            deleteOrderDialog();
                        } else {
                            System.err.println(selection + " Er ikke en mulighed i menuen 'Aktive Ordre', prøv igen");
                        }
                        break;
                    case 4:
                        //Se en ordre
                        if (activeOrderlist.getOrdersListSize() > 0) {
                            seeOrderDialog();
                        } else {
                            System.err.println(selection + " Er ikke en mulighed i menuen 'Aktive Ordre', prøv igen");
                        }
                        break;
                    case 5:
                        //Se en ordre
                        if (activeOrderlist.getOrdersListSize() > 0) {
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
        boolean correctNumber;
        int orderNumber;
        int selection;
        do {
            System.out.println("Mario's Pizzaria - Order");
            System.out.println("-------------------------");
            System.out.println(activeOrderlist.ShowAllCurrentOrders());
            System.out.println("-------------------------");
            System.out.println("Indtast ordre nummeret på ordren du vil se");
            orderNumber = getOrderNumber();

            //Udskriver orderen
            System.out.println("Mario's Pizzaria - Order " + orderNumber);
            System.out.println("-------------------------");
            System.out.println(activeOrderlist.showOrder(orderNumber));
            System.out.println("-------------------------");
            do {
                System.out.println("1 - For at gå tilbage");
                System.out.println("2 - Se anden ordre");
                System.out.println("3 - Tilføj pizza til ordre");
                System.out.println("4 - Færdigør ordre");
                if (activeOrderlist.getPizzaCountInOrder(orderNumber - 1) > 1) {
                    System.out.println("5 - Fjern pizza fra ordre");
                }

                selection = getUserInput();

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
                        addPizzaDialog(orderNumber - 1);
                        seeOtherOrder = false;
                        exit = true;
                        break;
                    case 4:
                        completeOrder(orderNumber - 1);
                        seeOtherOrder = false;
                        exit = true;
                        break;
                    case 5:
                        if (activeOrderlist.getPizzaCountInOrder(orderNumber - 1) > 1) {
                            //Fjern pizza til ordre
                            deletePizzaDialog(orderNumber - 1);
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
            System.out.println(activeOrderlist.ShowAllCurrentOrders());
            System.out.println("-------------------------");
            System.out.println("Indtast ordre nummeret på ordren du vil fjerne");

            orderNumber = getUserInput();
            if (orderNumber > 0 && orderNumber < activeOrderlist.getOrdersListSize() + 1) {
                //Er et korrekt ordre nr
                correctNumber = true;
            } else {
                //Er ikke et korrekt ordre nr
                correctNumber = false;
                System.out.println(orderNumber + " er ikke mellem 1 og antallet af ordre (" + activeOrderlist.getOrdersListSize() + ")");
            }

            //Bliver ved indtil korrekt svar
        } while (!correctNumber);

        //Fjerne ordren fra ordre arrayet og går tilbage igen
        activeOrderlist.deleteOrder(orderNumber - 1);
    }

    private void deletePizzaDialog(int orderNumber) {
        boolean correctNumber;
        int pizzaNumber;

        do {
            System.out.println("Mario's Pizzaria - Fjern Pizza fra Ordre");
            System.out.println("-------------------------");
            System.out.println(activeOrderlist.showAllPizzasInOrder(orderNumber));
            System.out.println("-------------------------");
            System.out.println("Indtast nummeret ud for pizzaen du vil fjerne fra orderen");

            pizzaNumber = getUserInput();
            if (pizzaNumber > 0 && pizzaNumber < activeOrderlist.getPizzaCountInOrder(orderNumber) + 1) {
                //Er et korrekt ordre nr
                correctNumber = true;
            } else {
                //Er ikke et korrekt ordre nr
                correctNumber = false;
                System.out.println(pizzaNumber + " er ikke mellem 1 og antallet af ordre (" + activeOrderlist.getOrdersListSize() + ")");
            }

            //Bliver ved indtil korrekt svar
        } while (!correctNumber);

        //Fjerne pizza fra orderen og går tilbage
        activeOrderlist.deletePizzaFromOrder(orderNumber, pizzaNumber - 1);
    }

    private void completeOrderDialog() {
        System.out.println("Mario's Pizzaria - Færdiggør en ordre");
        System.out.println("-------------------------");
        System.out.println(activeOrderlist.ShowAllCurrentOrders());
        System.out.println("-------------------------");
        System.out.println("Indtast ordre nummeret på ordren du vil færdiggøre");

        //Fjerne ordren fra ordre arrayet og går tilbage igen
        //Minus en for at få det til at passe til listen
        activeOrderlist.completeOrder(getOrderNumber() - 1);
    }

    private int getOrderNumber() {
        int orderNumber;
        boolean correctNumber;
        do {
            //
            orderNumber = getUserInput();
            if (orderNumber > 0 && orderNumber < activeOrderlist.getOrdersListSize() + 1) {
                //Er et korrekt ordre nr
                correctNumber = true;
            } else {
                //Er ikke et korrekt ordre nr
                correctNumber = false;
                System.out.println(orderNumber + " er ikke mellem 1 og antallet af ordre (" + activeOrderlist.getOrdersListSize() + ")");
            }

            //Bliver ved indtil korrekt svar
        } while (!correctNumber);
        return orderNumber;
    }

    private void makeNewOrderDialog() {
        boolean exit = false;
        int selection;
        int orderArrayPosition;
        do {
            System.out.println("Mario's Pizzaria - Ny Ordre");
            System.out.println("-------------------------");
            System.out.println("Er denne ordre bestilt over telefonen?");
            System.out.println("1. - Ja");
            System.out.println("2. - Nej");
            System.out.println("3. - Annuller oprettelse af ordre");

            selection = getUserInput();

            if (selection != -1) {
                switch (selection) {
                    case 1:
                        //Er bestilt på telefon
                        orderArrayPosition = activeOrderlist.createOrder(true);
                        makeNewOrderByTelehoneDialog(orderArrayPosition);
                        addNameDialog(orderArrayPosition);
                        addPizzaDialog(orderArrayPosition);
                        exit = true;
                        break;
                    case 2:
                        //Er ikke bestilt på telefon
                        orderArrayPosition = activeOrderlist.createOrder(false);
                        addNameDialog(orderArrayPosition);
                        addPizzaDialog(orderArrayPosition);
                        exit = true;
                        break;
                    case 3:
                        //Annuller ordre
                        exit = true;
                        break;
                    default:
                        System.err.println(selection + " Er ikke en mulighed for valg af telefon eller ej, prøv igen");
                }
            }
        } while (!exit);
    }

    private void makeNewOrderByTelehoneDialog(int orderArrayPosition) {
        int input;

        System.out.println("Mario's Pizzaria - Ny Ordre");
        System.out.println("-------------------------");
        System.out.println("Indtast telefon nr.");

        //Bliver ved indtil et korrekt telefon nummer er indtastet
        do {
            input = getUserInput();
        } while (!isValidPhoneNumber(input));

        //Tilføjer telefon nummer
        activeOrderlist.getOrder(orderArrayPosition).setCostumerPhoneNumber(input);

    }

    private void addNameDialog(int orderArrayPosition) {
        boolean exit = false;
        int selection;
        do {
            System.out.println("Vil du tilføje navn til ordren?");
            System.out.println("1. - Ja");
            System.out.println("2. - Nej");

            selection = getUserInput();

            switch (selection) {
                case 1:
                    //Tilføj navn
                    activeOrderlist.getOrder(orderArrayPosition).setCostumerName(getValidName());
                    exit = true;
                    break;
                case 2:
                    //Intet navn
                    exit = true;
                    break;
                default:
                    System.err.println(selection + " Er ikke en mulighed for at valg af navn eller ej, prøv igen");
            }
        } while (!exit);
    }

    private void addPizzaDialog(int orderArrayPosition) {
        boolean addNewSize = true;
        boolean addNewPizza = true;
        boolean addExtras = true;
        boolean addExtraPizzaDialog = false;
        int pizzaNumber;
        int pizzaSize;
        int selection;

        //Ydderste loop, som er pizza loopet
        do {
            System.out.println("Indtast nummeret på pizzaen der skal tilføjes");
            pizzaNumber = getUserInput();

            //Tjekker at det indtastet pizzanummer er større end 0 samt at det ikke er højere end antal pizzaer
            if (pizzaNumber > 0 && pizzaNumber < Menu.getListOfPizzaName().size()) {

                //Loop til at vælge pizza størrelse
                do {
                    System.out.println("Vil du vælge størrelse?");
                    System.out.println("1. - Ja");
                    System.out.println("2. - Nej");

                    selection = getUserInput();

                    switch (selection) {
                        case 1:
                            //Vælg størrelse
                            pizzaSize = chooseSizeDialog();
                            activeOrderlist.getOrder(orderArrayPosition).addPizza(pizzaNumber, pizzaSize);
                            addNewSize = false;
                            break;
                        case 2:
                            //Lav Pizza
                            activeOrderlist.getOrder(orderArrayPosition).addPizza(pizzaNumber);
                            addNewSize = false;
                            break;
                        default:
                            System.err.println(selection + " Er ikke en mulighed for størrelsen");
                            System.out.println("Prøv igen");
                    }

                } while (addNewSize);

                //Loop til tilbehør
                do {
                    System.out.println("Skal der tilføjes ekstra tilbehør?");
                    System.out.println("1. - Ja");
                    System.out.println("2. - Nej");

                    selection = getUserInput();

                    switch (selection) {
                        case 1:
                            //Vælg ekstra tilbehør
                            chooseExtrasDialog(orderArrayPosition);
                            addExtras = false;
                            break;
                        case 2:
                            //Ingen ekstra tilbehør
                            addExtras = false;
                            break;
                        default:
                            System.err.println(selection + " Er ikke en mulighed for tilbehør");
                            System.out.println("Prøv igen");
                    }

                } while (addExtras);

                //Spørg om der skal laves en pizza mere
                do {
                    System.out.println("Tilføj en ekstra Pizza til orderen?");
                    System.out.println("1. - Ja");
                    System.out.println("2. - Nej");

                    selection = getUserInput();

                    switch (selection) {
                        case 1:
                            //Tilføj en ekstra pizza
                            addExtraPizzaDialog = false;
                            break;
                        case 2:
                            //Afslut
                            addExtraPizzaDialog = false;
                            addNewPizza = false;

                            break;
                        default:
                            System.err.println(selection + " Er ikke en mulighed for om det skal tilføjes en ny pizza");
                            System.out.println("Prøv igen");
                            break;
                    }
                    //Bliver ved indtil brugeren har valgt om der skal tilføjes ny pizza eller ej)
                } while (addExtraPizzaDialog);

            } else {
                //Brugeren har indtastet et tal der ikke er mellem 1 og antal pizzaer i menuen
                System.err.println(pizzaNumber + " Er ikke mellem 1 og antallet af pizzaer i menuen (" + Menu.getListOfPizzaName().size() + ")");
                System.out.println("Prøv igen");
            }

            //Hvis den er true Kan man tilføje ny pizza (TRUE)
            //Ellers afslutter den og går tilbage til menuen (FALSE)
        } while (addNewPizza);
    }

    //Retunere størrelsen så den kan gammes med ordren
    private int chooseSizeDialog() {
        boolean exit = false;
        int selection;
        do {
            System.out.println("Indtast den ønsket størrelse?");
            System.out.println("1. - Almindelig");
            System.out.println("2. - Familie");
            System.out.println("3. - Deep pan");

            selection = getUserInput();

            switch (selection) {
                case 1:
                    //Alm
                    selection = 0;
                    exit = true;
                    break;
                case 2:
                    //Fam
                    selection = 1;
                    exit = true;
                    break;
                case 3:
                    //Deep
                    selection = 2;
                    exit = true;
                    break;
                default:
                    System.err.println(selection + " Er ikke en mulighed prøv igen");
            }
        } while (!exit);
        return selection;
    }

    private void statisticsDialog() {
        boolean exit = false;
        int selection;
        do {
            System.out.println("Mario's Pizzaria - Statistik");
            System.out.println("-------------------------");

            System.out.println(Statistics.getStatistics() + "\n");
            System.out.println("1 - Gå tilbage");

            selection = getUserInput();
            if (selection == 1) {  //Gå tilbage
                exit = true;
            } else {
                System.err.println(selection + " Er ikke en mulighed prøv igen");
            }
        } while (!exit);
    }

    private void chooseExtrasDialog(int orderArrayPosition) {
        boolean exit = false;
        boolean extraQuantityCorrect;
        int extraSelection; // nummeret på tilbehøret
        int extraQuantitySelection; // Antallet af tilbehøret der er valgt
        int moreExtraQuantitySelection; // Bruges når brugeren skal vælge om der skal tilføjes mere
        int counter;
        int pizzaPos;
        do {
            counter = 1;
            System.out.println("Tilængeligt tilbehør: ");
            for (ExtraTopping topping : Menu.getToppingList()) {
                System.out.println(counter + ". " + topping.getExtraToppingName() + "\t" + topping.getExtraToppingPrice() + " kr.");
                counter++;
            }
            System.out.println("Indtast nummeret på hvilket tilbehør der skal tilføjes");

            extraSelection = getUserInput();

            if (extraSelection > 0 && extraSelection <= Menu.getToppingList().size()) {
                //Tallet er korrekt

                //For fat i pizza positionen i order arrayet
                //Pizzaen er lige blevet tilføjet, dermed ved vi det er den sidste plads
                pizzaPos = activeOrderlist.getOrder(orderArrayPosition).getOrderSize() - 1;
                do {
                    System.out.println("Indtast hvor meget tilbehør af " + Menu.getToppingList().get(extraSelection - 1).getExtraToppingName() + " der skal tilføjes");
                    extraQuantitySelection = getUserInput();

                    //Hvis svaret er mellem 1 og 5
                    if (extraQuantitySelection > 0 && extraQuantitySelection < 6) {
                        //er mellem 1 og 5
                        //Tilføjer topping til orderen på den korrekte pizza(pizzaPos)
                        activeOrderlist.getOrder(orderArrayPosition).addExtraTopping(pizzaPos, extraSelection - 1, extraQuantitySelection);
                        extraQuantityCorrect = true;
                    } else {
                        System.err.println("Du kan kun tilføje mellem 1x-5x af den samme slags tilbehør");
                        System.out.println("Prøv igen");
                        extraQuantityCorrect = false;
                    }
                    //Bliver ved indtil antallet af extra er korrekt
                } while (!extraQuantityCorrect);

                do {
                    System.out.println("Tilføj mere tilbehør?");
                    System.out.println("1. - Ja");
                    System.out.println("2. - Nej");
                    moreExtraQuantitySelection = getUserInput();

                    switch (moreExtraQuantitySelection) {
                        case 1:
                            //Ja
                            //Hopper ud af switch men ikke hele loopet
                            exit = false;
                            extraQuantityCorrect = true;
                            break;
                        case 2:
                            //Nej
                            //Hopper ud af switch og loopet udenom
                            exit = true;
                            extraQuantityCorrect = true;
                            break;
                        default:
                            System.err.println(moreExtraQuantitySelection + " Er ikke en mulighed prøv igen");
                            //Bliver ved indtil antallet af extra er korrekt
                    }

                } while (!extraQuantityCorrect);

            } else {
                //Tallet er ikke mellem 1 og antallet af toppinngs
                System.out.println(extraSelection + "Tallet er ikke mellem 1 og antallet af toppings(" + Menu.getToppingList().size() + ")");
            }
        } while (!exit);
    }

   

    private void completeOrder(int orderNumber) {
        activeOrderlist.completeOrder(orderNumber - 1);
    }

}
