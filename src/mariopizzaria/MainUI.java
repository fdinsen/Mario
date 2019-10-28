
package mariopizzaria;

public class MainUI {
    
private void MainMenuDialog() {
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

            selection = inputValidation.getInstance().getUserInput();
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
                        statisticsDialog();
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
}
