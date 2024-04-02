import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private double price;
    private int quantity;

    public Book(String title, String author, double price, int quantity) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

public class BookstoreInventory {
    private static Map<Integer, Book> bookstore = new HashMap<>();
    private static int bookIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            System.out.println("\n1. Add Book\n2. Sell Book\n3. Display Inventory\n4. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    sellBook(scanner);
                    break;
                case 3:
                    displayInventory();
                    break;
                case 4:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    private static void addBook(Scanner scanner) {
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        Book newBook = new Book(title, author, price, quantity);
        bookstore.put(bookIdCounter++, newBook);
        System.out.println("Book added successfully.");
    }

    private static void sellBook(Scanner scanner) {
        System.out.print("Enter book ID to sell: ");
        int bookId = scanner.nextInt();

        if (bookstore.containsKey(bookId)) {
            Book book = bookstore.get(bookId);
            if (book.getQuantity() > 0) {
                book.setQuantity(book.getQuantity() - 1);
                System.out.println("Book sold successfully.");
            } else {
                System.out.println("Sorry, this book is out of stock.");
            }
        } else {
            System.out.println("Book ID not found.");
        }
    }

    private static void displayInventory() {
        if (bookstore.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Book Inventory:");
            for (Map.Entry<Integer, Book> entry : bookstore.entrySet()) {
                Book book = entry.getValue();
                System.out.println("ID: " + entry.getKey() +
                        ", Title: " + book.getTitle() +
                        ", Author: " + book.getAuthor() +
                        ", Price: $" + book.getPrice() +
                        ", Quantity: " + book.getQuantity());
            }
        }
    }
}