import java.util.Scanner;
class Point {
    private double xCoordinate;
    private double yCoordinate;
    public Point(double xCoordinate, double yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
    public double getXCoordinate() {
        return xCoordinate;
    }
    public void setXCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }
    public double getYCoordinate() {
        return yCoordinate;
    }
    public void setYCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
    public double calculateDistance() {
        double distance = Math.sqrt((xCoordinate * xCoordinate) + (yCoordinate * yCoordinate));
        return Math.round(distance * 100.0) / 100.0; // Round to 2 decimal places
    }
    public double calculateDistance(Point point) {
        double xDiff = xCoordinate - point.getXCoordinate();
        double yDiff = yCoordinate - point.getYCoordinate();
        double distance = Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
        return Math.round(distance * 100.0) / 100.0; // Round to 2 decimal places
    }
}
public class TestPoint {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter details for Point 1:");
        System.out.print("x-coordinate: ");
        double x1 = scanner.nextDouble();
        System.out.print("y-coordinate: ");
        double y1 = scanner.nextDouble();
        Point point1 = new Point(x1, y1);

        System.out.println("Enter details for Point 2:");
        System.out.print("x-coordinate: ");
        double x2 = scanner.nextDouble();
        System.out.print("y-coordinate: ");
        double y2 = scanner.nextDouble();
        Point point2 = new Point(x2, y2);
        double distanceFromOrigin = point1.calculateDistance();
        double distanceBetweenPoints = point1.calculateDistance(point2);
        System.out.printf("\nDistance of Point 1 from origin: %.2f\n", distanceFromOrigin);
        System.out.printf("Distance of Point 1 from Point 2: %.2f\n", distanceBetweenPoints);
        scanner.close();
    }
}
