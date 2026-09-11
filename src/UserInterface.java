import java.util.Scanner;

public class UserInterface {
    // constants for text formatting
    public static final String RED = "\u001B[31m";
    public static final String BOLD = "\u001B[1m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";

    // attributes
    Scanner scanner = new Scanner(System.in);
    private static boolean doContinue = true;
    private static String userString;
    private static int userInt;

    InsuranceCompany company;

    public UserInterface(InsuranceCompany company) {
        this.company = company;
    }

    public void mainMenu() {
        displayMainMenu();
        userInt = getInt("Please Enter An Option From 1-3");
        while (doContinue) {
            switch (userInt) {
                case 1:
                    if (adminLogin()) {
                        press();
                        adminMenu();
                    }

                    break;
                case 2:

                    break;
                case 3:
                    System.out.println(BOLD + "===== GoodBye! ====" + RESET);
                    doContinue = false;
                    break;

                default:
                    System.out.println(RED + "Invalid Input Please Try Again" + RESET);
                    break;
            }
        }

    }

    public static void displayMainMenu() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(BOLD + "========= Wekcome To Program! ==========" + RESET);
        System.out.println("1. Admin Login");
        System.out.println("2. User Login");
        System.out.println("3. Exit Program");
    }

    public void adminMenu() {
        displayAdminMenu();

    }

    public static void displayAdminMenu() {

    }

    public boolean adminLogin() {
        String username = getString("Please Enter Admin Username");
        String password = getString("Please Enter Adimn Password");
        if (!company.validateAdmin(username, password)) {
            System.out.println(RED + "Wrong Username or Password!" + RESET);
            return false;
        } else {
            System.out.println(GREEN + "\nLog in successful!" + RESET);
            return true;
        }
    }

    // Reads an integer from user input
    private int getInt(String massage) {
        int value = 0;
        boolean isValid = false;

        while (!isValid) {
            System.out.print(massage);
            try {
                String input = scanner.nextLine();
                value = Integer.parseInt(input);
                isValid = true;
            } catch (NumberFormatException e) {
                System.err.println(RED + "Error: Invalid number. Please enter a valid integer." + RESET);
            }
        }
        return value;
    }

    // Reads a non-empty string from user input
    private String getString(String massage) {
        System.out.print(massage);
        return scanner.nextLine();
    }

    private void press() {
        System.out.print("\nPlease press any key to continue");
        scanner.nextLine();
        scanner.nextLine();
    }

}
