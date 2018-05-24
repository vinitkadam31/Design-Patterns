package studentCoursePlanner.state;

import studentCoursePlanner.util.CourseCategoryEnum;

public class ElectivesState implements CoursePlannerStateI {
	private StateContext stateContext;
	
	public ElectivesState(StateContext stateContextIn)
	{
		stateContext = stateContextIn;
	}
	public boolean addCourse(Student student, String course, boolean isWaitListItem) {
		// TODO Auto-generated method stub
		boolean courseTaken = false;
		CheckCategory(course);
		if(CheckRule(course))
			courseTaken = student.addCourses(course);
		stateContext.IncreamentCourseCount();
		stateContext.SetStudent(student);
		CheckStates(course);
		return courseTaken;
	}
	
	private void CheckCategory(String course)
	{
		stateContext.SetCurrentCourseCategoryEnum(CourseCategoryEnum.Elective);
	}
	
	public void CheckStates(String course) {
		// TODO Auto-generated method stub
		//if(!Arrays.asList(CourseCategory.electiveCourses).contains(course))
		stateContext.setState(stateContext.GetNonGraduateState());
	}
	public boolean CheckRule(String course) {
		// TODO Auto-generated method stub
		return true;
	}
	public void CheckGraduationCriteria(Student student) {
		// TODO Auto-generated method stub
		
	}
}
