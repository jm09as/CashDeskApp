package hu.ak_akademia.cash_desk_main;

import java.time.LocalDateTime;

public class Entry {

	private int id;
	private LocalDateTime timeEntry;
	private String nameEntry;
	private int sum;
	private int cashDeskId;

	private Entry(Builder builder) {
		this.id = builder.id;
		this.timeEntry = builder.timeEntry;
		this.nameEntry = builder.nameEntry;
		this.sum = builder.sum;
		this.cashDeskId = builder.cashDeskId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getTimeEntry() {
		return timeEntry;
	}

	public void setTimeEntry(LocalDateTime timeEntry) {
		this.timeEntry = timeEntry;
	}

	public String getNameEntry() {
		return nameEntry;
	}

	public void setNameEntry(String nameEntry) {
		this.nameEntry = nameEntry;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getCashDeskId() {
		return cashDeskId;
	}

	public void setCashDeskId(CashDesk cashDesk) {
		this.cashDeskId = cashDesk.getIdNumber();
	}

	@Override
	public String toString() {
		return "Entry [id=" + id + ", timeEntry=" + timeEntry + ", nameEntry=" + nameEntry + ", sum=" + sum + ", cashDeskId=" + cashDeskId + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private int id;
		private LocalDateTime timeEntry;
		private String nameEntry;
		private int sum;
		private int cashDeskId;

		private Builder() {
		}

		public Builder withId(int id) {
			this.id = id;
			return this;
		}

		public Builder withTimeEntry(LocalDateTime timeEntry) {
			this.timeEntry = timeEntry;
			return this;
		}

		public Builder withNameEntry(String nameEntry) {
			this.nameEntry = nameEntry;
			return this;
		}

		public Builder withSum(int sum) {
			this.sum = sum;
			return this;
		}

		public Builder withCashDeskId(CashDesk cashDesk) {
			this.cashDeskId = cashDesk.getIdNumber();
			return this;
		}

		public Entry build() {
			return new Entry(this);
		}
	}

}
