package hu.ak_akademia.cash_desk_main;

import java.time.LocalDateTime;

public class CashDesk {
// Na most akkor ez itt egy komment egyenesen Kínából.
	private String cashDeskName;
	private int idNumber;
	private int limit;
	private LocalDateTime entryTime;

	private CashDesk(Builder builder) {
		this.cashDeskName = builder.cashDeskName;
		this.idNumber = builder.idNumber;
		this.limit = builder.limit;
		this.entryTime = builder.entryTime;
	}

	public CashDesk() {
	}

	public String getCashDeskName() {
		return cashDeskName;
	}

	public void setCashDeskName(String cashDeskName) {
		this.cashDeskName = cashDeskName;
	}

	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}

	@Override
	public String toString() {
//		return "%15s %5d %,15d Ft %25s".formatted(cashDeskName, idNumber, limit, entryTime);
		return "CashDesk [cashDeskName=" + cashDeskName + ", idNumber=" + idNumber + ", limit=" + limit + ", entryTime=" + entryTime + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {

		private String cashDeskName;
		private int idNumber;
		private int limit;
		private LocalDateTime entryTime;

		private Builder() {
		}

		public Builder withCashDeskName(String cashDeskName) {
			this.cashDeskName = cashDeskName;
			return this;
		}

		public Builder withIdNumber(int idNumber) {
			this.idNumber = idNumber;
			return this;
		}

		public Builder withLimit(int limit) {
			this.limit = limit;
			return this;
		}

		public Builder withEntryTime(LocalDateTime entryTime) {
			this.entryTime = entryTime;
			return this;
		}

		public CashDesk build() {
			return new CashDesk(this);
		}
	}

}
