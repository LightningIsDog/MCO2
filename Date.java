public class Date {
    private int month;
    private int day;
    private int year;

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }


    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public void display() {
        String m = String.valueOf(month);
        String d = String.valueOf(day);
        String y = String.valueOf(year);

        System.out.println(m + "/" + d + "/" + y);
    }

     @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", month, day, year);
    }
}
