package studentCoursePlanner.util;

import studentCoursePlanner.state.Student;
import studentCoursePlanner.util.FileProcessor;
import studentCoursePlanner.util.FileDisplayInterface;
import studentCoursePlanner.util.StdoutDisplayInterface;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	private FileProcessor fileProcessor;
	public Results()
	{
		fileProcessor = new FileProcessor();
	}
	
	public void write(Student student)
	{
		String str = "";
		if(student != null)
			str = student.toString();
		System.out.println(str);
		System.out.println();
	}
	
	public void write(Student student, String outputFile)
	{
		String str = "";
		if(student != null)
			str = student.toString();
		fileProcessor.writeToFile(str, outputFile);
	}
}
