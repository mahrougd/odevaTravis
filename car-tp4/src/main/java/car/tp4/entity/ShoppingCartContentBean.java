package car.tp4.entity;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * all the item in a shopping cart
 */
@Stateless
@Local
public class ShoppingCartContentBean {

	@PersistenceContext(unitName = "cart-content-pu")
	private EntityManager entityManager;

	/**
	 * add a new item in the entitymanager
	 * 
	 * @param the
	 *            item to add
	 * @return the id of the content
	 */
	public long addShoppingCartContent(ShoppingCartContent content) {
		content = save(content);
		return content.getId();
	}

	/**
	 * save or update an item in the cart (same save as in book)
	 * 
	 * @param scc
	 *            the item to save or update
	 * @return an instance of this content from the entitymanager
	 */
	public ShoppingCartContent save(ShoppingCartContent scc) {
		return entityManager.merge(scc);
	}

	/**
	 * return the element matching the ID
	 * 
	 * @param id
	 *            the ID of the element
	 * @return the element matching the ID if it exist
	 */
	public ShoppingCartContent getById(long id) {
		return entityManager.find(ShoppingCartContent.class, id);
	}

	/**
	 * return all the element of the cart.
	 * 
	 * @return a list of all the element in the cart.
	 */
	public List<ShoppingCartContent> getAll() {
		Query query = entityManager.createQuery("SELECT c FROM ShoppingCartContent as c");
		return query.getResultList();
	}

	/**
	 * Return all the element from the shopping cart with this id
	 * 
	 * @param scId
	 *            the sopping cart id
	 * @return list of elements from the shopping cart matching the id
	 */
	public List<ShoppingCartContent> getAllFromShoppingCart(long scId) {
		Query query = entityManager
				.createQuery("SELECT c FROM ShoppingCartContent as c WHERE c.shoppingCartId = :scId");
		query.setParameter("scId", scId);
		return query.getResultList();
	}

	/**
	 * Return an element matching the given id from a shopping cart matching the
	 * other given id
	 * 
	 * @param scId
	 *            the id of the shopping cart
	 * @param bId
	 *            the id of the book
	 * @return the element from the shopping cart
	 */
	public ShoppingCartContent getFromShoppingCartAndBookId(long scId, long bId) {
		Query query = entityManager.createQuery(
				"SELECT c FROM ShoppingCartContent as c WHERE c.shoppingCartId = :scId AND c.bookId = :bId");
		query.setParameter("scId", scId);
		query.setParameter("bId", bId);
		return (ShoppingCartContent) query.getSingleResult();
	}
}
