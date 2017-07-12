package edu.csulb.library;

import java.util.ArrayList;
import java.util.List;

import edu.csulb.library.dao.BookDao;
import edu.csulb.library.dao.impl.BookDaoImpl;
import edu.csulb.library.entity.Book;

public class App {

	BookDao<Book> bookDao;

	public static void main(String[] args) {

		App app = new App();
		System.out.println("Welcome to the tutorial on HIBERNATE");

		// Create Multiple Book Entries to store in hibernate
		List<Book> listOfBooks = new ArrayList<Book>();

		Book book1 = app.createBookEntity("Introduction to Algorithms", "Cormen", 2009);
		listOfBooks.add(book1);

		Book book2 = app.createBookEntity("Introduction to Information Retrieval", "Larry Page", 2005);
		listOfBooks.add(book2);

		Book book3 = app.createBookEntity("Computer Simulation", "Todd Ebert", 2016);
		listOfBooks.add(book3);

		// Initializing hibernate configuration
		app.initializeHibernateEnv();

		// Beginning a new session
		app.startAHibernateSession();
		for (Book b : listOfBooks) {
			app.saveBook(b);
		}
		app.endAHibernateSession();

		Book bookFound = null;

		System.out.println("\nUsing get() method to fetch a row from hibernate");
		app.startAHibernateSession();
		bookFound = app.findBookUsingGet(1);
		System.out.println("Printing the book details");
		System.out.println(
				"OBSERVE THAT BEFORE PRINTING THE HIBERNATE HITS THE DATABASE AND FETCHES ALL THE DETAILS. THE QUERY IS ALREADY MADE");
		app.printBookDetails(bookFound);
		app.endAHibernateSession();
		bookFound = null;
		System.out.println();

		System.out.println("\nUsing load() method to fetch a row from hibernate");
		app.startAHibernateSession();
		bookFound = app.findBookUsingLoad(2);
		System.out.println("Printing the book details");
		System.out.println(
				"OBSERVE THAT HERE HIBERNATE HITS THE DATABASE ONLY WHEN IT NEEDS THE DETAILS. THE QUERY IS MADE BELOW WHERE THE DETAILS ARE PRINTED");
		app.printBookDetails(bookFound);
		app.endAHibernateSession();

		// Closing the hibernate environment
		// Note that this must be the last statement in the main block
		// otherwise your main program won't terminate automatically
		app.closeHibernateEnvironment();

	}

	private Book findBookUsingLoad(int id) {
		this.bookDao = new BookDaoImpl();
		return this.bookDao.findByIdUsingLoad(id);
	}

	private Book findBookUsingGet(int id) {
		this.bookDao = new BookDaoImpl();
		return this.bookDao.findByIdUsingGet(id);
	}


	/**
	 * 
	 * @param b
	 */
	public void saveBook(Book b) {
		this.bookDao = new BookDaoImpl();
		this.bookDao.persist(b);
	}

	public void printBookDetails(Book b) {
		if (b != null) {
			System.out.println("Title : " + b.getTitle() + ", Author : " + b.getAuthor() + ", Publishing Year : "
					+ b.getPublishingYear());
		}
	}

	/**
	 * 
	 * @param name
	 * @param author
	 * @param publishingYear
	 * @return
	 */
	public Book createBookEntity(String name, String author, int publishingYear) {

		System.out.println("Creating a BookEntity object to save in the database\n");
		Book book = new Book(name, author, publishingYear);
		return book;
	}

	/**
	 * 
	 */
	private void initializeHibernateEnv() {

		System.out.println("Calling the HibernateUtil.initialize() to initialize the hibernate the framework\n");

		HibernateUtil.initialize();

		System.out.println("Hibenate Framework is initialized successfully. This means you have the session factory "
				+ "available now and can create a new session from that session factory\n");
	}

	private void closeHibernateEnvironment() {
		HibernateUtil.closeSessionFactory();
	}

	private void startAHibernateSession() {
		HibernateUtil.getCurrentSession();
	}

	private void endAHibernateSession() {
		HibernateUtil.endCuurentSession();
	}
}
