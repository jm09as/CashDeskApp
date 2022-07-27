package hu.ak_akademia.cash_desk.cash_desks;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.MySQLUtils;

class CreateNewCashDeskOption extends AbstractMenuOption {

	private String newCaskDesk = "INSERT INTO cash_desks.cash_desk (`name`, `limit`, `pay_time`) VALUES (?, ?, ?)";
	private String msg;

	@Override
	public String getName() {
		return "Új pénztár felvétele";
	}

	public void process(List<String> list, CashDesk cashD) {
		cashDesk = cashD;
		try (var con = MySQLUtils.getMySQLConnection() //
				; var insert = con.prepareStatement(newCaskDesk)) {
			msg = "%s".formatted(insertNewCashDesk(list, insert) == 0 ? "nem sikerült" : "sikerült");
			close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.printf("%nHiba lépet fel az adatbevitel közben!%n" + e.getMessage());
		}
	}

	private int insertNewCashDesk(List<String> cashDeskData, PreparedStatement insert) throws SQLException {
		LocalDate setDate = LocalDate.of(Integer.parseInt(cashDeskData.get(2)), //
				Integer.parseInt(cashDeskData.get(3)), //
				Integer.parseInt(cashDeskData.get(4)));
		insert.setString(1, cashDeskData.get(0));
		insert.setInt(2, Integer.parseInt(cashDeskData.get(1)));
		insert.setString(3, LocalDateTime.of(setDate, LocalTime.now()).toString());
		return insert.executeUpdate();
	}

	@Override
	public CashDesk setup() {
		return cashDesk;
	}

	public String getMsg() {
		return msg;
	}

}
