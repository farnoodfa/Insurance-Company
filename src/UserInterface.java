import java.util.*;
import java.io.*;

public class UserInterface {
    // constants for text formatting
    public static final String RED = "\u001B[31m";
    public static final String BOLD = "\u001B[1m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";

    // attributes
    Scanner scanner = new Scanner(System.in);
    private boolean doContinue = true;
    private String userString;
    private int userInt;
    private int userID;
    private String userPassword;
    private String adminPassword;
    private String adminUsername;

    InsuranceCompany company;

    public UserInterface(InsuranceCompany company) {
        this.company = company;
    }

    public void mainMenu() {
        while (doContinue) {
            displayMainMenu();
            userInt = getInt("Please Enter An Option From 1-3: ");
            switch (userInt) {
                // In mainMenu()
                case 1:
                    if (adminLogin()) {
                        press();
                        adminMenu();
                    } else {
                        System.out.println(RED + "Admin login failed. Returning to main menu." + RESET);
                    }
                    break;
                case 2:
                    if (userLogin()) {
                        press();
                        userMenu();
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
        System.out.println(BOLD + "========= Admin Login =========" + RESET);
        adminUsername = getString("Enter Admin Username: ");
        adminPassword = getString("Enter Admin Password: ");

        if (!company.validateAdmin(adminUsername, adminPassword)) {
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
            int choice = getInt("Select an option (1-20): ");
            switch (choice) {
                case 1:
                    runTestCode();
                    break; //
                case 2:
                    createUser();
                    break;
                case 3:
                    createUserWithID();
                    break;
                case 4:
                    createThirdPartyPolicy();
                    break;
                case 5:
                    createComprehensivePolicy();
                    break;
                case 6:
                    printUserInformation();
                    break;
                case 7:
                    filterByCarModel();
                    break;
                case 8:
                    filterByExpiryDate();
                    break;
                case 9:
                    updateAddress();
                    break;
                case 10:
                    reportPaymentsPerCarModelForUser();
                    break;
                case 11:
                    reportPaymentPerCity();
                    break;
                case 12:
                    reportPaymentsPerCarModelCompany();
                    break;
                case 13:
                    removePolicyAdmin();
                    break;
                case 14:
                    removeUser();
                    break;
                case 15:
                    changeAdminPassword();
                    break;
                // -------------------------------lab6
                case 16:
                    saveDataToTextFile();
                    break;
                case 17:
                    loadDataFromTextFile();
                    break;
                case 18:
                    saveDataToBinaryFile();
                    break;
                case 19:
                    loadDataFromBinaryFile();
                    break;
                case 20:
                    System.out.println(BOLD + "Logging out of Admin portal..." + RESET);
                    adminPassword = null;
                    adminUsername = null;
                    inAdminMenu = false;
                    break;
                default:
                    System.out.println(RED + "Invalid choice! Please select an option from 1 to 20." + RESET);
                    break;
            }
            if (inAdminMenu) {
                press();
            }
        }
    }

    public static void displayAdminMenu() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\n" + BOLD + "================ ADMIN MENU ================" + RESET);
        System.out.println("1.  Run Test Code");
        System.out.println("2.  Create User (Auto-generated ID)");
        System.out.println("3.  Create User (Manual ID)");
        System.out.println("4.  Create ThirdParty Policy for User");
        System.out.println("5.  Create Comprehensive Policy for User");
        System.out.println("6.  Print User Information");
        System.out.println("7.  Filter Policies by Car Model");
        System.out.println("8.  Filter Policies by Expiry Date");
        System.out.println("9.  Update User Address");
        System.out.println("10. Car Model Premium Payments Report (User)");
        System.out.println("11. City Premium Payments Report across all Users");
        System.out.println("12. Car Model Premium Payments Report across all Users");
        System.out.println("13. Remove a Policy from User");
        System.out.println("14. Remove a User");
        System.out.println("15. Change Admin Password");
        // -------------------------------lab6
        System.out.println("16. Save data to file (text)");
        System.out.println("17. Load data from file (text)");
        System.out.println("18. Save data to file (binary)");
        System.out.println("19. Load data from file (binary)");
        System.out.println("20. Log Out");
        System.out.println(BOLD + "============================================" + RESET);
    }

    // =================================================================
    // ADMIN ACTIONS
    // =================================================================
    public void runTestCode() {
        System.out.println(BOLD + "\n--- Executing Automated Test Code ---" + RESET);
        try {
            App.testCase();
        } catch (CloneNotSupportedException e) {
            System.out.println(BOLD + RED + "Eror: " + e + RESET);
        } catch (PolicyException err) {
            System.out.println(BOLD + RED + "Eror: " + err + RESET);
        } catch (ClassNotFoundException e) {
            System.out.println(BOLD + RED + "Eror: " + e + RESET);
        } catch (IOException e) {
            System.out.println(BOLD + RED + "Eror: " + e + RESET);
        } catch (PolicyHolderNameException e) {
            System.out.println(RED + "Eror: " + e + RESET);
        }
    }

    public void createUser() {
        System.out.println(BOLD + "\n--- Create New User ---" + RESET);
        int nextID = User.getCount() + 1;
        if (company.findUser(nextID, adminUsername, adminPassword) != null) {
            System.out.println(RED + "Failed to add user: User ID " + nextID + " already exists." + RESET);
            return;
        }
        userString = getString("Enter User Full Name: ");
        String password = getString("Enter user password");
        Address address = promptAddress();
        User newUser = new User(userString, password, address);
        if (company.addUser(newUser, adminUsername, adminPassword)) {
            System.out.println(
                    GREEN + "User created successfully with auto-generated ID: " + newUser.getUserID() + RESET);
        } else {
            System.out.println(RED + "Failed to add user" + RESET);
        }

    }

    public void createUserWithID() {
        System.out.println(BOLD + "\n--- Create New User ---" + RESET);
        userInt = getInt("Enter User ID: ");
        if (company.findUser(userInt, adminUsername, adminPassword) != null) {
            System.out.println(RED + "Failed to add user: User ID " + userInt + " already exists." + RESET);
            return;
        }
        userString = getString("Enter User Full Name: ");
        String password = getString("Enter user password");
        Address address = promptAddress();
        if (company.addUser(userString, userInt, address, password, adminUsername, adminUsername)) {

            System.out.println(GREEN + "User created and added successfully!" + RESET);
        } else {
            System.out.println(RED + "Failed to add user" + RESET);
        }
    }

    public void createThirdPartyPolicy() {
        System.out.println(BOLD + "\n--- Create ThirdParty Policy ---" + RESET);
        int userID = getInt("Enter User ID: ");
        if (company.findUser(userID, adminUsername, adminPassword) == null) {
            System.out.println(RED + "Failed: User with ID " + userID + " does not exist." + RESET);
            return;
        }
        String policyHolder = getString("Enter Policy Holder Name: ");
        int policyID = getInt("Enter Policy ID: ");
        Car car = promptCar();
        int claims = getInt("Enter Number of Claims: ");
        MyDate expiryDate = promptDate("Expiry");
        String comments = getString("Enter Comments: ");

        boolean done = false;
        while (!done) {
            try {
                boolean added = company.createThirdPartyPolicy(userID, policyHolder, policyID, car, claims,
                        expiryDate, comments, adminUsername, adminPassword);
                if (added) {
                    System.out.println(GREEN + "ThirdParty Policy created and attached successfully! ID: "
                            + policyID + RESET);
                } else {
                    System.out
                            .println(RED + "Failed: Policy ID " + policyID + " already exists for this user." + RESET);
                }
                done = true;
            } catch (PolicyHolderNameException e) {
                System.out.println(e);
                policyHolder = getString("Enter Policy Holder Name: "); // re-ask only the name
            } catch (PolicyException e) {
                System.out.println(e);
                policyID = e.getID(); // retry with the generated ID
            }
        }
    }

    public void createComprehensivePolicy() {
        System.out.println(BOLD + "\n--- Create Comprehensive Policy ---" + RESET);
        int userID = getInt("Enter User ID: ");
        if (company.findUser(userID, adminUsername, adminPassword) == null) {
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

        boolean done = false;
        while (!done) {
            try {
                boolean added = company.createComprehensivePolicy(userID, policyHolder, policyID, car, claims,
                        expiryDate, driverAge, level, adminUsername, adminPassword);
                if (added) {
                    System.out.println(GREEN + "Comprehensive Policy created and attached successfully! ID: "
                            + policyID + RESET);
                } else {
                    System.out
                            .println(RED + "Failed: Policy ID " + policyID + " already exists for this user." + RESET);
                }
                done = true;
            } catch (PolicyHolderNameException e) {
                System.out.println(e);
                policyHolder = getString("Enter Policy Holder Name: "); 
            } catch (PolicyException e) {
                System.out.println(e);
                policyID = e.getID();
            }
        }
    }

    public void printUserInformation() {
        System.out.println(BOLD + "\n--- Print User Details ---" + RESET);
        int id = getInt("Enter User ID: ");
        User user = company.findUser(id, adminUsername, adminPassword);
        if (user != null) {
            company.printPolicies(id, adminUsername, adminPassword);
        } else {
            System.out.println(RED + "Error: User ID " + id + " not found." + RESET);
        }
    }

    public void filterByCarModel() {
        System.out.println(BOLD + "\n--- Filter Policies by Car Model ---" + RESET);
        String model = getString("Enter Car Model to search: ");
        ArrayList<InsurancePolicy> results = company.filterByCarModel(model, adminUsername, adminPassword);

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
        int id = getInt("Enter User ID: ");
        if (company.findUser(id, adminUsername, adminPassword) == null) {
            System.out.println(RED + "Error: User ID " + id + " not found." + RESET);
            return;
        }

        MyDate cutoffDate = promptDate("Cutoff");
        ArrayList<InsurancePolicy> expiredPolicies = company.filterByExpiryDate(id, cutoffDate, adminUsername,
                adminPassword);

        if (expiredPolicies == null || expiredPolicies.isEmpty()) {
            System.out.println("No expired policies found for User ID " + id + " by " + cutoffDate.getYear() + "/"
                    + cutoffDate.getMonth() + "/" + cutoffDate.getDay());
        } else {
            System.out.println(GREEN + "Found " + expiredPolicies.size() + " expired policy(ies):" + RESET);
            InsurancePolicy.printPolicies(expiredPolicies, company.getFlatRate());
        }
    }

    // public void updateAddress() {
    // System.out.println(BOLD + "\n--- Update User Address ---" + RESET);
    // int id = getInt("Enter User ID: ");
    // User user = company.findUser(id);

    // if (user != null) {
    // System.out.println("Current Address: " + user.getAddress());
    // Address newAddress = promptAddress();
    // user.setAddress(newAddress);
    // System.out.println(GREEN + "Address updated successfully!" + RESET);
    // } else {
    // System.out.println(RED + "Error: User ID " + id + " not found." + RESET);
    // }
    // }

    public void updateAddress() {
        System.out.println(BOLD + "\n--- Update User Address ---" + RESET);
        int id = getInt("Enter User ID: ");

        String currentAddress = company.getUserAddress(id, adminUsername, adminPassword);
        if (currentAddress == null) {
            System.out.println(RED + "Error: User ID " + id + " not found (or invalid credentials)." + RESET);
            return;
        }

        System.out.println("Current Address: " + currentAddress);

        Address newAddress = promptAddress();
        boolean updated = company.updateUserAddress(id, newAddress, adminUsername, adminPassword);
        if (updated) {
            System.out.println(GREEN + "Address updated successfully!" + RESET);
        } else {
            System.out.println(RED + "Failed to update address for User ID " + id + "." + RESET);
        }
    }

    // ---------------------lab5
    public void reportPaymentsPerCarModelForUser() {
        System.out.println(BOLD + "\n--- Car Model Payments Report for Given User ---" + RESET);

        int userID = getInt("Enter User ID: ");
        User user = company.findUser(userID);

        if (user == null) {
            System.out.println(RED + "Error: User ID " + userID + " not found." + RESET);
            return;
        }
        HashMap<String, Integer> counts = user.getTotalCountPerCarModel();
        HashMap<String, Double> payments = user.getTotalPremiumPerCarModel(company.getFlatRate());

        if (counts == null || counts.isEmpty()) {
            System.out.println(RED + "No car policies found for " + user.getName() + " (ID: " + userID + ")." + RESET);
            return;
        }
        System.out.println(GREEN + "Generating report for " + user.getName() + "..." + RESET);
        user.reportPaymentsPerCarModel(counts, payments);
    }

    public void reportPaymentPerCity() {
        System.out.println(BOLD + "\n--- City Premium Payments Report ---" + RESET);
        company.reportPaymentPerCity();
    }

    public void reportPaymentsPerCarModelCompany() {
        System.out.println(BOLD + "\n--- Company Car Model Payments Report ---" + RESET);
        company.reportPaymentsPerCarModel();
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
    public boolean userLogin() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(BOLD + "========= User Login =========" + RESET);
        userID = getInt("Enter your User ID: ");
        userPassword = getString("Enter your password: ");

        if (!company.validateUser(userID, userPassword)) {
            System.out.println(RED + "Error: Invalid User ID or password!" + RESET);
            return false;
        } else {
            System.out.println(GREEN + "Login successful! Welcome, User " + userID + "!" + RESET);
            return true;
        }
    }

    public void userMenu() {
        boolean inUserMenu = true;
        while (inUserMenu) {
            displayUserMenu();
            int choice = getInt("Select an option (1-8): ");
            switch (choice) {
                case 1:
                    userAddThirdPartyPolicy();
                    break;
                case 2:
                    userAddComprehensivePolicy();
                    break;
                case 3:
                    userPrintAllPolicies();
                    break;
                case 4:
                    userFindPolicy();
                    break;
                case 5:
                    userFilterByCarModel();
                    break;
                case 6:
                    userFilterByExpiryDate();
                    break;
                case 7:
                    userUpdateAddress();
                    break;
                case 8:
                    userViewTotalPremiums();
                    break;
                case 9:
                    userReportCarModel();
                    break;
                case 10:
                    userRemovePolicy();
                    break;
                case 11:
                    System.out.println(BOLD + "Logging out..." + RESET);
                    userID = 0;
                    userPassword = null;
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
        System.out.println("9. Payments per Car Model");
        System.out.println("10. Remove a Policy");
        System.out.println("11. Log Out");
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

        try {
            boolean added = user.createThirdPartyPolicy(policyHolder, policyID, car, claims, expiryDate, comments);
            if (added) {
                System.out.println(GREEN + "Third-Party Policy added successfully!" + RESET);
            } else {
                System.out.println(RED + "Failed to add policy." + RESET);
            }

        } catch (PolicyHolderNameException e) {
            System.out.println(e);
        } catch (PolicyException e) {
            System.out.println(e);

            try {
                boolean added = user.createThirdPartyPolicy(policyHolder, e.getID(), car, claims, expiryDate, comments);
                if (added) {
                    System.out.println(GREEN + "Third-Party Policy added with newly assigned ID: " + e.getID() + RESET);
                }
            } catch (PolicyHolderNameException err) {
                System.out.println(err);
            } catch (PolicyException ignored) {
            }
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

        try {
            boolean added = user.createComprehensivePolicy(policyHolder, policyID, car, claims, expiryDate, driverAge,
                    level);
            if (added) {
                System.out.println(GREEN + "Comprehensive Policy added successfully!" + RESET);
            } else {
                System.out.println(RED + "Failed to add policy." + RESET);
            }
        } catch (PolicyHolderNameException e) {
            System.out.println(e);
        } catch (PolicyException e) {
            System.out.println(e);

            try {
                boolean added = user.createComprehensivePolicy(policyHolder, e.getID(), car, claims, expiryDate,
                        driverAge, level);
                if (added) {
                    System.out
                            .println(GREEN + "Comprehensive Policy added with newly assigned ID: " + e.getID() + RESET);
                }
            } catch (PolicyHolderNameException err) {
                System.out.println(err);
            } catch (PolicyException ignored) {
            }
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
            policy.print(company.getFlatRate());
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

    private void userReportCarModel(User user) {
        HashMap<String, Integer> counts = user.getTotalCountPerCarModel();
        HashMap<String, Double> payments = user.getTotalPremiumPerCarModel(company.getFlatRate());
        user.reportPremiumCarModel(counts, payments);
    }

    private void userRemovePolicy(User user) {
        int policyID = getInt("Policy ID to remove: ");
        boolean result = user.removePolicy(policyID);
        System.out.println(result ? "Policy removed." : "Policy not found.");
    }

    // =================================================================
    // REUSABLE PROMPT & INPUT HELPERS
    // =================================================================

    // ============================= Assignment2 changes(erorr handeling)
    public Address promptAddress() {
        while (true) {
            try {
                System.out.println("--- Enter Address Details ---");
                userInt = getInt("Enter Street Number: ");
                String street = getString("Enter Street Name: ");
                String suburb = getString("Enter Suburb: ");
                String city = getString("Enter City: ");
                return new Address(userInt, street, suburb, city);
            } catch (IllegalArgumentException e) {
                System.out.println(RED + "Error: " + e.getMessage() + " Please try again." + RESET);
            }
        }
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

    // changes(erorr handeling)
    public MyDate promptDate(String label) {
        while (true) {
            try {
                System.out.println("--- Enter " + label + " Date ---");
                int year = getInt("Enter Year (e.g. 2026): ");
                int month = getInt("Enter Month (1-12): ");
                int day = getInt("Enter Day (1-31): ");
                return new MyDate(year, month, day);
            } catch (IllegalArgumentException e) {
                System.out.println(RED + "Error: " + e.getMessage() + " Please try again." + RESET);
            }
        }
    }

    public double getDouble(String message) {
        double input = 0;
        boolean isValid = false;
        while (!isValid) {
            System.out.print(message);
            try {
                input = scanner.nextDouble();
                scanner.nextLine();
                isValid = true;
            } catch (InputMismatchException err) {
                System.out.println(RED + "Error: Please enter a valid double." + RESET);
                scanner.nextLine();
            }
        }
        return input;
    }

    // Reads an integer from user input
    private int getInt(String message) {
        int input = 0;
        boolean isValid = false;

        while (!isValid) {
            System.out.print(message);
            try {
                input = scanner.nextInt();
                scanner.nextLine();
                isValid = true;
            } catch (InputMismatchException err) {
                System.out.println(RED + "Error: Please enter a valid integer." + RESET);
                scanner.nextLine();
            }
        }
        return input;
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

        // -----------------------------------------------lab6

    public void saveDataToTextFile() {
        String fileName = getString("Enter filename to save data (e.g., data.txt): ");
        try {
            company.saveTextFile(fileName);
            System.out.println(GREEN + "Data successfully saved to " + fileName + RESET);
        } catch (IOException e) {
            System.out.println(RED + "Error saving data: " + e.getMessage() + RESET);
        }
    }

    public void loadDataFromTextFile() {
        String fileName = getString("Enter filename to load data (e.g., data.txt): ");
        try {
            company.loadTextFile(fileName);
            System.out.println(GREEN + "Data successfully loaded from " + fileName + RESET);
        } catch (IOException | PolicyException | RuntimeException e) {
            System.out.println(RED + "Error loading data: " + e + RESET);
        } catch (PolicyHolderNameException e) {
            System.out.println(e);
        }
    }

    public void saveDataToBinaryFile() {
        String fileName = getString("Enter filename to save data (e.g., data.bin): ");
        try {
            company.save(fileName);
            System.out.println(GREEN + "Data successfully saved to " + fileName + RESET);
        } catch (IOException e) {
            System.out.println(RED + "Error saving data: " + e.getMessage() + RESET);
        }
    }

    public void loadDataFromBinaryFile() {
        String fileName = getString("Enter filename to load data (e.g., data.bin): ");
        try {
            company.load(fileName);
            System.out.println(GREEN + "Data successfully loaded from " + fileName + RESET);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(RED + "Error loading data: " + e.getMessage() + RESET);
        }
    }

}