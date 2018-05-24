package studentCoursePlanner.util;

public final class CourseCategory {
	public static String[] longProgrammingcourses = {"A","B","C","D"};
	public static String[] dataStructuresCourses = {"E","F","G","H"};
	public static String[] hardwareSequencecourses = {"I","J","K","L"};
	public static String[] dataAnalystCourses = {"M","N","O","P"};
	public static String[] electiveCourses = {"Q","R","S","T","U","V","W","X","Y","Z"};
	
	private static int longProgrammingCounter = 0;
	private static int dataStructuresCounter = 0;
	private static int hardwareSequenceCounter = 0;
	private static int dataAnalystCounter = 0;
	private static int electiveCounter = 0;
	
	public static void IncreamentLongProgrammingCounter()
	{
		longProgrammingCounter++;
	}
	
	public static void IncreamentDataStructuresCounter()
	{
		dataStructuresCounter++;
	}
	
	public static void IncreamentHardwareSequenceCounter()
	{
		hardwareSequenceCounter++;
	}
	
	public static void IncreamentDataAnalystCounter()
	{
		dataAnalystCounter++;
	}
	
	public static void IncreamentElectiveCounter()
	{
		electiveCounter++;
	}
	
	public static int GetLongProgrammingCounter()
	{
		return longProgrammingCounter;
	}
	
	public static int GetDataStructuresCounter()
	{
		return dataStructuresCounter;
	}
	
	public static int GetHardwareSequenceCounter()
	{
		return hardwareSequenceCounter;
	}
	
	public static int GetDataAnalystCounter()
	{
		return dataAnalystCounter;
	}
	
	public static int GetElectiveCounter()
	{
		return electiveCounter;
	}
	
	
}
