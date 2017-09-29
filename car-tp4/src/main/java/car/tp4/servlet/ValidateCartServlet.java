package car.tp4.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import car.tp4.entity.*;

/**
 * Servlet for cart validation
 *
 */

@WebServlet("/validate_cart")
public class ValidateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BookBean bookBean;
	@EJB
	private ShoppingCartBean shoppingCartBean;
	@EJB
	private ShoppingCartContentBean shoppingCartContentBean;

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

		if (content.isEmpty()) {
			request.setAttribute("error", "Your shopping cart is empty.");
			getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
			return;
		}

		cart.setValidated(true);
		shoppingCartBean.save(cart);

		session.removeAttribute("shoppingCart");

		request.setAttribute("cart_id", cart.getId());
		getServletContext().getRequestDispatcher("/jsp/cart_validated.jsp").forward(request, response);
	}
}
