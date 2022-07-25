package hu.ak_akademia.registry.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.MenuOption;

@WebServlet("/delregistry")

public class DelRegistry extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var session = request.getSession(false);
		var mo = (MenuOption) session.getAttribute("mo");
		var cashDesk = (CashDesk) session.getAttribute("cdesk");
		List<String> list = List.of(request.getParameter("id"));
		mo.process(list, cashDesk);
//		session.setAttribute("result", result);
		request.getRequestDispatcher("result.jsp").forward(request, response);

	}

}
