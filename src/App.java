import java.util.ArrayList;
import java.util.Scanner;

// Driver class to test Insurance Policy system functionality
public class App {
    // Standard flat rate used across premium calculation tests
    private static final int FLAT_RATE = 20;

    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        // Initialize user, cars, and policies
        User user = initializeUserWithPolicies();

        // Display user details using print() and toString()
        displayUserInformation(user);

        // 1. Search for invalid ID (shows error message)
        testPolicyLookup(user, 999);

        // 2. Search for valid ID, print it, and save the returned policy object
        InsurancePolicy validPolicy = testPolicyLookup(user, 101);

        // 3. Perform modifications if found
        if (validPolicy != null) {
            testPolicyModifications(validPolicy);
        }

        // Test updating user address details
        testAddressUpdates(user, inputReader);

        // Test premium calculations and price rise effects
        testPremiumCalculations(user);

        // Test filtering policies by car model
        testPolicyFiltering(user, inputReader);

        inputReader.close();
    }

    // Creates test vehicles, policies, and a User instance with attached policies
    private static User initializeUserWithPolicies() {
        Car car1 = new Car(2020, 25000.0, "Toyota Camry", Car.CarType.SED);
        Car car2 = new Car(2022, 50000.0, "BMW X5", Car.CarType.SUV);
        Car car3 = new Car(2021, 18000.0, "Honda Civic", Car.CarType.HATCH);

        InsurancePolicy policy1 = new ThirdPartyPolicy("Alice Smith", 101, car1, 1, "Standard third-party cover");
        InsurancePolicy policy2 = new ComprehensivePolicy("Bob Johnson", 102, car2, 0, 25, 1);
        InsurancePolicy policy3 = new ThirdPartyPolicy("Charlie Brown", 103, car3, 2, "Includes road assistance");

        Address initialAddress = new Address(12, "Crown", "Gwynneville", "Sydney");
        User user = new User("John Doe", 1001, initialAddress);

        System.out.println("=== Adding Policies to User ===");
        addPolicyToUser(user, policy1);
        addPolicyToUser(user, policy2);
        addPolicyToUser(user, policy3);

        return user;
    }

    // Adds a policy to the user and prints status based on return value
    private static void addPolicyToUser(User user, InsurancePolicy policy) {
        boolean isAdded = user.addPolicy(policy);
        if (isAdded) {
            System.out.println("Policy ID " + policy.getID() + " added successfully.");
        } else {
            System.out.println("Failed to add Policy ID " + policy.getID() + " (Policy already exists).");
        }
    }

    // Prints user information using print() and toString()
    private static void displayUserInformation(User user) {
        System.out.println("\n=== User Details (print) ===");
        user.print();

        System.out.println("\n=== User Details (toString) ===");
        System.out.println(user.toString());
    }

    // Searches for a policy by ID, prints error or policy details, and returns the
    // policy
    private static InsurancePolicy testPolicyLookup(User user, int policyID) {
        System.out.println("\n=== Searching for Policy ID: " + policyID + " ===");
        InsurancePolicy policy = user.findPolicy(policyID);
        if (policy == null) {
            System.out.println("Policy has not been found");
        } else {
            policy.print();
        }
        return policy;
    }

    // Applies 10% price rise, updates policy holder name to Robert, and changes car
    // model
    private static void testPolicyModifications(InsurancePolicy policy) {
        // Apply 10% price rise and print before and after
        System.out.println("\n=== Policy Price Rise Test ===");
        policy.print();
        policy.carPriceRise(0.1);
        System.out.println("After 10% price rise:");
        policy.print();

        // Change policy holder name to Robert
        System.out.println("\n=== Updating Policy Holder Name ===");
        policy.setPolicyHolderName("Robert");
        policy.print();

        // Change car model to Toyota Camry 2018
        System.out.println("\n=== Updating Car Model ===");
        policy.setCarModel("Toyota Camry 2018");
        policy.print();
    }

    // Tests updating city and entering a completely new address
    private static void testAddressUpdates(User user, Scanner inputReader) {
        System.out.println("\n=== Updating City Directly ===");
        user.setCity("Wollongong");
        System.out.println("Updated Address: " + user.getAddress());

        System.out.println("\n=== Enter New Address Details ===");
        Address newAddress = promptForAddress(inputReader);
        user.setAddress(newAddress);

        System.out.println("\nUser details after new address update:");
        user.print();
    }

    // Tests calculating total premiums before and after a 10% rise across all
    // policies
    private static void testPremiumCalculations(User user) {
        System.out.println("\n=== Total Premium Payments ===");
        System.out.printf("Total Premium: $%.2f%n", user.calcTotalPremiums(FLAT_RATE));

        System.out.println("\n=== Applying 10% Car Price Rise to All Policies ===");
        user.carPriceRiseAll(0.1);

        System.out.println("=== Total Premium Payments After Price Rise ===");
        System.out.printf("Total Premium: $%.2f%n", user.calcTotalPremiums(FLAT_RATE));
    }

    // Prompts for a car model, filters user policies, and prints matches
    private static void testPolicyFiltering(User user, Scanner inputReader) {
        System.out.println("\n=== Filter Policies by Car Model ===");
        String searchModel = getUserString(inputReader, "Enter car model to search: ");

        ArrayList<InsurancePolicy> filteredPolicies = user.filterByCarModel(searchModel);

        System.out.println("\n=== Filtered Results ===");
        if (filteredPolicies.isEmpty()) {
            System.out.println("No policies found matching: " + searchModel);
        } else {
            InsurancePolicy.printPolicies(filteredPolicies);
        }
    }

    // Reads full address details from user input and creates an Address object
    private static Address promptForAddress(Scanner inputReader) {
        int streetNum = getUserint(inputReader, "Enter Street number: ");
        String street = getUserString(inputReader, "Enter Street Name: ");
        String suburb = getUserString(inputReader, "Enter Suburb: ");
        String city = getUserString(inputReader, "Enter City: ");

        return new Address(streetNum, street, suburb, city);
    }

    // Displays prompt and reads a trimmed line of text from console
    private static String getUserString(Scanner inputReader, String promptMessage) {
        System.out.print(promptMessage);
        return inputReader.nextLine();
    }

    private static int getUserint(Scanner inputReader, String promptMessage) {
        System.out.println(promptMessage);
        int userInt = inputReader.nextInt();
        inputReader.nextLine();
        return userInt;
    }
}