package hu.ak_akademia.cash_desk_entry_management;

import java.sql.SQLException;
import java.util.List;

import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.MySQLUtils;

class RegistryDelete extends AbstractRegistry {

	private static final String REG_DELETE = "delete FROM cash_desks.cash_registry where id = ? and id_number = ?";

	@Override
	public String getName() {
		return "bejegyzés törlése";
	}

	@Override
	public void process(List<String> list, CashDesk cashD) {
		try (var con = MySQLUtils.getMySQLConnection(); var del = con.prepareStatement(REG_DELETE)) {
			int registryId = Integer.parseInt(list.get(0));
			del.setInt(1, cashD.getIdNumber());
			del.setInt(2, registryId);
			del.executeUpdate();
		} catch (SQLException e) {
		}
	}

}
