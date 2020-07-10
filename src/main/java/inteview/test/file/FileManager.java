package inteview.test.file;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class to manage file read and writes
 * @author anna simonenko
 * 
 *
 */
public class FileManager {

	
    /**
     * method to read file one line at a time, process the data, and write record to output file.
     * 
     * @param inputPath
     * @param outputPath
     */
	public void processFile(String inputPath, String outputPath) {
		FileInputStream inputStream = null;

		FileWriter fw = null;

		Scanner sc = null;
		String latestCusip = null;
		String latestPrice = null;
		try {
			inputStream = new FileInputStream(inputPath);
			fw = new FileWriter(outputPath);
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {

				String line = sc.nextLine();
				// skip empty lines
				if (line.length() == 0) {
					continue;
				}
				if (isCusip(line)) {
					writeToFile(fw, latestCusip, latestPrice);
					latestCusip = line.trim();
				} else {

					latestPrice = line.trim();
				}

			}
			// write last cusip and price
			writeToFile(fw, latestCusip, latestPrice);

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
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
	}

	private void writeToFile(FileWriter fw, String latestCusip, String latestPrice) throws IOException {
		if (latestCusip != null && latestPrice != null) {

			fw.write(latestCusip + ": " + latestPrice);
			fw.write("\n");

		}

	}

	private boolean isCusip(String line) {
		
		if (line.trim().chars().allMatch(Character::isLetterOrDigit) && line.trim().length() == 8) {
			return true;
		}
		return false;
	}

}
