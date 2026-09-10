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
     * Car car3 = new Car(2021, 18000.0, "Honda Civic", Car.CarType.HATCH);
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
     * Car car3 = new Car(2021, 18000.0, "Honda Civic", Car.CarType.HATCH);
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
     * Car car5 = new Car(2021, 30000.0, "Mazda 3", Car.CarType.SED);
     * 
     * MyDate expDate1 = new MyDate(2025, 6, 30);
     * MyDate expDate2 = new MyDate(2027, 12, 31);
     * MyDate expDate3 = new MyDate(2024, 3, 15);
     * MyDate expDate4 = new MyDate(2026, 8, 20);
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
     * println("\n=== 13. Filter by Expiry Date for User 1001 (Cutoff: 2026-01-01) ==="
     * );
     * MyDate cutoffDate1001 = new MyDate(2026, 1, 1);
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

    public static void main(String[] args) {
        coreTest();
    }

    // =================================================================
    // SHARED STATE
    // =================================================================
    static InsuranceCompany company;
    static User user1, user2, user3, user4, user5;
    static InsurancePolicy policy1, policy2, policy3;

    /** Builds every instance exactly as written by the student. */
    public static void setup() {
        company = new InsuranceCompany("SafeGuard Insurance",
                "admin", "admin123", 20);

        Address addr1 = new Address(12, "Crown St", "Gwynneville", "Wollongong");
        Address addr2 = new Address(45, "George St", "Haymarket", "Shiraz");
        Address addr3 = new Address(78, "Bourke St", "Surry Hills", "Shiraz");
        Address addr4 = new Address(90, "King St", "Newtown", "Wollongong");
        Address addr5 = new Address(34, "Pitt St", "CBD", "Shiraz");

        user1 = new User("Alice Smith", 1001, addr1);
        user2 = new User("Bob Johnson", 1002, addr2);
        user3 = new User("Charlie Brown", 1003, addr3);
        user4 = new User("Diana Prince", 1004, addr4);
        user5 = new User("Ethan Hunt", 1005, addr5);

        Car car1 = new Car(2020, 25000.0, "Toyota Camry", Car.CarType.SED);
        Car car2 = new Car(2022, 52000.0, "BMW X5", Car.CarType.SUV);
        Car car3 = new Car(2019, 18000.0, "Honda Civic", Car.CarType.HATCH);
        Car car4 = new Car(2023, 45000.0, "Toyota RAV4", Car.CarType.SUV);
        Car car5 = new Car(2021, 30000.0, "Mazda 3", Car.CarType.SED);

        MyDate expDate1 = new MyDate(2025, 6, 30);
        MyDate expDate2 = new MyDate(2027, 12, 31);
        MyDate expDate3 = new MyDate(2024, 3, 15);
        MyDate expDate4 = new MyDate(2026, 8, 20);

        policy1 = new ThirdPartyPolicy("Alice Smith", 101, car1, 1,
                expDate1, "Standard third-party");
        policy2 = new ComprehensivePolicy("Alice Smith", 102, car2,
                0, expDate2, 28, 1);
        policy3 = new ThirdPartyPolicy("Bob Johnson", 201, car3, 2,
                expDate3, "Roadside assistance");

        // Register all users with the company
        company.addUser(user1);
        company.addUser(user2);
        company.addUser(user3);
        company.addUser(user4);
        company.addUser(user5);

        // Attach policies to their owners
        company.addPolicy(1001, policy1); // Alice: ThirdParty (Camry)
        company.addPolicy(1001, policy2); // Alice: Comprehensive (BMW X5)
        company.addPolicy(1002, policy3); // Bob: ThirdParty (Civic)
    }

    // =================================================================
    // SHARED HELPER
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

    /** Correct username + password must return true. */
    public static void testValidateAdminCorrect() {
        System.out.println("TEST [1/21] validateAdmin - correct credentials → expected: true");
        testResult(true, company.validateAdmin("admin", "admin123"));
    }

    /** Wrong password must return false. */
    public static void testValidateAdminWrong() {
        System.out.println("TEST [2/21] validateAdmin - wrong password → expected: false");
        testResult(false, company.validateAdmin("admin", "wrongpass"));
    }

    // =================================================================
    // USER MANAGEMENT
    // =================================================================

    /** Registering a user whose ID already exists must return false. */
    public static void testAddUserDuplicate() {
        System.out.println("TEST [3/21] addUser - duplicate ID 1001 → expected: false");
        User duplicate = new User("Alice Clone", 1001,
                new Address(1, "Fake St", "Nowhere", "Wollongong"));
        testResult(false, company.addUser(duplicate));
    }

    /** findUser must return the exact same object that was added. */
    public static void testFindUserExists() {
        System.out.println("TEST [4/21] findUser - ID 1001 exists → expected: user1 object (true)");
        // User does not override equals(); same reference is stored and returned.
        testResult(user1, company.findUser(1001));
    }

    /** findUser on an unknown ID must produce null. */
    public static void testFindUserNotFound() {
        System.out.println("TEST [5/21] findUser - ID 9999 does not exist → expected: null → (true)");
        testResult(true, company.findUser(9999) == null);
    }

    // =================================================================
    // POLICY MANAGEMENT
    // =================================================================

    /** A policy that was added must be findable via findPolicy. */
    public static void testFindPolicyExists() {
        System.out.println("TEST [6/21] findPolicy - policy ID 101 for user 1001 → expected: non-null (true)");
        testResult(true, company.findPolicy(1001, 101) != null);
    }

    /** Searching for a policy ID that was never added must return null. */
    public static void testFindPolicyNotFound() {
        System.out.println("TEST [7/21] findPolicy - policy ID 9999 for user 1001 → expected: null (true)");
        testResult(true, company.findPolicy(1001, 9999) == null);
    }

    /**
     * Adding a policy whose ID is already registered for a user must return false.
     */
    public static void testAddPolicyDuplicate() {
        System.out.println("TEST [8/21] addPolicy - duplicate policy ID 101 for user 1001 → expected: false");
        Car dupCar = new Car(2018, 10000.0, "Kia Rio", Car.CarType.HATCH);
        ThirdPartyPolicy dup = new ThirdPartyPolicy(
                "Alice Smith", 101, dupCar, 0, new MyDate(2025, 1, 1), "duplicate");
        testResult(false, company.addPolicy(1001, dup));
    }

    // =================================================================
    // PREMIUM CALCULATION (isolated objects - no shared state)
    // =================================================================

    /**
     * ThirdParty formula: price / (100 + claims×200 + flatRate)
     * = 25000 / (100 + 1×200 + 20) = 25000 / 320 = 78.125 (exact)
     */
    public static void testThirdPartyCalcPayment() {
        System.out
                .println("TEST [9/21] ThirdPartyPolicy.calcPayment - 25000/(100+200+20)=25000/320 → expected: 78.125");
        Car testCar = new Car(2020, 25000.0, "Toyota Camry", Car.CarType.SED);
        ThirdPartyPolicy tp = new ThirdPartyPolicy(
                "Alice Smith", 101, testCar, 1, new MyDate(2025, 6, 30), "test");
        double expected = 25000.0 / (100 + 1 * 200 + 20); // = 78.125
        testResult(expected, tp.calcPayment(20));
    }

    /**
     * Comprehensive - driver age 35 (≥ 30, no age surcharge):
     * = 52000 / (50 + 0×200 + 20) = 52000 / 70 ≈ 742.857
     */
    public static void testComprehensiveCalcPaymentOlderDriver() {
        System.out.println(
                "TEST [10/21] ComprehensivePolicy.calcPayment - age 35 (no surcharge), 52000/(50+20) → expected: 52000/70 ≈ 742.857");
        Car testCar = new Car(2022, 52000.0, "BMW X5", Car.CarType.SUV);
        ComprehensivePolicy cp = new ComprehensivePolicy(
                "Alice Smith", 102, testCar, 0, new MyDate(2027, 12, 31), 35, 1);
        double expected = 52000.0 / (50 + 0 * 200 + 20); // ≈ 742.857
        testResult(expected, cp.calcPayment(20));
    }

    /**
     * Comprehensive - driver age 28 (< 30, age surcharge applies):
     * base = 52000 / (50 + 0×200 + 20) = 52000 / 70 ≈ 742.857
     * surcharge = (30 − 28) × 50 = 100
     * total ≈ 842.857
     */
    public static void testComprehensiveCalcPaymentYoungDriver() {
        System.out.println(
                "TEST [11/21] ComprehensivePolicy.calcPayment - age 28 (surcharge +100), 52000/70+100 → expected: ≈ 842.857");
        Car testCar = new Car(2022, 52000.0, "BMW X5", Car.CarType.SUV);
        ComprehensivePolicy cp = new ComprehensivePolicy(
                "Alice Smith", 102, testCar, 0, new MyDate(2027, 12, 31), 28, 1);
        double expected = 52000.0 / (50 + 0 * 200 + 20) + (30 - 28) * 50; // ≈ 842.857
        testResult(expected, cp.calcPayment(20));
    }

    // =================================================================
    // TOTAL PAYMENTS (uses company state - must run BEFORE price rise)
    // =================================================================

    /**
     * Alice (1001) has policy1 + policy2:
     * 78.125 + 52000/70 + 100 ≈ 920.982
     */
    public static void testCalcTotalPaymentsForUser() {
        System.out.println(
                "TEST [12/21] calcTotalPayments(1001) - policy1(78.125) + policy2(≈842.857) → expected: ≈ 920.982");
        double expected = 25000.0 / (100 + 1 * 200 + 20)
                + 52000.0 / (50 + 0 * 200 + 20) + (30 - 28) * 50;
        testResult(expected, company.calcTotalPayments(1001));
    }

    /**
     * All users: Alice(≈920.982) + Bob(≈34.615) ≈ 955.598
     */
    public static void testCalcTotalPaymentsAll() {
        System.out.println("TEST [13/21] calcTotalPayments() - Alice(≈920.982) + Bob(≈34.615) → expected: ≈ 955.598");
        double expected = 25000.0 / (100 + 1 * 200 + 20)
                + 52000.0 / (50 + 0 * 200 + 20) + (30 - 28) * 50
                + 18000.0 / (100 + 2 * 200 + 20);
        testResult(expected, company.calcTotalPayments());
    }

    // =================================================================
    // FILTERING
    // =================================================================

    /**
     * "Toyota" appears only in car1 ("Toyota Camry") → 1 match company-wide.
     * car4 ("Toyota RAV4") is not attached to any policy, so it is not counted.
     */
    public static void testFilterByCarModelFound() {
        System.out
                .println("TEST [14/21] filterByCarModel(\"Toyota\") - matches policy1 (Camry) only → expected size: 1");
        testResult(1, company.filterByCarModel("Toyota").size());
    }

    /** "Tesla" matches no car model in any policy → empty list. */
    public static void testFilterByCarModelNotFound() {
        System.out.println("TEST [15/21] filterByCarModel(\"Tesla\") - no match → expected size: 0");
        testResult(0, company.filterByCarModel("Tesla").size());
    }

    /**
     * Cutoff 2025-07-01:
     * policy1 exp 2025-06-30 → month 7 > 6 → EXPIRED ✓
     * policy2 exp 2027-12-31 → year 2025 < 2027 → valid
     * policy3 exp 2024-03-15 → year 2025 > 2024 → EXPIRED ✓
     * Expected count: 2
     */
    public static void testFilterByExpiryDate() {
        System.out
                .println("TEST [16/21] filterByExpiryDate(2025-07-01) - policy1 & policy3 expired → expected size: 2");
        MyDate cutoff = new MyDate(2025, 7, 1);
        testResult(2, company.filterByExpiryDate(cutoff).size());
    }

    // =================================================================
    // ALL POLICIES COUNT
    // =================================================================

    /** Three policies were added across all users → allPolicies() returns 3. */
    public static void testAllPoliciesCount() {
        System.out.println("TEST [17/21] allPolicies() - 3 policies added across all users → expected size: 3");
        testResult(3, company.allPolicies().size());
    }

    // ===============================================================
    // CAR PRICE RISE (modifies company state - runs after payment tests)
    // =================================================================

    /**
     * Raise all of Alice's car prices by 10%:
     * car1: $25 000 → $27 500 | new payment1 = 27500/320 = 85.9375
     * car2: $52 000 → $57 200 | new payment2 = 57200/70 + 100 ≈ 917.143
     * New Alice total ≈ 1003.080
     */
    public static void testCarPriceRiseEffect() {
        System.out.println(
                "TEST [18/21] carPriceRise(1001, 10%) - Camry $25000→$27500, BMW $52000→$57200 → expected: ≈ 1003.080");
        company.carPriceRise(1001, 0.10);
        double newCar1Price = 25000.0 * 1.10;
        double newCar2Price = 52000.0 * 1.10;
        double expected = newCar1Price / (100 + 1 * 200 + 20)
                + newCar2Price / (50 + 0 * 200 + 20) + (30 - 28) * 50;
        testResult(expected, company.calcTotalPayments(1001));
    }

    // =================================================================
    // DATE LOGIC (MyDate.isExpired)
    // =================================================================

    /**
     * Cutoff year > expiry year → expired → true.
     * cutoff(2025,7,1).isExpired(expiry(2025,6,30)): same year, month 7 > 6 → true
     */
    public static void testMyDateIsExpiredTrue() {
        System.out.println(
                "TEST [19/21] MyDate.isExpired - cutoff(2025,7,1) vs expiry(2025,6,30), month 7>6 → expected: true");
        testResult(true, new MyDate(2025, 7, 1).isExpired(new MyDate(2025, 6, 30)));
    }

    /**
     * Cutoff year < expiry year → still valid → false.
     * cutoff(2024,1,1).isExpired(expiry(2025,6,30)): year 2024 < 2025 → false
     */
    public static void testMyDateIsExpiredFalse() {
        System.out.println(
                "TEST [20/21] MyDate.isExpired - cutoff(2024,1,1) vs expiry(2025,6,30), year 2024<2025 → expected: false");
        testResult(false, new MyDate(2024, 1, 1).isExpired(new MyDate(2025, 6, 30)));
    }

    /**
     * Cutoff == expiry (same day) → treated as expired → true.
     * isExpired returns: day >= expiryDate.day when year & month are equal.
     * cutoff(2025,6,30).isExpired(expiry(2025,6,30)): 30 >= 30 → true
     */
    public static void testMyDateIsExpiredSameDay() {
        System.out.println(
                "TEST [21/21] MyDate.isExpired - cutoff(2025,6,30) vs expiry(2025,6,30), same day edge case → expected: true");
        testResult(true, new MyDate(2025, 6, 30).isExpired(new MyDate(2025, 6, 30)));
    }

    // =================================================================
    // single entry point that runs every test in story order
    // =================================================================

    public static void coreTest() {
        setup();

        System.out.println("=================================================================");
        System.out.println("         SAFEGUARD INSURANCE - CORE TEST SUITE                  ");
        System.out.println("=================================================================");
        System.out.println("Company flat rate : 20");
        System.out.println("Alice (1001) holds : policy1 ThirdParty Camry $25000 exp 2025-06-30");
        System.out.println("                   : policy2 Comprehensive BMW X5 $52000 age 28 exp 2027-12-31");
        System.out.println("Bob   (1002) holds : policy3 ThirdParty Civic $18000 exp 2024-03-15");
        System.out.println("Charlie/Diana/Ethan: registered, no policies");
        System.out.println("=================================================================\n\n");

        // Group 1 - Admin validation
        testValidateAdminCorrect();
        testValidateAdminWrong();

        // Group 2 - User management
        testAddUserDuplicate();
        testFindUserExists();
        testFindUserNotFound();

        // Group 3 - Policy management
        testFindPolicyExists();
        testFindPolicyNotFound();
        testAddPolicyDuplicate();

        // Group 4 - Premium calculations (isolated objects)
        testThirdPartyCalcPayment();
        testComprehensiveCalcPaymentOlderDriver();
        testComprehensiveCalcPaymentYoungDriver();

        // Group 5 - Total payments (BEFORE price rise)
        testCalcTotalPaymentsForUser();
        testCalcTotalPaymentsAll();

        // Group 6 - Filtering
        testFilterByCarModelFound();
        testFilterByCarModelNotFound();
        testFilterByExpiryDate();

        // Group 7 - All policies count
        testAllPoliciesCount();

        // Group 8 - Price rise (state change - placed after all payment tests)
        testCarPriceRiseEffect();

        // Group 9 - Date logic
        testMyDateIsExpiredTrue();
        testMyDateIsExpiredFalse();
        testMyDateIsExpiredSameDay();

        System.out.println("=================================================================");
        System.out.println("                    ALL TESTS COMPLETE                          ");
        System.out.println("=================================================================");
    }

}
