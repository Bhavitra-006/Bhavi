import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// Book class
class Book {
    String bookID, title, author, genre;
    boolean isCheckedOut;

    public Book(String bookID, String title, String author, String genre) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isCheckedOut = false;
    }
}

// User class
class User {
    String userID;
    Map<String, Date> borrowedBooks; // Map of bookID and due date

    public User(String userID) {
        this.userID = userID;
        this.borrowedBooks = new HashMap<>();
    }
}

public class LibraryManagementSystem {
    private static Map<String, Book> books = new HashMap<>();
    private static Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        // Sample Data
        books.put("101", new Book("101", "Book One", "Author A", "Fiction"));
        books.put("102", new Book("102", "Book Two", "Author B", "Science"));
        books.put("103", new Book("103", "Book Three", "Author C", "History"));
        users.put("U001", new User("U001"));
        users.put("U002", new User("U002"));

        // Create GUI
        JFrame frame = new JFrame("Library Management System - Dark Mode");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        frame.setLayout(new GridLayout(3, 1));

        // Define dark mode colors
        Color backgroundColor = new Color(34, 34, 34); // Dark gray
        Color panelColor = new Color(45, 45, 45); // Slightly lighter gray
        Color textColor = Color.WHITE; // White text
        Color buttonColor = new Color(70, 130, 180); // Steel blue
        Color borderColor = new Color(100, 149, 237); // Cornflower blue

        // Panel for Check-in/Check-out
        JPanel checkInOutPanel = createPanel("Check-in/Check-out Books", panelColor, textColor, borderColor);
        JTextField bookIDField = createTextField(backgroundColor, textColor);
        JTextField userIDField = createTextField(backgroundColor, textColor);
        JButton checkOutButton = createButton("Check Out", buttonColor, textColor);
        JButton checkInButton = createButton("Check In", buttonColor, textColor);
        JLabel checkInOutMessage = createLabel("", textColor);

        checkInOutPanel.add(new JLabel("Book ID:", JLabel.RIGHT));
        checkInOutPanel.add(bookIDField);
        checkInOutPanel.add(new JLabel("User ID:", JLabel.RIGHT));
        checkInOutPanel.add(userIDField);
        checkInOutPanel.add(checkOutButton);
        checkInOutPanel.add(checkInButton);
        checkInOutPanel.add(checkInOutMessage);

        // Panel for Book Search
        JPanel searchPanel = createPanel("Search for Books", panelColor, textColor, borderColor);
        JTextField searchTitleField = createTextField(backgroundColor, textColor);
        JTextField searchAuthorField = createTextField(backgroundColor, textColor);
        JTextField searchGenreField = createTextField(backgroundColor, textColor);
        JButton searchButton = createButton("Search", buttonColor, textColor);
        JLabel searchResults = createLabel("", textColor);

        searchPanel.add(new JLabel("Title:", JLabel.RIGHT));
        searchPanel.add(searchTitleField);
        searchPanel.add(new JLabel("Author:", JLabel.RIGHT));
        searchPanel.add(searchAuthorField);
        searchPanel.add(new JLabel("Genre:", JLabel.RIGHT));
        searchPanel.add(searchGenreField);
        searchPanel.add(searchButton);
        searchPanel.add(searchResults);

        // Panel for Fine Management
        JPanel finePanel = createPanel("Manage Fines", panelColor, textColor, borderColor);
        JTextField fineUserField = createTextField(backgroundColor, textColor);
        JButton calculateFineButton = createButton("Calculate Fine", buttonColor, textColor);
        JLabel fineMessage = createLabel("", textColor);

        finePanel.add(new JLabel("User ID:", JLabel.RIGHT));
        finePanel.add(fineUserField);
        finePanel.add(calculateFineButton);
        finePanel.add(fineMessage);

        // Adding Panels to Frame
        frame.add(checkInOutPanel);
        frame.add(searchPanel);
        frame.add(finePanel);
        frame.setVisible(true);

        // Action Listeners
        checkOutButton.addActionListener(e -> {
            String bookID = bookIDField.getText();
            String userID = userIDField.getText();

            if (!books.containsKey(bookID)) {
                checkInOutMessage.setText("Error: Book ID not found.");
                return;
            }
            if (!users.containsKey(userID)) {
                checkInOutMessage.setText("Error: User ID not found.");
                return;
            }

            Book book = books.get(bookID);
            User user = users.get(userID);

            if (book.isCheckedOut) {
                checkInOutMessage.setText("Error: Book is already checked out.");
                return;
            }

            book.isCheckedOut = true;
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, 14); // Due in 14 days
            user.borrowedBooks.put(bookID, calendar.getTime());
            checkInOutMessage.setText("Success: Book checked out.");
        });

        checkInButton.addActionListener(e -> {
            String bookID = bookIDField.getText();
            String userID = userIDField.getText();

            if (!books.containsKey(bookID)) {
                checkInOutMessage.setText("Error: Book ID not found.");
                return;
            }
            if (!users.containsKey(userID)) {
                checkInOutMessage.setText("Error: User ID not found.");
                return;
            }

            Book book = books.get(bookID);
            User user = users.get(userID);

            if (!book.isCheckedOut || !user.borrowedBooks.containsKey(bookID)) {
                checkInOutMessage.setText("Error: Book not checked out by this user.");
                return;
            }

            book.isCheckedOut = false;
            user.borrowedBooks.remove(bookID);
            checkInOutMessage.setText("Success: Book checked in.");
        });

        searchButton.addActionListener(e -> {
            String title = searchTitleField.getText().toLowerCase();
            String author = searchAuthorField.getText().toLowerCase();
            String genre = searchGenreField.getText().toLowerCase();

            StringBuilder result = new StringBuilder("<html>");
            books.values().stream()
                    .filter(book -> (title.isEmpty() || book.title.toLowerCase().contains(title)) &&
                                    (author.isEmpty() || book.author.toLowerCase().contains(author)) &&
                                    (genre.isEmpty() || book.genre.toLowerCase().contains(genre)))
                    .forEach(book -> result.append(book.bookID).append(": ").append(book.title).append("<br>"));

            if (result.toString().equals("<html>")) {
                result.append("No books found.");
            }
            result.append("</html>");
            searchResults.setText(result.toString());
        });

        calculateFineButton.addActionListener(e -> {
            String userID = fineUserField.getText();
            if (!users.containsKey(userID)) {
                fineMessage.setText("Error: User ID not found.");
                return;
            }

            User user = users.get(userID);
            long fine = 0;
            Date today = new Date();

            for (Date dueDate : user.borrowedBooks.values()) {
                if (today.after(dueDate)) {
                    fine += (today.getTime() - dueDate.getTime()) / (1000 * 60 * 60 * 24); // Fine = days overdue
                }
            }

            fineMessage.setText("Fine: $" + fine);
        });
    }

    // Helper Methods
    private static JPanel createPanel(String title, Color background, Color textColor, Color borderColor) {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.setBackground(background);
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(borderColor), title));
        return panel;
    }

    private static JTextField createTextField(Color background, Color textColor) {
        JTextField textField = new JTextField();
        textField.setBackground(background);
        textField.setForeground(textColor);
        return textField;
    }

    private static JButton createButton(String text, Color background, Color textColor) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(textColor);
        return button;
    }

    private static JLabel createLabel(String text, Color textColor) {
        JLabel label = new JLabel(text);
        label.setForeground(textColor);
        return label;
    }
}