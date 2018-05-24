package studentCoursePlanner.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Driver --- FileProcessor to read and write file.
 * @author    Vinit Kadam
 */
public class FileProcessor {
	private BufferedReader reader;
	public FileProcessor(String fileName) throws FileNotFoundException {
		// TODO Auto-generated constructor stub
		
		FileReader fr = null;
		fr = new FileReader(fileName);
		
		reader = new BufferedReader(fr);
	}
	
	public FileProcessor(){
	}
	
	/**
	 * returning next line
	 */
	public String GetNextLine() throws IOException
	{
		return reader.readLine();
	}
	
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
