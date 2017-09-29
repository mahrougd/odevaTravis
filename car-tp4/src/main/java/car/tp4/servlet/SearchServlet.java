package car.tp4.servlet;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.*;

/**
 * servlet for book searching
 *
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private BookBean bookBean;

	/**
	 * method called from get request
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/jsp/search.jsp").forward(request, response);
	}

	/**
	 * method called from post request
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String searchRequest = request.getParameter("search_request");
		String searchMode = request.getParameter("search_mode");
		List<Book> searchResult;

		if (searchRequest != null) {
			if (searchRequest.isEmpty()) {
				searchResult = bookBean.getAllBooks();
			} else if ("title".equals(searchMode))
				searchResult = bookBean.getBookByTitlePortion(searchRequest);
			else if ("author".equals(searchMode))
				searchResult = bookBean.getBooksByAuthor(searchRequest);
			else {
				request.setAttribute("error", "Haha good try, Search Mode Not Selected.");
				getServletContext().getRequestDispatcher("/jsp/search.jsp").forward(request, response);
				return;
			}
		} else {
			request.setAttribute("error", "Haha good try");
			getServletContext().getRequestDispatcher("/jsp/search.jsp").forward(request, response);
			return;
		}

		if (request.getParameter("sort") != null) {
			searchResult = searchResult.subList(0, searchResult.size());
			searchResult.sort(new Comparator<Book>() {

				@Override
				public int compare(Book b1, Book b2) {
					return Book.compareByReleaseDate(b1, b2);
				}

			});
		}
		request.setAttribute("books", searchResult);
		request.getRequestDispatcher("/jsp/list.jsp").forward(request, response);
	}

}
