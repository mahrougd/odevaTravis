package car.tp4.entity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ShoppingCartContentTest {

	ShoppingCartContent scc;
	long scId, bId;
	int qtt;

	@Before
	public void setUp() throws Exception {
		scId = 1;
		bId = 2;
		qtt = 3;
		scc = new ShoppingCartContent(scId, bId, qtt);
	}

	@Test
	public void testShoppingCartContent() {
		assertEquals(1, scc.getShoppingCartId());
		assertEquals(2, scc.getBookId());
		assertEquals(3, scc.getQuantity());
	}

	@Test
	public void testGetId() {
		assertEquals(0, scc.getId());
	}

	@Test
	public void testGetShoppingCartId() {
		assertEquals(1, scc.getShoppingCartId());
		scc.setShoppingCartId(5);
		assertEquals(5, scc.getShoppingCartId());
	}

	@Test
	public void testGetBookId() {
		assertEquals(2, scc.getBookId());
		scc.setBookId(42);
		assertEquals(42, scc.getBookId());
	}

	@Test
	public void testGetQuantity() {
		assertEquals(3, scc.getQuantity());
		scc.setQuantity(24);
		assertEquals(24, scc.getQuantity());
	}

}
