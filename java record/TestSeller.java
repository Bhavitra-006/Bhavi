import java.util.Scanner;

// Tax Interface
interface Tax {
    double calculateTax(double price);
}

// Class: PurchaseDetails
class PurchaseDetails implements Tax {
    private String purchaseId;
    private String paymentType;
    private double taxPercentage;

    // Constructor
    public PurchaseDetails(String purchaseId, String paymentType) {
        this.purchaseId = purchaseId;
        this.paymentType = paymentType;
    }

    // Getter methods
    public String getPurchaseId() {
        return purchaseId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    // Overridden calculateTax method
    @Override
    public double calculateTax(double price) {
        if (paymentType.equalsIgnoreCase("Credit Card")) {
            taxPercentage = 3.0; // 3% tax for Credit Card
        } else if (paymentType.equalsIgnoreCase("UPI")) {
            taxPercentage = 1.0; // 1% tax for UPI
        } else if (paymentType.equalsIgnoreCase("Cash")) {
            taxPercentage = 2.0; // 2% tax for Cash
        } else {
            System.out.println("Invalid payment type.");
            return 0.0;
        }

        double taxAmount = price * taxPercentage / 100;
        return price + taxAmount; // Total amount including tax
    }
}

// Class: Seller
class Seller implements Tax {
    private String location;
    private double taxPercentage;

    // Constructor
    public Seller(String location) {
        this.location = location;
    }

    // Getter method
    public String getLocation() {
        return location;
    }

    // Overridden calculateTax method
    @Override
    public double calculateTax(double price) {
        if (location.equalsIgnoreCase("Urban")) {
            taxPercentage = 10.0; // 10% tax for Urban location
        } else if (location.equalsIgnoreCase("Rural")) {
            taxPercentage = 5.0; // 5% tax for Rural location
        } else {
            System.out.println("Invalid location.");
            return 0.0;
        }

        return price * taxPercentage / 100; // Tax amount only
    }
}

// Tester Class
public class TestSeller {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input for PurchaseDetails
        System.out.println("Enter Purchase Details:");
        System.out.print("Purchase ID: ");
        String purchaseId = scanner.nextLine();
        System.out.print("Payment Type (Credit Card / UPI / Cash): ");
        String paymentType = scanner.nextLine();
        System.out.print("Enter Purchase Price: ");
        double purchasePrice = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        PurchaseDetails purchase = new PurchaseDetails(purchaseId, paymentType);
        double totalAmount = purchase.calculateTax(purchasePrice);
        if (totalAmount > 0) {
            System.out.printf("Total amount to be paid (including tax): $%.2f\n", totalAmount);
        }

        // Input for Seller
        System.out.println("\nEnter Seller Details:");
        System.out.print("Location (Urban / Rural): ");
        String sellerLocation = scanner.nextLine();
        System.out.print("Enter Sale Price: ");
        double salePrice = scanner.nextDouble();

        Seller seller = new Seller(sellerLocation);
        double taxAmount = seller.calculateTax(salePrice);
        if (taxAmount > 0) {
            System.out.printf("Tax amount to be paid by Seller: $%.2f\n", taxAmount);
        }

        // Close the scanner
        scanner.close();
    }
}
