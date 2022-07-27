package hu.ak_akademia.cash_desk_entry_management;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.Entry;
import hu.ak_akademia.cash_desk_main.EntryOption;
import hu.ak_akademia.cash_desk_main.MySQLUtils;

abstract class AbstractRegistry implements EntryOption {

	PreparedStatement select;
	String msg;
	int count;
	List<Entry> listEntry;
	private static final String SELECTOR = "SELECT * FROM cash_desks.cash_registry where id = ?";


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
	public void run(CashDesk cashD) {
		try (var con = MySQLUtils.getMySQLConnection() //
				; var select = con.prepareStatement(SELECTOR)) { //
			listEntry = getAllEntry(select, cashD);
			msg = limitTester(cashD.getLimit(), countCashDeskSum(cashD));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	List<Entry> getAllEntry(PreparedStatement select, CashDesk cashD) throws SQLException {
		List<Entry> entryList = new ArrayList<>();
		select.setInt(1, cashD.getIdNumber());
		try (var rs = select.executeQuery()) {
			while (rs.next()) {
				entryList.add(Entry.builder() //
						.withId(rs.getInt(5)) //
						.withTimeEntry((LocalDateTime) rs.getObject(2)) //
						.withNameEntry(rs.getString(3)) //
						.withSum(rs.getInt(4)) //
						.withCashDeskId(cashD) //
						.build()); //
			}
		}
		return entryList;
	}

	@Override
	public String limitMessage() {
		return msg;
	}

	@Override
	public List<Entry> getAllEntry() {
		return listEntry;
	}
}
