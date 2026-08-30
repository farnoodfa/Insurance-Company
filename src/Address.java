public class Address {
    private int streetNum;
    private String street;
    private String suburb;
    private String city;

    public Address(int streetNum, String street, String suburb, String city) {
        if (streetNum < 1 || street == null || suburb == null || city == null) {
            throw new IllegalArgumentException("Invalid address values.");
        }
        this.streetNum = streetNum;
        this.street = street;
        this.suburb = suburb;
        this.city = city;
    }

    // getters
    public int getStreetNum() {
        return streetNum;
    }

    public String getStreet() {
        return street;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getCity() {
        return city;
    }

    // setters
    public void setCity(String city) {
        if (city == null) {
            throw new IllegalArgumentException("City cannot be null.");
        }
        this.city = city;
    }
    public void setStreetNum(int streetNum) {
        if (streetNum < 1) {
            throw new IllegalArgumentException("Street number must be a positive integer.");
        }
        this.streetNum = streetNum;
    }

    public void setStreet(String street) {
        if (street == null) {
            throw new IllegalArgumentException("Street cannot be null.");
        }
        this.street = street;
    }

    public void setSuburb(String suburb) {
        if (suburb == null) {
            throw new IllegalArgumentException("Suburb cannot be null.");
        }
        this.suburb = suburb;
    }

    public String toString() {
        return city + ", " + suburb + ", " + street + "St, St Number: " + streetNum;
    }

}
