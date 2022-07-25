package hu.ak_akademia.cash_desk.cash_desks;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.MySQLUtils;

class DeleteCashDeskOption extends AbstractMenuOption {

	private String selectCD = "SELECT * FROM cash_desks.cash_desk where name = ?";
	private String deleteCD = "DELETE FROM `cash_desks`.`cash_desk` WHERE (`id` = ?)";
	private String deleteRegistry = "DELETE FROM cash_desks.cash_registry where id = ?";
	private String msg;

	@Override
	public String getName() {
		return "Pénztár törlése";
	}

	private CashDesk chooseCashDeskToDelete(String input, CashDesk cashD, Connection con) throws SQLException {
		select = con.prepareStatement(selectCD);
		select.setString(1, input);
		rs = select.executeQuery();
		if (rs.next()) {
			cashD = CashDesk.builder() //
					.withCashDeskName(rs.getString(2)) //
					.withIdNumber(rs.getInt(1)) //
					.withLimit(rs.getInt(3)) //
					.withEntryTime((LocalDateTime) rs.getObject(4)) //
					.build(); //
		}
		return cashD;
	}

	private void deleteFromTable(CashDesk cashD, String sqlQuerry, Connection con) throws SQLException {
		delete = con.prepareStatement(sqlQuerry);
		delete.setInt(1, cashD.getIdNumber());
		delete.executeUpdate();
	}

	@Override
	public CashDesk setup() {
		return cashDesk;
	}

	@Override
	public void process(List<String> list, CashDesk cashDesk) {
		List<String> message = new ArrayList<>();
		CashDesk cashD = null;
		this.cashDesk = cashDesk;
		try (Connection con = MySQLUtils.getMySQLConnection()) {
			cashD = chooseCashDeskToDelete(list.get(0), cashD, con);
			System.out.println("test");
			if (cashD != null && cashDesk.getIdNumber() != cashD.getIdNumber()) {
				deleteFromTable(cashD, deleteRegistry, con);
				deleteFromTable(cashD, deleteCD, con);
				msg = "sikerült törölni";
				close();
			} else {
				System.out.printf("%n%nA betöltött pénztárat nem lehet törölni%n");
				msg = "nem sikerült törölni";
			}
			message.add(msg);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
