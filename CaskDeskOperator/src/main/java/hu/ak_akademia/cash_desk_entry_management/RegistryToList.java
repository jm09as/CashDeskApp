package hu.ak_akademia.cash_desk_entry_management;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.Entry;
import hu.ak_akademia.cash_desk_main.MySQLUtils;

class RegistryToList extends AbstractRegistry {

	private static final String SELECTOR = "SELECT * FROM cash_desks.cash_registry where id = ?";

	@Override
	public String getName() {
		return "bejegyzések listázása";
	}

	@Override
	public void process(List<String> list, CashDesk cashD) {
		try (var con = MySQLUtils.getMySQLConnection() //
				; var select = con.prepareStatement(SELECTOR) //
				; var rs = select.executeQuery()) { //
			select.setInt(1, cashD.getIdNumber());
			getAllEntry(rs, cashD);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private List<Entry> getAllEntry(ResultSet rs, CashDesk cashD) throws SQLException {
		List<Entry> entryList = new ArrayList<>();
		while (rs.next()) {
			entryList.add(Entry.builder() //
					.withId(rs.getInt(1)) //
					.withTimeEntry((LocalDateTime) rs.getObject(2)) //
					.withNameEntry(rs.getString(3)) //
					.withSum(rs.getInt(4)) //
					.withCashDeskId(cashD) //
					.build()); //
		}
		return entryList;
	}

}
