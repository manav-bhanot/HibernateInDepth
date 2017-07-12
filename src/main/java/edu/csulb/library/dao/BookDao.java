/**
 * 
 */
package edu.csulb.library.dao;

import java.util.List;

/**
 * @author Manav
 *
 */
public interface BookDao<T> {

	void persist(T entity);

	void update(T entity);

	T findByIdUsingGet(long id);

	T findByIdUsingLoad(long id);

	void deleteBook(T entity);

	List<T> findAllBooks();

	void deleteAllBooks();
}
