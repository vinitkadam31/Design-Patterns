package studentCoursesBackup.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Results --- Responsible for reading file line by line and writing to file.
 * @author    Vinit Kadam
 */
public class FileProcessor {
	private BufferedReader reader;

	/**
	   * FileProcessor(String fileName)
	   * Parameterized constructor which accepts file and process it
	   * @param file: fileName: file to read
	   * @exception FileNotFoundException
	   * @return No return value.
	   */
	public FileProcessor(String fileName) throws FileNotFoundException {
		// TODO Auto-generated constructor stub
		
		FileReader fr = null;
		fr = new FileReader(fileName);
		
		reader = new BufferedReader(fr);
	}
	
	/**
	   * FileProcessor()
	   * Empty constructor
	   * @return No return value.
	   */
	public FileProcessor(){
	}
	
	/**
	   * String GetNextLine()
	   * returning next line
	   * @exception IOException
	   * @return String: next line from file.
	   */
	/**
	 * returning next line
	 */
	public String GetNextLine() throws IOException
	{
		return reader.readLine();
	}
	
	/**
	   * writeToFile(String content, String fileName)
	   * Process file using FileReader and BufferedReader
	   * @param file: content: which needs to be written, fileName: output file
	   * @exception IOException
	   * @return No return value.
	   */
	public void writeToFile(String content, String fileName)
	{
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		    writer.write(content);
		    writer.close();
		}
		catch(IOException e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
		finally
		{
			
		}
	}
}
