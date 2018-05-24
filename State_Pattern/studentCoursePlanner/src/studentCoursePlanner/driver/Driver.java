package studentCoursePlanner.driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import studentCoursePlanner.state.StateContext;
import studentCoursePlanner.state.Student;
import studentCoursePlanner.util.FileProcessor;
import studentCoursePlanner.util.Results;
import studentCoursePlanner.util.FileDisplayInterface;

/**
 * Driver --- program to implement state pattern.
 * @author    Vinit Kadam
 */
public class Driver {
	private FileProcessor fileProcessor;
	private Student student;
	private FileDisplayInterface displayInterface;
	public Driver()
	{
		displayInterface = new Results();
	}
	
	/**
	   * Entry point
	   * @param args string array containing the command line arguments.
	   * @return No return value.
	   */
	public static void main(String[] args) {
		String input = args[0];
		String output = args[1];
		Driver driver = new Driver();
		driver.validateArguments(args);
		driver.processFiles(input);
		driver.Write(output);
		System.out.println("Done");
	}
	
	/**
	   * Validating required command line arguments
	   * @param args string array containing the command line arguments.
	   * @return No return value.
	   */
	private void validateArguments(String[] args)
	{
		if (args.length != 2 || args[0].equals("${arg0}") || args[1].equals("${arg1}")) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 2 argumnets.");
			System.exit(0);
		}
	}
	
	/**
	   * Read file using FileReader and BufferedReader
	   * @param file: file to read, isDelete: type of file
	   * @exception FileNotFoundException
	   * @return No return value.
	   */
	private void processFiles(String file)
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
		ProcessFile(fileProcessor);
	}
	
	public void ProcessFile(FileProcessor fileProcessor)
	{
		try
		{
			String currenLine = "";
			while((currenLine = fileProcessor.GetNextLine()) != null)
			{
				String parts[] = currenLine.split(":");
				String bnumber = parts[0].trim();
				String courses = parts[1].trim();
				String courseList[] = courses.split(" ");
				student = new Student(bnumber);
				StateContext stateContext = new StateContext(student);
				boolean currentCourseTaken = false;
				for (String course : courseList) {
					/*if(course.equalsIgnoreCase("C"))
					{
						System.out.print("");
					}*/
					boolean isWaitListNotEmpty = student.GetWaitList().size() > 0;
					if((student.CanProcessAllWaitListItems() && isWaitListNotEmpty))//(currentCourseTaken && isWaitListNotEmpty) ||
					{
						ProcessWaitListCourses(stateContext);
					}
					
					if(!student.GetIsGraduated())
						currentCourseTaken = stateContext.VerifyAndAddCourse(course, false);
					else
						break;
				}
				if(student.GetWaitList().size() > 0)
					ProcessWaitListCourses(stateContext);
				
				if(!student.GetIsGraduated())
					stateContext.VerifyAndAddCourse(null, false);
			}
		}
		catch(IOException e)
		{
			System.out.println(e.toString());
			//IO Exception
		}
		finally
		{
			
		}
	}
	
	/**
	   * Print tree
	   * @param file: output1: output file
	   * @return No return value.
	   */
	private void Write(String output)
	{
		displayInterface.write(student, output);
	}
	
	/**
	   * Process all courses using state pattern
	   * @param file: StateContext: context
	   * @return No return value.
	   */
	private void ProcessWaitListCourses(StateContext stateContext)
	{
		Set<Entry<String, String>> set = student.GetWaitList().entrySet();
	    Iterator<Entry<String, String>> iterator = set.iterator();
	    boolean waitingListCourseTaken = false;
	    ArrayList<String> coursesNeedTobeRemoved = new ArrayList<String>();
	    while(iterator.hasNext()) {
	       Entry<String, String> mentry = iterator.next();
	       waitingListCourseTaken = stateContext.VerifyAndAddCourse(mentry.getKey(), true);
	       if(waitingListCourseTaken)
	       {
	    	   coursesNeedTobeRemoved.add(mentry.getKey());
	    	   stateContext.SoftDeleteFromWaitlist(mentry.getKey());
	       }
	    }
	    
		for (String string : coursesNeedTobeRemoved) {
			stateContext.RemoveCourseFromWaitList(string);
		}
	}
}
