import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryCatalog catalog = new LibraryCatalog();

        // Add some books
        catalog.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        catalog.addBook(new Book("1984", "George Orwell"));
        catalog.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));

        while (true) {
            System.out.println("\nLibrary Catalog Menu:");
            System.out.println("1. Display all books");
            System.out.println("2. Search for a book by title");
            System.out.println("3. Search for books by author");
            System.out.println("4. Check out a book");
            System.out.println("5. Return a book");
            System.out.println("6. Exit");

            int choice = -1;
            try {
                System.out.print("Enter your choice (1-6): ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                scanner.nextLine(); // Consume the invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    catalog.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter the title of the book: ");
                    String title = scanner.nextLine();
                    Book book = catalog.searchByTitle(title);
                    if (book != null) {
                        System.out.println(book);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the author's name: ");
                    String author = scanner.nextLine();
                    List<Book> books = catalog.searchByAuthor(author);
                    if (books.isEmpty()) {
                        System.out.println("No books found by that author.");
                    } else {
                        for (Book b : books) {
                            System.out.println(b);
                        }
                    }
                    break;
                case 4:
                    System.out.print("Enter the title of the book to check out: ");
                    String checkOutTitle = scanner.nextLine();
                    boolean checkedOut = catalog.checkOutBook(checkOutTitle);
                    System.out.println("Checking out '" + checkOutTitle + "': " + (checkedOut ? "Success" : "Failed"));
                    break;
                case 5:
                    System.out.print("Enter the title of the book to return: ");
                    String returnTitle = scanner.nextLine();
                    boolean returned = catalog.returnBook(returnTitle);
                    System.out.println("Returning '" + returnTitle + "': " + (returned ? "Success" : "Failed"));
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }
}
