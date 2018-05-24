package studentCoursePlanner.state;

public interface CoursePlannerStateI {
	boolean addCourse(Student student, String course, boolean isWaitListItem);
	void CheckStates(String course);
	boolean CheckRule(String course);
	void CheckGraduationCriteria(Student student);
}
