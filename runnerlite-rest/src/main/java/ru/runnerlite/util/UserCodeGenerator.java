package ru.runnerlite.util;

/**
 * Генератор уникального идентификатора пользователя
 */
public class UserCodeGenerator {
	
	/**
	 * Генерирует уникальный идентификатор пользователя из ID команды и ID пользователя
	 *
	 * @param userId ID пользователя
	 * @return уникальный идентификатор пользователя
	 */
	public static String generate(long userId) {
		StringBuilder sb = new StringBuilder("RRL");
		sb.append(String.format("%07d", userId));
		System.out.println(sb);
		return sb.toString();
	}
}
