// Book.java
public class Book {
    private String title;
    private String author;
    private boolean checkedOut;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.checkedOut = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void checkOut() {
        if (!checkedOut) {
            checkedOut = true;
        } else {
            throw new IllegalStateException("Book is already checked out.");
        }
    }

    public void returnBook() {
        if (checkedOut) {
            checkedOut = false;
        } else {
            throw new IllegalStateException("Book was not checked out.");
        }
    }

    @Override
    public String toString() {
        return String.format("Title: %s, Author: %s, Status: %s",
                title, author, checkedOut ? "Checked Out" : "Available");
    }
}
