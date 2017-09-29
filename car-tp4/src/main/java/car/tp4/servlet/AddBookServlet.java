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
 * Servlet for book adding
 *
 */
@WebServlet("/add")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	protected BookBean bookBean;

	/**
	 * method called from get request.
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/jsp/add.jsp").forward(request, response);
	}

	/**
	 * method called from the post request.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String author = checkParamIsNotNull(request, "author", "Author's name is not given");
			String title = checkParamIsNotNull(request, "title", "Title's name is not given");
			String yearS = checkParamIsNotNull(request, "year", "Year is not given");
			int year = checkParamIsInt(yearS, "Year number is not integer");
			String stockS = checkParamIsNotNull(request, "stock", "Stock is not given");
			int stock = checkParamIsInt(stockS, "Stock number is not integer");

			bookBean.save(new Book(author, title, year, stock));

			response.sendRedirect("/list");
		} catch (RuntimeException e) {
			error(request, response, e.getMessage());
		}

	}

	/**
	 * error page
	 * 
	 * @param messageError
	 *            errormessage displayed on the error page
	 */
	protected void error(HttpServletRequest request, HttpServletResponse response, String messageError)
			throws ServletException, IOException {
		request.setAttribute("error", messageError);
		getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
		return;
	}

	/**
	 * check if a parameter is not null
	 * 
	 * @param messageError
	 *            error message if the parameter is not valid.
	 * @throws RuntimeException
	 *             if the parameter is null.
	 */
	protected String checkParamIsNotNull(HttpServletRequest request, String parameter, String messageError) {

		String param = request.getParameter(parameter);

		if (param == null) {
			throw new RuntimeException(messageError);
		}

		return param;
	}

	/**
	 * check if a string is convertible to int
	 * 
	 * @param integer
	 *            the string
	 * @param messageError
	 *            if the parameter is not valid.
	 * @throws RuntimeException
	 *             if the parameter cannot be converted
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
