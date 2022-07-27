package hu.ak_akademia.registry.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.ak_akademia.cash_desk_entry_management.MenuRegistryOptionImpl;
import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.EntryOption;
import hu.ak_akademia.cash_desk_main.MenuOption;

@WebServlet("/selectoption")

public class SelectOption extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var selectId = Integer.parseInt(request.getParameter("registryid"));
		var mo = MenuRegistryOptionImpl.getInstance().getRegistry(selectId);
		request.getSession(false).setAttribute("registryid", selectId);
		System.out.println(selectId);
		switch (selectId) {
			case 0 -> makeNewRegistry(request, response, mo, "registry.jsp");
			case 1 -> deleteRegistry(request, response, mo, "registry.jsp");
			case 2 -> listRegistry(request, response, mo, "registry.jsp");
			case 3 -> backToCashDeskMenu(request, response, mo, "setup");

		}
	}

	private void backToCashDeskMenu(HttpServletRequest request, HttpServletResponse response, MenuOption mo, String jsp)
			throws ServletException, IOException {
		System.out.println(mo.getName());
		request.getRequestDispatcher(jsp).forward(request, response);
	}

	private void deleteRegistry(HttpServletRequest request, HttpServletResponse response, EntryOption mo, String jsp)
			throws ServletException, IOException {
		var session = request.getSession(false);
		var cashDesk = (CashDesk) session.getAttribute("cdesk");
		session.setAttribute("mo", mo);
		mo.run(cashDesk);
		session.setAttribute("entrylist", mo.getAllEntry());
		System.out.println(mo.getName());
		request.getRequestDispatcher(jsp).forward(request, response);
	}

	private void makeNewRegistry(HttpServletRequest request, HttpServletResponse response, MenuOption mo, String jsp)
			throws ServletException, IOException {
		request.getSession(false).setAttribute("mo", mo);
		System.out.println(mo.getName());
		request.getRequestDispatcher(jsp).forward(request, response);

	}

	private void listRegistry(HttpServletRequest request, HttpServletResponse response, EntryOption mo, String jsp)
			throws ServletException, IOException {
		var session = request.getSession(false);
		session.setAttribute("mo", mo);
		System.out.println(mo.getName());
		var cashDesk = (CashDesk) session.getAttribute("cdesk");
		mo.process(List.of(" "), cashDesk);
		session.setAttribute("entrylist", mo.getAllEntry());
		request.getRequestDispatcher(jsp).forward(request, response);
	}

}
