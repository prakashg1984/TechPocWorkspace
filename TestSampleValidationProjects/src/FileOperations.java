import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Scanner;

public class FileOperations {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		writeFile();
		readFiles();
		readFilesFIS();
		Properties props = readPropertiesFile("data.props");
		System.out.println("props : "+props);
	}
	
	public static void readFiles() {
		try {
			File myObj = new File("input.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(data);
			}
			myReader.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	
	public static void readFilesFIS() {
		FileInputStream fstream = null;
		BufferedReader br = null;
		String fileLines;

		try {
			fstream = new FileInputStream("input.txt");
			br = new BufferedReader(new InputStreamReader(fstream));
			while ((fileLines = br.readLine()) != null) {
				System.out.println("FIS : "+fileLines);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fstream != null) {
				try {
					fstream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void writeFile() {
		try {
			File output = new File("output.txt");
			FileWriter writer = new FileWriter(output);

			writer.write("This text was written with a FileWriter");
			writer.flush();
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	
	   public static Properties readPropertiesFile(String fileName) {
		      FileInputStream fis = null;
		      Properties prop = null;
		      try {
		         fis = new FileInputStream(fileName);
		         prop = new Properties();
		         prop.load(fis);
		      } catch(FileNotFoundException fnfe) {
		         fnfe.printStackTrace();
		      } catch(IOException ioe) {
		         ioe.printStackTrace();
		      } finally {
		         try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		      return prop;
		   }

}
