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
//		var id = Integer.parseInt(request.getParameter("nameid"));
		var enumName = request.getParameter("nameid");
		var moEnum = MenuOptions.valueOf(enumName);
		var mo = Menu.getInstance().getMenuOption(moEnum);
//		@SuppressWarnings("unchecked")
//		var menu = (List<MenuOption>) session.getAttribute("menu");
		var cashDesk = session.getAttribute("cdesk");
		cashDesk = cashDesk == null ? new CashDesk() : (CashDesk) cashDesk;
		System.out.println(enumName + " id");
//		MenuOption mo = menu.get(id);
		List<CashDesk> cashDesks = mo.getAllCashDesk();
		session.setAttribute("cashDesks", cashDesks);
		session.setAttribute("mo", mo);
		session.setAttribute("cdesk", cashDesk);
		switch (moEnum) {
			case CREATE -> loadCashDeskOption(request, response, "cashdesk.jsp");
			case LOAD -> loadCashDeskOption(request, response, "load.jsp");
			case DELETE -> loadCashDeskOption(request, response, "delete.jsp");
			case REGISTRY -> loadCashDeskOption(request, response, "registry");
			case QUIT -> loadCashDeskOption(request, response, "");
		}
	}

	private void loadCashDeskOption(HttpServletRequest request, HttpServletResponse response, String jsp) throws ServletException, IOException {
		CashDesk cashDesk = (CashDesk) request.getSession(false).getAttribute("cdesk");
		MenuOption mo = (MenuOption) request.getSession(false).getAttribute("mo");
		System.out.println(cashDesk + " cd");
		System.out.println(mo + " mo");
		request.getRequestDispatcher(jsp).forward(request, response);
	}

}
