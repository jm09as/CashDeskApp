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

@WebServlet("/delete")
public class Delete extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var session = request.getSession(false);
		var mo = (MenuOption) session.getAttribute("mo");
		CashDesk cashDesk = (CashDesk) session.getAttribute("cdesk");
		System.out.println(cashDesk);
		System.out.println(mo);
		List<String> list = new ArrayList<>();
		list.add(request.getParameter("name"));
		mo.process(list, cashDesk);
		request.getRequestDispatcher("setup").forward(request, response);
	}

}
