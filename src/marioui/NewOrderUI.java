

package marioui;

import mariopizzaria.ExtraTopping;
import mariopizzaria.Menu;
import mariopizzaria.Orderlist;
import mariopizzaria.inputValidation;

public class NewOrderUI {

    private static NewOrderUI newOrderUI_instance = null;
    private inputValidation inputVal = inputValidation.getInstance();
    private Orderlist orderlist = Orderlist.getInstance();
    private Menu menu = Menu.getInstance();
    

    public NewOrderUI() {
    }

        
    public static NewOrderUI getInstance() {
        return NewOrderUIHolder.INSTANCE;
    }
    
    private static class NewOrderUIHolder {
        private static final NewOrderUI INSTANCE = new NewOrderUI();
    }
    
    public void makeNewOrderDialog() {
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

            selection = inputVal.getUserInput();

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
        int input;

        System.out.println("Mario's Pizzaria - Ny Ordre");
        System.out.println("-------------------------");
        System.out.println("Indtast telefon nr.");

        //Bliver ved indtil et korrekt telefon nummer er indtastet
        do {
            input = inputVal.getUserInput();
        } while (!inputVal.isValidPhoneNumber(input));

        //Tilføjer telefon nummer
        orderlist.getOrder(orderArrayPosition).setCostumerPhoneNumber(input);

    }
    
    private void addNameDialog(int orderArrayPosition) {
        boolean exit = false;
        int selection;
        do {
            System.out.println("Vil du tilføje navn til ordren?");
            System.out.println("1. - Ja");
            System.out.println("2. - Nej");

            selection = inputVal.getUserInput();

            switch (selection) {
                case 1:
                    //Tilføj navn
                    orderlist.getOrder(orderArrayPosition).setCostumerName(inputVal.getValidName());
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
    
    public void addPizzaDialog(int orderArrayPosition) {
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
            pizzaNumber = inputVal.getUserInput();

            //Tjekker at det indtastet pizzanummer er større end 0 samt at det ikke er højere end antal pizzaer
            if (pizzaNumber > 0 && pizzaNumber < menu.getAmountOfPizzas() + 1) {

                //Loop til at vælge pizza størrelse
                do {
                    System.out.println("Vil du vælge størrelse?");
                    System.out.println("1. - Ja");
                    System.out.println("2. - Nej");

                    selection = inputVal.getUserInput();

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

                    selection = inputVal.getUserInput();

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

                    selection = inputVal.getUserInput();

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
                System.err.println(pizzaNumber + " Er ikke mellem 1 og antallet af pizzaer i menuen (" + menu.getListOfPizzaName().size() + ")");
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

            selection = inputVal.getUserInput();

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
        boolean extraQuantityCorrect;
        int extraSelection; // nummeret på tilbehøret
        int extraQuantitySelection; // Antallet af tilbehøret der er valgt
        int moreExtraQuantitySelection; // Bruges når brugeren skal vælge om der skal tilføjes mere
        int counter;
        int pizzaPos;
        do {
            counter = 1;
            System.out.println("Tilængeligt tilbehør: ");
            for (ExtraTopping topping : menu.getToppingList()) {
                System.out.println(counter + ". " + topping.getExtraToppingName() + "\t" + topping.getExtraToppingPrice() + " kr.");
                counter++;
            }
            System.out.println("Indtast nummeret på hvilket tilbehør der skal tilføjes");

            extraSelection = inputVal.getUserInput();

            if (extraSelection > 0 && extraSelection <= menu.getToppingList().size()) {
                //Tallet er korrekt

                //For fat i pizza positionen i order arrayet
                //Pizzaen er lige blevet tilføjet, dermed ved vi det er den sidste plads
                pizzaPos = orderlist.getOrder(orderArrayPosition).getOrderSize() - 1;
                do {
                    System.out.println("Indtast hvor meget tilbehør af " + menu.getToppingList().get(extraSelection - 1).getExtraToppingName() + " der skal tilføjes");
                    extraQuantitySelection = inputVal.getUserInput();

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
                    moreExtraQuantitySelection = inputVal.getUserInput();

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
                System.out.println(extraSelection + "Tallet er ikke mellem 1 og antallet af toppings(" + menu.getToppingList().size() + ")");
            }
        } while (!exit);
    }
}
