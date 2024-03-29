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

@WebServlet("/load")
public class Load extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> list = new ArrayList<>();
		list.add(request.getParameter("name"));
		var session = request.getSession(false);
		var mo = (MenuOption) session.getAttribute("mo");
		var cashDesk = (CashDesk) session.getAttribute("cdesk");
		mo.process(list, cashDesk);
		cashDesk = mo.setup();
		session.setAttribute("cdesk", cashDesk);
		request.getRequestDispatcher("setup").forward(request, response);
	}

}
