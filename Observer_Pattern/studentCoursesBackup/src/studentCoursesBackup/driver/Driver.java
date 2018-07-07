package studentCoursesBackup.driver;

import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.util.TreeBuilder;
import studentCoursesBackup.util.FileDisplayInterface;
import studentCoursesBackup.util.Results;

/**
 * Driver --- program to implement observer pattern.
 * @author    Vinit Kadam
 */
public class Driver {
	private FileProcessor fileProcessor;
	private TreeBuilder builder;
	private FileDisplayInterface displayInterface;
	public Driver()
	{
		builder = new TreeBuilder();
		displayInterface = new Results();
		
	}
	
	/**
	   * main(String[] args)
	   * Entry point
	   * @param args string array containing the command line arguments.
	   * @return No return value.
	   */
	public static void main(String[] args) {
		
		Driver driver = new Driver();
		driver.validateArguments(args);
		
		driver.processFiles(args[0], false);
		driver.processFiles(args[1], true);
		
		driver.printTree(args[2], args[3], args[4]);
		System.out.println("Exceution completed successfully");
	}
	
	/**
	   * validateArguments(String[] args)
	   * Validating required command line arguments
	   * @param args string array containing the command line arguments.
	   * @return No return value.
	   */
	private void validateArguments(String[] args)
	{
		if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
				|| args[3].equals("${arg3}") || args[4].equals("${arg4}")) {

			System.err.println("Error: Incorrect number of arguments. Program accepts 5 argumnets.");
			System.exit(0);
		}
	}
	
	/**
	   * processFiles(String file, boolean isDelete)
	   * Process file using FileReader and BufferedReader
	   * @param file: file to read, isDelete: type of file
	   * @exception FileNotFoundException
	   * @return No return value.
	   */
	private void processFiles(String file, boolean isDelete)
	{
		try
		{
			fileProcessor = new FileProcessor(file);
		}
		catch(Exception e)
		{
			System.out.println("The system cannot find the file specified: " + file);
			System.exit(0);
			//File not found exception
		}
		finally
		{
			
		}
		builder.ProcessFile(fileProcessor, isDelete);
	}
	
	/**
	   * printTree(String output1, String output2, String output3)
	   * It will call write method depending upon type of reference
	   * @param file: output1, output2, output3: output files
	   * @return No return value.
	   */
	private void printTree(String output1, String output2, String output3)
	{
		displayInterface.write(builder, output1, output2, output3);
	}


}
