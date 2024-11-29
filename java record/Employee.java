import java.util.Scanner;
public class Employee {
    private String name;         
    private String employeeID;     
    private double baseSalary;    
    private int jobLevel;        
    private double bonus;         
    public Employee(String name, String employeeID, double baseSalary, int jobLevel, double bonus) {
        this.name = name;
        this.employeeID = employeeID;
        this.baseSalary = baseSalary;
        this.jobLevel = jobLevel;
        this.bonus = bonus;
    }
    public String getName() {
        return name;
    }
    public String getEmployeeID() {
        return employeeID;
    }
    public double getBaseSalary() {
        return baseSalary;
    }
    public int getJobLevel() {
        return jobLevel;
    }
    public double getBonus() {
        return bonus;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }
    public void setJobLevel(int jobLevel) {
        this.jobLevel = jobLevel;
    }
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
    public double calculateSalary() {
        double totalSalary = baseSalary;
        switch (jobLevel) {
            case 1:
                totalSalary += bonus * 0.1; // 10% bonus for level 1
                break;
            case 2:
                totalSalary += bonus * 0.2; // 20% bonus for level 2
                break;
            case 3:
                totalSalary += bonus * 0.3; // 30% bonus for level 3
                break;
            default:
                totalSalary += bonus * 0.05; // 5% bonus for other levels
        }        
        return totalSalary;
    }
    public void displayEmployeeInfo() {
        System.out.println("Employee Name: " + name);
        System.out.println("Employee ID: " + employeeID);
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("Job Level: " + jobLevel);
        System.out.println("Bonus: " + bonus);
        System.out.println("Total Salary: " + calculateSalary());
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee ID: ");
        String employeeID = scanner.nextLine();  // Now accepting as a String
        System.out.print("Enter Base Salary: ");
        double baseSalary = scanner.nextDouble();
        System.out.print("Enter Job Level (1-3): ");
        int jobLevel = scanner.nextInt();
        System.out.print("Enter Bonus: ");
        double bonus = scanner.nextDouble();
        Employee employee = new Employee(name, employeeID, baseSalary, jobLevel, bonus);
        employee.displayEmployeeInfo();
        scanner.close();
    }
}

