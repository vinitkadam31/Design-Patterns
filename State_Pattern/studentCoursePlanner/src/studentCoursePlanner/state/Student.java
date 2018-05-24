package studentCoursePlanner.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import studentCoursePlanner.util.CourseCategory;

/**
 * Driver --- Student to store student's information including bunumber and course info
 * @author    Vinit Kadam
 */
public class Student {
	private String bnumber;
	private ArrayList<String> courses;
	private HashMap<String, String> waitlist;
	private int numberOfSemester;
	private boolean isGraduated;
	private final int requiredCourseNo = 2; 
	
	/**
	   * Constructor: to initialize courses and waitlist
	   * @param bunumber - to create student object
	   * @return No return value.
	   */
	public Student(String bnumberIn){
		bnumber = bnumberIn;
		courses = new ArrayList<String>();
		waitlist = new HashMap<String, String>();
	}
	
	/**
	   * GetBnumber: to get current bunumber
	   * @param no parameter
	   * @return bnumber
	   */
	public String GetBnumber()
	{
		return bnumber;
	}
	
	public ArrayList<String> GetCourses() 
	{
		return courses;
	}
	
	public boolean addCourses(String course)
	{
		CheckSemester();
		if(!this.courses.contains(course))
		{
			this.courses.add(course);
			return true;
		}
		return false;
	}
	
	/**
	   * updateWaitList: to add courses into waitlist
	   * @param course - which needs to add, isActive: true - waitlist course still active false - soft delete
	   * @return No return value.
	   */
	public void updateWaitList(String course, String isActive)
	{
		waitlist.put(course, isActive);
	}
	
	/**
	   * RemoveCourseFromWaitList: to remove course from waitlist
	   * @param course - course which needs to be removed
	   * @return No return value.
	   */
	public void RemoveCourseFromWaitList(String course)
	{
		waitlist.remove(course);
	}
	
	/**
	   * CheckSemester: Increment semeter if courses are multiple of 3
	   * @param no parameter
	   * @return No return value.
	   */
	public void CheckSemester()
	{
		if(courses.size() % 3 == 0)
		{
			IncreamentSemester();
			//waitlist.clear();
		}
	}
	
	/**
	   * IsEndOfSemester: depending upon assigned courses and waitlist decide end of of semester
	   * @param no parameter
	   * @return boolean - is end of semester
	   */
	private boolean IsEndOfSemester()
	{
		if((courses.size() + WaitListSize()) % 3 == 0 && WaitListSize() != 0)
		{
			return true;
		}
		return false;
	}
	
	/**
	   * Constructor: get number of active waitlist courses 
	   * @param no parameter
	   * @return size- number of active waitlist
	   */
	public int WaitListSize()
	{
		Set<Entry<String, String>> set = waitlist.entrySet();
	    Iterator<Entry<String, String>> iterator = set.iterator();
	    int size = 0;
	    while(iterator.hasNext()) {
	       Entry<String, String> mentry = iterator.next();
	       if(mentry.getValue().equalsIgnoreCase("true"))
	    	   size ++;
	    }
		return size;
	}
	
	public void SoftDeleteFromWaitlist(String course)
	{
		waitlist.replace(course, "false");
	}
	
	public boolean CanProcessAllWaitListItems()
	{
		return IsEndOfSemester();
	}
	
	public int GetNumberOfSemester()
	{
		return numberOfSemester;
	}
	
	public void IncreamentSemester()
	{
		numberOfSemester++;
	}
	
	public boolean GetIsGraduated() 
	{
		return isGraduated;
	}
	
	public HashMap<String, String> GetWaitList()
	{
		return waitlist;
	}
	
	/**
	   * CheckGraduationCondition: check graduation condition ie. student should take atleast 2 courses
	   * @param no parameter
	   * @return boolean - student can graduate
	   */
	public boolean CheckGraduationCondition()
	{
		if(CourseCategory.GetLongProgrammingCounter() >= requiredCourseNo &&
				CourseCategory.GetDataStructuresCounter() >= requiredCourseNo &&
				CourseCategory.GetHardwareSequenceCounter() >= requiredCourseNo &&
				CourseCategory.GetDataAnalystCounter() >= requiredCourseNo &&
				CourseCategory.GetElectiveCounter() >= requiredCourseNo)
			return true;
		else 
			return false;
	}
	
	/**
	   * GratuadionCompleted: set is graduated
	   * @param no parameter
	   * @return No return value.
	   */
	public void GratuadionCompleted()
	{
		isGraduated = true;
	}
	
	/**
	   * Constructor: to get formated text
	   * @param no parameter
	   * @return formatted text
	   */
	public String toString()
	{
		String writer = "";
		if(this != null)
		{
			writer = String.format("%s: ", this.bnumber);
			for (String course : this.courses) {
				writer += String.format("%s ", course);
			}
			writer = writer.trim();
			if(this.isGraduated)
				writer += ", Graduated";
			else 
				writer += ", Can't Graduate";
			writer += ", Sem: " + this.numberOfSemester;
		}
		return writer;
	}
}
