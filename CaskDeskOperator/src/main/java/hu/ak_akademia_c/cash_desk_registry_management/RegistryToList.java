package hu.ak_akademia_c.cash_desk_registry_management;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.MySQLUtils;

class RegistryToList extends AbstractRegistry {

	private static final String SELECTOR = "SELECT * FROM cash_desks.cash_registry where id = ?";

	@Override
	public String getName() {
		return "bejegyzések listázása";
	}

	@Override
	public List<String> process(List<String> list, CashDesk cashD) {
		List<String> result = null;
		try (var con = MySQLUtils.getMySQLConnection(); var select = con.prepareStatement(SELECTOR);) {
			select.setInt(1, cashD.getIdNumber());
			var rs = select.executeQuery();
			result = printAllRegistry(rs);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	private List<String> printAllRegistry(ResultSet rs) throws SQLException {
		List<String> printRow = new ArrayList<>();
		printRow.add("%n%4s %20s %20s %12s %22s%n".formatted("ID", "BEJEGYZÉS IDEJE", "BEJEGYZÉS NEVE", "ÖSSZEG", "BEJEGYZÉS ID"));
		while (rs.next()) {
			printRow.add("%4d %20s %20s %,12d Ft %22d%n".formatted( //
					rs.getInt(1), //
					rs.getObject(2).toString(), //
					rs.getString(3), //
					rs.getInt(4), //
					rs.getInt(5)));
		}
		return printRow;
	}


	@Override
	public int getId() {
		return 2;
	}

}
