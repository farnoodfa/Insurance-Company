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
        if (findPolicy(policy.getID()) == null) {
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
            for (InsurancePolicy policy : policies) {
                result += policy.toString() + "\n";
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
}
