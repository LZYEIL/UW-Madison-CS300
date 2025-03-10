
/**
 * Demonstrates the usage of the Library and Book classes.
 */
public class LibraryDriver {
    /**
     * The main method to demonstrate library functionalities.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Library myLibrary = new Library();
        myLibrary.addBook(new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328));
        myLibrary.addBook(new Book("Animal Farm", "George Orwell", 1945, "Secker & Warburg", 112));
        myLibrary.addBook(new Book("To Kill a Mockingbird", "Harper Lee", 1960, "J.B. Lippincott & Co.", 281));

        System.out.println("Total books: " + myLibrary.getTotalBooks());

        // Print all books information
        System.out.println("All books in the library:");
        myLibrary.printAllBooks();

        // Remove a book and check the count
        boolean removed = myLibrary.removeBookByTitle("1984");
        if (removed) {
            System.out.println("Book '1984' removed.");
        } else {
            System.out.println("Book '1984' not found.");
        }
        System.out.println("Total books after removal: " + myLibrary.getTotalBooks());

        // Print all books information
        System.out.println("All books in the library:");
        myLibrary.printAllBooks();

        // Update a book's title and author
        boolean titleUpdated = myLibrary.updateBookTitle("Animal Farm", "Animal Farm Updated");
        boolean authorUpdated = myLibrary.updateBookAuthor("Animal Farm Updated", "George Orwell Updated");

        if (titleUpdated && authorUpdated) {
            System.out.println("Book 'Animal Farm' updated.");
        }

        // Print all books information
        System.out.println("All books in the library:");
        myLibrary.printAllBooks();

        // Find and print books by updated author
        System.out.println("Books by George Orwell Updated:");
        for (Book book : myLibrary.findBooksByAuthor("George Orwell Updated")) {
            System.out.println(book.getTitle() + " by " + book.getAuthor());
        }
    }
}