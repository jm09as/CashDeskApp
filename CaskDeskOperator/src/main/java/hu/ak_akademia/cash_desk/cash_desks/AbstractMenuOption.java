package hu.ak_akademia.cash_desk.cash_desks;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.MenuOption;
import hu.ak_akademia.cash_desk_main.MySQLUtils;

abstract class AbstractMenuOption implements MenuOption, AutoCloseable {

	private static final String SELECT_ALL = "SELECT * FROM cash_desks.cash_desk";
	public PreparedStatement select;
	public PreparedStatement delete;
	public PreparedStatement insert;
	public ResultSet rs;
	public CashDesk cashDesk;

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

	public List<CashDesk> getAllCashDesk() {
		List<CashDesk> list = new ArrayList<>();
		try (PreparedStatement ps = MySQLUtils.getMySQLConnection().prepareStatement(SELECT_ALL)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(CashDesk.builder() //
						.withCashDeskName(rs.getString(2)) //
						.withIdNumber(rs.getInt(1)) //
						.withLimit(rs.getInt(3)) //
						.withEntryTime((LocalDateTime) rs.getObject(4)) //
						.build());
			}
		} catch (Exception e) {
		}
		return list;
	}

	public CashDesk getLastModification(List<CashDesk> list) {
		Comparator<LocalDateTime> ldt = LocalDateTime::compareTo;
		return list.stream().sorted((cd1, cd2) -> ldt.compare(cd1.getEntryTime(), cd2.getEntryTime())) //
				.toList().get(list.size() - 1);
	}
}
