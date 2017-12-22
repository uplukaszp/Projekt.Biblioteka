package projekt.model;

import java.util.Date;

public class Lend {
	private Reader reader;
	private Book book;
	private Date lendDate;
	private Date returnDate;
	public Reader getReader() {
		return reader;
	}
	public void setReader(Reader reader) {
		this.reader = reader;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Date getLendDate() {
		return lendDate;
	}
	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	@Override
	public String toString() {
		return "Lend [reader=" + reader + ", book=" + book + ", lendDate=" + lendDate + ", returnDate=" + returnDate
				+ "]";
	}
	
}
