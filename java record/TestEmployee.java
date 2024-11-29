import java.util.Scanner;
class Employee {
    private String employeeId;
    private String employeeName;
    public Employee(String employeeId, String employeeName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
class PermanentEmployee extends Employee {
    private double basicPay;
    private double hra;
    private float experience;
    private double salary;
    public PermanentEmployee(String empId, String name, double basicPay, double hra, float experience) {
        super(empId, name);
        this.basicPay = basicPay;
        this.hra = hra;
        this.experience = experience;
    }
    public void calculateMonthlySalary() {
        double variableComponent = 0;

        if (experience <= 2) {
            variableComponent = basicPay * 0.10;
        } else if (experience <= 5) {
            variableComponent = basicPay * 0.15;
        } else {
            variableComponent = basicPay * 0.20;
        }

        salary = basicPay + hra + variableComponent;
        salary = Math.round(salary); // Round the salary
    }
    public double getSalary() {
        return salary;
    }

    public void setBasicPay(double basicPay) {
        this.basicPay = basicPay;
    }

    public void setHra(double hra) {
        this.hra = hra;
    }

    public void setExperience(float experience) {
        this.experience = experience;
    }
}
class ContractEmployee extends Employee {
    private double wage;
    private float hoursWorked;
    private double salary;
    public ContractEmployee(String empId, String name, double wage, float hoursWorked) {
        super(empId, name);
        this.wage = wage;
        this.hoursWorked = hoursWorked;
    }
    public void calculateSalary() {
        salary = hoursWorked * wage;
        salary = Math.round(salary); // Round the salary
    }
    public double getSalary() {
        return salary;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public void setHoursWorked(float hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
}
public class TestEmployee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter details for Permanent Employee:");
        System.out.print("Employee ID: ");
        String empId = scanner.nextLine();  
        System.out.print("Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Basic Pay: ");
        double basicPay = scanner.nextDouble();
        System.out.print("HRA: ");
        double hra = scanner.nextDouble();
        System.out.print("Experience (in years): ");
        float experience = scanner.nextFloat();
        PermanentEmployee permanentEmployee = new PermanentEmployee(empId, name, basicPay, hra, experience);
        permanentEmployee.calculateMonthlySalary();
        System.out.printf("\nHi %s, your salary is $%.1f\n", permanentEmployee.getEmployeeName(), permanentEmployee.getSalary());
        System.out.println("\nEnter details for Contract Employee:");
        scanner.nextLine();  // To consume the remaining newline
        System.out.print("Employee ID: ");
        empId = scanner.nextLine();
        System.out.print("Employee Name: ");
        name = scanner.nextLine();
        System.out.print("Wage (per hour): ");
        double wage = scanner.nextDouble();
        System.out.print("Hours Worked: ");
        float hoursWorked = scanner.nextFloat();
        ContractEmployee contractEmployee = new ContractEmployee(empId, name, wage, hoursWorked);
        contractEmployee.calculateSalary();
        System.out.printf("\nHi %s, your salary is $%.1f\n", contractEmployee.getEmployeeName(), contractEmployee.getSalary());
        scanner.close();
    }
}