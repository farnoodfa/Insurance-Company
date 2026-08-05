import java.util.ArrayList;

public class App {
    // Constant for flat rate passed to calculation methods
    private static double flatRate = 20.0;

    public static void main(String[] args) throws Exception {
        // Create Car examples
        Car car1 = new Car(2020, 25000.0, "Toyota Camry", Car.CarType.SED);
        Car car2 = new Car(2022, 50000.0, "BMW X5", Car.CarType.SUV);
        Car car3 = new Car(2021, 18000.0, "Honda Civic", Car.CarType.HATCH);

        // Create policy examples
        InsurancePolicy policy1 = new ThirdPartyPolicy("Alice Smith", 101, car1, 1, "Standard third-party cover");
        InsurancePolicy policy2 = new ComprehensivePolicy("Bob Johnson", 102, car2, 0, 25, 1);
        InsurancePolicy policy3 = new ThirdPartyPolicy("Charlie Brown", 103, car3, 2, "Includes road assistance");

        // array list of parents 
        ArrayList<InsurancePolicy> policies = new ArrayList<>();
        policies.add(policy1); // adding children
        policies.add(policy2);
        policies.add(policy3);

        // Print all policies using print() method
        System.out.println("=== Printing Policies using print() Method ===");
        for (InsurancePolicy policy : policies) {
            policy.print();
            System.out.println("----------------------------------------");
        }

        // Print all policies using toString() method
        System.out.println("\n=== Printing Policies using toString() Method ===");
        for (InsurancePolicy policy : policies) {
            System.out.println(policy.toString());
            System.out.println("----------------------------------------");
        }

        // Calculate total premium payments passing flatRate as a constant number
        double totalPremium = 0.0;
        for (InsurancePolicy policy : policies) {
            totalPremium += policy.calcPayment(flatRate);
        }

        // Print total premium payments
        System.out.println("\nTotal Policy Premiums Summary");
        System.out.println("Applied Flat Rate: $" + flatRate);
        System.out.println("Total Combined Premium Payment: $" + totalPremium);
    }
}