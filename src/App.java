import java.util.*;

// Driver class to test Insurance Policy system functionality
public class App {
    // lab1
    /*
     * Lab_1 testCode
     * // Constant for flat rate passed to calculation methods
     * private static double flatRate = 20.0;
     * 
     * public static void main(String[] args) throws Exception {
     * // Create Car examples
     * Car car1 = new Car(2020, 25000.0, "Toyota Camry", Car.CarType.SED);
     * Car car2 = new Car(2022, 50000.0, "BMW X5", Car.CarType.SUV);
     * Car car3 = new Car(2043, 18000.0, "Honda Civic", Car.CarType.HATCH);
     * 
     * // Create policy examples
     * InsurancePolicy policy1 = new ThirdPartyPolicy("Alice Smith", 101, car1, 1,
     * "Standard third-party cover");
     * InsurancePolicy policy2 = new ComprehensivePolicy("Bob Johnson", 102, car2,
     * 0, 25, 1);
     * InsurancePolicy policy3 = new ThirdPartyPolicy("Charlie Brown", 103, car3, 2,
     * "Includes road assistance");
     * 
     * // array list of parents
     * ArrayList<InsurancePolicy> policies = new ArrayList<>();
     * policies.add(policy1); // adding children
     * policies.add(policy2);
     * policies.add(policy3);
     * 
     * // Print all policies using print() method
     * System.out.println("=== Printing Policies using print() Method ===");
     * for (InsurancePolicy policy : policies) {
     * policy.print();
     * System.out.println("----------------------------------------");
     * }
     * 
     * // Print all policies using toString() method
     * System.out.println("\n=== Printing Policies using toString() Method ===");
     * for (InsurancePolicy policy : policies) {
     * System.out.println(policy.toString());
     * System.out.println("----------------------------------------");
     * }
     * 
     * // Calculate total premium payments passing flatRate as a constant number
     * double totalPremium = 0.0;
     * for (InsurancePolicy policy : policies) {
     * totalPremium += policy.calcPayment(flatRate);
     * }
     * 
     * // Print total premium payments
     * System.out.println("\nTotal Policy Premiums Summary");
     * System.out.println("Applied Flat Rate: $" + flatRate);
     * System.out.println("Total Combined Premium Payment: $" + totalPremium);
     */

    // lab2
    /*
     * Lab_2 testCOde
     * // Standard flat rate used across premium calculation tests
     * private static final int FLAT_RATE = 20;
     * 
     * public static void main(String[] args) {
     * Scanner inputReader = new Scanner(System.in);
     * 
     * // Initialize user, cars, and policies
     * User user = initializeUserWithPolicies();
     * 
     * // Display user details using print() and toString()
     * displayUserInformation(user);
     * 
     * // 1. Search for invalid ID (shows error message)
     * testPolicyLookup(user, 999);
     * 
     * // 2. Search for valid ID, print it, and save the returned policy object
     * InsurancePolicy validPolicy = testPolicyLookup(user, 101);
     * 
     * // 3. Perform modifications if found
     * if (validPolicy != null) {
     * testPolicyModifications(validPolicy);
     * }
     * 
     * // Test updating user address details
     * testAddressUpdates(user, inputReader);
     * 
     * // Test premium calculations and price rise effects
     * testPremiumCalculations(user);
     * 
     * // Test filtering policies by car model
     * testPolicyFiltering(user, inputReader);
     * 
     * inputReader.close();
     * }
     * 
     * // Creates test vehicles, policies, and a User instance with attached
     * policies
     * private static User initializeUserWithPolicies() {
     * Car car1 = new Car(2020, 25000.0, "benz", Car.CarType.SED);
     * Car car2 = new Car(2022, 50000.0, "BMW X5", Car.CarType.SUV);
     * Car car3 = new Car(2043, 18000.0, "Honda Civic", Car.CarType.HATCH);
     * 
     * InsurancePolicy policy1 = new ThirdPartyPolicy("Alice Smith", 101, car1, 1,
     * "Standard third-party cover");
     * InsurancePolicy policy2 = new ComprehensivePolicy("Bob Johnson", 102, car2,
     * 0, 25, 1);
     * InsurancePolicy policy3 = new ThirdPartyPolicy("Charlie Brown", 103, car3, 2,
     * "Includes road assistance");
     * InsurancePolicy policy4 = new ThirdPartyPolicy("Charlie Brown", 103, car3, 2,
     * "Includes road assistance");
     * 
     * 
     * Address initialAddress = new Address(12, "Crown", "Gwynneville", "Sydney");
     * User user = new User("John Doe", 1001, initialAddress);
     * 
     * System.out.println("=== Adding Policies to User ===");
     * addPolicyToUser(user, policy1);
     * addPolicyToUser(user, policy2);
     * addPolicyToUser(user, policy3);
     * addPolicyToUser(user, policy4);
     * 
     * return user;
     * }
     * 
     * // Adds a policy to the user and prints status based on return value
     * private static void addPolicyToUser(User user, InsurancePolicy policy) {
     * boolean isAdded = user.addPolicy(policy);
     * if (isAdded) {
     * System.out.println("Policy ID " + policy.getID() + " added successfully.");
     * } else {
     * System.out.println("Failed to add Policy ID " + policy.getID() +
     * " (Policy already exists).");
     * }
     * }
     * 
     * // Prints user information using print() and toString()
     * private static void displayUserInformation(User user) {
     * System.out.println("\n=== User Details (print) ===");
     * user.print();
     * 
     * System.out.println("\n=== User Details (toString) ===");
     * System.out.println(user.toString());
     * }
     * 
     * // Searches for a policy by ID, prints error or policy details, and returns
     * the
     * // policy
     * private static InsurancePolicy testPolicyLookup(User user, int policyID) {
     * System.out.println("\n=== Searching for Policy ID: " + policyID + " ===");
     * InsurancePolicy policy = user.findPolicy(policyID);
     * if (policy == null) {
     * System.out.println("Policy has not been found");
     * } else {
     * policy.print();
     * }
     * return policy;
     * }
     * 
     * // Applies 10% price rise, updates policy holder name to Robert, and changes
     * car
     * // model
     * private static void testPolicyModifications(InsurancePolicy policy) {
     * // Apply 10% price rise and print before and after
     * System.out.println("\n=== Policy Price Rise Test ===");
     * policy.print();
     * policy.carPriceRise(0.1);
     * System.out.println("After 10% price rise:");
     * policy.print();
     * 
     * // Change policy holder name to Robert
     * System.out.println("\n=== Updating Policy Holder Name ===");
     * policy.setPolicyHolderName("Robert");
     * policy.print();
     * 
     * // Change car model to Toyota Camry 2018
     * System.out.println("\n=== Updating Car Model ===");
     * policy.setCarModel("Toyota Camry 2018");
     * policy.print();
     * }
     * 
     * // Tests updating city and entering a completely new address
     * private static void testAddressUpdates(User user, Scanner inputReader) {
     * System.out.println("\n=== Updating City Directly ===");
     * user.setCity("Wollongong");
     * System.out.println("Updated Address: " + user.getAddress());
     * 
     * System.out.println("\n=== Enter New Address Details ===");
     * Address newAddress = promptForAddress(inputReader);
     * user.setAddress(newAddress);
     * 
     * System.out.println("\nUser details after new address update:");
     * user.print();
     * }
     * 
     * // Tests calculating total premiums before and after a 10% rise across all
     * // policies
     * private static void testPremiumCalculations(User user) {
     * System.out.println("\n=== Total Premium Payments ===");
     * System.out.printf("Total Premium: $%.2f%n",
     * user.calcTotalPremiums(FLAT_RATE));
     * 
     * System.out.println("\n=== Applying 10% Car Price Rise to All Policies ===");
     * user.carPriceRiseAll(0.1);
     * 
     * System.out.println("=== Total Premium Payments After Price Rise ===");
     * System.out.printf("Total Premium: $%.2f%n",
     * user.calcTotalPremiums(FLAT_RATE));
     * }
     * 
     * // Prompts for a car model, filters user policies, and prints matches
     * private static void testPolicyFiltering(User user, Scanner inputReader) {
     * System.out.println("\n=== Filter Policies by Car Model ===");
     * String searchModel = getUserString(inputReader,
     * "Enter car model to search: ");
     * 
     * ArrayList<InsurancePolicy> filteredPolicies =
     * user.filterByCarModel(searchModel);
     * 
     * System.out.println("\n=== Filtered Results ===");
     * if (filteredPolicies.isEmpty()) {
     * System.out.println("No policies found matching: " + searchModel);
     * } else {
     * InsurancePolicy.printPolicies(filteredPolicies);
     * }
     * }
     * 
     * // Reads full address details from user input and creates an Address object
     * private static Address promptForAddress(Scanner inputReader) {
     * int streetNum = getUserint(inputReader, "Enter Street number: ");
     * String street = getUserString(inputReader, "Enter Street Name: ");
     * String suburb = getUserString(inputReader, "Enter Suburb: ");
     * String city = getUserString(inputReader, "Enter City: ");
     * 
     * return new Address(streetNum, street, suburb, city);
     * }
     * 
     * // Displays prompt and reads a trimmed line of text from console
     * private static String getUserString(Scanner inputReader, String
     * promptMessage) {
     * System.out.print(promptMessage);
     * return inputReader.nextLine();
     * }
     * 
     * private static int getUserint(Scanner inputReader, String promptMessage) {
     * System.out.println(promptMessage);
     * int userInt = inputReader.nextInt();
     * inputReader.nextLine();
     * return userInt;
     * }
     */

    // lab3
    /*
     * public static void main(String[] args) {
     * Scanner inputReader = new Scanner(System.in);
     * 
     * // 1. Create InsuranceCompany, users, cars, dates, and policies
     * InsuranceCompany company = new InsuranceCompany("SafeGuard Insurance",
     * "admin", "admin123", 20);
     * 
     * Address addr1 = new Address(12, "Crown St", "Gwynneville", "Wollongong");
     * Address addr2 = new Address(45, "George St", "Haymarket", "Sydney");
     * Address addr3 = new Address(78, "Bourke St", "Surry Hills", "Sydney");
     * 
     * User user1 = new User("Alice Smith", 1001, addr1);
     * User user2 = new User("Bob Johnson", 1002, addr2);
     * 
     * Car car1 = new Car(2020, 25000.0, "Toyota Camry", Car.CarType.SED);
     * Car car2 = new Car(2022, 52000.0, "BMW X5", Car.CarType.SUV);
     * Car car3 = new Car(2019, 18000.0, "Honda Civic", Car.CarType.HATCH);
     * Car car4 = new Car(2023, 45000.0, "Toyota RAV4", Car.CarType.SUV);
     * Car car5 = new Car(2043, 30000.0, "Mazda 3", Car.CarType.SED);
     * 
     * MyDate expDate1 = new MyDate(2025, 6, 30);
     * MyDate expDate2 = new MyDate(2027, 12, 31);
     * MyDate expDate3 = new MyDate(2024, 3, 15);
     * MyDate expDate4 = new MyDate(2043, 8, 20);
     * 
     * InsurancePolicy policy1 = new ThirdPartyPolicy("Alice Smith", 101, car1, 1,
     * expDate1, "Standard third-party");
     * InsurancePolicy policy2 = new ComprehensivePolicy("Alice Smith", 102, car2,
     * 0, expDate2, 28, 1);
     * InsurancePolicy policy3 = new ThirdPartyPolicy("Bob Johnson", 201, car3, 2,
     * expDate3, "Roadside assistance");
     * 
     * // 2. Test Admin Login (Successful and Unsuccessful)
     * System.out.println("=== 2. Testing Admin Login ===");
     * testAdminLogin(company, "admin", "admin123");
     * testAdminLogin(company, "admin", "wrongPass");
     * 
     * // 3. Add Users to InsuranceCompany (Both versions + Duplicate ID failure)
     * System.out.println("\n=== 3. Adding Users ===");
     * testAddUser(company, user1);
     * testAddUser(company, user2);
     * testAddUserByDetails(company, "Charlie Brown", 1003, addr3);
     * testAddUser(company, user1); // Duplicate ID failure
     * 
     * // 4. Add Policies via addPolicy(int userID, InsurancePolicy policy)
     * System.out.println("\n=== 4. Adding Policies via addPolicy ===");
     * testAddPolicy(company, 1001, policy1);
     * testAddPolicy(company, 1001, policy2);
     * testAddPolicy(company, 1002, policy3);
     * testAddPolicy(company, 9999, policy1); // Invalid userID failure
     * testAddPolicy(company, 1001, policy1); // Duplicate policyID failure
     * 
     * // 5. Create Policies via createThirdPartyPolicy and
     * createComprehensivePolicy
     * System.out.
     * println("\n=== 5. Creating Policies via Company Factory Methods ===");
     * testCreateThirdParty(company, 1003, "Charlie Brown", 301, car4, 0, expDate4,
     * "Includes towing");
     * testCreateComprehensive(company, 1003, "Charlie Brown", 302, car5, 1,
     * expDate1, 35, 2);
     * // failure
     * testCreateThirdParty(company, 9999, "Ghost User", 303, car4, 0, expDate4,
     * "Invalid user"); // Invalid userID
     * // failure
     * testCreateComprehensive(company, 1003, "Charlie Brown", 301, car5, 1,
     * expDate1, 35, 2); // Duplicate policyID
     * 
     * // 6. Prompt for User ID and print user and policies
     * System.out.println("\n=== 6. Print User and Policies by User ID ===");
     * int promptUserID = getInt(inputReader, "Enter User ID to display: ");
     * company.printPolicies(promptUserID);
     * 
     * // 7. Prompt for User ID and Policy ID, find and print the policy
     * System.out.println("\n=== 7. Find Policy for Given User ===");
     * int searchUserID = getInt(inputReader, "Enter User ID: ");
     * int searchPolicyID = getInt(inputReader, "Enter Policy ID: ");
     * 
     * InsurancePolicy foundPolicy = company.findPolicy(searchUserID,
     * searchPolicyID);
     * if (foundPolicy != null) {
     * System.out.println("Policy Found:");
     * foundPolicy.print();
     * } else {
     * System.out.println("Policy has not been found.");
     * }
     * 
     * // 8. Print all users and their policies
     * System.out.println("\n=== 8. Print All Users in Company ===");
     * company.print();
     * 
     * // 9. Raise price of cars for all users by 10% and reprint
     * System.out.println("=== 9. Applying 10% Price Rise Across All Policies ===");
     * company.carPriceRise(0.10);
     * company.print();
     * 
     * // 10. Print total premium payments for a given User ID
     * System.out.println("=== 10. Total Premium for User ID 1001 ===");
     * System.out.printf("Total Premium for User 1001: $%.2f%n",
     * company.calcTotalPayments(1001));
     * 
     * // 11. Print total premium payments for all users in the company
     * System.out.println("\n=== 11. Total Premium for All Users ===");
     * System.out.printf("Grand Total Company Premiums: $%.2f%n",
     * company.calcTotalPayments());
     * 
     * // 12. Retrieve and print all policies in company
     * System.out.println("\n=== 12. Displaying All Policies in Company ===");
     * ArrayList<InsurancePolicy> allPoliciesList = company.allPolicies();
     * InsurancePolicy.printPolicies(allPoliciesList);
     * 
     * // 13. Filter policies by expiry date for a specific User ID
     * System.out.
     * println("\n=== 13. Filter by Expiry Date for User 1001 (Cutoff: 2043-01-01) ==="
     * );
     * MyDate cutoffDate1001 = new MyDate(2043, 1, 1);
     * ArrayList<InsurancePolicy> userExpiredList = company.filterByExpiryDate(1001,
     * cutoffDate1001);
     * if (userExpiredList.isEmpty()) {
     * System.out.println("No expired policies found for User 1001.");
     * } else {
     * InsurancePolicy.printPolicies(userExpiredList);
     * }
     * 
     * // 14. Filter policies across the company by car model
     * System.out.
     * println("\n=== 14. Filter Policies Company-Wide by Car Model (Toyota) ===");
     * ArrayList<InsurancePolicy> toyotas = company.filterByCarModel("Toyota");
     * InsurancePolicy.printPolicies(toyotas);
     * 
     * // 15. Prompt customer for date and filter all company policies by expiry
     * date
     * System.out.println("\n=== 15. Filter All Expired Policies by Custom Date ==="
     * );
     * MyDate customDate = promptForDate(inputReader);
     * ArrayList<InsurancePolicy> globalExpiredList =
     * company.filterByExpiryDate(customDate);
     * if (globalExpiredList.isEmpty()) {
     * System.out.println("No policies expired before " + customDate.getYear() + "/"
     * + customDate.getMonth() + "/"
     * + customDate.getDay());
     * } else {
     * InsurancePolicy.printPolicies(globalExpiredList);
     * }
     * 
     * // 16. Find user, prompt for new address, and update address
     * System.out.println("\n=== 16. Update User Address ===");
     * User selectedUser = company.findUser(1001);
     * if (selectedUser != null) {
     * System.out.println("Current User Details:");
     * selectedUser.print();
     * 
     * System.out.println("\nEnter new address details for this user:");
     * Address newAddress = promptForAddress(inputReader);
     * selectedUser.setAddress(newAddress);
     * 
     * System.out.println("\nUser Details After Address Update:");
     * selectedUser.print();
     * } else {
     * System.out.println("User not found.");
     * }
     * 
     * inputReader.close();
     * }
     * 
     * // Input Helper Methods
     * 
     * // Reads an integer from user input with error handling against crashes
     * public static int getInt(Scanner inputReader, String prompt) {
     * int value = 0;
     * boolean isValid = false;
     * 
     * while (!isValid) {
     * System.out.print(prompt);
     * try {
     * String input = inputReader.nextLine().trim();
     * value = Integer.parseInt(input);
     * isValid = true;
     * } catch (NumberFormatException e) {
     * System.err.println("Error: Invalid number. Please enter a valid integer.");
     * }
     * }
     * return value;
     * }
     * 
     * // Reads a non-empty string from user input
     * public static String getString(Scanner inputReader, String prompt) {
     * System.out.print(prompt);
     * return inputReader.nextLine().trim();
     * }
     * 
     * // Prompts user to input year, month, and day to create a MyDate object
     * private static MyDate promptForDate(Scanner inputReader) {
     * int year = getInt(inputReader, "Enter Year: ");
     * int month = getInt(inputReader, "Enter Month (1-12): ");
     * int day = getInt(inputReader, "Enter Day (1-31): ");
     * 
     * return new MyDate(year, month, day);
     * }
     * 
     * // Prompts user to input address fields to create an Address object
     * private static Address promptForAddress(Scanner inputReader) {
     * int streetNum = getInt(inputReader, "Enter Street Number: ");
     * String street = getString(inputReader, "Enter Street Name: ");
     * String suburb = getString(inputReader, "Enter Suburb: ");
     * String city = getString(inputReader, "Enter City: ");
     * 
     * return new Address(streetNum, street, suburb, city);
     * }
     * 
     * // Action Test Helper Methods
     * 
     * private static void testAdminLogin(InsuranceCompany company, String user,
     * String pass) {
     * if (company.validateAdmin(user, pass)) {
     * System.out.println("Admin login SUCCESSFUL for username: " + user);
     * } else {
     * System.out.println("Admin login FAILED for username: " + user);
     * }
     * }
     * 
     * private static void testAddUser(InsuranceCompany company, User user) {
     * if (company.addUser(user)) {
     * System.out.println("User added successfully: " + user.getName() + " (ID: " +
     * user.getUserID() + ")");
     * } else {
     * System.out.println("Failed to add user: " + user.getName() +
     * " (Duplicate ID: " + user.getUserID() + ")");
     * }
     * }
     * 
     * private static void testAddUserByDetails(InsuranceCompany company, String
     * name, int id, Address addr) {
     * if (company.addUser(name, id, addr)) {
     * System.out.println("User created & added successfully: " + name + " (ID: " +
     * id + ")");
     * } else {
     * System.out.println("Failed to create user: " + name + " (Duplicate ID: " + id
     * + ")");
     * }
     * }
     * 
     * private static void testAddPolicy(InsuranceCompany company, int userID,
     * InsurancePolicy policy) {
     * if (company.addPolicy(userID, policy)) {
     * System.out.println("Policy ID " + policy.getID() + " added to User ID " +
     * userID + " successfully.");
     * } else {
     * System.out.println("Failed to add Policy ID " + policy.getID() +
     * " to User ID " + userID
     * + " (Invalid User or Duplicate Policy).");
     * }
     * }
     * 
     * private static void testCreateThirdParty(InsuranceCompany company, int
     * userID, String name, int id, Car car,
     * int claims, MyDate exp, String comm) {
     * if (company.createThirdPartyPolicy(userID, name, id, car, claims, exp, comm))
     * {
     * System.out.println("ThirdParty Policy " + id +
     * " created successfully for User " + userID);
     * } else {
     * System.out.println("Failed to create ThirdParty Policy " + id + " for User "
     * + userID
     * + " (Invalid User or Duplicate Policy).");
     * }
     * }
     * 
     * private static void testCreateComprehensive(InsuranceCompany company, int
     * userID, String name, int id, Car car,
     * int claims, MyDate exp, int age, int lvl) {
     * if (company.createComprehensivePolicy(userID, name, id, car, claims, exp,
     * age, lvl)) {
     * System.out.println("Comprehensive Policy " + id +
     * " created successfully for User " + userID);
     * } else {
     * System.out.println("Failed to create Comprehensive Policy " + id +
     * " for User " + userID
     * + " (Invalid User or Duplicate Policy).");
     * }
     * }
     */

    public static void main(String[] args) throws CloneNotSupportedException {
        InsuranceCompany insuranceCompany = new InsuranceCompany("SafeGuard Insurance", "admin", "admin123", 20);
        fillData(insuranceCompany);
        testLab4(insuranceCompany);
        UserInterface UI = new UserInterface(insuranceCompany);
        UI.mainMenu();

    }

    public static void fillData(InsuranceCompany insuranceCompany) {

        // --- Addresses ---
        Address addr1 = new Address(12, "Crown St", "Gwynneville", "Wollongong");
        Address addr2 = new Address(45, "George St", "Haymarket", "Shiraz");
        Address addr3 = new Address(78, "Bourke St", "Surry Hills", "Shiraz");
        Address addr4 = new Address(90, "King St", "Newtown", "Wollongong");
        Address addr5 = new Address(34, "Pitt St", "CBD", "Shiraz");

        // --- Users ---
        User user1 = new User("Alice Smith", 1001, addr1);
        User user2 = new User("Bob Johnson", 1002, addr2);
        User user3 = new User("Charlie Brown", 1003, addr3);
        User user4 = new User("Diana Prince", 1004, addr4);
        User user5 = new User("Ethan Hunt", 1005, addr5);

        insuranceCompany.addUser(user1);
        insuranceCompany.addUser(user2);
        insuranceCompany.addUser(user3);
        insuranceCompany.addUser(user4);
        insuranceCompany.addUser(user5);

        // --- Cars ---
        Car car1 = new Car(2020, 25000.0, "Toyota Camry", Car.CarType.SED);
        Car car2 = new Car(2022, 52000.0, "BMW X5", Car.CarType.SUV);
        Car car3 = new Car(2019, 18000.0, "Honda Civic", Car.CarType.HATCH);
        Car car4 = new Car(2023, 45000.0, "Toyota RAV4", Car.CarType.SUV);
        Car car5 = new Car(2043, 30000.0, "Mazda 3", Car.CarType.SED);

        // --- Expiry Dates ---
        MyDate expDate1 = new MyDate(2025, 6, 30);
        MyDate expDate2 = new MyDate(2027, 12, 31);
        MyDate expDate3 = new MyDate(2024, 3, 15);
        MyDate expDate4 = new MyDate(2043, 8, 20);

        // --- Policies ---
        InsurancePolicy policy1 = new ThirdPartyPolicy("Alice Smith", 101, car1, 1, expDate1, "Standard third-party");
        InsurancePolicy policy2 = new ComprehensivePolicy("Alice Smith", 102, car2, 0, expDate2, 28, 1);
        InsurancePolicy policy3 = new ThirdPartyPolicy("Bob Johnson", 201, car3, 2, expDate3, "Roadside assistance");
        InsurancePolicy policy4 = new ComprehensivePolicy("Charlie Brown", 301, car4, 0, expDate4, 35, 2);
        InsurancePolicy policy5 = new ThirdPartyPolicy("Diana Prince", 401, car5, 1, expDate1, "Basic coverage");

        // --- Attach policies to users ---
        insuranceCompany.addPolicy(1001, policy1);
        insuranceCompany.addPolicy(1001, policy2);
        insuranceCompany.addPolicy(1002, policy3);
        insuranceCompany.addPolicy(1003, policy4);
        insuranceCompany.addPolicy(1004, policy5);
        // user5 (Ethan) intentionally left with no policies
    }

    // =================================================================
    // HELPER METHODS
    // =================================================================

    public static void addPolicy(User user, InsurancePolicy policy) {
        user.addPolicy(policy);
    }

    public static void addPolicy(InsuranceCompany company, int userID, InsurancePolicy policy) {
        company.addPolicy(userID, policy);
    }

    // =================================================================
    // SHARED TEST HELPER
    // =================================================================

    public static void testResult(Object expected, Object actual) {
        System.out.println("The Expected result is: " + expected);
        System.out.println("The Actual result is:   " + actual);
        if (expected.equals(actual)) {
            System.out.println(UserInterface.BOLD + UserInterface.GREEN + "Test Passed" + UserInterface.RESET);
        } else {
            System.out.println(UserInterface.BOLD + UserInterface.RED + "Test Failed" + UserInterface.RESET);
        }
        System.out.println("----------------------------------------\n\n");
    }

    // =================================================================
    // ADMIN VALIDATION
    // =================================================================

    public static void testValidateAdminCorrect(InsuranceCompany company) {
        System.out.println("TEST [1/43] validateAdmin - correct credentials → expected: true");
        testResult(true, company.validateAdmin("admin", "admin123"));
    }

    public static void testValidateAdminWrong(InsuranceCompany company) {
        System.out.println("TEST [2/43] validateAdmin - wrong password → expected: false");
        testResult(false, company.validateAdmin("admin", "wrongpass"));
    }

    // =================================================================
    // USER MANAGEMENT
    // =================================================================

    public static void testAddUserDuplicate(InsuranceCompany company) {
        System.out.println("TEST [3/43] addUser - duplicate ID 1001 → expected: false");
        User duplicate = new User("Alice Clone", 1001,
                new Address(1, "Fake St", "Nowhere", "Wollongong"));
        testResult(false, company.addUser(duplicate));
    }

    public static void testFindUserExists(InsuranceCompany company, User user1) {
        System.out.println("TEST [4/43] findUser - ID 1001 exists → expected: user1 object (true)");
        testResult(user1, company.findUser(1001));
    }

    public static void testFindUserNotFound(InsuranceCompany company) {
        System.out.println("TEST [5/43] findUser - ID 9999 does not exist → expected: null (true)");
        testResult(true, company.findUser(9999) == null);
    }

    // =================================================================
    // POLICY MANAGEMENT
    // =================================================================

    public static void testFindPolicyExists(InsuranceCompany company) {
        System.out.println("TEST [6/43] findPolicy - policy ID 101 for user 1001 → expected: non-null (true)");
        testResult(true, company.findPolicy(1001, 101) != null);
    }

    public static void testFindPolicyNotFound(InsuranceCompany company) {
        System.out.println("TEST [7/43] findPolicy - policy ID 9999 for user 1001 → expected: null (true)");
        testResult(true, company.findPolicy(1001, 9999) == null);
    }

    public static void testAddPolicyDuplicate(InsuranceCompany company) {
        System.out.println("TEST [8/43] addPolicy - duplicate policy ID 101 for user 1001 → expected: false");
        Car dupCar = new Car(2018, 10000.0, "Kia Rio", Car.CarType.HATCH);
        ThirdPartyPolicy dup = new ThirdPartyPolicy(
                "Alice Smith", 101, dupCar, 0, new MyDate(2025, 1, 1), "duplicate");
        testResult(false, company.addPolicy(1001, dup));
    }

    // =================================================================
    // PREMIUM CALCULATION (isolated objects - no company needed)
    // =================================================================

    /**
     * ThirdParty: 25000/100 + 1*200 + 20 = 250 + 200 + 20 = 470.0
     */
    public static void testThirdPartyCalcPayment() {
        System.out.println("TEST [9/43] ThirdPartyPolicy.calcPayment - 25000/100 + 1*200 + 20   expected: 470.0");
        Car testCar = new Car(2020, 25000.0, "Toyota Camry", Car.CarType.SED);
        ThirdPartyPolicy tp = new ThirdPartyPolicy(
                "Alice Smith", 101, testCar, 1, new MyDate(2025, 6, 30), "test");
        double expected = (25000.0 / 100.0) + (1 * 200.0) + 20.0;
        testResult(expected, tp.calcPayment(20));
    }

    /**
     * Comprehensive - age 35 (> 30, no surcharge):
     * 52000/50 + 0*200 + 20 = 1040 + 0 + 20 = 1060.0
     */
    public static void testComprehensiveCalcPaymentOlderDriver() {
        System.out.println("TEST [10/43] ComprehensivePolicy.calcPayment - age 35 (no surcharge)   expected: 1060.0");
        Car testCar = new Car(2022, 52000.0, "BMW X5", Car.CarType.SUV);
        ComprehensivePolicy cp = new ComprehensivePolicy(
                "Alice Smith", 102, testCar, 0, new MyDate(2027, 12, 31), 35, 1);
        double expected = (52000.0 / 50.0) + (0 * 200.0) + 20.0;
        testResult(expected, cp.calcPayment(20));
    }

    /**
     * Comprehensive - age 28 (<= 30, surcharge applies):
     * 52000/50 + 0*200 + 20 + (30-28)*50 = 1040 + 20 + 100 = 1160.0
     */
    public static void testComprehensiveCalcPaymentYoungDriver() {
        System.out.println("TEST [11/43] ComprehensivePolicy.calcPayment - age 28 (surcharge +100)   expected: 1160.0");
        Car testCar = new Car(2022, 52000.0, "BMW X5", Car.CarType.SUV);
        ComprehensivePolicy cp = new ComprehensivePolicy(
                "Alice Smith", 102, testCar, 0, new MyDate(2027, 12, 31), 28, 1);
        double expected = (52000.0 / 50.0) + (0 * 200.0) + 20.0 + (30 - 28) * 50.0;
        testResult(expected, cp.calcPayment(20));
    }

    // =================================================================
    // TOTAL PAYMENTS
    // =================================================================

    /**
     * Alice (1001):
     * - Policy 1: 25000/100 + 1*200 + 20 = 470.0
     * - Policy 2: 52000/50 + 0*200 + 20 + (30-28)*50 = 1160.0
     * Expected: 470.0 + 1160.0 = 1630.0
     */
    public static void testCalcTotalPaymentsForUser(InsuranceCompany company) {
        System.out
                .println("TEST [12/43] calcTotalPayments(1001) - policy1(470.0) + policy2(1160.0)   expected: 1630.0");
        double policy1 = (25000.0 / 100.0) + (1 * 200.0) + 20.0;
        double policy2 = (52000.0 / 50.0) + (0 * 200.0) + 20.0 + (30 - 28) * 50.0;
        double expected = policy1 + policy2;
        testResult(expected, company.calcTotalPayments(1001));
    }

    /**
     * All 5 initial policies:
     * - Policy 1 (Alice): 470.0
     * - Policy 2 (Alice): 1160.0
     * - Policy 3 (Bob): 18000/100 + 2*200 + 20 = 600.0
     * - Policy 4 (Charlie): 45000/50 + 0*200 + 20 = 920.0
     * - Policy 5 (Diana): 30000/100 + 1*200 + 20 = 520.0
     * Expected: 470 + 1160 + 600 + 920 + 520 = 3670.0
     */
    public static void testCalcTotalPaymentsAll(InsuranceCompany company) {
        System.out.println("TEST [13/43] calcTotalPayments() - all 5 policies   expected: 3670.0");
        double policy1 = (25000.0 / 100.0) + (1 * 200.0) + 20.0;
        double policy2 = (52000.0 / 50.0) + (0 * 200.0) + 20.0 + (30 - 28) * 50.0;
        double policy3 = (18000.0 / 100.0) + (2 * 200.0) + 20.0;
        double policy4 = (45000.0 / 50.0) + (0 * 200.0) + 20.0;
        double policy5 = (30000.0 / 100.0) + (1 * 200.0) + 20.0;
        double expected = policy1 + policy2 + policy3 + policy4 + policy5;
        testResult(expected, company.calcTotalPayments());
    }

    // =================================================================
    // FILTERING
    // =================================================================

    public static void testFilterByCarModelFound(InsuranceCompany company) {
        System.out.println(
                "TEST [14/43] filterByCarModel(\"Toyota\") - matches policy1 (Camry) + policy4 (RAV4) → expected size: 2");
        testResult(2, company.filterByCarModel("Toyota").size());
    }

    public static void testFilterByCarModelNotFound(InsuranceCompany company) {
        System.out.println("TEST [15/43] filterByCarModel(\"Tesla\") - no match → expected size: 0");
        testResult(0, company.filterByCarModel("Tesla").size());
    }

    /**
     * Cutoff 2025-07-01:
     * policy1 exp 2025-06-30 → month 7 > 6 → EXPIRED ✓
     * policy2 exp 2027-12-31 → year 2025 < 2027 → valid
     * policy3 exp 2024-03-15 → year 2025 > 2024 → EXPIRED ✓
     * Expected count: 2
     */
    public static void testFilterByExpiryDate(InsuranceCompany company) {
        System.out.println(
                "TEST [16/43] filterByExpiryDate(2025-07-01) - policy1, policy3, policy5 expired → expected size: 3");
        testResult(3, company.filterByExpiryDate(new MyDate(2025, 7, 1)).size());
    }

    // =================================================================
    // ALL POLICIES COUNT
    // =================================================================

    public static void testAllPoliciesCount(InsuranceCompany company) {
        System.out.println("TEST [17/43] allPolicies() - 5 policies added across all users → expected size: 5");
        testResult(5, company.allPolicies().size());
    }

    // =================================================================
    // CAR PRICE RISE (modifies state - runs after payment tests)
    // =================================================================

    /**
     * Raise Alice's cars by 10%:
     * - car1: 25000 * 1.10 = 27500 -> 27500/100 + 200 + 20 = 495.0
     * - car2: 52000 * 1.10 = 57200 -> 57200/50 + 20 + 100 = 1264.0
     * Expected: 495.0 + 1264.0 = 1759.0
     */
    public static void testCarPriceRiseEffect(InsuranceCompany company) {
        System.out.println("TEST [18/43] carPriceRise(1001, 10%) - Camry $27500, BMW $57200   expected: 1759.0");
        company.carPriceRise(1001, 0.10);
        double newCar1 = 25000.0 * 1.10;
        double newCar2 = 52000.0 * 1.10;
        double expected = (newCar1 / 100.0 + 1 * 200.0 + 20.0)
                + (newCar2 / 50.0 + 0 * 200.0 + 20.0 + (30 - 28) * 50.0);
        testResult(expected, company.calcTotalPayments(1001));
    }

    // =================================================================
    // DATE LOGIC
    // =================================================================

    public static void testMyDateIsExpiredTrue() {
        System.out.println(
                "TEST [19/43] MyDate.isExpired - cutoff(2025,7,1) vs expiry(2025,6,30), month 7>6 → expected: true");
        testResult(true, new MyDate(2025, 7, 1).isExpired(new MyDate(2025, 6, 30)));
    }

    public static void testMyDateIsExpiredFalse() {
        System.out.println(
                "TEST [20/43] MyDate.isExpired - cutoff(2024,1,1) vs expiry(2025,6,30), year 2024<2025 → expected: false");
        testResult(false, new MyDate(2024, 1, 1).isExpired(new MyDate(2025, 6, 30)));
    }

    public static void testMyDateIsExpiredSameDay() {
        System.out.println(
                "TEST [21/43] MyDate.isExpired - cutoff(2025,6,30) vs expiry(2025,6,30), same day → expected: true");
        testResult(true, new MyDate(2025, 6, 30).isExpired(new MyDate(2025, 6, 30)));
    }

    // =================================================================
    // CITY AGGREGATION & REPORTING (Standard Level)
    // =================================================================

    /**
     * populateDistinctCityNames must return unique city names in discovery order.
     * Order from setup: Alice (Wollongong) -> Bob (Shiraz) -> Charlie (Shiraz) ->
     * Diana (Wollongong) -> Ethan (Shiraz)
     * Expected unique list: ["Wollongong", "Shiraz"]
     */
    public static void testPopulateDistinctCityNames(InsuranceCompany company) {
        System.out.println(
                "TEST [22/43] populateDistinctCityNames - unique cities in order   expected: [Wollongong, Shiraz]");

        ArrayList<String> expected = new ArrayList<>();
        expected.add("Wollongong");
        expected.add("Shiraz");
        testResult(expected, company.populateDistinctCityNames());
    }

    /**
     * Wollongong includes:
     * - Alice (1001, post-rise): 1759.0
     * - Diana (1004): 30000/100 + 1*200 + 20 = 520.0
     * Expected: 1759.0 + 520.0 = 2279.0
     */
    public static void testGetTotalPaymentForCityWollongong(InsuranceCompany company) {
        System.out.println("TEST [23/43] getTotalPaymentForCity(\"Wollongong\") - Alice + Diana   expected: 2279.0");
        double aliceTotal = ((25000.0 * 1.10) / 100.0 + 1 * 200.0 + 20.0)
                + ((52000.0 * 1.10) / 50.0 + 0 * 200.0 + 20.0 + (30 - 28) * 50.0);
        double dianaTotal = (30000.0 / 100.0) + (1 * 200.0) + 20.0;
        double expected = aliceTotal + dianaTotal;
        testResult(expected, company.getTotalPaymentForCity("Wollongong"));
    }

    /**
     * Shiraz includes:
     * - Bob (1002): 18000/100 + 2*200 + 20 = 600.0
     * - Charlie (1003): 45000/50 + 0*200 + 20 = 920.0
     * - Ethan (1005): 0 policies = 0.0
     * Expected: 600.0 + 920.0 = 1520.0
     */
    public static void testGetTotalPaymentForCityShiraz(InsuranceCompany company) {
        System.out.println("TEST [24/43] getTotalPaymentForCity(\"Shiraz\") - Bob + Charlie   expected: 1520.0");
        double bobTotal = (18000.0 / 100.0) + (2 * 200.0) + 20.0;
        double charlieTotal = (45000.0 / 50.0) + (0 * 200.0) + 20.0;
        double expected = bobTotal + charlieTotal;
        testResult(expected, company.getTotalPaymentForCity("Shiraz"));
    }

    /**
     * getTotalPaymentForCity for a city with no registered users must return 0.0.
     */
    public static void testGetTotalPaymentForCityNotFound(InsuranceCompany company) {
        System.out.println("TEST [25/43] getTotalPaymentForCity(\"Melbourne\") - no registered users   expected: 0.0");
        testResult(0.0, company.getTotalPaymentForCity("Melbourne"));
    }

    /**
     * Aggregated payments for [Wollongong, Shiraz] -> [2279.0, 1520.0]
     */
    public static void testGetTotalPaymentPerCity(InsuranceCompany company) {
        System.out.println("TEST [26/43] getTotalPaymentPerCity - aggregated payments for [Wollongong, Shiraz]");
        String city1 = "Wollongong";
        String city2 = "Shiraz";
        ArrayList<String> cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);
        double expected1 = 2279.0;
        double expected2 = 1520.0;
        ArrayList<Double> expected = new ArrayList<>();
        expected.add(expected1);
        expected.add(expected2);
        testResult(expected, company.getTotalPaymentPerCity(cities));
    }

    // =================================================================
    // ADVANCE LEVEL TESTS
    // ================================================================
    // =================================================================
    // REMOVE POLICY
    // =================================================================

    public static void testRemovePolicySuccess(InsuranceCompany company) {
        System.out.println("TEST [27/43] removePolicy - remove policy 401 from user 1004 → expected: true");
        testResult(true, company.removePolicy(1004, 401));
    }

    public static void testRemovePolicyAlreadyRemoved(InsuranceCompany company) {
        System.out.println("TEST [28/43] removePolicy - remove policy 401 again (already removed) → expected: false");
        testResult(false, company.removePolicy(1004, 401));
    }

    public static void testRemovePolicyInvalidUser(InsuranceCompany company) {
        System.out.println("TEST [29/43] removePolicy - user 9999 does not exist → expected: false");
        testResult(false, company.removePolicy(9999, 101));
    }

    // =================================================================
    // AUTO-GENERATED USER ID
    // =================================================================

    public static void testAutoGeneratedUserIDIncremental() {
        System.out
                .println("TEST [30/43] getNextUserID - IDs must increment by 1 each call → expected: consecutive ints");
        int first = User.getNextUserID();
        int second = User.getNextUserID();
        int third = User.getNextUserID();
        testResult(true, second == first + 1 && third == second + 1);
    }

    public static void testAutoGeneratedUserIDIsUnique(InsuranceCompany company) {
        System.out.println(
                "TEST [31/43] getNextUserID - new user added with auto ID, must not duplicate → expected: true");
        int autoID = User.getNextUserID();
        User newUser = new User("Generated User", autoID, new Address(1, "Auto St", "GenSuburb", "Wollongong"));
        testResult(true, company.addUser(newUser));
    }

    // =================================================================
    // REMOVE USER
    // =================================================================

    public static void testRemoveUserSuccess(InsuranceCompany company) {
        System.out.println("TEST [32/43] removeUser - remove user 1005 (Ethan) → expected: true");
        testResult(true, company.removeUser(1005));
    }

    public static void testRemoveUserNotFound(InsuranceCompany company) {
        System.out.println("TEST [33/43] removeUser - user 9999 does not exist → expected: false");
        testResult(false, company.removeUser(9999));
    }

    public static void testRemoveUserConfirmGone(InsuranceCompany company) {
        System.out.println("TEST [34/43] findUser - user 1005 removed, findUser must return null → expected: true");
        testResult(true, company.findUser(1005) == null);
    }

    // =================================================================
    // CHANGE ADMIN PASSWORD
    // =================================================================

    public static void testChangeAdminPasswordWrongOld(InsuranceCompany company) {
        System.out.println("TEST [35/43] changeAdminPassword - wrong old password → expected: false");
        testResult(false, company.changeAdminPassword("wrongPassword", "newPass123"));
    }

    public static void testChangeAdminPasswordSuccess(InsuranceCompany company) {
        System.out.println("TEST [36/43] changeAdminPassword - correct old password → expected: true");
        testResult(true, company.changeAdminPassword("admin123", "newPass123"));
    }

    public static void testChangeAdminPasswordValidateNew(InsuranceCompany company) {
        System.out.println("TEST [37/43] validateAdmin - login with new password after change → expected: true");
        testResult(true, company.validateAdmin("admin", "newPass123"));
    }

    public static void testChangeAdminPasswordOldInvalid(InsuranceCompany company) {
        System.out.println("TEST [38/43] validateAdmin - old password must no longer work → expected: false");
        testResult(false, company.validateAdmin("admin", "admin123"));
    }

    // =================================================================
    // CAR MODEL AGGREGATION
    // =================================================================

    /**
     * Alice (1001) has:
     * policy1 → Toyota Camry
     * policy2 → BMW X5
     * Expected distinct models: [Toyota Camry, BMW X5]
     */
    public static void testPopulateDistinctCarModelsUser(InsuranceCompany company) {
        System.out.println("TEST [39/43] populateDistinctCarModels (User 1001) - Camry + BMW X5 → expected size: 2");
        User alice = company.findUser(1001);
        testResult(2, alice.populateDistinctCarModels().size());
    }

    /**
     * Alice has 1 Toyota Camry policy (after car price rise, same count).
     * Count for "Toyota Camry" → expected: 1
     * Total payment for "Toyota Camry" → 27500 / (100 + 1×200 + 20) = 27500/320 =
     * 85.9375
     */
    public static void testGetTotalCountForCarModelUser(InsuranceCompany company) {
        System.out.println("TEST [40/43] getTotalCountForCarModel (User 1001, \"Toyota Camry\") → expected: 1.0");
        User alice = company.findUser(1001);
        testResult(1.0, alice.getTotalCountForCarModel("Toyota Camry"));
    }

    /**
     * Alice (1001) Toyota Camry post-rise price: $27500.0
     * Payment: 27500/100 + 1*200 + 20 = 275 + 200 + 20 = 495.0
     */
    public static void testGetTotalPaymentForCarModelUser(InsuranceCompany company) {
        System.out.println(
                "TEST [41/43] getTotalPaymentForCarModel (User 1001, \"Toyota Camry\", flatRate 20)   expected: 495.0");
        User alice = company.findUser(1001);
        double expected = ((25000.0 * 1.10) / 100.0) + (1 * 200.0) + 20.0;
        testResult(expected, alice.getTotalPaymentForCarModel("Toyota Camry", 20));
    }

    // =================================================================
    // CAR MODEL AGGREGATION
    // =================================================================

    /**
     * After removing Ethan (1005, no policies) and Diana's policy 401:
     * Remaining policies:
     * Alice → Toyota Camry, BMW X5
     * Bob → Honda Civic
     * Charlie→ Toyota RAV4
     * Expected distinct company models: [Toyota Camry, BMW X5, Honda Civic, Toyota
     * RAV4]
     */
    public static void testPopulateDistinctCarModelsCompany(InsuranceCompany company) {
        System.out.println(
                "TEST [42/43] populateDistinctCarModels (Company) - Camry, BMW X5, Civic, RAV4 → expected size: 4");
        testResult(4, company.populateDistinctCarModels().size());
    }

    /**
     * Count and payment per model across whole company:
     * Toyota Camry → 1 policy, post-rise: 27500/320 = 85.9375
     * BMW X5 → 1 policy, post-rise: 57200/70 + 100 ≈ 917.143
     * Honda Civic → 1 policy: 18000/520 ≈ 34.615
     * Toyota RAV4 → 1 policy: 45000/70 ≈ 642.857
     * Total count across all models → 4
     * Total payments summed → same as calcTotalPayments() at this state
     */
    public static void testGetTotalCountPerCarModelCompany(InsuranceCompany company) {
        System.out.println(
                "TEST [43/43] getTotalCountPerCarModel (Company) - 1 policy each for 4 models → expected sum: 4");
        ArrayList<String> models = company.populateDistinctCarModels();
        ArrayList<Integer> counts = company.getTotalCountPerCarModel(models);
        int total = 0;
        for (int c : counts)
            total += c;
        testResult(4, total);
    }

    public static void testCase() throws CloneNotSupportedException {
        // Build the company and populate it
        InsuranceCompany company = new InsuranceCompany("SafeGuard Insurance", "admin", "admin123", 20);
        fillData(company);

        // Retrieve user1 from the company to pass into tests that need it
        User user1 = company.findUser(1001);

        System.out.println("=================================================================");
        System.out.println("         SAFEGUARD INSURANCE - CORE TEST SUITE                  ");
        System.out.println("=================================================================");
        System.out.println("Company flat rate : 20");
        System.out.println("Alice (1001) holds: policy1 ThirdParty   Camry  $25000 exp 2025-06-30");
        System.out.println("                    policy2 Comprehensive BMW X5 $52000 age 28 exp 2027-12-31");
        System.out.println("Bob   (1002) holds: policy3 ThirdParty   Civic  $18000 exp 2024-03-15");
        System.out.println("=================================================================\n\n");

        // Admin validation
        testValidateAdminCorrect(company);
        testValidateAdminWrong(company);

        // User management
        testAddUserDuplicate(company);
        testFindUserExists(company, user1);
        testFindUserNotFound(company);

        // Policy management
        testFindPolicyExists(company);
        testFindPolicyNotFound(company);
        testAddPolicyDuplicate(company);

        // Premium calculations (isolated - no company needed)
        testThirdPartyCalcPayment();
        testComprehensiveCalcPaymentOlderDriver();
        testComprehensiveCalcPaymentYoungDriver();

        // Total payments (BEFORE price rise)
        testCalcTotalPaymentsForUser(company);
        testCalcTotalPaymentsAll(company);

        // Filtering
        testFilterByCarModelFound(company);
        testFilterByCarModelNotFound(company);
        testFilterByExpiryDate(company);

        // All policies count
        testAllPoliciesCount(company);

        // Price rise (state change - after all payment tests)
        testCarPriceRiseEffect(company);

        // Date logic
        testMyDateIsExpiredTrue();
        testMyDateIsExpiredFalse();
        testMyDateIsExpiredSameDay();

        // City aggregation & reporting
        testPopulateDistinctCityNames(company);
        testGetTotalPaymentForCityWollongong(company);
        testGetTotalPaymentForCityShiraz(company);
        testGetTotalPaymentForCityNotFound(company);
        testGetTotalPaymentPerCity(company);

        // Remove Policy
        testRemovePolicySuccess(company);
        testRemovePolicyAlreadyRemoved(company);
        testRemovePolicyInvalidUser(company);

        // Auto-generated User ID
        testAutoGeneratedUserIDIncremental();
        testAutoGeneratedUserIDIsUnique(company);

        // Remove User
        testRemoveUserSuccess(company); // removes Ethan (1005)
        testRemoveUserNotFound(company);
        testRemoveUserConfirmGone(company);

        // Change Admin Password
        testChangeAdminPasswordWrongOld(company);
        testChangeAdminPasswordSuccess(company);
        testChangeAdminPasswordValidateNew(company);
        testChangeAdminPasswordOldInvalid(company);

        // Car Model Aggregation - User
        testPopulateDistinctCarModelsUser(company);
        testGetTotalCountForCarModelUser(company);
        testGetTotalPaymentForCarModelUser(company);

        // Car Model Aggregation - Company
        testPopulateDistinctCarModelsCompany(company);
        testGetTotalCountPerCarModelCompany(company);

        testLab4(company);

        System.out.println("=================================================================");
        System.out.println("                    ALL TESTS COMPLETE                          ");
        System.out.println("=================================================================");
    }

    // lab4
    public static void testLab4(InsuranceCompany company) throws CloneNotSupportedException {
        System.out.println("==============================  LAB-4 TEST ===============================");

        User user = company.findUser(1001);

        // 1. Make shallow and deep copies of user's policies
        ArrayList<InsurancePolicy> shallowPolicies = user.shallowCopyPolicies();
        ArrayList<InsurancePolicy> deepPolicies = user.deepCopyPolicies();

        // 2. Change user's city to "New York" and add a new policy
        user.setCity("New York");
        Car newCar = new Car(2021, 35000.0, "Nissan Altima", Car.CarType.SED);
        MyDate newExp = new MyDate(2026, 5, 10);
        InsurancePolicy newPolicy = new ThirdPartyPolicy("Alice Smith", 501, newCar, 0, newExp, "New Policy");
        user.addPolicy(newPolicy);

        // 3. Sort policies by expiry date
        ArrayList<InsurancePolicy> sortedPolicies = user.sortPoliciesByDate();

        // 4. Print shallow, deep, and current policies to compare
        System.out.println("\n--- [1] Shallow Copy of Policies (Before Changes) ---");
        InsurancePolicy.printPolicies(shallowPolicies);

        System.out.println("\n--- [2] Deep Copy of Policies (Before Changes) ---");
        InsurancePolicy.printPolicies(deepPolicies);

        System.out.println("\n--- [3] User's Current Policies (With New Policy Added) ---");
        InsurancePolicy.printPolicies(user.getPolicies());

        System.out.println("\n--- [4] User's Policies Sorted by Expiry Date ---");
        InsurancePolicy.printPolicies(sortedPolicies);

        // -------------------------------------------------------------------------

        // 1. Make shallow and deep copies of company's users
        ArrayList<User> shallowUsers = company.shallowCopyUsers();
        ArrayList<User> deepUsers = company.deepCopyUsers();

        // 2. Add a new user to the company
        Address newAddr = new Address(55, "Broadway", "Midtown", "New York");
        User newUser = new User("Frank Castle", 1006, newAddr);
        company.addUser(newUser);

        // 3. Sort company users by city
        ArrayList<User> sortedUsers = company.sortUsers();

        // 4. Print shallow, deep, current, and sorted users to compare
        System.out.println("\n--- [1] Shallow Copy of Users (Before Adding User) ---");
        for (User u : shallowUsers) {
            System.out.println("ID: " + u.getUserID() + " | Name: " + u.getName() + " | City: " + u.getCity());
        }

        System.out.println("\n--- [2] Deep Copy of Users (Before Adding User) ---");
        for (User u : deepUsers) {
            System.out.println("ID: " + u.getUserID() + " | Name: " + u.getName() + " | City: " + u.getCity());
        }

        System.out.println("\n--- [3] Company's Current Users (With Frank Added) ---");
        for (User u : company.getUsers()) {
            System.out.println("ID: " + u.getUserID() + " | Name: " + u.getName() + " | City: " + u.getCity());
        }

        System.out.println("\n--- [4] Company Users Sorted by City ---");
        for (User u : sortedUsers) {
            System.out.println("ID: " + u.getUserID() + " | Name: " + u.getName() + " | City: " + u.getCity());
        }

        // -------------------------------------------------------------------------

        System.out.println("\n--- Testing InsuranceCompany Deep Copy Cloning ---");
        InsuranceCompany clonedCompany = company.clone();

        // Mutate an address inside the original company
        company.findUser(1001).setCity("Los Angeles");

        // Verify original updated
        boolean originalUpdated = company.findUser(1001).getCity().equals("Los Angeles");
        // Verify clone remains unchanged at "New York"
        boolean cloneUnchanged = clonedCompany.findUser(1001).getCity().equals("New York");

        System.out.println("Original Company User 1001 City updated to Los Angeles: " + originalUpdated);
        System.out.println("Cloned Company User 1001 City remains New York: " + cloneUnchanged);

        testResult(true, originalUpdated && cloneUnchanged);

        System.out.println("============================  LAB-4 test completed =========================");
    }
}
