import java.util.Scanner;

public class LuckyNumberChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number
        System.out.print("Enter a number: ");
        String number = scanner.nextLine();

        // Check if the number is a lucky number
        boolean isLucky = isLuckyNumber(number);

        // Display the result
        if (isLucky) {
            System.out.println(number + " is a lucky number.");
        } else {
            System.out.println(number + " is not a lucky number.");
        }
    }

    private static boolean isLuckyNumber(String number) {
        int sumOfSquares = 0;

        // Iterate through the digits in the number
        for (int i = 1; i < number.length(); i += 2) {
            char digitChar = number.charAt(i);
            int digit = Character.getNumericValue(digitChar);
            sumOfSquares += digit * digit; // Square the digit and add to the sum
        }

        // Check if the sum of squares is a multiple of 9
        return sumOfSquares % 9 == 0;
    }
}
