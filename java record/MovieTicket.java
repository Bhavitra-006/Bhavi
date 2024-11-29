import java.util.Scanner;
public class MovieTicket {
    private int movieId;        
    private int noOfSeats;      
    private double costPerTicket;  
    private double taxRate = 0.02; 
    public MovieTicket(int movieId, int noOfSeats) {
        this.movieId = movieId;
        this.noOfSeats = noOfSeats;
        setCostPerTicket(movieId); // Set the cost per ticket based on movieId
    }
    private void setCostPerTicket(int movieId) {
        switch (movieId) {
            case 1:
                this.costPerTicket = 150;
                break;
            case 2:
                this.costPerTicket = 200;
                break;
            case 3:
                this.costPerTicket = 250;
                break;
            default:
                System.out.println("Invalid movie ID");
                this.costPerTicket = 0; 
                break;
        }
    }
    public double calculateTotalAmount() {
        double totalAmount = costPerTicket * noOfSeats;  
        totalAmount += totalAmount * taxRate;  
        return Math.round(totalAmount);  
    }
    public void displayTicketDetails() {
        System.out.println("Movie ID: " + movieId);
        System.out.println("Number of Seats: " + noOfSeats);
        System.out.println("Cost Per Ticket: " + costPerTicket);
        System.out.println("Total Amount (including tax): " + calculateTotalAmount());
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Movie ID (1-3): ");
        int movieId = scanner.nextInt();
        System.out.print("Enter number of seats: ");
        int noOfSeats = scanner.nextInt();
        MovieTicket ticket = new MovieTicket(movieId, noOfSeats);
        ticket.displayTicketDetails();
        scanner.close();
    }
}
