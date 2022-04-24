package ru.runnerlite.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Генератор QR-кодов
 */
public class QRCodeGenerator {
	
	/**
	 * <p>Генерирует QR-код из передаваемой строки string и сохраняет его в файл с именем filename.</p><br>
	 * <i><p>Пример:</p></i><br>
	 * <p><b>CodeGenerator.generate("AA00BB22", "qrs/QR.png", 300)</b></p>
	 *
	 * @param string   - строка, которую требуется закодировать
	 * @param filename - путь к директории и имя файла, в который сохранится QR-код
	 * @param size     - длина стороны QR-кода в пикселях
	 */
	public static void generate(String string, String filename, int size) {
		try {
			BitMatrix matrix = new MultiFormatWriter().encode(string, BarcodeFormat.QR_CODE, size, size);
			MatrixToImageWriter.writeToPath(matrix, filename.substring(filename.lastIndexOf('.') + 1),
				Paths.get(filename));
		} catch (IOException | WriterException e) {
			e.printStackTrace();
		}
	}
}
