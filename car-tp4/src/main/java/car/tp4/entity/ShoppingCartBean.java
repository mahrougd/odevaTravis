package car.tp4.entity;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * All the shopping cart
 */
@Stateless
@Local
public class ShoppingCartBean {

	@PersistenceContext(unitName = "cart-pu")
	private EntityManager entityManager;

	/**
	 * create a new shopping cart
	 * 
	 * @return the new shopping cart
	 */
	public ShoppingCart createCart() {
		return save(new ShoppingCart());
	}

	/**
	 * save the shopping cart (same function as Bookbean save)
	 * 
	 * @param sc
	 *            the shopping cart to save
	 * @return the sc instance from the entitymanager
	 */
	public ShoppingCart save(ShoppingCart sc) {
		return entityManager.merge(sc);
	}

	/**
	 * return the shoppingcart matching the given ID
	 * 
	 * @param id
	 *            the shopping cart ID
	 * @return the shoppingcart matching the ID if it exist
	 */
	public ShoppingCart getById(long id) {
		return entityManager.find(ShoppingCart.class, id);
	}

	/**
	 * return all the shopping cart that has been validated
	 * 
	 * @return a list of all the validated shopping cart
	 */
	public List<ShoppingCart> getValidatedShoppingCarts() {
		Query query = entityManager.createQuery("SELECT c FROM ShoppingCart as c WHERE c.validated is true");
		return query.getResultList();
	}
}
