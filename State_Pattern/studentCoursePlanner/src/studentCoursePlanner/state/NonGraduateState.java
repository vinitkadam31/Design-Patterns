package studentCoursePlanner.state;

public class NonGraduateState implements CoursePlannerStateI {

	private StateContext stateContext;
	public NonGraduateState(StateContext stateContextIn)
	{
		stateContext = stateContextIn;
	}
	
	public boolean addCourse(Student student, String course, boolean isWaitListItem) {
		// TODO Auto-generated method stub
		return false;
	}

	public void CheckStates(String course) {
		// TODO Auto-generated method stub
		
	}

	public boolean CheckRule(String course) {
		// TODO Auto-generated method stub
		return false;
	}

	public void CheckGraduationCriteria(Student student) {
		// TODO Auto-generated method stub
		if(student.CheckGraduationCondition())
		{
			student.GratuadionCompleted();
			stateContext.SetStudent(student);
			stateContext.setState(stateContext.GetGraduateState());
		}
		else
		{
			stateContext.setState(stateContext.GetInitialState());
		}
	}

}
