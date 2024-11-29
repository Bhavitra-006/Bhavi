import java.util.Scanner;

// Superclass Animal
class Animal {
    // Method for the Animal class
    public void eat() {
        System.out.println("Eating...");
    }
}

// Subclass Mammal that extends Animal
class Mammal extends Animal {
    // Method for the Mammal class
    public void sleep() {
        System.out.println("Sleeping...");
    }
}

// Subclass Dog that extends Mammal
class Dog extends Mammal {
    // Method for the Dog class
    public void bark() {
        System.out.println("Barking...");
    }
}

// Main class to test the functionality
public class TestInheritance {
    public static void main(String[] args) {
        // Create a scanner object to get input from the user
        Scanner scanner = new Scanner(System.in);

        // Create an instance of the Dog class
        Dog myDog = new Dog();

        // Ask user what action they want the dog to perform
        System.out.println("Enter an action for the dog (eat, sleep, bark): ");
        String action = scanner.nextLine().toLowerCase();  // Read input and convert to lowercase

        // Perform the action based on user input
        switch (action) {
            case "eat":
                myDog.eat();  // Call the eat() method from Animal class
                break;
            case "sleep":
                myDog.sleep();  // Call the sleep() method from Mammal class
                break;
            case "bark":
                myDog.bark();  // Call the bark() method from Dog class
                break;
            default:
                System.out.println("Invalid action. Please enter 'eat', 'sleep', or 'bark'.");
        }

        // Close the scanner to avoid resource leak
        scanner.close();
    }
}
