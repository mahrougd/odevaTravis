package car.tp4.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Book item
 */
@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	private String author;
	private String title;
	private int year;
	private int stock;

	/**
	 * book constructor
	 * 
	 * @param author
	 *            authors name
	 * @param title
	 *            books title
	 * @param year
	 *            Year it came out
	 * @param stock
	 *            quantity available
	 */
	public Book(String author, String title, int year, int stock) {
		this.author = author;
		this.title = title;
		this.stock = stock;
		this.year = year;
	}

	public long getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		if (stock < 0)
			throw new IllegalArgumentException("can't add negative numbers of book");
		this.stock = stock;
	}

	/**
	 * Add "add" to this book stock
	 * 
	 * @param add
	 *            the quantity to add
	 */

	public void addStock(int add) {
		setStock(getStock() + add);
	}

	/**
	 * remove "remove" to this book stock
	 * 
	 * @param remove
	 *            the quantity to remove
	 */
	public void removeStock(int remove) {
		addStock(-remove);
	}

	@Override
	public String toString() {
		return "Book{" + "author='" + author + "'" + ", title='" + title + "'" + ", year='" + year + "'" + '}';
	}

	/**
	 * Compare 2 book by their date
	 * 
	 * @param b1
	 *            the first book
	 * @param b2
	 *            the 2nd book
	 * @return 0 si if the date is the same, -1 if b1 <b2, 1 otherwise.
	 */
	public static int compareByReleaseDate(Book b1, Book b2) {
		return Integer.compare(b1.year, b2.year);
	}

	/**
	 * equals for 2 books
	 * 
	 * @param o
	 * @return true if title and author are the same
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Book book = (Book) o;

		if (!author.equals(book.author))
			return false;
		return title.equals(book.title);
	}

	@Override
	public int hashCode() {
		int h = 31 * 31 * year;
		h += 31 * author.hashCode();
		h += title.hashCode();
		return h;
	}
}
