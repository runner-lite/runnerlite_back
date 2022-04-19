package ru.runnerlite.util;

public enum Gender {
	
	MALE("Мужской"),
	FEMALE("Женский");
	
	final String description;
	
	Gender(String description) {
		this.description = description;
	}
}
