package mariopizzaria;

import java.util.Scanner;

public class inputValidation {

    private static inputValidation inputValidation_instance = null;

    public inputValidation() {
    }
    
    
    
    public static inputValidation getInstance() {
        if (inputValidation_instance == null) {
            inputValidation_instance = new inputValidation();
        }

        return inputValidation_instance;
    }


    public int getUserInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        int userSelection = -1;
        try {
            userSelection = Integer.parseInt(input);
        } catch (NumberFormatException ne) {
            System.err.println("Ugyldigt input!");
            System.out.println("Prøv igen");
        }
        return userSelection;
    }

    public boolean isValidPhoneNumber(int phoneNumber) {
        int length = String.valueOf(phoneNumber).length();
        if (length == 8) {
            return true;
        } else {
            System.err.println("Telefon nummeret har ikke 8 tal");
            System.out.println("Prøv igen");
            return false;
        }
    }

    public String getValidName() {

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Indtast navnet: ");
            String name = sc.nextLine();
            if (name.length() > 1) {
                if (!name.matches("[a-zA-Z ]+")) {
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
