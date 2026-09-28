import java.util.*;

abstract class InsurancePolicy implements Cloneable, Comparable<InsurancePolicy> {
    protected String policyHolderName;
    protected int id;
    protected Car car;
    protected int numberOfClaims;
    protected MyDate expiryDate;

    public InsurancePolicy(String policyHolderName, int id, Car car, int numberOfClaims, MyDate expiryDate)
            throws PolicyException {
        if (id < 300000 || id > 399999) {
            Random rand = new Random();
            int generatedID = 300000 + rand.nextInt(100000); // Generates 300000 - 399999

            this.id = generatedID;
            throw new PolicyException(generatedID);
        } else {
            this.id = id;
        }
        this.policyHolderName = policyHolderName;
        this.car = car;
        this.numberOfClaims = numberOfClaims;
        this.expiryDate = expiryDate;
    }

    // getters
    public int getID() {
        return id;
    }

    public MyDate getExpiryDate() {
        return expiryDate;
    }

    public String getCarModel() {
        return car.getModel();
    }

    public Car getCar() {
        return car;
    }

    public void setExpiryDate(MyDate expiryDate) {
        this.expiryDate = expiryDate;
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

    // filtering by model
    public static ArrayList<InsurancePolicy> filterByCarModel(ArrayList<InsurancePolicy> policies, String model) {
        ArrayList<InsurancePolicy> filteredPolicies = new ArrayList<>();
        for (InsurancePolicy insurancePolicy : policies) {
            if (insurancePolicy.car.getModel().toLowerCase().contains(model.toLowerCase())) {
                filteredPolicies.add(insurancePolicy);
            }
        }
        return filteredPolicies;
    }

    // Filters and returns a list of policies expired by the given cutoff date
    public static ArrayList<InsurancePolicy> filterByExpiryDate(ArrayList<InsurancePolicy> policies, MyDate date) {
        ArrayList<InsurancePolicy> expiredPolicies = new ArrayList<>();
        if (policies == null || date == null) {
            return expiredPolicies;
        }
        for (InsurancePolicy policy : policies) {
            if (policy.getExpiryDate() != null && date.isExpired(policy.getExpiryDate())) {
                expiredPolicies.add(policy);
            }
        }
        return expiredPolicies;
    }

    // -------------------------------------------lab4
    // copy constructor
    public InsurancePolicy(InsurancePolicy other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot copy from a null InsurancePolicy object.");
        }
        this.policyHolderName = other.policyHolderName;
        this.id = other.id;
        this.car = new Car(other.car);
        this.numberOfClaims = other.numberOfClaims;
        this.expiryDate = new MyDate(other.expiryDate);
    }

    // lab4
    @Override
    public InsurancePolicy clone() throws CloneNotSupportedException {
        InsurancePolicy cloned = (InsurancePolicy) super.clone();
        cloned.car = car.clone();
        cloned.expiryDate = expiryDate.clone();
        return cloned;
    }

    // lab4
    public static ArrayList<InsurancePolicy> deepCopy(ArrayList<InsurancePolicy> policies)
            throws CloneNotSupportedException {
        ArrayList<InsurancePolicy> copied = new ArrayList<>();
        for (InsurancePolicy insurancePolicy : policies) {
            copied.add(insurancePolicy.clone());
        }
        return copied;
    }

    // lab4
    public static ArrayList<InsurancePolicy> shallowCopy(ArrayList<InsurancePolicy> policies) {
        ArrayList<InsurancePolicy> copied = new ArrayList<>();
        for (InsurancePolicy insurancePolicy : policies) {
            copied.add(insurancePolicy);
        }
        return copied;
    }

    // lab4
    @Override
    public int compareTo(InsurancePolicy other) {
        return this.expiryDate.compareTo(other.expiryDate);
    }

    // ----------------------------lab5

    public static HashMap<Integer, InsurancePolicy> filterByCarModel(HashMap<Integer, InsurancePolicy> policies,
            String carModel) {
        HashMap<Integer, InsurancePolicy> filteredPolicies = new HashMap<>();
        for (InsurancePolicy policy : policies.values()) {
            if (policy.car.getModel().contains(carModel)) {
                filteredPolicies.put(policy.getID(), policy);
            }
        }
        return filteredPolicies;
    }

    public static HashMap<Integer, InsurancePolicy> filterByExpiryDate(HashMap<Integer, InsurancePolicy> policies,
            MyDate date) {
        HashMap<Integer, InsurancePolicy> expiredPolicies = new HashMap<>();
        if (policies == null || date == null) {
            return expiredPolicies;
        }
        for (InsurancePolicy policy : policies.values()) {
            if (date.isExpired(policy.getExpiryDate())) {
                expiredPolicies.put(policy.getID(), policy);
            }
        }
        return expiredPolicies;
    }

    public static ArrayList<InsurancePolicy> deepCopy(HashMap<Integer, InsurancePolicy> policies)
            throws CloneNotSupportedException {
        ArrayList<InsurancePolicy> copied = new ArrayList<>();
        for (InsurancePolicy insurancePolicy : policies.values()) {
            copied.add(insurancePolicy.clone());
        }
        return copied;
    }

    public static HashMap<Integer, InsurancePolicy> deepCopyHashMap(HashMap<Integer, InsurancePolicy> policies)
            throws CloneNotSupportedException {
        HashMap<Integer, InsurancePolicy> copied = new HashMap<>();

        for (InsurancePolicy insurancePolicy : policies.values()) {
            copied.put(insurancePolicy.id, insurancePolicy.clone());
        }
        return copied;
    }

    public static HashMap<Integer, InsurancePolicy> deepCopyHashMap(ArrayList<InsurancePolicy> policies)
            throws CloneNotSupportedException {
        HashMap<Integer, InsurancePolicy> copied = new HashMap<>();

        for (InsurancePolicy insurancePolicy : policies) {
            copied.put(insurancePolicy.id, insurancePolicy.clone());
        }
        return copied;
    }

    public static ArrayList<InsurancePolicy> shallowCopy(HashMap<Integer, InsurancePolicy> policies) {
        ArrayList<InsurancePolicy> copied = new ArrayList<>();
        for (InsurancePolicy insurancePolicy : policies.values()) {
            copied.add(insurancePolicy);
        }
        return copied;
    }

    public static HashMap<Integer, InsurancePolicy> shallowCopyHashMap(HashMap<Integer, InsurancePolicy> policies) {
        HashMap<Integer, InsurancePolicy> copied = new HashMap<>();
        for (InsurancePolicy insurancePolicy : policies.values()) {
            copied.put(insurancePolicy.id, insurancePolicy);
        }
        return copied;
    }

    public static HashMap<Integer, InsurancePolicy> shallowCopyHashMap(ArrayList<InsurancePolicy> policies) {
        HashMap<Integer, InsurancePolicy> copied = new HashMap<>();
        for (InsurancePolicy insurancePolicy : policies) {
            copied.put(insurancePolicy.id, insurancePolicy);
        }
        return copied;
    }

    public static void printPolicies(HashMap<Integer, InsurancePolicy> policies) {
        for (InsurancePolicy policy : policies.values()) {
            System.out.println(policy.toString());
        }
    }

    // Overloaded version to include flatRate and premium calculation
    public static void printPolicies(HashMap<Integer, InsurancePolicy> policies, double flatRate) {
        for (InsurancePolicy policy : policies.values()) {
            policy.print();
            System.out.println("Premium Payment: $" + policy.calcPayment(flatRate));
        }
    }

    public static double calcTotalPayments(HashMap<Integer, InsurancePolicy> policies, int flatRate) {
        double tatalPayments = 0.0;
        for (InsurancePolicy policy : policies.values()) {
            tatalPayments += policy.calcPayment(flatRate);
        }
        return tatalPayments;
    }

    public static void carPriceRiseAll(HashMap<Integer, InsurancePolicy> policies, double risePercent) {
        if (policies == null) {
            return;
        }
        for (InsurancePolicy insurancePolicy : policies.values()) {
            insurancePolicy.carPriceRise(risePercent);
        }
    }
}