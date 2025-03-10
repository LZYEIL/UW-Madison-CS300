//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P03 Library Management Library Class
// Course:   CS 300 Fall 2024
//
// Author:   Zhiyuan Li
// Email:    zli2562@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////


import java.util.ArrayList;

/**
 * Represents a library that manages a collection of books.
 */
public class Library {
  
  private ArrayList<Book> books;
  
  /**
   * Constructs an empty library. This constructor initializes the 'books' list so that 
   * books can be added later.
   */
  public Library() {
    this.books= new ArrayList<Book>();
  }
  
  /**
   * Retrieves the total number of books in the library.
   * This method returns the size of the book list, which represents the total number of books.
   * 
   * @return the total number of books in the library
   */
  public int getTotalBooks() {
    return books.size();
  }

  /**
   * Returns a list of all books in the library.
   * This method creates and returns a new list that contains all the books in the library.
   * 
   * @return a new ArrayList containing all books in the library
   */
  public ArrayList<Book> getAllBooks() {
    ArrayList<Book> copyList = new ArrayList<Book>();
    
    for (int i = 0; i < books.size(); i++) {
      copyList.add(books.get(i));     
    }
    return copyList;
  }
  
  
  /**
   * Adds a book to the library in chronological order based on the year of publication.
   * If the library is empty or the book's year of publication is greater than or equal to
   * the year of the most recently published book, it is added at the end. Otherwise, the book
   * is inserted at the correct position to maintain the chronological order.
   * 
   * @param book the book to be added to the library
   */
  public void addBook(Book book) {
    int year = book.getYearOfPublication();
    
    if (books.isEmpty() || year >= books.get(books.size() - 1).getYearOfPublication()) {
      books.add(book);
    }
    else {
      for (int i = 0; i < books.size(); i++) {
        if (books.get(i).getYearOfPublication() > year) {
          books.add(i, book);
          break;
        }
      }
      
    }
    
  }
  
  
  /**
   * Removes a book from the library by its title.
   * This method searches the books list for a book with the specified title and removes it.
   * 
   * @param title the title of the book to remove
   * @return true if the book was removed successfully, false if no book with the specified title 
   * was found
   */
  public boolean removeBookByTitle(String title) {
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).getTitle().equals(title)) {
        books.remove(i);
        return true;
      }
    }
    return false;
  }

  
  /**
   * Finds books by a specific author.
   * This method searches the books list for books written by the specified author.
   * 
   * @param author the author whose books are to be found
   * @return an ArrayList of books written by the specified author, or an empty list if no 
   * books are found
   */
  public ArrayList<Book> findBooksByAuthor(String author) {
    ArrayList<Book> bookList = new ArrayList<Book>();
    
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).getAuthor().equals(author)) {
        bookList.add(books.get(i));
      }
    }
    return bookList;
  }

  /**
   * Updates the title of a book. This method searches for a book with the specified old title 
   * and updates it to the new title.
   * 
   * @param oldTitle  the current title of the book
   * @param newTitle  the new title to assign
   * @return  true if the title was updated, false otherwise
   */
  public boolean updateBookTitle(String oldTitle, String newTitle) {
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).getTitle().equals(oldTitle)) {
        books.get(i).setTitle(newTitle);
        return true;
      }
    }
    return false;
  }
  
  
  /**
   * Updates the author of a book. This method searches for a book with the specified title 
   * and updates its author.
   * 
   * @param title the title of the book whose author is to be updated
   * @param newAuthor   the new author to assign
   * @return   true if the author was updated, false otherwise
   */
  public boolean updateBookAuthor(String title, String newAuthor) {
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).getTitle().equals(title)) {
        books.get(i).setAuthor(newAuthor);
        return true;
      }
    }
    return false;
  }
  
  /**
   * Prints all books in the library. This method prints the title and author of each book in the 
   * library to the console.
   */
  public void printAllBooks() {
    for (int i = 0; i < books.size(); i++) {
      System.out.println("Title: " + books.get(i).getTitle() +
          ", Author: " + books.get(i).getAuthor());
    }
  }



  
}