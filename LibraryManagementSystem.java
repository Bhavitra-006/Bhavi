import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

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

class User {
    String userID;
    Map<String, Date> borrowedBooks; 
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

        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new GridLayout(4, 1));

        JPanel checkInOutPanel = new JPanel(new GridLayout(4, 2));
        checkInOutPanel.setBorder(BorderFactory.createTitledBorder("Check-in/Check-out Books"));
        JTextField bookIDField = new JTextField();
        JTextField userIDField = new JTextField();
        JButton checkOutButton = new JButton("Check Out");
        JButton checkInButton = new JButton("Check In");
        JLabel checkInOutMessage = new JLabel();
        checkInOutPanel.add(new JLabel("Book ID:"));
        checkInOutPanel.add(bookIDField);
        checkInOutPanel.add(new JLabel("User ID:"));
        checkInOutPanel.add(userIDField);
        checkInOutPanel.add(checkOutButton);
        checkInOutPanel.add(checkInButton);
        checkInOutPanel.add(checkInOutMessage);

        JPanel searchPanel = new JPanel(new GridLayout(4, 2));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search for Books"));
        JTextField searchTitleField = new JTextField();
        JTextField searchAuthorField = new JTextField();
        JTextField searchGenreField = new JTextField();
        JButton searchButton = new JButton("Search");
        JLabel searchResults = new JLabel();
        searchPanel.add(new JLabel("Title:"));
        searchPanel.add(searchTitleField);
        searchPanel.add(new JLabel("Author:"));
        searchPanel.add(searchAuthorField);
        searchPanel.add(new JLabel("Genre:"));
        searchPanel.add(searchGenreField);
        searchPanel.add(searchButton);
        searchPanel.add(searchResults);

        JPanel finePanel = new JPanel(new GridLayout(2, 1));
        finePanel.setBorder(BorderFactory.createTitledBorder("Manage Fines"));
        JTextField fineUserField = new JTextField();
        JButton calculateFineButton = new JButton("Calculate Fine");
        JLabel fineMessage = new JLabel();
        finePanel.add(new JLabel("User ID:"));
        finePanel.add(fineUserField);
        finePanel.add(calculateFineButton);
        finePanel.add(fineMessage);

        frame.add(checkInOutPanel);
        frame.add(searchPanel);
        frame.add(finePanel);
        frame.setVisible(true);

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
                    fine += (today.getTime() - dueDate.getTime()) / (1000 * 60 * 60 * 24);
                }
            }

            fineMessage.setText("Fine: $" + fine);
        });
    }
}