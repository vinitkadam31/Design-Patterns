package studentCoursesBackup.myTree;

public interface SubjectI {
	void register(ObserverI observer);
	void unregister(ObserverI observer);
	void myNotifyAll(Operation op, String course);
}
