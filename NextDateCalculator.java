import java.util.Scanner;

public class NextDateCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter day: ");
        int day = scanner.nextInt();
        System.out.print("Enter month: ");
        int month = scanner.nextInt();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        int[] nextDate = getNextDate(day, month, year);
        System.out.println(nextDate[0]+"-"+nextDate[1]+"-"+nextDate[2]);
    }
    private static int[] getNextDate(int day, int month, int year) {
        int daysInMonth = getDaysInMonth(month, year);
        if (day < daysInMonth) {
            return new int[]{day + 1, month, year};
        } else if (month == 12) {
            return new int[]{1, 1, year + 1};
        } else {
            return new int[]{1, month + 1, year};
        }
    }
    private static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return 0;
        }
    }
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
