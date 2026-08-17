public class Address {
    private int streetNum;
    private String street;
    private String suburb;
    private String city;

    public Address(int streetNum, String street, String suburb, String city) {
        this.streetNum = streetNum;
        this.street = street;
        this.suburb = suburb;
        this.city = city;
    }
    // getters

    // setters
    public void setCity(String city) {
        this.city = city;
    }

    public String toString() {
        return city + ", " + suburb + ", " + street + "St, St Number: " + streetNum;
    }

}
