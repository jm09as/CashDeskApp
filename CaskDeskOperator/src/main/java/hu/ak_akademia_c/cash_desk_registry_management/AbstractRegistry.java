package hu.ak_akademia_c.cash_desk_registry_management;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.MenuOption;
import hu.ak_akademia.cash_desk_main.MySQLUtils;

abstract class AbstractRegistry implements MenuOption {

	PreparedStatement select;

	@Override
	public CashDesk setup() {
		return null;
	}

	public int countCashDeskSum(CashDesk cashDesk) throws SQLException {
		try (var con = MySQLUtils.getMySQLConnection()) {
			select = con.prepareStatement("SELECT sum(sum) FROM cash_desks.cash_registry where id = ?");
			select.setInt(1, cashDesk.getIdNumber());
			var rs = select.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			select.close();
			rs.close();
		}
		return -1;
	}

	@Override
	public List<CashDesk> getAllCashDesk() {
		return null;
	}

}
