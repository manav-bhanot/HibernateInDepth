/**
 * 
 */
package edu.csulb.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Manav
 *
 */

@Entity
@Table(name = "Book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "author")
	String author;

	@Column(name = "Publishing_Year")
	int publishingYear;

	public Book() {
	}

	public Book(Long id, String title, String author, int publishingYear) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publishingYear = publishingYear;
	}

	public Book(String title, String author, int publishingYear) {
		super();
		this.title = title;
		this.author = author;
		this.publishingYear = publishingYear;
	}

	public Book(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPublishingYear() {
		return publishingYear;
	}

	public void setPublishingYear(int publishingYear) {
		this.publishingYear = publishingYear;
	}

	@Override
	public String toString() {
		return "Book : " + this.id + ", " + this.title + ", " + this.author + ", " + this.publishingYear;
	}

}
