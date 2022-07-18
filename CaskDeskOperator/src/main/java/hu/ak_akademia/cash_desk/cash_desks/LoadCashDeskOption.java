package hu.ak_akademia.cash_desk.cash_desks;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import hu.ak_akademia.cash_desk_main.CashDesk;
import hu.ak_akademia.cash_desk_main.MenuOptions;
import hu.ak_akademia.cash_desk_main.MySQLUtils;

class LoadCashDeskOption extends AbstractMenuOption {

	private static final String SELECTOR = "SELECT * FROM cash_desks.cash_desk where name = ?";

	@Override
	public String getName() {
		return "Pénztár betöltése < %s > ".formatted(cashDesk == null ? "nincs kijelölve pénztár" //
				: "%s (%d)".formatted(cashDesk.getCashDeskName(), cashDesk.getIdNumber()));
	}

	@Override
	public List<String> process(List<String> list, CashDesk cashD) {
		cashDesk = null;
		try (Connection con = MySQLUtils.getMySQLConnection()) {
			select = con.prepareStatement(SELECTOR);
			select.setString(1, list.get(0));
			rs = select.executeQuery();
			if (rs.next()) {
				cashD = CashDesk.builder() //
						.withCashDeskName(rs.getString(2)) //
						.withIdNumber(rs.getInt(1)) //
						.withLimit(rs.getInt(3)) //
						.withEntryTime((LocalDateTime) rs.getObject(4)) //
						.build();//
			}
			if (cashD == null) {
				return List.of("Nincs ilyen nevű pénztár!");
			}
			cashDesk = cashD;
			limitTester(cashDesk.getLimit(), countCashDeskSum(cashDesk));
			System.out.println(cashDesk.getIdNumber());
			close();
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return cashDesk == null ? List.of("Nincs ilyen nevű pénztár!") : printAllCashDesk(getAllCashDesk());
	}

	private List<String> printAllCashDesk(List<CashDesk> allCashDesks) {
		List<String> result = new ArrayList<>();
		result.add("%n %15s %5s %15s %25s %n".formatted("NAME", "ID", "LIMIT", "BEJEGYZÉS IDEJE"));
		for (var cd : allCashDesks) {
			result.add(" %15s %5d %,15d Ft %25s %n".formatted(cd.getCashDeskName(), cd.getIdNumber(), cd.getLimit(), cd.getEntryTime()));
		}
		return result;
	}

	@Override
	public CashDesk setup() {
		return cashDesk;
	}

	@Override
	public void close() throws SQLException {
		select.close();
	}

	@Override
	public int getId() {
		return MenuOptions.LOAD.ordinal();
	}

}