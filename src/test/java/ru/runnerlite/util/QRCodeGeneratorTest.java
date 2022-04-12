package ru.runnerlite.util;

import org.junit.Test;
import java.io.File;

import static org.junit.Assert.*;

public class QRCodeGeneratorTest {
	
	@Test
	public void shouldCreateFileWithQRCode() {
		StringBuilder sb = new StringBuilder();
		sb.append("qrs")
			.append(File.separator)
			.append("testFileName_")
			.append(System.currentTimeMillis())
			.append(".png");
		
		String filename = sb.toString();
		File file = new File(filename);
		assertFalse(file.exists());
		
		QRCodeGenerator.generate("String for test", filename, 300);
		file = new File(filename);
		assertTrue(file.exists());
		file.delete();
	}
	
	@Test
	public void shouldReturnRRL0123456() {
		long userId = 123456;
		
		assertEquals("RRL0123456", UserCodeGenerator.generate(userId));
	}
}
