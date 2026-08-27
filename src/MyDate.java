import java.util.ArrayList;

public class MyDate {
    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day) {
        if (year <= 0 || month < 1 || month > 12 || day < 1 || day > 31) {
            throw new IllegalArgumentException("Invalid date values.");
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // getters
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    // setters
    public void setYear(int year) {
        if (year > 0) {
            this.year = year;
        }
    }

    public void setMonth(int month) {
        if (month >= 1 && month <= 12) {
            this.month = month;
        }
    }

    public void setDay(int day) {
        if (day >= 1 && day <= 31) {
            this.day = day;
        }
    }

    // Returns true if the policy has expired relative to the given check date
    public boolean isExpired(MyDate expiryDate) {
        if (expiryDate == null) {
            return false;
        }

        // 1. If given date year is strictly after expiry year = expired
        if (expiryDate.year > this.year) {
            return true;
        }
        // 2. If given date year is before expiry year =  not expired
        if (expiryDate.year < this.year) {
            return false;
        }

        // 3. Years are equal; compare months
        if (expiryDate.month > this.month) {
            return true;
        }
        if (expiryDate.month < this.month) {
            return false;
        }

        // 4. Years and months are equal; check if given day has reached or passed
        // expiry day
        return expiryDate.day >= this.day;
    }

    // Filters and returns all policies that are expired by the given date
    public static ArrayList<InsurancePolicy> filterByExpiryDate(ArrayList<InsurancePolicy> policies, MyDate date) {
        ArrayList<InsurancePolicy> expiredPolicies = new ArrayList<>();

        if (policies == null || date == null) {
            return expiredPolicies;
        }

        for (InsurancePolicy policy : policies) {
            // Check if policy has an expiry date and if it is expired relative to 'date'
            if (policy.getExpiryDate() != null && date.isExpired(policy.getExpiryDate())) {
                expiredPolicies.add(policy);
            }
        }

        return expiredPolicies;
    }
}
