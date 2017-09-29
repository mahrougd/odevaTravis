package car.tp4.entity;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCartTest {

	protected ShoppingCart sc;

	@Before
	public void setUp() throws Exception {
		sc = new ShoppingCart();
	}

	@Test
	public void testShoppingCart() {
		assertNotNull(sc);
	}

	@Test
	public void testGetId() {
		assertEquals(0, sc.getId());
	}

	@Test
	public void testSetAndISValidated() {
		sc.setValidated(true);
		assertTrue(sc.isValidated());
	}
}
