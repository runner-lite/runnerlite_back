package ru.runnerlite.util;

public enum RunningStatus {
	
	PLANED("Запланирован"),
	END("Выполнен"),
	CHANCELED("Отменён"),
	POSTPONED("Перенесён");
	
	final String status;
	
	RunningStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return status;
	}
}
