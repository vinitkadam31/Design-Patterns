package studentCoursesBackup.myTree;
import java.util.ArrayList;

/**
 * Node --- node of a tree and implemented observer pattern on node
 * @author    Vinit Kadam
 */
public class Node implements SubjectI, ObserverI {
	private String bnumber;
	private ArrayList<String> courseNames;
	public Node left;
	public Node right;
	public String temp;
	
	private ArrayList<ObserverI> observers;
	
	/**
	   * Node(String bnumber, String courseName)
	   * Parametrized constructor
	   * @param it accepts bnumber and course name
	   * @return No return value.
	   */
	public Node(String bnumber, String courseName){
		this.bnumber = bnumber;
		courseNames = new ArrayList<String>();
		observers = new ArrayList<ObserverI>();
		courseNames.add(courseName);
		left = null;
		right = null;
	}
	
	/**
	   * Node(String bnumber, ArrayList<String> courseNames)
	   * Parametrized constructor
	   * @param it accepts bnumber and course name arraylist
	   * @return No return value.
	   */
	public Node(String bnumber, ArrayList<String> courseNames){
		this.bnumber = bnumber;
		this.courseNames = courseNames;
		left = null;
		right = null;
	}
	
	/**
	   * Node(Node cpNode)
	   * copy constructor
	   * @param it accepts node as a parameter
	   * @return No return value.
	   */	
	public Node(Node cpNode) {
		this(cpNode.GetBnumber(), cpNode.GetCourseName());
	}
	
	/**
	   * GetBnumber()
	   * Getter method for bnumber
	   * @return returns bnumber
	   */
	public String GetBnumber()
	{
		return bnumber;
	}
	
	/**
	   * ArrayList<String> GetCourseName() 
	   * Entry point
	   * @return returns course name as a ArrayList.
	   */
	public ArrayList<String> GetCourseName() 
	{
		return courseNames;
	}
	
	/**
	   * register(ObserverI observer)
	   * to register observer
	   * @param observer - which needs to be registered
	   * @return No return value.
	   */
	public void register(ObserverI observer)
	{
		observers.add(observer);
	}
	
	/**
	   * unregister(ObserverI observer)
	   * to unregister observer
	   * @param observer - which needs to be unregistered
	   * @return No return value.
	   */
	public void unregister(ObserverI observer)
	{
		observers.remove(observer);
	}
	
	/**
	   * myNotifyAll(Operation op, String course)
	   * it will call respective update method of observer
	   * @param op: type of peration e.g insert/delete, course: which needs to be added/removed
	   * @return No return value.
	   */
	public void myNotifyAll(Operation op, String course)
	{
		//call respective update method of all observers
		for (ObserverI observer : observers) {
			observer.update(op, course);
		}
	}
	
	/**
	   * update(Operation op, String course)
	   * it will add/remove the courses
	   * @param op: type of peration e.g insert/delete, course: which needs to be added/removed
	   * @return No return value.
	   */
	public void update(Operation op, String course)
	{
		if(op == Operation.UPDATE)
		{
			if(!this.courseNames.contains(course))
				this.courseNames.add(course);
		}
		else if(op == Operation.DELETE)
		{
			if(this.courseNames.contains(course))
				this.courseNames.remove(course);
		}
	}
	
	/**
	   * toString()
	   * to print in particular format
	   * @return it will return formated string with bnumber and course list.
	   */
	public String toString()
	{
		String writer = String.format("%s:", this.bnumber);
		for (String course : this.courseNames) {
			writer += String.format("%s ", course);
		}
		return writer;
		
	}
}
