package hu.ak_akademia_c.cash_desk_registry_management;

import java.sql.SQLException;
import java.util.ArrayList;
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
	public List<String> process(List<String> list, CashDesk cashD) {
		List<String> msg = new ArrayList<>();
		try (var con = MySQLUtils.getMySQLConnection(); var del = con.prepareStatement(REG_DELETE)) {
			int registryId = Integer.parseInt(list.get(0));
			del.setInt(1, cashD.getIdNumber());
			del.setInt(2, registryId);
			int result = del.executeUpdate();
			msg.add("%n%s törölni a %s pénztár egyik bejegyzését!%n" //
					.formatted(result != 0 ? "Sikerült" : "Nem sikerült", cashD.getCashDeskName()));
		} catch (SQLException e) {
		}
		return msg;
	}

	@Override
	public int getId() {
		return 1;
	}

}
