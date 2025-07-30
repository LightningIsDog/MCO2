/** This class represents a date with month, day, and year attributes.
 * It provides methods to display the date and format it for file output.
 @author Justin Miguel Agustin L. Lotilla
 @author Maurienne Marie M. Mojica
 @version 2.0
 */

public class Date {

    /**
     * Attributes:
     * month: The month of the date (1-12)
     * day: The day of the month (1-31)
     * year: The year of the date (e.g., 2023)
     */
    private int month;
    private int day;
    private int year;

    /**
     * Constructor to initialize the Date object with month, day, and year.
     * @param month The month of the date (1-12)
     * @param day The day of the month (1-31)
     * @param year The year of the date (e.g., 2023)
     */
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * Gets the month of the date.
     * @return The month of the date (1-12).
     */
    public int getMonth() {
        return month;
    }

    /**
     * Gets the day of the date.
     * @return The day of the date (1-31).
     */
    public int getDay() {
        return day;
    }

    /**
     * Gets the year of the date.
     * @return The year of the date (e.g., 2023).
     */
    public int getYear() {
        return year;
    }

    /**
     * This method displays the date in "MM/DD/YYYY" format.
     * It prints the date to the console.
     */
    public void display() {
        String m = String.valueOf(month);
        String d = String.valueOf(day);
        String y = String.valueOf(year);
        System.out.println(m + "/" + d + "/" + y);
    }

    /**
     * Returns a string representation of the date in "MM/DD/YYYY" format.
     * @return A string representing the date in "MM/DD/YYYY" format.
     */
     @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", month, day, year);
    }

    /**
     * Formats the date as a String in "MM-dd-yyyy" format for file output.
     * @return The formatted date string.
     */
    public String toFileString() {
        // Use String.format to ensure leading zeros for month and day if needed
        return String.format("%02d-%02d-%04d", month, day, year);
    }
}
