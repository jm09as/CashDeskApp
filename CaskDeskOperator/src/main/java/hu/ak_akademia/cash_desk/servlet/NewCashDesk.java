package hu.ak_akademia.cash_desk.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.ak_akademia.cash_desk.cash_desks.Menu;
import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.MenuOption;
import hu.ak_akademia.cash_desk_main.MenuOptions;

@WebServlet("/cashdesk")
public class NewCashDesk extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var session = request.getSession(false);
		var enumName = request.getParameter("moenum");
		System.out.println(enumName + "enumName cashdesk.java");
		var moEnum = MenuOptions.valueOf(enumName);
		var mo = Menu.getInstance().getMenuOption(moEnum);
		var cashDesk = session.getAttribute("cdesk");
		cashDesk = cashDesk == null ? new CashDesk() : (CashDesk) cashDesk;
		List<CashDesk> cashDesks = mo.getAllCashDesk();
		session.setAttribute("cashDesks", cashDesks);
		session.setAttribute("mo", mo);
		session.setAttribute("cdesk", cashDesk);
		session.setAttribute("moenum", moEnum);
		switch (moEnum) {
			case CREATE -> loadCashDeskOption(request, response, "setup");
			case LOAD -> loadCashDeskOption(request, response, "setup");
			case DELETE -> loadCashDeskOption(request, response, "setup");
			case REGISTRY -> loadCashDeskOption(request, response, "registry");
			case QUIT -> loadCashDeskOption(request, response, "");
		}
	}

	private void loadCashDeskOption(HttpServletRequest request, HttpServletResponse response, String jsp) throws ServletException, IOException {
		CashDesk cashDesk = (CashDesk) request.getSession(false).getAttribute("cdesk");
		MenuOption mo = (MenuOption) request.getSession(false).getAttribute("mo");
		System.out.println(cashDesk + " cd cashdesk.java");
		System.out.println(mo + " mo cashdesk.java");
		request.getRequestDispatcher(jsp).forward(request, response);
	}

}
