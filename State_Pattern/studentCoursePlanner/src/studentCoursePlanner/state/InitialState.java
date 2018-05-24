package studentCoursePlanner.state;

import java.util.Arrays;

import studentCoursePlanner.util.CourseCategory;
import studentCoursePlanner.util.CourseCategoryEnum;

/**
 * Driver --- Initial state
 * @author    Vinit Kadam
 */
public class InitialState implements CoursePlannerStateI {

	private StateContext stateContext;
	
	public InitialState(StateContext stateContextIn)
	{
		stateContext = stateContextIn;
	}
	
	public boolean addCourse(Student student, String course, boolean isWaitListItem) {
		return false;
	}

	public void CheckStates(String course) {
		if(course == null)
			stateContext.setState(stateContext.GetNonGraduateState());
		
		if(Arrays.asList(CourseCategory.electiveCourses).contains(course))
		{
			stateContext.SetCurrentCourseCategoryEnum(CourseCategoryEnum.Elective);
			stateContext.setState(stateContext.GetElectivesState());
		}
		else
			stateContext.setState(stateContext.GetPrerequisiteCategoryState());
	}

	public boolean CheckRule(String course) {
		return false;
	}
	
	public void CheckGraduationCriteria(Student student)
	{
		
	}

}
