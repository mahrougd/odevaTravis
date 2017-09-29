package car.tp4.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import car.tp4.entity.*;

/**
 * servlet for listing all the item in the cart
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BookBean bookBean;

	@EJB
	private ShoppingCartBean shoppingCartBean;

	@EJB
	private ShoppingCartContentBean shoppingCartContentBean;

	/**
	 * method called from get request
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// session verification

		HttpSession session = request.getSession(true);
		ShoppingCart cart;
		Object cartIdObj = session.getAttribute("shoppingCart");
		if (cartIdObj != null) {
			cart = shoppingCartBean.getById((Long) cartIdObj);
		} else {
			cart = shoppingCartBean.createCart();
			session.setAttribute("shoppingCart", cart.getId());
		}

		List<ShoppingCartContent> content = shoppingCartContentBean.getAllFromShoppingCart(cart.getId());

		Map<Long, Integer> mapContent = new TreeMap<Long, Integer>();
		List<Long> booksIds = new ArrayList<Long>();

		for (ShoppingCartContent c : content) {
			booksIds.add(c.getBookId());
			mapContent.put(c.getBookId(), c.getQuantity());
		}

		request.setAttribute("books", bookBean.getAllFromIds(booksIds));
		request.setAttribute("cart_quantities", mapContent);
		getServletContext().getRequestDispatcher("/jsp/cart.jsp").forward(request, response);
	}
}
