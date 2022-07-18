package hu.ak_akademia.registry.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.ak_akademia.cash_desk_main.MenuOption;
import hu.ak_akademia_c.cash_desk_registry_management.MenuRegistryOptionImpl;

@WebServlet("/registry")

public class Registry extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var session = request.getSession(false);
		session.setAttribute("regmenu", regMenu());
		request.getRequestDispatcher("registry.jsp").forward(request, response);
	}

	MenuOption[] regMenu() {
		return MenuRegistryOptionImpl.getInstance().getAllRegistryOptions();
	}

}
