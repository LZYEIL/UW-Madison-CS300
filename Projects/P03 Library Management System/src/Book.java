//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P03 Library Management Book Class
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


/**
 * Represents the Book class that has multiple features
 */
public class Book {
  
  private String title;
  private String author;
  private int yearOfPublication;
  private String publisher;
  private int numberOfPages;
  
  
  /**
   * Constructs a Book object with the specified title, author, year of publication, publisher, 
   * and number of pages.
   * 
   * @param title   The title of the book
   * @param author  The author of the book
   * @param yearOfPublication  The year that the book was published
   * @param publisher The publisher of the book
   * @param numberOfPages The number of pages of the book
   * @throws IllegalArgumentException Throw if yearOfPublication is negative, more than 2024, or 
   * numberOfPages is negative
   */
  public Book(String title, String author, int yearOfPublication, String publisher, 
      int numberOfPages) {
    this.title = title;
    this.author = author;
    if (yearOfPublication < 0 || yearOfPublication > 2024) {
      throw new IllegalArgumentException("Invalid year of publication");
    }
    this.yearOfPublication = yearOfPublication;
    this.publisher = publisher;
    if (numberOfPages < 0) {
      throw new IllegalArgumentException("Invalid book page");
    }
    this.numberOfPages = numberOfPages;
  }
  
  
  /**
   * Returns the title of the book.
   * 
   * @return the title of the book
   */
  public String getTitle() {
    return this.title;
  }
  
  /**
   * Returns the author of the book.
   * 
   * @return the author of the book
   */
  public String getAuthor() {
    return this.author;
  }
  
  /**
   * Returns the year of publication of the book.
   * 
   * @return the year the book was published
   */
  public int getYearOfPublication() {
    return this.yearOfPublication;
  }
  
  /**
   * Returns the publisher of the book.
   * 
   * @return the publisher of the book
   */
  public String getPublisher() {
    return this.publisher;
  }
  
  /**
   * Returns the number of pages in the book.
   * 
   * @return the number of pages in the book
   */
  public int getNumberOfPages() {
    return this.numberOfPages;
  }
  
  /**
   * Sets the title of the book.
   * 
   * @param title the title to be set for the book
   */
  public void setTitle(String title) {
    this.title = title;
  }
  
  /**
   * Sets the author of the book.
   * 
   * @param author the author to be set for the book
   */
  public void setAuthor(String author) {
    this.author = author;
  }
  
  /**
   * Sets the year of publication of the book.
   * Ensures the year is within a valid range (0-2024).
   * 
   * @param year the year of publication to be set
   * @throws IllegalArgumentException if the year is negative or greater than 2024
   */
  public void setYearOfPublication(int year) {
    if (year < 0 || year > 2024) {
      throw new IllegalArgumentException("Invalid parameter");
    }
    this.yearOfPublication = year;
  }
  
  /**
   * Sets the publisher of the book.
   * 
   * @param publisher the publisher to be set for the book
   */
  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }
  
  /**
   * Sets the number of pages in the book.
   * Ensures that the number of pages is not negative.
   * 
   * @param pages the number of pages to be set
   * @throws IllegalArgumentException if the number of pages is negative
   */
  public void setNumberOfPages(int pages) {
    if (pages < 0) {
      throw new IllegalArgumentException("Invalid parameter");
    }
    this.numberOfPages = pages;
  }
  
  
  
}