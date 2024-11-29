import java.util.Scanner;

// Abstract Class: Student
abstract class Student {
    protected String studentName;
    protected int[] testScores = new int[4];
    protected String testResult;

    // Constructor
    public Student(String studentName) {
        this.studentName = studentName;
    }

    // Abstract method to be implemented by subclasses
    public abstract void generateResult();

    // Method to set test scores
    public void setTestScore(int testNumber, int testScore) {
        if (testNumber >= 1 && testNumber <= 4) {
            this.testScores[testNumber - 1] = testScore;
        } else {
            System.out.println("Invalid test number. Please enter a value between 1 and 4.");
        }
    }

    // Getter methods
    public String getStudentName() {
        return studentName;
    }

    public int[] getTestScores() {
        return testScores;
    }

    public String getTestResult() {
        return testResult;
    }
}

// UndergraduateStudent class
class UndergraduateStudent extends Student {

    // Constructor
    public UndergraduateStudent(String studentName) {
        super(studentName);
    }

    // Implementing generateResult
    @Override
    public void generateResult() {
        int total = 0;
        for (int score : testScores) {
            total += score;
        }
        double average = total / 4.0;

        // Undergraduate result logic
        if (average >= 60) {
            testResult = "Pass";
        } else {
            testResult = "Fail";
        }
    }
}

// GraduateStudent class
class GraduateStudent extends Student {

    // Constructor
    public GraduateStudent(String studentName) {
        super(studentName);
    }

    // Implementing generateResult
    @Override
    public void generateResult() {
        int total = 0;
        for (int score : testScores) {
            total += score;
        }
        double average = total / 4.0;

        // Graduate result logic
        if (average >= 70) {
            testResult = "Pass";
        } else {
            testResult = "Fail";
        }
    }
}

// Tester Class
public class Tester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input for Undergraduate Student
        System.out.print("Enter Undergraduate Student Name: ");
        String ugStudentName = scanner.nextLine();
        UndergraduateStudent ugStudent = new UndergraduateStudent(ugStudentName);

        System.out.println("Enter test scores for Undergraduate Student:");
        for (int i = 1; i <= 4; i++) {
            System.out.print("Test " + i + " score: ");
            int score = scanner.nextInt();
            ugStudent.setTestScore(i, score);
        }

        // Generate result for Undergraduate Student
        ugStudent.generateResult();
        System.out.println("\nUndergraduate Student Result:");
        System.out.println("Name: " + ugStudent.getStudentName());
        System.out.println("Test Scores: " + arrayToString(ugStudent.getTestScores()));
        System.out.println("Result: " + ugStudent.getTestResult());

        // Input for Graduate Student
        scanner.nextLine(); // Consume newline
        System.out.print("\nEnter Graduate Student Name: ");
        String gradStudentName = scanner.nextLine();
        GraduateStudent gradStudent = new GraduateStudent(gradStudentName);

        System.out.println("Enter test scores for Graduate Student:");
        for (int i = 1; i <= 4; i++) {
            System.out.print("Test " + i + " score: ");
            int score = scanner.nextInt();
            gradStudent.setTestScore(i, score);
        }

        // Generate result for Graduate Student
        gradStudent.generateResult();
        System.out.println("\nGraduate Student Result:");
        System.out.println("Name: " + gradStudent.getStudentName());
        System.out.println("Test Scores: " + arrayToString(gradStudent.getTestScores()));
        System.out.println("Result: " + gradStudent.getTestResult());

        // Close scanner
        scanner.close();
    }

    // Helper method to convert array to string
    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
