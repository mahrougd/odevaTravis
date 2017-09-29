package car.tp4.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.*;

/**
 * Servlet for changing a book stock
 *
 */
@WebServlet("/add_stock")
public class AddStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	protected BookBean bookBean;

	/**
	 * method called from post request
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String idS = checkParamIsNotNull(request, "id", "Book id is not given");
			long id = checkParamIsInt(idS, "Book id is not valid integer");
			String stockS = checkParamIsNotNull(request, "nb", "Stock is not given");
			int stock = checkParamIsInt(stockS, "Stock number is not integer");

			Book b = bookBean.getById(id);

			if (b == null) {
				error(request, response, "Book with id " + id + " not exist.");
				return;
			}

			b.addStock(stock);
			bookBean.save(b);

			response.sendRedirect("/list");
		} catch (RuntimeException e) {
			error(request, response, e.getMessage());
		}

	}

	/**
	 * see same method in addbookservlet
	 */
	protected void error(HttpServletRequest request, HttpServletResponse response, String messageError)
			throws ServletException, IOException {
		request.setAttribute("error", messageError);
		getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
		return;
	}

	/**
	 * see same method in addstockservlet
	 */
	protected String checkParamIsNotNull(HttpServletRequest request, String parameter, String messageError) {

		String param = request.getParameter(parameter);

		if (param == null) {
			throw new RuntimeException(messageError);
		}

		return param;
	}

	/**
	 * see same method in addstockservlet
	 */
	protected int checkParamIsInt(String integer, String messageError) {

		int res = 0;
		try {
			res = Integer.parseInt(integer);
		} catch (NumberFormatException e) {
			throw new RuntimeException(messageError);
		}

		return res;
	}

}
