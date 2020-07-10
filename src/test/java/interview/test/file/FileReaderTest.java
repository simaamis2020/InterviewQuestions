package interview.test.file;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inteview.test.file.FileManager;

class FileReaderTest {
	FileManager fileManager = null;
	File file = null;

	@BeforeEach
	void setUp() throws Exception {
		fileManager = new FileManager();
		// create empty file on a classpath
		//if file exists, delete it and create empty one.
		file = new File("output.txt");
		if(file.exists()) {
			file.delete();
		}
		file.createNewFile();
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	/**
	 * simple to create output file. additional tests can be created to read from
	 * output file and compare actual lines values to desired outcome.
	 */
	@Test
	void testProcessFile() {
		fileManager.processFile("input.txt", "output.txt");
		assertTrue(file.exists());

	}

	/**
	 * Simple test to read and verify records of created file.
	 */
	@Test
	void readRecordsOfCreatedFile() {
		FileInputStream inputStream = null;
		Scanner sc = null;

		try {
			inputStream = new FileInputStream("output.txt");

			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {

				String line = sc.nextLine();
				assertEquals(line.trim(), "08000AA7: 200.222");
				assertEquals(line.trim(), "08000AA8: 35.800");
				assertEquals(line.trim(), "08000AA9: 19.130");
				assertEquals(line.trim(), "08000AB1: 20.653");

			}

		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			if (sc != null) {
				sc.close();
			}

		}
	}

}
