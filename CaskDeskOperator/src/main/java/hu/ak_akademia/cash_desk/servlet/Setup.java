package hu.ak_akademia.cash_desk.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hu.ak_akademia.cash_desk.cash_desks.Menu;
import hu.ak_akademia.cash_desk_entry_management.MenuRegistryOptionImpl;
import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.MenuOptions;

@WebServlet("/setup")
public class Setup extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		session = request.getSession();
		session.setAttribute("menuenum", MenuOptions.values());
		var moEnum = (MenuOptions) session.getAttribute("moenum");
		var cashDesk = (CashDesk) session.getAttribute("cdesk");
		session.setAttribute("allcashdesk", getAllCD());
		session.setAttribute("moenum", moEnum = moEnum == null ? MenuOptions.QUIT : moEnum);
		session.setAttribute("ordinal", moEnum.ordinal());
		System.out.println(moEnum + " moEnum setup.java");
		session.setAttribute("cdesk", cashDesk = cashDesk == null ? loadLastCashDesk(getAllCD()) : cashDesk);
		session.setAttribute("msg", limitMsg(cashDesk));
		System.out.println(cashDesk + " cashDesk setup.java");
		request.getRequestDispatcher("setup.jsp").forward(request, response);
	}

	List<CashDesk> getAllCD() {
		return Menu.getInstance().getAllCashDesk();
	}

	CashDesk loadLastCashDesk(List<CashDesk> list) {
		return Menu.getInstance().getLastModification(list);
	}

	String limitMsg(CashDesk cashD) {
		var entryOption = MenuRegistryOptionImpl.getInstance().getRegistry(2);
		entryOption.run(cashD);
		return entryOption.limitMessage();
	}

}
