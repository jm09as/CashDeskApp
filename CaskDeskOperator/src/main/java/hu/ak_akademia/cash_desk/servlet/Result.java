package hu.ak_akademia.cash_desk.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.MenuOption;

@WebServlet("/result")
public class Result extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var session = request.getSession(false);
		var mo = (MenuOption) session.getAttribute("mo");
		var cashDesk = (CashDesk) session.getAttribute("cdesk");
		List<String> list = new ArrayList<>();
		list.add(request.getParameter("name")); //
		list.add(request.getParameter("limit")); //
		list.add(request.getParameter("year")); //
		list.add(request.getParameter("month")); //
		list.add(request.getParameter("day")); //
		List<String> result = mo.process(list, cashDesk);
		session.setAttribute("result", result);
		session.setAttribute("cdesk", cashDesk);
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

//	String cashDesk(MenuOption mo) {
//		return mo.setup() == null ? "nincs kijelölve pénztár" : mo.setup().getCashDeskName();
//	}

}
