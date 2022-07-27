package hu.ak_akademia.cash_desk_entry_management;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.MySQLUtils;

class MakeNewRegistry extends AbstractRegistry {

	private String insert = "INSERT INTO `cash_desks`.`cash_registry` (`id`, `time_entry`, `name_entry`, `sum`) VALUES (?, ?, ?, ?)";
	private String refreshTime = "UPDATE `cash_desks`.`cash_desk` SET `pay_time` = ? WHERE (`id` = ?);";

	@Override
	public String getName() {
		return "új bejegyzés hozzáadása";
	}

	@Override
	public void process(List<String> list, CashDesk cashD) {
		try (var con = MySQLUtils.getMySQLConnection(); var preSta = con.prepareStatement(insert);) {
			preSta.setInt(1, cashD.getIdNumber());
			preSta.setString(2, LocalDateTime.now().toString());
			preSta.setString(3, list.get(0));
			int i = Integer.parseInt(list.get(1));
			preSta.setInt(4, i);
			preSta.executeUpdate();
			updateCashDeskTime(con, cashD);
			msg = limitTester(cashD.getLimit(), countCashDeskSum(cashD));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void updateCashDeskTime(Connection con, CashDesk cashDesk) throws SQLException {
		try (var timeRefresh = con.prepareStatement(refreshTime)) {
			cashDesk.setEntryTime(LocalDateTime.now());
			timeRefresh.setObject(1, cashDesk.getEntryTime());
			timeRefresh.setInt(2, cashDesk.getIdNumber());
			timeRefresh.executeUpdate();
		}
	}

}
