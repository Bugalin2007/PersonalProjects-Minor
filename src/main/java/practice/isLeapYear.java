package practice;
/*
Program # :  1.1
Techniques : Java Basics Review
Goal : discriminate whether a given year is a leap year.
 */
public class isLeapYear {
    public static void main(String[] args) {
        System.out.println(isLeapYear(1));
        System.out.println(isLeapYear(1900));
        System.out.println(isLeapYear(1918));
        System.out.println(isLeapYear(2000));
        System.out.println(isLeapYear(2016));
        System.out.println(isLeapYear(2025));
        System.out.println(isLeapYear(3196));
        System.out.println(isLeapYear(3200));
    }

    private static boolean isLeapYear(int year) {
        return (year % 400 == 0 && year % 3200 != 0) || (year % 4 == 0 && year % 100 != 0);
    }
}
