package car.tp4.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * represent the shopping cart (one per "person")
 */
@Entity
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	private boolean validated = false;

	public ShoppingCart() {
	}

	public long getId() {
		return id;
	}

	/**
	 * validate this cart or invalidate this cart
	 * 
	 * @param validated
	 *            the new state of the cart
	 */
	public void setValidated(boolean validated) {
		this.validated = validated;
	}

	/**
	 * check if the cart has been validated
	 * 
	 * @return true if this cart has been validated, false otherwise
	 */
	public boolean isValidated() {
		return validated;
	}

}
