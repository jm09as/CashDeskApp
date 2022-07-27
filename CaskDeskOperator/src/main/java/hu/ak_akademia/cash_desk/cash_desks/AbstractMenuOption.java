package hu.ak_akademia.cash_desk.cash_desks;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.MenuOption;
import hu.ak_akademia.cash_desk_main.MySQLUtils;

abstract class AbstractMenuOption implements MenuOption, AutoCloseable {

	public PreparedStatement select;
	public PreparedStatement delete;
	public PreparedStatement insert;
	public ResultSet rs;
	public CashDesk cashDesk;
	public String msg;

	@Override
	public void close() throws SQLException {
		select.close();
		delete.close();
		rs.close();
	}

	public int countCashDeskSum(CashDesk cashDesk) throws SQLException {
		try (var con = MySQLUtils.getMySQLConnection()) {
			select = con.prepareStatement("SELECT sum(sum) FROM cash_desks.cash_registry where id = ?");
			select.setInt(1, cashDesk.getIdNumber());
			var rs = select.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		}
		return -1;
	}

	public String limitMessage() {
		return msg;
	}
}
