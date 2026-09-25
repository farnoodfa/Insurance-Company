import java.util.*;

public class User implements Cloneable, Comparable<User> {
    private static int count = 1000;

    private String name;
    private int userID;
    private Address address;
    private ArrayList<InsurancePolicy> policies;

    // Auto-generates userID incrementally (overload)

    public User(String name, Address address) {
        this.userID = ++count;
        this.name = name;
        this.address = address;
        this.policies = new ArrayList<InsurancePolicy>();
    }

    // manual ID assignments still work
    public User(String name, int userID, Address address) {
        this.name = name;
        this.userID = userID;
        this.address = address;
        this.policies = new ArrayList<InsurancePolicy>();
        // keep count ahead of any manually assigned ID
        if (userID >= count) {
            count = userID;
        }
    }

    public static void setCount(int newCount) {
        count = newCount;
    }

    // getters
    public String getName() {
        return name;
    }

    public int getUserID() {
        return userID;
    }

    public Address getAddress() {
        return address;
    }

    public ArrayList<InsurancePolicy> getPolicies() {
        return policies;
    }

    public String getCity() {
        return address.getCity();
    }

    // setters
    public void setAddress(Address address) {
        this.address = address;
    }

    public void setCity(String city) {
        address.setCity(city);
    }

    public InsurancePolicy findPolicy(int policyID) {
        for (InsurancePolicy policy : policies) {
            if (policy.getID() == policyID) {
                return policy;
            }
        }
        return null;
    }

    public void print() {
        System.out.println(this);
    }

    public String toString() {
        String result = "User: " + name + " | Address: " + address + " | ID: " + userID
                + "\n======= Policies: ========\n";

        if (policies == null || policies.isEmpty()) {
            result += "No policies found.\n";
        } else {
            for (int i = 0; i < policies.size(); i++) {
                result += "Policy " + (i + 1) + ":\n" + policies.get(i).toString() + "\n";
            }
        }
        return result;
    }

    public void printPolicies(int flatRate) {
        if (policies == null || policies.isEmpty()) {
            System.out.println("You have no Policies!");
            return;
        }
        InsurancePolicy.printPolicies(policies, flatRate);
    }

    public boolean addPolicy(InsurancePolicy policy) {
        if (policy != null && findPolicy(policy.getID()) == null) {
            policies.add(policy);
            return true;
        }
        return false;
    }

    public double calcTotalPremiums(int flatRate) {
        if (policies == null) {
            return 0;
        }
        return InsurancePolicy.calcTotalPayments(policies, flatRate);
    }

    public void carPriceRiseAll(double risePercent) {
        if (policies == null) {
            return;
        }
        InsurancePolicy.carPriceRiseAll(policies, risePercent);
    }

    public ArrayList<InsurancePolicy> filterByCarModel(String carModel) {
        if (this.policies == null) {
            return null;
        }
        return InsurancePolicy.filterByCarModel(this.policies, carModel);
    }

    public boolean createThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims,
            MyDate expiryDate, String comments) {
        ThirdPartyPolicy policy = new ThirdPartyPolicy(policyHolderName, id, car, numberOfClaims, expiryDate, comments);
        return addPolicy(policy);
    }

    public boolean createComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims,
            MyDate expiryDate, int driverAge, int level) {
        ComprehensivePolicy policy = new ComprehensivePolicy(policyHolderName, id, car, numberOfClaims, expiryDate,
                driverAge, level);
        return addPolicy(policy);
    }

    // Filters policies expired by the given date
    public ArrayList<InsurancePolicy> filterByExpiryDate(MyDate date) {
        if (this.policies == null) {
            return null;
        }
        return InsurancePolicy.filterByExpiryDate(this.policies, date);
    }

    // Remove a policy by policyID
    public boolean removePolicy(int policyID) {
        InsurancePolicy policy = findPolicy(policyID);
        if (policy != null) {
            policies.remove(policy);
            return true;
        }
        return false;
    }

    // Static counter for auto-generating user IDs
    private static int userCount = 0;

    public static int getNextUserID() {
        return ++userCount;
    }

    // Populate distinct car models across this user's policies
    public ArrayList<String> populateDistinctCarModels() {
        ArrayList<String> models = new ArrayList<String>();
        for (InsurancePolicy policy : policies) {
            boolean found = false;
            for (String model : models) {
                if (policy.getCarModel().equals(model)) {
                    found = true;
                    break;
                }
            }
            if (!found)
                models.add(policy.getCarModel());
        }
        return models;
    }

    // Count how many policies this user has for a given car model
    public double getTotalCountForCarModel(String carModel) {
        if (policies == null || carModel == null)
            return 0;
        int count = 0;
        for (InsurancePolicy policy : policies) {
            if (policy.car.getModel().equalsIgnoreCase(carModel)) {
                count++;
            }
        }
        return count;
    }

    /*
     * getTotalPaymentForCarModel needs flatRate
     * i wrote bothe ways one should be removed
     * for now i will keep both and you can decide which one to keep
     * right now I will get flat rate as 0 because it's needed
     */

    // Total premium payments for a given car model for this user
    public double getTotalPaymentForCarModel(String carModel) {
        if (policies == null || carModel == null)
            return 0;
        double total = 0.0;
        for (InsurancePolicy policy : policies) {
            if (policy.car.getModel().equalsIgnoreCase(carModel)) {
                total += policy.calcPayment(/* flatRate needed */ 0);
            }
        }
        return total;
    }

    public double getTotalPaymentForCarModel(String carModel, int flatRate) {
        if (policies == null || carModel == null)
            return 0;
        double total = 0.0;
        for (InsurancePolicy policy : policies) {
            if (policy.car.getModel().equalsIgnoreCase(carModel)) {
                total += policy.calcPayment(flatRate);
            }
        }
        return total;
    }

    // Count per model for a list of car models
    public ArrayList<Integer> getTotalCountPerCarModel(ArrayList<String> carModels) {
        ArrayList<Integer> counts = new ArrayList<Integer>();
        for (String model : carModels) {
            counts.add((int) getTotalCountForCarModel(model));
        }
        return counts;
    }

    // Total payment per model for a list of car models
    public ArrayList<Double> getTotalPaymentPerCarModel(ArrayList<String> carModels, int flatRate) {
        ArrayList<Double> payments = new ArrayList<Double>();
        for (String model : carModels) {
            payments.add(getTotalPaymentForCarModel(model, flatRate));
        }
        return payments;
    }

    // Report per car model for this user
    public void reportPaymentsPerCarModel(ArrayList<String> carModels, ArrayList<Integer> counts,
            ArrayList<Double> premiumPayments) {
        System.out.println("==========================================================================");
        System.out.printf("%-30s %-30s %-25s%n", "Car Model", "Total Premium Payment", "Average Premium Payment");
        System.out.println("--------------------------------------------------------------------------");
        for (int i = 0; i < counts.size(); i++) {
            String model = carModels.get(i);
            int count = counts.get(i);
            double total = premiumPayments.get(i);
            double average = (count > 0) ? total / count : 0.0;
            System.out.printf("%-30s $%,-29.2f $%,.2f%n", model, total, average);
        }
        System.out.println("==========================================================================");
    }

    // lab4
    // copy constructor
    public User(User user) {
        name = user.name;
        userID = user.userID;
        address = new Address(user.address);
        policies = new ArrayList<>();
        for (InsurancePolicy policy : user.policies) {
            if (policy instanceof ThirdPartyPolicy ? policies.add(new ThirdPartyPolicy((ThirdPartyPolicy) policy))
                    : policies.add(new ComprehensivePolicy((ComprehensivePolicy) policy))) {

            }
        }
    }

    // lab4
    public User clone() throws CloneNotSupportedException {
        User cloned = (User) super.clone();
        cloned.address = address.clone();
        cloned.policies = InsurancePolicy.deepCopy(policies);
        return cloned;
    }

    // lab4
    public static ArrayList<User> shallowCopy(ArrayList<User> users) {
        ArrayList<User> shallowCopy = new ArrayList<>();
        for (User user : users) {
            shallowCopy.add(user);
        }
        return shallowCopy;
    }

    // lab4
    public static ArrayList<User> deepCopy(ArrayList<User> users) throws CloneNotSupportedException {
        ArrayList<User> deepCopy = new ArrayList<>();
        for (User user : users) {
            deepCopy.add(user.clone());
        }
        return deepCopy;
    }

    // lab4
    public ArrayList<InsurancePolicy> deepCopyPolicies() throws CloneNotSupportedException {
        return InsurancePolicy.deepCopy(policies);
    }

    // lab4
    public ArrayList<InsurancePolicy> shallowCopyPolicies() {
        return InsurancePolicy.shallowCopy(policies);
    }

    // lab4
    @Override
    public int compareTo(User other) {
        return this.address.compareTo(other.address);
    }

    // lab4
    public int compareTo1(User other) {
        double total = InsurancePolicy.calcTotalPayments(this.policies, 20);
        double otherTotal = InsurancePolicy.calcTotalPayments(other.policies, 20);
        // return total - OtherTotal
        if (total < otherTotal) {
            return -1;
        }
        if (total > otherTotal) {
            return 1;
        }
        return 0;
    }

    // lab4
    public ArrayList<InsurancePolicy> sortPoliciesByDate() {
        ArrayList<InsurancePolicy> sorted = InsurancePolicy.shallowCopy(policies);
        Collections.sort(sorted);
        return sorted;
    }
}
