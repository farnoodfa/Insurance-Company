import java.util.*;

public class User {
    private String name; // the name of the account holder
    private int userID; // the user ID/number
    private Address address; // you need to define the Address class as described
    private ArrayList<InsurancePolicy> policies; // list of all the Insurance Policies this user holds

    public User(String name, int userID, Address address) {
        this.name = name;
        this.userID = userID;
        this.address = address;
        this.policies = new ArrayList<InsurancePolicy>();
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

    public boolean addPolicy(InsurancePolicy policy) {
        if (policy != null && findPolicy(policy.getID()) == null) {
            policies.add(policy);
            return true;
        }
        return false;
    }

    public void print() {
        System.out.println(this);
    }

    public String toString() {
        String result = "User: " + name + " |Address:  " + address + " |ID: " + userID + "\nPolicies:\n";
        if (policies != null) {
            for (int i = 0; i < policies.size(); i++) {
                result += "Policy" + (i + 1) + ": " + policies.get(i).toString() + "\n";
            }
        } else if (policies == null) {
            result += "You have no Policies!";
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
}
