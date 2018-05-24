package studentCoursePlanner.state;

import java.util.Arrays;

import studentCoursePlanner.util.CourseCategory;
import studentCoursePlanner.util.CourseCategoryEnum;

/**
 * Driver --- Prerequisite state (Includes all category except Elective)
 * @author    Vinit Kadam
 */
public class PrerequisiteCategoryState implements CoursePlannerStateI {

	private StateContext stateContext;
	
	/**
	   * Constructor: instantiate stateContext object
	   * @param StateContext
	   * @return No return value.
	   */
	public PrerequisiteCategoryState(StateContext stateContextIn)
	{
		stateContext = stateContextIn;
	}
	
	/**
	   * addCourse: to add current course
	   * @param student: to access student info, course: course which needs to add, isWaitListItem: is course from waiting list
	   * @return true - course successfully added
	   */
	public boolean addCourse(Student student, String course, boolean isWaitListItem) {
		boolean courseTaken = false;
		CheckCategory(course);
		if((isWaitListItem && student.CanProcessAllWaitListItems()) || CheckRule(course))
		{
			courseTaken = student.addCourses(course);
			if(courseTaken)
				stateContext.IncreamentCourseCount();
		}
		stateContext.SetStudent(student);
		CheckStates(course);
		return courseTaken;
	}
	
	/**
	   * addCourse: to decide next state
	   * @param course: to decide category
	   * @return no return value
	   */
	public void CheckStates(String course) {
		stateContext.setState(stateContext.GetNonGraduateState());
	}
	
	/**
	   * CheckCategory: to set course category
	   * @param course: to decide category
	   * @return no return value
	   */
	private void CheckCategory(String course)
	{
		if(Arrays.asList(CourseCategory.longProgrammingcourses).contains(course))
			stateContext.SetCurrentCourseCategoryEnum(CourseCategoryEnum.LongProgramming);
		else if(Arrays.asList(CourseCategory.dataStructuresCourses).contains(course))
			stateContext.SetCurrentCourseCategoryEnum(CourseCategoryEnum.DataStructures);
		else if(Arrays.asList(CourseCategory.hardwareSequencecourses).contains(course))
			stateContext.SetCurrentCourseCategoryEnum(CourseCategoryEnum.HardwareSequence);
		else if(Arrays.asList(CourseCategory.dataAnalystCourses).contains(course))
			stateContext.SetCurrentCourseCategoryEnum(CourseCategoryEnum.DataAnalyst);
		else
			stateContext.SetCurrentCourseCategoryEnum(CourseCategoryEnum.Elective);
	}
	
	/**
	   * CheckRule: validate pre-requisite rule and co-requisite rules
	   * @param course: to validate rule
	   * @return can add course or not
	   */
	public boolean CheckRule(String course) {
		
		switch (stateContext.GetCurrentCourseCategoryEnum()) {
		case LongProgramming:
			return stateContext.CheckRule(CourseCategory.longProgrammingcourses, course);
		case DataStructures:
			return stateContext.CheckRule(CourseCategory.dataStructuresCourses, course);
		case HardwareSequence:
			return stateContext.CheckRule(CourseCategory.hardwareSequencecourses, course);
		case DataAnalyst:
			return stateContext.CheckRule(CourseCategory.dataAnalystCourses, course);
		default:
			return true;
		}
	}
	
	/**
	   * CheckGraduateCondition: check graduatuion criteria met or not
	   * @param student: to validate rule
	   * @return no return value
	   */
	public void CheckGraduationCriteria(Student student) {
		// TODO Auto-generated method stub
		
	}

}
