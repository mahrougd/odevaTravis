package car.tp4.entity;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * All the books registered
 */
@Stateless
@Local
public class BookBean {

	@PersistenceContext(unitName = "book-pu")
	private EntityManager entityManager;

	/**
	 * Save a book state
	 * 
	 * @param the
	 *            book to save
	 * @return the book instances from the entityManager
	 */
	public Book save(Book book) {
		return entityManager.merge(book);
	}

	/**
	 * return all the books registered
	 * 
	 * @return the books registered
	 */
	public List<Book> getAllBooks() {
		Query query = entityManager.createQuery("SELECT b FROM Book as b");
		return query.getResultList();
	}

	/**
	 * return the book with the correct id
	 * 
	 * @param id
	 *            ID of the book
	 * @return the book that match the id, null if there is no such book
	 */
	public Book getById(long id) {
		return entityManager.find(Book.class, id);
	}

	/**
	 * return all the books that ids match with the parameter list of id
	 * 
	 * @param ids
	 *            the list of book ids
	 * @return all the books with the rights ids
	 */
	public List<Book> getAllFromIds(List<Long> ids) {
		List<Book> books = new ArrayList<Book>();
		for (Long id : ids) {
			books.add(getById(id));
		}
		return books;
	}

	/**
	 * return book that match the portion of title in parameter
	 * 
	 * @param title
	 *            the title (can be a portion or full title)
	 * @return all the books that match the parameter
	 */
	public List<Book> getBookByTitlePortion(String title) {
		String escape = "\\";
		Query query = entityManager
				.createQuery("SELECT b FROM Book as b WHERE b.title LIKE :title ESCAPE '" + escape + "'");
		query.setParameter("title",
				"%" + title.replace(escape, "").replace("%", escape + "%").replace("_", escape + "_") + "%");
		return query.getResultList();
	}

	/**
	 * Return the book for whom the author is the one given in the parameter
	 * 
	 * @param author
	 *            the author were looking for
	 * @return all the books that match the author
	 */
	public List<Book> getBooksByAuthor(String author) {
		Query query = entityManager.createQuery("SELECT b FROM Book as b WHERE b.author = :author");
		query.setParameter("author", author);
		return query.getResultList();
	}
}
