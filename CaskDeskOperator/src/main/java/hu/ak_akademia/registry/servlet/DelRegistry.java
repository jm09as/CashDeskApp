package hu.ak_akademia.registry.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.EntryOption;

@WebServlet("/delregistry")

public class DelRegistry extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var session = request.getSession(false);
		var mo = (EntryOption) session.getAttribute("mo");
		var cashDesk = (CashDesk) session.getAttribute("cdesk");
		List<String> list = List.of(request.getParameter("delid"));
		mo.process(list, cashDesk);
		mo.run(cashDesk);
		session.setAttribute("entrylist", mo.getAllEntry());
		request.getRequestDispatcher("registry.jsp").forward(request, response);

	}

}
