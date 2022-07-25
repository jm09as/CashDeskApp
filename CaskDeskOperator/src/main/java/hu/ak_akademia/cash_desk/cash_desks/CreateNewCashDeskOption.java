package hu.ak_akademia.cash_desk.cash_desks;

import java.sql.Connection;
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
		try (Connection con = MySQLUtils.getMySQLConnection()) {
			insert = con.prepareStatement(newCaskDesk);
			msg = "%s".formatted(setStatement(list) == 0 ? "nem sikerült" : "sikerült");
			close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.printf("%nHiba lépet fel az adatbevitel közben!%n" + e.getMessage());
		}
	}

	private int setStatement(List<String> cashDeskInfos) throws SQLException {
		LocalDate setDate = LocalDate.of(Integer.parseInt(cashDeskInfos.get(2)), //
				Integer.parseInt(cashDeskInfos.get(3)), //
				Integer.parseInt(cashDeskInfos.get(4)));
		insert.setString(1, cashDeskInfos.get(0));
		insert.setInt(2, Integer.parseInt(cashDeskInfos.get(1)));
		insert.setString(3, LocalDateTime.of(setDate, LocalTime.now()).toString());
		return insert.executeUpdate();
	}

//	private List<String> printAllCashDesk(List<CashDesk> allCashDesks) {
//		List<String> result = new ArrayList<>();
//		result.add("%n %15s %5s %15s %25s %n".formatted("NAME", "ID", "LIMIT", "BEJEGYZÉS IDEJE"));
//		for (var cd : allCashDesks) {
//			result.add(" %15s %5d %,15d Ft %25s" //
//					.formatted(cd.getCashDeskName(), cd.getIdNumber(), cd.getLimit(), cd.getEntryTime()));
//		}
//		return result;
//	}

	@Override
	public void close() throws SQLException {
		insert.close();
	}

	@Override
	public CashDesk setup() {
		return cashDesk;
	}

	public String getMsg() {
		return msg;
	}

}
