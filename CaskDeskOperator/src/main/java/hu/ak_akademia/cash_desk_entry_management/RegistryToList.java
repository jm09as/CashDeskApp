package hu.ak_akademia.cash_desk_entry_management;

import java.util.List;

import hu.ak_akademia.cash_desk_main.CashDesk;

class RegistryToList extends AbstractRegistry {

	@Override
	public String getName() {
		return "bejegyzések listázása";
	}

	@Override
	public void process(List<String> list, CashDesk cashD) {
		run(cashD);
	}

}
