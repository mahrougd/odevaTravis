package car.tp4.entity;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BookTest {

	protected Book b, bookToAddStock, bookToRemoveStock;
	protected String author, title;
	protected int year, stock, stockToAdd, stockToRemove;

	@Before
	public void setUp() throws Exception {
		author = "Douae";
		title = "Book 1";
		year = 2010;
		stock = 5;
		stockToAdd = 20;
		stockToRemove = 100;

		b = new Book(author, title, year, stock);
		bookToAddStock = new Book(author, title, year, stockToAdd);
		bookToRemoveStock = new Book(author, title, year, stockToRemove);

	}

	@Test
	public void testBook() {
		assertEquals("Douae", b.getAuthor());
		assertEquals("Book 1", b.getTitle());
		assertEquals(2010, b.getYear());
		assertEquals(5, b.getStock());
	}

	@Test
	public void testGetId() {
		assertEquals(0, b.getId());
	}

	@Test
	public void testGetAndSetAuthor() {
		assertEquals("Douae", b.getAuthor());
		b.setAuthor("toto");
		assertEquals("toto", b.getAuthor());
	}

	@Test
	public void testGetAndSetTitle() {
		assertEquals("Book 1", b.getTitle());
		b.setTitle("newtitle");
		assertEquals("newtitle", b.getTitle());
	}

	@Test
	public void testGetAndSetYear() {
		assertEquals(2010, b.getYear());
		b.setYear(2017);
		assertEquals(2017, b.getYear());
	}

	@Test
	public void testGetAndSetStock() {
		assertEquals(5, b.getStock());
		b.setStock(8);
		assertEquals(8, b.getStock());
	}

	@Test
	public void testAddStock() {
		bookToAddStock.addStock(2);
		assertEquals(22, bookToAddStock.getStock());
	}

	@Test
	public void testRemoveStock() {
		bookToRemoveStock.removeStock(1);
		assertEquals(99, bookToRemoveStock.getStock());
	}

}
