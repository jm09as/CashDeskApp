package hu.ak_akademia.cash_desk.servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.MenuOptions;

@WebServlet("/setup")
public class Setup extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		session = request.getSession();
		session.setAttribute("menu", MenuOptions.values());
		var cashDesk = (CashDesk) session.getAttribute("cdesk");
		System.out.println(cashDesk);
		session.setAttribute("cdesk", cashDesk == null ? cashDesk() : cashDesk);
		request.getRequestDispatcher("setup.jsp").forward(request, response);
	}

	CashDesk cashDesk() {
		return CashDesk.builder() //
				.withCashDeskName("Nincs megadva pénztár") //
				.withLimit(0) //
				.withIdNumber(Integer.MAX_VALUE) //
				.withEntryTime(LocalDateTime.MAX) //
				.build();
	}

}
