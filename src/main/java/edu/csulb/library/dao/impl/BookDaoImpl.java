/**
 * 
 */
package edu.csulb.library.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.csulb.library.HibernateUtil;
import edu.csulb.library.dao.BookDao;
import edu.csulb.library.entity.Book;

/**
 * @author Manav
 *
 */

public class BookDaoImpl implements BookDao<Book> {

	private Session session;
	private Transaction tx;

	public BookDaoImpl() {
		this.session = HibernateUtil.getCurrentSession();
	}

	public void persist(Book bookEntity) {
		try {
			this.tx = this.session.beginTransaction();

			this.session.save(bookEntity);

			this.tx.commit();
		} finally {
			// this.session.close();
		}

	}

	public void update(Book bookEntity) {
		this.tx = this.session.beginTransaction();
		this.session.update(bookEntity);
		this.tx.commit();
	}

	public Book findByIdUsingGet(long id) {
		Book book = null;
		try {
			book = session.get(Book.class, id);
		} finally {
			// this.session.close();
		}

		return book;
	}

	public Book findByIdUsingLoad(long id) {
		Book book = null;
		try {
			book = session.load(Book.class, id);
		} finally {
			// this.session.close();
		}

		return book;
	}

	public void deleteBook(Book bookEntity) {
		try {
			session.delete(bookEntity);
		} finally {
			// session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Book> findAllBooks() {

		List<Book> allBooks;

		try {
			allBooks = session.createQuery("from Book").list();
		} finally {
			// session.close();
		}

		return allBooks;
	}

	public void deleteAllBooks() {
		for (Book b : findAllBooks()) {
			deleteBook(b);
		}
	}

}
