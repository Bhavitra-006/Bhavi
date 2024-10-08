public class PatternDisplay {

    public static void main(String[] args) {
        int rows = 7; // Number of rows in the pattern

        for (int i = 0; i < rows; i++) {
            // Print leading spaces
            for (int j = 0; j < rows - i - 1; j++) {
                System.out.print(" ");
            }
            // Print characters from current row to 'A'
            for (char ch = (char) ('A' + i); ch >= 'A'; ch--) {
                System.out.print(ch + " ");
            }
            // Move to the next line
            System.out.println();
        }
    }
}
