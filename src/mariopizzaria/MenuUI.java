package mariopizzaria;

public class MenuUI {

    private static MenuUI menu_Ui_Instance = null; 
    private inputValidation inputVal = inputValidation.getInstance();
    private NewOrderUI newOrderUI = NewOrderUI.getInstance();
    
    private MenuUI(){
    }
    
    public static MenuUI getInstance() 
    { 
        if (menu_Ui_Instance == null) 
            menu_Ui_Instance = new MenuUI(); 
  
        return menu_Ui_Instance; 
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
            System.out.println("-------------------------");
            System.out.println("1. - Tilbage til hovedmenu");
            System.out.println("2. - Lav ny order");

            selection = inputVal.getUserInput();

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
}
