package car.tp4.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import car.tp4.entity.*;

/**
 * servlet for adding book to the cart
 * 
 */
@WebServlet("/add_cart")
public class AddCartServlet extends HttpServlet {
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
		long bookId = Long.parseLong(request.getParameter("id"));
		Book b = bookBean.getById(bookId);
		if (b == null) {
			request.setAttribute("error", "This book does not exist.");
			getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
			return;
		}
		if (b.getStock() == 0) {
			request.setAttribute("error", "There is no stock for this book");
			getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
			return;
		}

		HttpSession session = request.getSession(true);
		ShoppingCart cart;
		Object cartIdObj = session.getAttribute("shoppingCart");
		if (cartIdObj != null) {
			cart = shoppingCartBean.getById((Long) cartIdObj);
		} else {
			cart = shoppingCartBean.createCart();
			session.setAttribute("shoppingCart", cart.getId());
		}

		ShoppingCartContent cartContent = null;
		try {
			cartContent = shoppingCartContentBean.getFromShoppingCartAndBookId(cart.getId(), bookId);
		} catch (EJBException e) {
		}
		if (cartContent == null) {
			cartContent = new ShoppingCartContent(cart.getId(), bookId, 1);
			shoppingCartContentBean.addShoppingCartContent(cartContent);
		} else {
			cartContent.setQuantity(cartContent.getQuantity() + 1);
			shoppingCartContentBean.save(cartContent);
		}
		b.removeStock(1);
		bookBean.save(b);

		response.sendRedirect("/cart");
	}
}
