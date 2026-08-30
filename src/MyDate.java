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

        if (this.year > expiryDate.year) {
            return true;
        }
        if (this.year < expiryDate.year) {
            return false;
        } // years are equal, compare months
        if (this.month > expiryDate.month) {
            return true;
        }
        if (this.month < expiryDate.month) {
            return false;
        } // months are equal, compare days
        return this.day >= expiryDate.day;
    }

}
