package car.tp4.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * represent an item in the shopping cart (pair book / quantity)
 */
@Entity
public class ShoppingCartContent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	private long shoppingCartId;
	private long bookId;
	private int quantity;

	/**
	 * constructor for a new item
	 * 
	 * @param scId
	 *            the shoppingcart to add this item to
	 * @param bId
	 *            the id of the book to add
	 * @param qtt
	 *            the amount of time you want to add this book
	 */
	public ShoppingCartContent(long scId, long bId, int qtt) {
		setBookId(bId);
		setShoppingCartId(scId);
		setQuantity(qtt);
	}

	public long getId() {
		return id;
	}

	public long getShoppingCartId() {
		return shoppingCartId;
	}

	protected void setShoppingCartId(long shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public long getBookId() {
		return bookId;
	}

	protected void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
