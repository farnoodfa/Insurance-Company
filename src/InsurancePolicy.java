import java.util.*;

abstract class InsurancePolicy {
    protected String policyHolderName;
    protected int id;
    protected Car car;
    protected int numberOfClaims;
    protected MyDate expiryDate;

    public InsurancePolicy(String policyHolderName, int id, Car car, int numberOfClaims) {
        this.policyHolderName = policyHolderName;
        this.id = id;
        this.car = car;
        this.numberOfClaims = numberOfClaims;
    }

    // getters
    public int getID() {
        return id;
    }

    // setters
    public void setPolicyHolderName(String newName) {
        policyHolderName = newName;
    }

    public void setCarModel(String model) {
        car.setModel(model);
    }

    public void print() {// printing policy
        System.out.println("Name: " + policyHolderName + " ID: " + id + " Number Of Claims: " + numberOfClaims);
        car.print();
    }

    public static void printPolicies(ArrayList<InsurancePolicy> policies) {
        for (InsurancePolicy policy : policies) {
            System.out.println(policy.toString());
        }
    }

    // Overloaded version to include flatRate and premium calculation
    public static void printPolicies(ArrayList<InsurancePolicy> policies, double flatRate) {
        for (InsurancePolicy policy : policies) {
            policy.print();
            System.out.println("Premium Payment: $" + policy.calcPayment(flatRate));
        }
    }

    public String toString() {
        return "Name: " + policyHolderName + " ID: " + id + " Number Of Claims: " + numberOfClaims
                + "\n" + car;
    }

    public abstract double calcPayment(double flatRate); // super method for calculating the payment

    public static double calcTotalPayments(ArrayList<InsurancePolicy> policies, int flatRate) { // calculating all
        double tatalPayments = 0.0;
        for (InsurancePolicy policy : policies) {
            tatalPayments += policy.calcPayment(flatRate);
        }
        return tatalPayments;
    }

    public void carPriceRise(double risePercent) {// calling priceRise in Car
        car.priceRise(risePercent);
    }

    public static void carPriceRiseAll(ArrayList<InsurancePolicy> policies, double risePercent) { // rising all
        if (policies == null) {
            return;
        }
        for (InsurancePolicy insurancePolicy : policies) {
            insurancePolicy.carPriceRise(risePercent);
        }
    }

    public static ArrayList<InsurancePolicy> filterByCarModel(ArrayList<InsurancePolicy> policies, String carModel) {// filtering
                                                                                                                     // by
                                                                                                                     // model
        ArrayList<InsurancePolicy> filteredPolicies = new ArrayList<InsurancePolicy>();
        for (InsurancePolicy insurancePolicy : policies) {
            if (insurancePolicy.car.getModel().contains(carModel)) {
                filteredPolicies.add(insurancePolicy);
            }
        }
        return filteredPolicies;
    }
}