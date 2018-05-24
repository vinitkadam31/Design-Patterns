package studentCoursePlanner.state;

import java.util.ArrayList;
import java.util.HashMap;

import studentCoursePlanner.util.CourseCategory;
import studentCoursePlanner.util.CourseCategoryEnum;

/**
 * Driver --- StateContext to call the current operation of particular state
 * @author    Vinit Kadam
 */
public class StateContext {
	
	private CoursePlannerStateI state;
	private InitialState initialState;
	private PrerequisiteCategoryState prerequisiteCategoryState;
	private ElectivesState electivesState;
	private GraduateState graduateState;
	private NonGraduateState nonGraduateState;
	private CourseCategoryEnum categoryEnum;
	
	private Student student;
	
	/**
	   * Constructor: instantiate each state and set initial state
	   * @param Student: to use in this context class
	   * @return No return value.
	   */
	public StateContext(Student studentIn){
		initialState = new InitialState(this);
		prerequisiteCategoryState = new PrerequisiteCategoryState(this);
		electivesState = new ElectivesState(this);
		graduateState = new GraduateState(this);
		nonGraduateState = new NonGraduateState(this);
		state = initialState;
		student = studentIn;
	}
	
	/**
	   * setState: to set current state
	   * @param state: Current state reference
	   * @return No return value.
	   */
	public void setState(CoursePlannerStateI stateIn)
	{
		state = stateIn;		
	}
	
	/**
	   * getCurrentState: get current state
	   * @param no parameter
	   * @return state: current state
	   */
	public CoursePlannerStateI getCurrentState()
	{
		return state;
	}
	
	/**
	   * GetCurrentCourseCategoryEnum: to get current course category
	   * @param Student: no parameter
	   * @return CourseCategoryEnum: current course category
	   */
	public CourseCategoryEnum GetCurrentCourseCategoryEnum()
	{
		return categoryEnum;
	}
	
	/**
	   * SetCurrentCourseCategoryEnum: set current course category from state
	   * @param CourseCategoryEnum: current course category
	   * @return No return value.
	   */
	public void SetCurrentCourseCategoryEnum(CourseCategoryEnum categoryEnumIn)
	{
		categoryEnum = categoryEnumIn;
	}
	
	/**
	   * GetInitialState: get reference of initial state
	   * @param Student: no parameter
	   * @return initial state reference
	   */
	public CoursePlannerStateI GetInitialState()
	{
		return initialState;
	}
	
	/**
	   * Constructor: get reference of Prerequisite state
	   * @param Student: no parameter
	   * @return Prerequisite state reference
	   */
	public CoursePlannerStateI GetPrerequisiteCategoryState()
	{
		return prerequisiteCategoryState;
	}
	
	/**
	   * GetElectivesState: get reference of Electives state
	   * @param Student: no parameter
	   * @return Electives state reference
	   */
	public CoursePlannerStateI GetElectivesState()
	{
		return electivesState;
	}
	
	/**
	   * GetGraduateState: get reference of Graduate state
	   * @param Student: no parameter
	   * @return Graduate state reference
	   */
	public CoursePlannerStateI GetGraduateState()
	{
		return graduateState;
	}
	
	/**
	   * GetNonGraduateState: get reference of NonGraduate state
	   * @param Student: no parameter
	   * @return NonGraduate state reference
	   */
	public CoursePlannerStateI GetNonGraduateState()
	{
		return nonGraduateState;
	}
	
	/**
	   * SetStudent: to set current student
	   * @param Student: to use in this context class
	   * @return No return value.
	   */
	public void SetStudent(Student studentIn)
	{
		student = studentIn;
	}
	
	/**
	   * IncreamentCourseCount: Increment counter of each course to check graduate condition
	   * @param no parameter
	   * @return No return value.
	   */
	public void IncreamentCourseCount()
	{
		switch (this.categoryEnum) {
			case LongProgramming:
				CourseCategory.IncreamentLongProgrammingCounter();;
				break;
			case DataStructures:
				CourseCategory.IncreamentDataStructuresCounter();
				break;
			case HardwareSequence:
				CourseCategory.IncreamentHardwareSequenceCounter();
				break;
			case DataAnalyst:
				CourseCategory.IncreamentDataAnalystCounter();
				break;
			case Elective:
				CourseCategory.IncreamentElectiveCounter();
				break;
			default:
				break;
		}
	}
	
	/**
	   * VerifyAndAddCourse: main operation to decide current state and call addCourse method of each state
	   * @param course: which needs to add, isWaitListItem: whether item is from waitlist or not 
	   * @return course added or not
	   */
	public boolean VerifyAndAddCourse(String course, boolean isWaitListItem) 
	{
		state.CheckStates(course);
		if(course != null)
		{
			boolean courseTaken = false;
			courseTaken = state.addCourse(student, course, isWaitListItem);
			state.CheckGraduationCriteria(student);
			return courseTaken;
		}
		return false;
	}
	
	/**
	   * RemoveCourseFromWaitList: remove particular course from wait list
	   * @param course: which needs to remove
	   * @return No return value.
	   */
	public void RemoveCourseFromWaitList(String course)
	{
		student.RemoveCourseFromWaitList(course);
	}
	
	/**
	   * SoftDeleteFromWaitlist: set the value to false so that it can ignore from course count
	   * @param course: which needs to remove
	   * @return No return value.
	   */
	public void SoftDeleteFromWaitlist(String course)
	{
		student.SoftDeleteFromWaitlist(course);
	}
	
	/**
	   * CheckRule: to check all rules based on category
	   * @param course: categoryCourses: List of category courses, course: which needs to add
	   * @return No return value.
	   */
	public boolean CheckRule(String[] categoryCourses, String course)
	{
		/*if(course.equalsIgnoreCase("C"))
		{
			System.out.print("");
		}*/
		ArrayList<String> takenCourses = student.GetCourses();
		HashMap<String, String> waitList = student.GetWaitList();
		ArrayList<String> takenCategoryCourses = new ArrayList<String>();
		ArrayList<String> categoryWaitList = new ArrayList<String>();
		for (String item : categoryCourses) {
			if(takenCourses.contains(item))
				takenCategoryCourses.add(item);
			
			if(waitList.containsKey(item))
				categoryWaitList.add(item);
		}
		
		boolean canTakeCourse = false;
		
		if(!student.CanProcessAllWaitListItems())
		{
			if(takenCategoryCourses.size() == 0)
			{
				if(categoryCourses[0].equalsIgnoreCase(course))
					canTakeCourse = true;
				else
					student.updateWaitList(course, "true");
			}
		}
		if(takenCategoryCourses.size() > 0)
		{
			for (String string : takenCategoryCourses) {
				canTakeCourse = true;
				int comparision = string.compareToIgnoreCase(course);
				
				if(comparision > 0)
				{
					canTakeCourse = false;
				}
			}
			if(canTakeCourse)
				student.updateWaitList(course, "true");
			canTakeCourse = false;
		}
		return canTakeCourse;
	}
}