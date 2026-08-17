public class MyDate {
    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day) {
        if (year <= 0 || month <= 0 || day <= 0 || day > 31 || month > 12 || day > 31 || month > 12) {
            throw new IllegalArgumentException();
        } else {
            this.year = year;
            this.month = month;
            this.day = day;
        }
    }
}
