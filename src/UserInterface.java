import java.util.ArrayList;
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

    public boolean adminLogin() {
        String username = getString("Enter Admin Username: ");
        String password = getString("Enter Admin Password: ");

        if (!company.validateAdmin(username, password)) {
            System.out.println(RED + "Error: Invalid username or password!" + RESET);
            return false;
        } else {
            System.out.println(GREEN + "Login successful! Welcome Admin." + RESET);
            return true;
        }
    }

    public void adminMenu() {
        boolean inAdminMenu = true;
        while (inAdminMenu) {
            displayAdminMenu();
            int choice = getInt("Select an option (1-9): ");
            switch (choice) {
                case 1:
                    runTestCode();
                    break;
                case 2:
                    createUser();
                    break;
                case 3:
                    createThirdPartyPolicy();
                    break;
                case 4:
                    createComprehensivePolicy();
                    break;
                case 5:
                    printUserInformation();
                    break;
                case 6:
                    filterByCarModel();
                    break;
                case 7:
                    filterByExpiryDate();
                    break;
                case 8:
                    updateAddress();
                    break;
                case 9:
                    System.out.println(BOLD + "Logging out of Admin portal..." + RESET);
                    inAdminMenu = false;
                    break;
                default:
                    System.out.println(RED + "Invalid choice! Please select an option from 1 to 9." + RESET);
                    break;
            }
            if (inAdminMenu) {
                press();
            }
        }
    }

    public static void displayAdminMenu() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(BOLD + "================ ADMIN MENU ================" + RESET);
        System.out.println("1. Run Test Code");
        System.out.println("2. Create User");
        System.out.println("3. Create ThirdParty Policy");
        System.out.println("4. Create Comprehensive Policy");
        System.out.println("5. Print User Information");
        System.out.println("6. Filter by Car Model");
        System.out.println("7. Filter by Expiry Date");
        System.out.println("8. Update Address");
        System.out.println("9. Log Out");
        System.out.println(BOLD + "============================================" + RESET);
    }

    // =================================================================
    // ADMIN ACTIONS
    // =================================================================
    public void runTestCode() {
        System.out.println(BOLD + "\n--- Executing Automated Test Code ---" + RESET);
        App.testCase();
    }

    public void createUser() {
        System.out.println(BOLD + "\n--- Create New User ---" + RESET);
        userInt = getInt("Enter User ID: ");
        userString = getString("Enter User Full Name: ");
        Address address = promptAddress();

        boolean created = company.addUser(userString, userInt, address);
        if (created) {
            System.out.println(GREEN + "User created and added successfully!" + RESET);
        } else {
            System.out.println(RED + "Failed to add user: User ID " + userInt + " already exists." + RESET);
        }
    }

    public void createThirdPartyPolicy() {
        System.out.println(BOLD + "\n--- Create ThirdParty Policy ---" + RESET);
        int userID = getInt("Enter User ID: ");
        if (company.findUser(userID) == null) {
            System.out.println(RED + "Failed: User with ID " + userID + " does not exist." + RESET);
            return;
        }

        String policyHolder = getString("Enter Policy Holder Name: ");
        int policyID = getInt("Enter Policy ID: ");
        Car car = promptCar();
        int claims = getInt("Enter Number of Claims: ");
        MyDate expiryDate = promptDate("Expiry");
        String comments = getString("Enter Comments: ");

        boolean added = company.createThirdPartyPolicy(userID, policyHolder, policyID, car, claims, expiryDate,
                comments);
        if (added) {
            System.out.println(GREEN + "ThirdParty Policy created and attached successfully!" + RESET);
        } else {
            System.out.println(RED + "Failed: Policy ID " + policyID + " already exists for this user." + RESET);
        }
    }

    public void createComprehensivePolicy() {
        System.out.println(BOLD + "\n--- Create Comprehensive Policy ---" + RESET);
        int userID = getInt("Enter User ID: ");
        if (company.findUser(userID) == null) {
            System.out.println(RED + "Failed: User with ID " + userID + " does not exist." + RESET);
            return;
        }

        String policyHolder = getString("Enter Policy Holder Name: ");
        int policyID = getInt("Enter Policy ID: ");
        Car car = promptCar();
        int claims = getInt("Enter Number of Claims: ");
        MyDate expiryDate = promptDate("Expiry");
        int driverAge = getInt("Enter Driver Age: ");
        int level = getInt("Enter Policy Level: ");

        boolean added = company.createComprehensivePolicy(userID, policyHolder, policyID, car, claims, expiryDate,
                driverAge, level);
        if (added) {
            System.out.println(GREEN + "Comprehensive Policy created and attached successfully!" + RESET);
        } else {
            System.out.println(RED + "Failed: Policy ID " + policyID + " already exists for this user." + RESET);
        }
    }

    public void printUserInformation() {
        System.out.println(BOLD + "\n--- Print User Details ---" + RESET);
        int userID = getInt("Enter User ID: ");
        User user = company.findUser(userID);
        if (user != null) {
            company.printPolicies(userID);
        } else {
            System.out.println(RED + "Error: User ID " + userID + " not found." + RESET);
        }
    }

    public void filterByCarModel() {
        System.out.println(BOLD + "\n--- Filter Policies by Car Model ---" + RESET);
        String model = getString("Enter Car Model to search: ");
        ArrayList<InsurancePolicy> results = company.filterByCarModel(model);

        if (results == null || results.isEmpty()) {
            System.out.println("No policies found matching car model: " + model);
        } else {
            System.out.println(GREEN + "Found " + results.size() + " matching policy(ies):" + RESET);
            InsurancePolicy.printPolicies(results, company.getFlatRate());
            double totalPayment = InsurancePolicy.calcTotalPayments(results, company.getFlatRate());
            System.out.printf(BOLD + "\nTotal Payment for matching policies: $%.2f%n" + RESET, totalPayment);
        }
    }

    public void filterByExpiryDate() {
        System.out.println(BOLD + "\n--- Filter Expired Policies for User ---" + RESET);
        int userID = getInt("Enter User ID: ");
        if (company.findUser(userID) == null) {
            System.out.println(RED + "Error: User ID " + userID + " not found." + RESET);
            return;
        }

        MyDate cutoffDate = promptDate("Cutoff");
        ArrayList<InsurancePolicy> expiredPolicies = company.filterByExpiryDate(userID, cutoffDate);

        if (expiredPolicies == null || expiredPolicies.isEmpty()) {
            System.out.println("No expired policies found for User ID " + userID + " by " + cutoffDate.getYear() + "/"
                    + cutoffDate.getMonth() + "/" + cutoffDate.getDay());
        } else {
            System.out.println(GREEN + "Found " + expiredPolicies.size() + " expired policy(ies):" + RESET);
            InsurancePolicy.printPolicies(expiredPolicies, company.getFlatRate());
        }
    }

    public void updateAddress() {
        System.out.println(BOLD + "\n--- Update User Address ---" + RESET);
        int userID = getInt("Enter User ID: ");
        User user = company.findUser(userID);

        if (user != null) {
            System.out.println("Current Address: " + user.getAddress());
            Address newAddress = promptAddress();
            user.setAddress(newAddress);
            System.out.println(GREEN + "Address updated successfully!" + RESET);
        } else {
            System.out.println(RED + "Error: User ID " + userID + " not found." + RESET);
        }
    }

    // =================================================================
    // REUSABLE PROMPT & INPUT HELPERS
    // =================================================================
    public Address promptAddress() {
        System.out.println("--- Enter Address Details ---");
        userInt = getInt("Enter Street Number: ");
        String street = getString("Enter Street Name: ");
        String suburb = getString("Enter Suburb: ");
        String city = getString("Enter City: ");
        return new Address(userInt, street, suburb, city);
    }

    public Car promptCar() {
        System.out.println("--- Enter Vehicle Details ---");
        String model = getString("Enter Car Model: ");
        double price = getDouble("Enter Car Price: ");
        int year = getInt("Enter Manufacturing Year: ");

        System.out.println("Select Car Type: 1. SED  2. SUV  3. LUX  4. HATCH");
        int typeSelection = getInt("Enter selection (1-4): ");
        Car.CarType type = Car.CarType.SED;
        switch (typeSelection) {
            case 2:
                type = Car.CarType.SUV;
                break;
            case 3:
                type = Car.CarType.LUX;
                break;
            case 4:
                type = Car.CarType.HATCH;
                break;
            default:
                type = Car.CarType.SED;
                break;
        }
        return new Car(year, price, model, type);
    }

    public MyDate promptDate(String label) {
        System.out.println("--- Enter " + label + " Date ---");
        int year = getInt("Enter Year (e.g. 2026): ");
        int month = getInt("Enter Month (1-12): ");
        int day = getInt("Enter Day (1-31): ");
        return new MyDate(year, month, day);
    }

    public double getDouble(String message) {
        double value = 0.0;
        boolean isValid = false;
        while (!isValid) {
            System.out.print(message);
            try {
                String input = scanner.nextLine().trim();
                value = Double.parseDouble(input);
                isValid = true;
            } catch (NumberFormatException err) {
                System.out.println(RED + "Error: " + err + ". Please enter a valid number." + RESET);
            }
        }
        return value;
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
            } catch (NumberFormatException err) {
                System.err.println(RED + "Error: " + err + ". Please enter a valid integer." + RESET);
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
