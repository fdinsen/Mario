package mariopizzaria;

import java.util.Scanner;

/**
 *
 * @author <Oliver Vang>
 */
public class UserDialog {

    private Orderlist orderlist = new Orderlist();

    public UserDialog() {
        MainMenuDialog();

    }

    public void MainMenuDialog() {
        boolean exit = false;
        int selection;

        do {
            System.out.println("Mario's Pizzaria");
            System.out.println("-------------------------");
            System.out.println("1 - Menu");
            System.out.println("2 - Aktive Ordre");
            System.out.println("3 - Ny ordre");
            System.out.println("4 - Statistik");
            System.out.println("55 - Afslut program");

            selection = getUserInput();
            if (selection != -1) {
                switch (selection) {
                    case 1:
                        //Vis menuen
                        showPizzaMenuDialog();

                        break;
                    case 2:
                        //Viser aktive ordre
                        showActiveOrdersDialog();
                        break;
                    case 3:
                        //Lav ny ordre
                        makeNewOrderDialog();
                        break;
                    case 4:
                        //Viser staistik
                        break;
                    case 55:
                        //Slutter program
                        exit = true;
                        break;
                    default:
                        System.err.println(selection + " Er ikke en mulighed i menuen 'Hoved Menu', prøv igen");
                }
            }
        } while (!exit);

    }

    public void showPizzaMenuDialog() {
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

    public void showActiveOrdersDialog() {
        boolean exit = false;
        int selection;

        do {
            System.out.println("Mario's Pizzaria - Aktive Ordre");
            System.out.println("-------------------------");
            System.out.println(orderlist.ShowAllCurrentOrders());
            System.out.println("1. - Tilbage til hovedmenu");
            System.out.println("2. - Lav ny order");
            if (orderlist.getOrdersList().size() > 0) {
                System.out.println("3. - Fjern en ordre");
            }
            if (orderlist.getOrdersList().size() > 0) {
                System.out.println("4. - Se en ordre");
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
                        if (orderlist.getOrdersList().size() > 0) {
                            deleteOrderDialog();
                        } else {
                            System.err.println(selection + " Er ikke en mulighed i menuen 'Aktive Ordre', prøv igen");
                        }
                        break;
                    case 4:
                        //Se en ordre
                        if (orderlist.getOrdersList().size() > 0) {
                            seeOrderDialog();
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
        boolean correctNumber = false;
        int orderNumber = 0;
        int selection = 0;
        do {
            System.out.println("Mario's Pizzaria - Order");
            System.out.println("-------------------------");
            System.out.println(orderlist.ShowAllCurrentOrders());
            System.out.println("-------------------------");
            System.out.println("Indtast ordre nummeret på ordren du vil se");
            do {
                // 
                orderNumber = getUserInput();
                if (orderNumber > 0 && orderNumber < orderlist.getOrdersList().size() + 1) {
                    //Er et korrekt ordre nr
                    correctNumber = true;
                } else {
                    //Er ikke et korrekt ordre nr
                    correctNumber = false;
                    System.out.println(orderNumber + " er ikke mellem 1 og antallet af ordre (" + orderlist.getOrdersList().size() + ")");
                }

                //Bliver ved indtil korrekt svar
            } while (!correctNumber);
            //Udskriver orderen
            System.out.println("Mario's Pizzaria - Order " + orderNumber);
            System.out.println("-------------------------");
            System.out.println(orderlist.showOrder(orderNumber));
            System.out.println("-------------------------");
            do {
                System.out.println("1 - For at gå tilbage");
                System.out.println("2 - Se anden ordre");
                System.out.println("3 - Tilføj pizza til ordre");
                if (orderlist.getPizzaCountInOrder(orderNumber - 1) > 1) {
                    System.out.println("4 - Fjern pizza fra ordre");
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
                        if (orderlist.getPizzaCountInOrder(orderNumber - 1) > 1) {
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
        boolean exit = false;
        boolean correctNumber = false;
        int orderNumber = 0;
        int selection = 0;
        do {
            System.out.println("Mario's Pizzaria - Fjern Ordre");
            System.out.println("-------------------------");
            System.out.println(orderlist.ShowAllCurrentOrders());
            System.out.println("-------------------------");
            System.out.println("Indtast ordre nummeret på ordren du vil fjerne");
            do {
                // 
                orderNumber = getUserInput();
                if (orderNumber > 0 && orderNumber < orderlist.getOrdersList().size() + 1) {
                    //Er et korrekt ordre nr
                    correctNumber = true;
                } else {
                    //Er ikke et korrekt ordre nr
                    correctNumber = false;
                    System.out.println(orderNumber + " er ikke mellem 1 og antallet af ordre (" + orderlist.getOrdersList().size() + ")");
                }

                //Bliver ved indtil korrekt svar
            } while (!correctNumber);

            //Fjerne ordren fra ordre arrayet og går tilbage igen
            orderlist.deleteOrder(selection);
            exit = true;

        } while (!exit);
    }

    private void deletePizzaDialog(int orderNumber) {
        boolean exit = false;
        boolean correctNumber = false;
        int pizzaNumber = 0;
        do {
            System.out.println("Mario's Pizzaria - Fjern Pizza fra Ordre");
            System.out.println("-------------------------");
            System.out.println(orderlist.showAllPizzasInOrder(orderNumber));
            System.out.println("-------------------------");
            System.out.println("Indtast nummeret ud for pizzaen du vil fjerne fra orderen");
            do {
                // 
                pizzaNumber = getUserInput();
                if (pizzaNumber > 0 && pizzaNumber < orderlist.getPizzaCountInOrder(orderNumber) + 1) {
                    //Er et korrekt ordre nr
                    correctNumber = true;
                } else {
                    //Er ikke et korrekt ordre nr
                    correctNumber = false;
                    System.out.println(pizzaNumber + " er ikke mellem 1 og antallet af ordre (" + orderlist.getOrdersList().size() + ")");
                }

                //Bliver ved indtil korrekt svar
            } while (!correctNumber);

            //Fjerne pizza fra orderen og går tilbage
            orderlist.deletePizzaFromOrder(orderNumber, pizzaNumber - 1);
            exit = true;

        } while (!exit);
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

            selection = getUserInput();

            if (selection != -1) {
                switch (selection) {
                    case 1:
                        //Er bestilt på telefon
                        orderArrayPosition = orderlist.createOrder(true);
                        makeNewOrderByTelehoneDialog(orderArrayPosition);
                        addNameDialog(orderArrayPosition);
                        addPizzaDialog(orderArrayPosition);
                        exit = true;
                        break;
                    case 2:
                        //Er ikke bestilt på telefon
                        orderArrayPosition = orderlist.createOrder(false);
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
        boolean exit = false;
        int input = 0;
        String stringInput = "";

        System.out.println("Mario's Pizzaria - Ny Ordre");
        System.out.println("-------------------------");
        System.out.println("Indtast telefon nr.");

        //Bliver ved indtil et korrekt telefon nummer er indtastet
        do {
            input = getUserInput();
        } while (!isValidPhoneNumber(input));

        //Tilføjer telefon nummer
        orderlist.getOrder(orderArrayPosition).setCostumerPhoneNumber(input);

    }

    public void addNameDialog(int orderArrayPosition) {
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
                    orderlist.getOrder(orderArrayPosition).setCostumerName(getValidName());
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
                            orderlist.getOrder(orderArrayPosition).addPizza(pizzaNumber, pizzaSize);
                            addNewSize = false;
                            break;
                        case 2:
                            //Lav Pizza
                            orderlist.getOrder(orderArrayPosition).addPizza(pizzaNumber);
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
                    }
                    //Bliver ved indtil der enten skal tilføjes ekstra pizza(TRUE) eller skal afsluttes(FALSE)
                } while (addExtraPizzaDialog);

            } else {
                //Er ikke mellem 1 og antal pizzaer i menuen
                System.err.println(pizzaNumber + " Er ikke mellem 1 og antallet af pizzaer i menuen (" + Menu.getListOfPizzaName().size() + ")");
                System.out.println("Prøv igen");
            }

            //Hvis den er true Kan man tilføje ny pizza (TRUE)
            //Ellers afslutter den og går tilbage til menuen (FALSE)
        } while (addNewPizza);
    }

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

    private void chooseExtrasDialog(int orderArrayPosition) {
        boolean exit = false;
        boolean extraQuantityCorrect = false;
        boolean moreExtraQuantity = false;
        int extraSelection;
        int extraQuantitySelection;
        int moreExtraQuantitySelection;
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
                pizzaPos = orderlist.getOrder(orderArrayPosition).getOrderSize() - 1;
                do {
                    System.out.println("Indtast hvor meget tilbehør af " + Menu.getToppingList().get(extraSelection - 1).getExtraToppingName() + " der skal tilføjes");
                    extraQuantitySelection = getUserInput();

                    //Hvis svaret er mellem 1 og 5
                    if (extraQuantitySelection > 0 && extraQuantitySelection < 6) {
                        //er mellem 1 og 5
                        //Tilføjer topping til orderen på den korrekte pizza(pizzaPos)
                        orderlist.getOrder(orderArrayPosition).addExtraTopping(pizzaPos, extraSelection - 1, extraQuantitySelection);
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
                            System.err.println(moreExtraQuantity + " Er ikke en mulighed prøv igen");
                        //Bliver ved indtil antallet af extra er korrekt
                    }

                } while (!extraQuantityCorrect);

            } else {
                //Tallet er ikke mellem 1 og antallet af toppinngs
                System.out.println(extraSelection + "Tallet er ikke mellem 1 og antallet af toppings(" + Menu.getToppingList().size() + ")");
            }
        } while (!exit);
    }

    private int getUserInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        int userSelection = -1;
        try {
            userSelection = Integer.valueOf(input);
        } catch (NumberFormatException ne) {
            System.err.println("Ugyldigt input!");
            System.out.println("Prøv igen");
        }
        return userSelection;
    }

    private boolean isValidPhoneNumber(int phoneNumber) {
        int length = String.valueOf(phoneNumber).length();
        if (length == 8) {
            return true;
        } else {
            System.err.println("Telefon nummeret har ikke 8 tal");
            System.out.println("Prøv igen");
            return false;
        }
    }

    private String getValidName() {

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Indtast navnet: ");
            String name = sc.nextLine();
            if (name.length() > 1) {
                if (!name.matches("[a-zA-Z]+")) {
                    System.err.println("Du må kun bruge bokstaver!");
                    System.out.println("Prøv igen!");
                } else {
                    return name;

                }
            } else {
                System.err.println("Navnet skal være på mere end et bokstav");
                System.out.println("Prøv igen!");
            }

        }
    }

}
