//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P03 Library Management Tester
// Course:   CS 300 Fall 2024
//
// Author:   Zhiyuan Li
// Email:    zli2562@wisc.edu
// Lecturer: Hobbes LeGault
//
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * Tests methods of Book and Library classes.
 */
public class LibraryTester {
  /**
   * PROVIDED TESTER METHOD: example test method for testing the getTitle method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testGetTitle() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    return "1984".equals(book.getTitle());
  }

  /**
   * PROVIDED TESTER METHOD: example test method for testing the setTitle method.
   *
   * @return true if the test passes, false otherwise
   */
  public static boolean testSetTitle() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    book.setTitle("Animal Farm");
    return "Animal Farm".equals(book.getTitle());
  }

  /**
   * Method for testing the getAuthor method.
   *
   * @return true if the test passes (i.e., the author is correctly retrieved), false otherwise
   */
  public static boolean testGetAuthor() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    return "George Orwell".equals(book.getAuthor());
  }

  /**
   * Method for testing the setAuthor method.
   *
   * @return true if the test passes (i.e., the author is correctly set), false otherwise
   */
  public static boolean testSetAuthor() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    book.setAuthor("Hobbes LeGault");
    return "Hobbes LeGault".equals(book.getAuthor());
  }

  /**
   * Method for testing the getYearOfPublication method
   *
   * @return true if the test passes (i.e., the year of publication is correctly retrieved), 
   * false otherwise
   */
  public static boolean testGetYearOfPublication() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    return 1949 == book.getYearOfPublication();
  }

  /**
   * Method for testing the setYearOfPublication method
   *
   * @return true if the test passes (i.e., the year of publication is correctly set), 
   * false otherwise
   */
  public static boolean testSetYearOfPublication() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    book.setYearOfPublication(2000);
    return 2000 == book.getYearOfPublication();
  }

  /**
   * Method for testing the getPublisher method.
   *
   * @return true if the test passes (i.e., the publisher is correctly retrieved), false otherwise
   */
  public static boolean testGetPublisher() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    return "Secker & Warburg".equals(book.getPublisher());
  }

  /**
   * Method for testing the setPublisher method.
   *
   * @return true if the test passes (i.e., the publisher is correctly set), false otherwise
   */
  public static boolean testSetPublisher() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    book.setPublisher("UWM");
    return "UWM".equals(book.getPublisher());
  }

  /**
   * Method for testing the getNumberOfPages method.
   * 
   * @return true if the test passes (i.e., the number of pages is correctly retrieved), 
   * false otherwise
   */
  public static boolean testGetNumberOfPages() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    return 328 == book.getNumberOfPages();
  }

  /**
   * Method for testing the setNumberOfPages method.
   * 
   * @return true if the test passes (i.e., the number of pages is correctly set), 
   * false otherwise
   */
  public static boolean testSetNumberOfPages() {
    Book book = new Book("1984", "George Orwell", 1949, "Secker & Warburg", 328);
    book.setNumberOfPages(500);
    return 500 == book.getNumberOfPages();
  }

  /**
   * PROVIDED TESTER METHOD: Retrieves the total number of books in the library.
   * 
   * @return the total number of books
   */
  public static boolean testGetTotalBooks() {
    Library library = new Library();
    library.addBook(new Book("Book 1", "Author A", 2023, "Publisher Y", 200));
    library.addBook(new Book("Book 2", "Author B", 2023, "Publisher Z", 300));

    int expected = 2;
    int result = library.getTotalBooks();

    ArrayList<Book> expectedA = new ArrayList<>();
    expectedA.add(new Book("Book 1", "Author A", 2023, "Publisher Y", 200));
    expectedA.add(new Book("Book 2", "Author B", 2023, "Publisher Z", 300));

    if (expected != result) {
      return false;
    }
    return compareBooks(expectedA, library.getAllBooks());
  }


  /**
   * PROVIDED TESTER METHOD: example test method for adding a single book to the library.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testAddBook() {
    Library library = new Library();
    Book book = new Book("Test Book", "Test Author", 2023, "Publisher X", 100);
    library.addBook(book);

    ArrayList<Book> expected = new ArrayList<>();
    expected.add(new Book("Test Book", "Test Author", 2023, "Publisher X", 100));
    return compareBooks(expected, library.getAllBooks());
  }

  /**
   * Tests the method for adding multiple books to the library.
   *
   * @return true if all books were added successfully and the lists match, false otherwise.
   */
  public static boolean testAddMultipleBooks() {
    Library library = new Library();
    Book book1 = new Book("Java Beginner", "Legault", 1923, "UWM", 100);
    Book book2 = new Book("Python Advanced", "Lagault", 2021, "UIUC", 200);
    Book book3 = new Book("Leetcode Hard", "Ligault", 2024, "Northeastern", 30);
    
    library.addBook(book1);
    library.addBook(book2);
    library.addBook(book3);
    
    ArrayList<Book> expected = new ArrayList<>();
    expected.add(new Book("Java Beginner", "Legault", 1923, "UWM", 100));
    expected.add(new Book("Python Advanced", "Lagault", 2021, "UIUC", 200));
    expected.add(new Book("Leetcode Hard", "Ligault", 2024, "Northeastern", 30));
    
    return compareBooks(expected, library.getAllBooks());
  }

  /**
   * PROVIDED TESTER METHOD: example test method for removing a book by title from the library.
   * 
   * @return true if the test passes, false otherwise
   */
  public static boolean testRemoveBookByTitle() {
    Library library = new Library();
    library.addBook(new Book("Test Book", "Test Author", 2023, "Publisher X", 100));
    boolean result = library.removeBookByTitle("Test Book");

    // checking result from removeBookByTitle("Test Book")
    if (result != true) {
      return false;
    }
    // checking resulted number of books
    if (library.getTotalBooks() != 0) {
      return false;
    }
    ArrayList<Book> expected = new ArrayList<>();
    // checking resulted library
    if (!compareBooks(expected, library.getAllBooks())) {
      return false;
    }
    return true;
  }
  
  
  /**
   * Tests the removal of one of multiple books in the library.
   *
   * @return true if the specified book was removed and the library state is correct, 
   * false otherwise.
   */
  public static boolean testRemoveOneOfManyBooks() {
    Library library = new Library();
    library.addBook(new Book("Python", "LeGault", 2022, "Publisher X", 100));
    library.addBook(new Book("Java", "LeGeult", 2021, "Publisher Y", 300));
    library.addBook(new Book("Racket", "LeBault", 2023, "Publisher Z", 500));
    
    boolean result = library.removeBookByTitle("Java");
    
    if (result != true) {
      return false;
    }
    
    if (library.getTotalBooks() != 2) {
      return false;
    }
    ArrayList<Book> expected = new ArrayList<Book>();
    expected.add(new Book("Python", "LeGault", 2022, "Publisher X", 100));
    expected.add(new Book("Racket", "LeBault", 2023, "Publisher Z", 500));
    
    if (! compareBooks(expected, library.getAllBooks())) {
      return false;
    }
    return true;
  }
  
  /**
   * Tests the method for finding books by a specific author.
   *
   * @return true if the retrieved list of books matches the expected list for the specified author, 
   *         false otherwise.
   */
  public static boolean testFindBooksByAuthor() {
    Library library = new Library();
    library.addBook(new Book("Python", "LeGault", 2022, "Publisher X", 100));
    library.addBook(new Book("Java", "LeGault", 2021, "Publisher Z", 200));
    library.addBook(new Book("HTML", "Charwhop", 2020, "Publisher Y", 2000));
    library.addBook(new Book("CSS", "Ama", 2023, "Publisher D", 1700));
    library.addBook(new Book("JS", "LeGault", 2011, "Publisher M", 300));
    
    ArrayList<Book> result = library.findBooksByAuthor("LeGault");
    
    if (library.getTotalBooks() != 5) {
      return false;
    }
    
    ArrayList<Book> expected = new ArrayList<Book>();
    expected.add(new Book("JS", "LeGault", 2011, "Publisher M", 300));
    expected.add(new Book("Java", "LeGault", 2021, "Publisher Z", 200));
    expected.add(new Book("Python", "LeGault", 2022, "Publisher X", 100));
    
    if (! compareBooks(expected, result)) {
      return false;
    }
    return true; 
  }

  /**
   * Tests the method for finding books by other authors.
   *
   * @return true if the retrieved list of books matches the expected list for the specified author, 
   *         false otherwise.
   */
  public static boolean testFindBooksByMultipleAuthors() {
    Library library = new Library();
    library.addBook(new Book("Python", "LeGault", 2022, "Publisher X", 100));
    library.addBook(new Book("Java", "LeGault", 2021, "Publisher Z", 200));
    library.addBook(new Book("HTML", "Charwhop", 2020, "Publisher Y", 2000));
    library.addBook(new Book("CSS", "Ama", 2023, "Publisher D", 1700));
    library.addBook(new Book("JS", "LeGault", 2011, "Publisher M", 300));
    library.addBook(new Book("C++", "Charwhop", 2022, "Publisher K", 700));
    
    ArrayList<Book> result = library.findBooksByAuthor("Charwhop");
    
    if (library.getTotalBooks() != 6) {
      return false;
    }
    
    ArrayList<Book> expected = new ArrayList<Book>();
    expected.add(new Book("HTML", "Charwhop", 2020, "Publisher Y", 2000));
    expected.add(new Book("C++", "Charwhop", 2022, "Publisher K", 700));
    
    if (! compareBooks(expected, result)) {
      return false;
    }
    return true; 
  }


  /**
   * Tests the method for updating the title of a specific book.
   *
   * @return true if the book title was updated successfully and the library state is correct, 
   * false otherwise.
   */
  public static boolean testUpdateBookTitle() {
    Library library = new Library();
    library.addBook(new Book("Python", "LeGault", 2022, "Publisher X", 100));
    
    boolean result = library.updateBookTitle("Python", "Thon");
    
    if (result != true) {
      return false;
    }
    
    if (library.getTotalBooks() != 1) {
      return false;
    }
    
    ArrayList<Book> expected = new ArrayList<Book>();
    expected.add(new Book("Thon", "LeGault", 2022, "Publisher X", 100));
    
    if (! compareBooks(expected, library.getAllBooks())) {
      return false;
    }
    return true; 
  }

  /**
   * Tests the method for updating multiple book titles in the library.
   * This method adds several books to the library, updates the title 
   * of one of them
   *
   * @return true if the book title was updated successfully and the library state is correct, 
   * false otherwise.
   */
  public static boolean testUpdateMultipleBookTitles() {
    Library library = new Library();
    library.addBook(new Book("Python", "LeGault", 2022, "Publisher X", 100));
    library.addBook(new Book("Java", "LeGault", 2021, "Publisher Z", 200));
    library.addBook(new Book("HTML", "Charwhop", 2020, "Publisher Y", 2000));
    library.addBook(new Book("CSS", "Ama", 2023, "Publisher D", 1700));
    library.addBook(new Book("JS", "LeGault", 2011, "Publisher M", 300));
    
    boolean result = library.updateBookTitle("CSS", "C#");
    
    if (result != true) {
      return false;
    }
    
    if (library.getTotalBooks() != 5) {
      return false;
    }
    
    ArrayList<Book> expected = new ArrayList<Book>();
    expected.add(new Book("JS", "LeGault", 2011, "Publisher M", 300));
    expected.add(new Book("HTML", "Charwhop", 2020, "Publisher Y", 2000));
    expected.add(new Book("Java", "LeGault", 2021, "Publisher Z", 200));
    expected.add(new Book("Python", "LeGault", 2022, "Publisher X", 100));
    expected.add(new Book("C#", "Ama", 2023, "Publisher D", 1700));
    
    if (! compareBooks(expected, library.getAllBooks())) {
      return false;
    }
    return true; 
  }

  /**
   * Tests the method for updating the author of a specific book.
   *
   * @return true if the author was updated successfully and the library state is correct, 
   * false otherwise.
   */
  public static boolean testUpdateBookAuthor() {
    Library library = new Library();
    library.addBook(new Book("Python", "LeGault", 2022, "Publisher X", 100));
    
    boolean result = library.updateBookAuthor("Python", "Andrew");
    
    if (result != true) {
      return false;
    }
    
    if (library.getTotalBooks() != 1) {
      return false;
    }
    
    ArrayList<Book> expected = new ArrayList<Book>();
    expected.add(new Book("Python", "Andrew", 2022, "Publisher X", 100));
    
    if (! compareBooks(expected, library.getAllBooks())) {
      return false;
    }
    return true; 
  }

  /**
   * Tests the method for updating multiple book authors in the library.
   * This method adds several books to the library, updates the author 
   * of one of them.
   *
   * @return true if the author was updated successfully and the library state is correct, 
   * false otherwise.
   */
  public static boolean testUpdateMultipleBookAuthors() {
    Library library = new Library();
    library.addBook(new Book("Python", "LeGault", 2022, "Publisher X", 100));
    library.addBook(new Book("Java", "LeGault", 2021, "Publisher Z", 200));
    library.addBook(new Book("HTML", "Charwhop", 2020, "Publisher Y", 2000));
    library.addBook(new Book("CSS", "Ama", 2023, "Publisher D", 1700));
    library.addBook(new Book("JS", "LeGault", 2011, "Publisher M", 300));
    
    boolean result = library.updateBookAuthor("CSS", "Andy");
    
    if (result != true) {
      return false;
    }
    
    if (library.getTotalBooks() != 5) {
      return false;
    }
    
    ArrayList<Book> expected = new ArrayList<Book>();
    expected.add(new Book("JS", "LeGault", 2011, "Publisher M", 300));
    expected.add(new Book("HTML", "Charwhop", 2020, "Publisher Y", 2000));
    expected.add(new Book("Java", "LeGault", 2021, "Publisher Z", 200));
    expected.add(new Book("Python", "LeGault", 2022, "Publisher X", 100));
    expected.add(new Book("CSS", "Andy", 2023, "Publisher D", 1700));
    
    if (! compareBooks(expected, library.getAllBooks())) {
      return false;
    }
    return true; 
  }

  /**
   * Tests the removal of a non-existent book from the library.
   *
   * @return true if the non-existent book was not removed and the library state is correct, 
   * false otherwise.
   */
  public static boolean testRemoveNonExistentBook() {
    Library library = new Library();
    library.addBook(new Book("Test Book", "Test Author", 2023, "Publisher X", 100));
    boolean result = library.removeBookByTitle("Some Book");

    if (result != false) {
      return false;
    }

    if (library.getTotalBooks() != 1) {
      return false;
    }

    ArrayList<Book> expected = new ArrayList<>();
    expected.add(new Book("Test Book", "Test Author", 2023, "Publisher X", 100));
    
    if (!compareBooks(expected, library.getAllBooks())) {
      return false;
    }
    return true;
  }

  /**
   * Compares two lists of books for equality.
   * 
   * @param expected the expected list of books
   * @param result   the list of books to compare
   * @return true if both lists contain the same books, false otherwise
   */
  private static boolean compareBooks(ArrayList<Book> expected, ArrayList<Book> result) {
    if (expected.size() != result.size()) {
      return false;
    }
    for (int i = 0; i < expected.size(); i++) {
      Book expectedBook = expected.get(i);
      Book resultBook = result.get(i);
      if (!expectedBook.getTitle().equals(resultBook.getTitle())
          || !expectedBook.getAuthor().equals(resultBook.getAuthor())
          || !(expectedBook.getPublisher().equals(resultBook.getPublisher()))
          || !(expectedBook.getNumberOfPages() == resultBook.getNumberOfPages())
          || !(expectedBook.getYearOfPublication() == resultBook.getYearOfPublication())) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    // test two functions in book.class
    System.out.println("Test getTitle: " + testGetTitle());
    System.out.println("Test setTitle: " + testSetTitle());
    System.out.println("Test getAuthor: " + testGetAuthor());
    System.out.println("Test setAuthor: " + testSetAuthor());
    System.out.println("Test getYearOfPublication: " + testGetYearOfPublication());
    System.out.println("Test setYearOfPublication: " + testSetYearOfPublication());
    System.out.println("Test getPublisher: " + testGetPublisher());
    System.out.println("Test setPublisher: " + testSetPublisher());
    System.out.println("Test getNumberOfPages: " + testGetNumberOfPages());
    System.out.println("Test setNumberOfPages: " + testSetNumberOfPages());
    System.out.println("Test addBook: " + testAddBook());
    System.out.println("Test addMultipleBooks: " + testAddMultipleBooks());
    System.out.println("Test removeBookByTitle: " + testRemoveBookByTitle());
    System.out.println("Test removeOneOfManyBooks: " + testRemoveOneOfManyBooks());
    System.out.println("Test findBooksByAuthor: " + testFindBooksByAuthor());
    System.out.println("Test findBooksByMultipleAuthors: " + testFindBooksByMultipleAuthors());
    System.out.println("Test updateBookTitle: " + testUpdateBookTitle());
    System.out.println("Test updateMultipleBookTitles: " + testUpdateMultipleBookTitles());
    System.out.println("Test updateBookAuthor: " + testUpdateBookAuthor());
    System.out.println("Test updateMultipleBookAuthors: " + testUpdateMultipleBookAuthors());
    System.out.println("Test removeNonExistentBook: " + testRemoveNonExistentBook());

  }
}
