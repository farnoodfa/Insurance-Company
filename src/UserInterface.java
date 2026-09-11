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
        while (doContinue) {
            displayMainMenu();
            int choice = getInt("Please Enter An Option From 1-3: ");
            switch (choice) {
                case 1:
                    if (adminLogin()) {
                        press();
                        adminMenu();
                    } else {
                        System.out.println(RED + "Admin login failed. Returning to main menu." + RESET);
                    }
                    break;
                case 2:
                    User loggedInUser = userLogin();
                    if (loggedInUser != null) {
                        press();
                        userMenu(loggedInUser);
                    } else {
                        System.out.println(RED + "User login failed. Returning to main menu." + RESET);
                    }
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

    // ====================================
    // ADMIN LOGIN & MENU
    // ====================================

    public boolean adminLogin() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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
            int choice = getInt("Select an option (1-14): ");
            switch (choice) {
                case 1:
                    runTestCode();
                    break; //
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
                    reportPaymentPerCity();
                    break;
                case 10:
                    reportPaymentsPerCarModelCompany();
                    break;
                case 11:
                    removePolicyAdmin();
                    break;
                case 12:
                    removeUser();
                    break;
                case 13:
                    changeAdminPassword();
                    break;
                case 14:
                    System.out.println(BOLD + "Logging out of Admin portal..." + RESET);
                    inAdminMenu = false;
                    break;
                default:
                    System.out.println(RED + "Invalid choice! Please select an option from 1 to 14." + RESET);
                    break;
            }
            if (inAdminMenu) {
                press();
            }
        }
    }

    public static void displayAdminMenu() {
        System.out.println("\n" + BOLD + "================ ADMIN MENU ================" + RESET);
        System.out.println("1.  Run Test Code");
        System.out.println("2.  Create User (Auto-generated ID)");
        System.out.println("3.  Create ThirdParty Policy for User");
        System.out.println("4.  Create Comprehensive Policy for User");
        System.out.println("5.  Print User Information");
        System.out.println("6.  Filter Policies by Car Model");
        System.out.println("7.  Filter Policies by Expiry Date");
        System.out.println("8.  Update User Address");
        System.out.println("9.  City Premium Payments Report");
        System.out.println("10. Car Model Premium Payments Report (Company)");
        System.out.println("11. Remove a Policy from User");
        System.out.println("12. Remove a User");
        System.out.println("13. Change Admin Password");
        System.out.println("14. Log Out");
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

    public void reportPaymentPerCity() {
        System.out.println(BOLD + "\n--- City Premium Payments Report ---" + RESET);
        ArrayList<String> distinctCities = company.populateDistinctCityNames();

        if (distinctCities == null || distinctCities.isEmpty()) {
            System.out.println("No registered users or cities found.");
            return;
        }

        ArrayList<Double> totalPayments = company.getTotalPaymentPerCity(distinctCities);
        company.reportPaymentPerCity(distinctCities, totalPayments);
    }

    public void reportPaymentsPerCarModelCompany() {
        System.out.println(BOLD + "\n--- Company Car Model Payments Report ---" + RESET);
        ArrayList<String> distinctModels = company.populateDistinctCarModels();

        if (distinctModels == null || distinctModels.isEmpty()) {
            System.out.println("No car policies found in the system.");
            return;
        }

        ArrayList<Integer> counts = company.getTotalCountPerCarModel(distinctModels);
        ArrayList<Double> payments = company.getTotalPaymentPerCarModel(distinctModels);
        company.reportPaymentsPerCarModel(distinctModels, counts, payments);
    }

    public void removePolicyAdmin() {
        System.out.println(BOLD + "\n--- Remove Policy from User ---" + RESET);
        int userID = getInt("Enter User ID: ");
        User user = company.findUser(userID);

        if (user == null) {
            System.out.println(RED + "Error: User ID " + userID + " not found." + RESET);
            return;
        }

        int policyID = getInt("Enter Policy ID to remove: ");
        boolean removed = company.removePolicy(userID, policyID);

        if (removed) {
            System.out.println(GREEN + "Policy " + policyID + " successfully removed." + RESET);
        } else {
            System.out.println(RED + "Failed: Policy " + policyID + " not found for this user." + RESET);
        }
    }

    public void removeUser() {
        System.out.println(BOLD + "\n--- Remove User ---" + RESET);
        int userID = getInt("Enter User ID to remove: ");
        boolean removed = company.removeUser(userID);

        if (removed) {
            System.out.println(GREEN + "User ID " + userID + " and all associated data successfully removed." + RESET);
        } else {
            System.out.println(RED + "Failed: User ID " + userID + " does not exist." + RESET);
        }
    }

    public void changeAdminPassword() {
        System.out.println(BOLD + "\n--- Change Admin Password ---" + RESET);
        String currentPassword = getString("Enter current password: ");
        String newPassword = getString("Enter new password: ");

        boolean changed = company.changeAdminPassword(currentPassword, newPassword);
        if (changed) {
            System.out.println(GREEN + "Password successfully updated!" + RESET);
        } else {
            System.out.println(RED + "Error: Current password does not match. Password unchanged." + RESET);
        }
    }

    // =================================================================
    // USER LOGIN & MENU
    // =================================================================
    public User userLogin() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(BOLD + "========= User Login =========" + RESET);
        int userID = getInt("Enter your User ID: ");
        User user = company.findUser(userID);
        if (user == null) {
            System.out.println(RED + "Error: No account found for ID " + userID + "." + RESET);
            return null;
        }
        System.out.println(GREEN + "Welcome, " + user.getName() + "!" + RESET);
        return user;
    }

    public void userMenu(User user) {
        boolean inUserMenu = true;
        while (inUserMenu) {
            displayUserMenu(user);
            int choice = getInt("Select an option (1-8): ");
            switch (choice) {
                case 1:
                    userAddThirdPartyPolicy(user);
                    break;
                case 2:
                    userAddComprehensivePolicy(user);
                    break;
                case 3:
                    userPrintAllPolicies(user);
                    break;
                case 4:
                    userFindPolicy(user);
                    break;
                case 5:
                    userFilterByCarModel(user);
                    break;
                case 6:
                    userFilterByExpiryDate(user);
                    break;
                case 7:
                    userUpdateAddress(user);
                    break;
                case 8:
                    userViewTotalPremiums(user);
                    break;
                case 9:
                    System.out.println(BOLD + "Logging out..." + RESET);
                    inUserMenu = false;
                    break;
                default:
                    System.out.println(RED + "Invalid choice! Please select an option from 1 to 9." + RESET);
                    break;
            }
            if (inUserMenu) {
                press();
            }
        }
    }

    public static void displayUserMenu(User user) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(BOLD + "================ USER MENU " + user.getName() + " ================" + RESET);
        System.out.println("1. Add Third-Party Policy");
        System.out.println("2. Add Comprehensive Policy");
        System.out.println("3. Print All My Policies");
        System.out.println("4. Find a Policy by ID");
        System.out.println("5. Filter Policies by Car Model");
        System.out.println("6. Filter Policies by Expiry Date");
        System.out.println("7. Update My Address");
        System.out.println("8. View Total Premium Payments");
        System.out.println("9. Log Out");
        System.out.println(BOLD + "=================================================" + RESET);
    }

    // =================================================================
    // USER ACTIONS
    // =================================================================

    public void userAddThirdPartyPolicy(User user) {
        System.out.println(BOLD + "\n--- Add Third-Party Policy ---" + RESET);
        int policyID = getInt("Enter Policy ID: ");
        if (user.findPolicy(policyID) != null) {
            System.out.println(RED + "Failed: Policy ID " + policyID + " already exists." + RESET);
            return;
        }
        String policyHolder = getString("Enter Policy Holder Name: ");
        Car car = promptCar();
        int claims = getInt("Enter Number of Claims: ");
        MyDate expiryDate = promptDate("Expiry");
        String comments = getString("Enter Comments: ");

        boolean added = user.createThirdPartyPolicy(policyHolder, policyID, car, claims, expiryDate, comments);
        if (added) {
            System.out.println(GREEN + "Third-Party Policy added successfully!" + RESET);
        } else {
            System.out.println(RED + "Failed to add policy." + RESET);
        }
    }

    public void userAddComprehensivePolicy(User user) {
        System.out.println(BOLD + "\n--- Add Comprehensive Policy ---" + RESET);
        int policyID = getInt("Enter Policy ID: ");
        if (user.findPolicy(policyID) != null) {
            System.out.println(RED + "Failed: Policy ID " + policyID + " already exists." + RESET);
            return;
        }
        String policyHolder = getString("Enter Policy Holder Name: ");
        Car car = promptCar();
        int claims = getInt("Enter Number of Claims: ");
        MyDate expiryDate = promptDate("Expiry");
        int driverAge = getInt("Enter Driver Age: ");
        int level = getInt("Enter Policy Level: ");

        boolean added = user.createComprehensivePolicy(policyHolder, policyID, car, claims, expiryDate, driverAge,
                level);
        if (added) {
            System.out.println(GREEN + "Comprehensive Policy added successfully!" + RESET);
        } else {
            System.out.println(RED + "Failed to add policy." + RESET);
        }
    }

    public void userPrintAllPolicies(User user) {
        System.out.println(BOLD + "\n--- All Policies for " + user.getName() + " ---" + RESET);
        if (user.getPolicies() == null || user.getPolicies().isEmpty()) {
            System.out.println("You have no policies.");
            return;
        }
        user.printPolicies(company.getFlatRate());
    }

    public void userFindPolicy(User user) {
        System.out.println(BOLD + "\n--- Find Policy by ID ---" + RESET);
        int policyID = getInt("Enter Policy ID: ");
        InsurancePolicy policy = user.findPolicy(policyID);
        if (policy != null) {
            policy.print();
            System.out.printf("Premium Payment: $%.2f%n", policy.calcPayment(company.getFlatRate()));
        } else {
            System.out.println(RED + "No policy found with ID " + policyID + "." + RESET);
        }
    }

    public void userFilterByCarModel(User user) {
        System.out.println(BOLD + "\n--- Filter My Policies by Car Model ---" + RESET);
        String model = getString("Enter Car Model to search: ");
        ArrayList<InsurancePolicy> results = user.filterByCarModel(model);

        if (results == null || results.isEmpty()) {
            System.out.println("No policies found matching car model: " + model);
        } else {
            System.out.println(GREEN + "Found " + results.size() + " matching policy(ies):" + RESET);
            InsurancePolicy.printPolicies(results, company.getFlatRate());
        }
    }

    public void userFilterByExpiryDate(User user) {
        System.out.println(BOLD + "\n--- Filter My Expired Policies ---" + RESET);
        MyDate cutoffDate = promptDate("Cutoff");
        ArrayList<InsurancePolicy> expiredPolicies = user.filterByExpiryDate(cutoffDate);

        if (expiredPolicies == null || expiredPolicies.isEmpty()) {
            System.out.println("No expired policies found by " + cutoffDate.getYear() + "/"
                    + cutoffDate.getMonth() + "/" + cutoffDate.getDay());
        } else {
            System.out.println(GREEN + "Found " + expiredPolicies.size() + " expired policy(ies):" + RESET);
            InsurancePolicy.printPolicies(expiredPolicies, company.getFlatRate());
        }
    }

    public void userUpdateAddress(User user) {
        System.out.println(BOLD + "\n--- Update My Address ---" + RESET);
        System.out.println("Current Address: " + user.getAddress());
        Address newAddress = promptAddress();
        user.setAddress(newAddress);
        System.out.println(GREEN + "Address updated successfully!" + RESET);
    }

    public void userViewTotalPremiums(User user) {
        System.out.println(BOLD + "\n--- Total Premium Payments ---" + RESET);
        double total = user.calcTotalPremiums(company.getFlatRate());
        System.out.printf("Total premium payments for " + user.getName() + ": $%.2f%n", total);
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
        System.out.print("\nPlease press enter key to continue");
        scanner.nextLine();

    }

}
