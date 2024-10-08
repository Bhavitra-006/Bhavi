import java.util.*;
public class SwiftFood {
    static class Customer {
        private final String id;
        private String name,contactNumber,address;
        private final String type;
        public Customer(String id, String name, String contactNumber, String address, String type) {
            this.id = id;
            this.name = name;
            this.contactNumber = contactNumber;
            this.address = address;
            this.type = type;
        }
        public void viewDetails() {
            System.out.println("ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Contact: " + contactNumber);
            System.out.println("Address: " + address);
        }
        public void updateDetails(String name, String contactNumber, String address) {
            this.name = name;
            this.contactNumber = contactNumber;
            this.address = address;
        }
        public double getDiscount() {
            switch (type) {
                case "Regular":
                    return 5.0; // 5% discount
                case "Premium":
                    return 15.0; // 15% discount
                default:
                    return 0.0; // No discount for Guest
            }
        }
    }
    static class Food {
        private final String name;
        private final String type;
        private final double unitPrice;
        private int quantityAvailable;
        public Food(String name, String type, double unitPrice, int quantityAvailable) {
            this.name = name;
            this.type = type;
            this.unitPrice = unitPrice;
            this.quantityAvailable = quantityAvailable;
        }
        public void display() {
            System.out.println(name + " (" + type + "): $" + unitPrice + " (Available: " + quantityAvailable + ")");
        }
        public boolean updateQuantity(int quantity) {
            if (quantityAvailable >= quantity) {
                quantityAvailable -= quantity;
                return true;
            }
            return false;
        }
        public double getUnitPrice() {
            return unitPrice;
        }
        public String getName() {
            return name;
        }
    }
    static class Restaurant {
        private final String name;
        private final String address;
        private final double rating;
        private List<Food> menu = new ArrayList<>();
        public Restaurant(String name, String address, double rating) {
            this.name = name;
            this.address = address;
            this.rating = rating;
        }
        public void addFood(Food food) {
            menu.add(food);
        }
        public void displayMenu() {
            System.out.println("Menu:");
            for (Food food : menu) {
                food.display();
            }
        }
        public Food findFoodByName(String name) {
            for (Food food : menu) {
                if (food.getName().equalsIgnoreCase(name)) {
                    return food;
                }
            }
            return null;
        }
    }
    static class Order {
        private static int orderCount = 0;
        private final int orderId;
        private final Customer customer;
        private final Map<Food, Integer> orderedFoods = new HashMap<>();
        private double totalPrice;

        public Order(Customer customer) {
            this.orderId = ++orderCount;
            this.customer = customer;
        }
        public void addFood(Food food, int quantity) {
            if (food.updateQuantity(quantity)) {
                orderedFoods.put(food, quantity);
                totalPrice += food.getUnitPrice() * quantity;
            } else {
                System.out.println("Insufficient quantity for " + food.getName());
            }
        }
        public double calculateTotalPrice() {
            return totalPrice - (totalPrice * customer.getDiscount() / 100);
        }
    }
    static class Bill {
        private final String billId;
        private final String paymentMode;
        private final double amount;
        public Bill(String billId, String paymentMode, double amount) {
            this.billId = billId;
            this.paymentMode = paymentMode;
            this.amount = amount;
        }
        public void generateBill() {
            System.out.println("Bill ID: " + billId);
            System.out.println("Amount: $" + amount);
            System.out.println("Payment Mode: " + paymentMode);
        }
        public void processPayment() {
            List<String> validPaymentModes = Arrays.asList("Credit Card", "Cash");
            if (validPaymentModes.contains(paymentMode)) {
                System.out.println("Payment of $" + amount + " processed via " + paymentMode);
            } else {
                System.out.println("Invalid payment mode");
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Create restaurant
        System.out.print("Enter restaurant name: ");
        String restaurantName = scanner.nextLine();
        System.out.print("Enter restaurant address: ");
        String restaurantAddress = scanner.nextLine();
        System.out.print("Enter restaurant rating: ");
        double restaurantRating = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        Restaurant restaurant = new Restaurant(restaurantName, restaurantAddress, restaurantRating);
        // Add food items to the restaurant
        boolean addingFood = true;
        while (addingFood) {
            System.out.print("Enter food name: ");
            String foodName = scanner.nextLine();
            System.out.print("Enter food type (veg/non-veg): ");
            String foodType = scanner.nextLine();
            System.out.print("Enter food unit price: ");
            double foodUnitPrice = scanner.nextDouble();
            System.out.print("Enter food quantity available: ");
            int foodQuantityAvailable = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            Food food = new Food(foodName, foodType, foodUnitPrice, foodQuantityAvailable);
            restaurant.addFood(food);
            System.out.print("Do you want to add another food item? (yes/no): ");
            addingFood = scanner.nextLine().equalsIgnoreCase("yes");
        }
        // Display menu
        System.out.println("Restaurant Menu:");
        restaurant.displayMenu();
        // Create customers
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter customer contact number: ");
        String customerContactNumber = scanner.nextLine();
        System.out.print("Enter customer address: ");
        String customerAddress = scanner.nextLine();
        System.out.print("Enter customer type (Guest/Regular/Premium): ");
        String customerType = scanner.nextLine();
        Customer customer = new Customer(customerId, customerName, customerContactNumber, customerAddress, customerType);
        // Create orders
        Order order = new Order(customer);
        boolean ordering = true;
        while (ordering) {
            System.out.print("Enter food name to order: ");
            String foodName = scanner.nextLine();
            Food food = restaurant.findFoodByName(foodName);
            if (food != null) {
                System.out.print("Enter quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                order.addFood(food, quantity);
            } else {
                System.out.println("Food not found.");
            }
            System.out.print("Do you want to add more food items to the order? (yes/no): ");
            ordering = scanner.nextLine().equalsIgnoreCase("yes");
        }
        // Calculate and display total price
        double totalPrice = order.calculateTotalPrice();
        System.out.println("\nTotal Price for the Order: $" + totalPrice);
        // Create and process bill
        System.out.print("Enter bill ID: ");
        String billId = scanner.nextLine();
        System.out.print("Enter payment mode (Credit Card/Cash): ");
        String paymentMode = scanner.nextLine();
        Bill bill = new Bill(billId, paymentMode, totalPrice);
        bill.generateBill();
        bill.processPayment();
        scanner.close();
    }
} 