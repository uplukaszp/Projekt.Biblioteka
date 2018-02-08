package project.model;

import java.sql.Date;

public class Book {
	private long id;
	private String title;
	private Date publicationDate;
	private String ISBN;
	private String Keywords;
	private BookStatus status;
	private String type;
	private Author author;
	private Publisher publisher;

	public Book()
	{
		author=new Author();
		publisher=new Publisher();
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getKeywords() {
		return Keywords;
	}

	public void setKeywords(String keywords) {
		Keywords = keywords;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public BookStatus getStatus() {
		return status;
	}

	public void setStatus(BookStatus status) {
		this.status = status;
	}
	
	public boolean isAccesible()
	{
		return status==BookStatus.lent||status==BookStatus.available;
	}

	@Override
	public String toString() {
		return title + " " + author;
	}
}
