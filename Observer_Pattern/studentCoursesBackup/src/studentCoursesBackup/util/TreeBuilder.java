package studentCoursesBackup.util;

import java.io.IOException;

import studentCoursesBackup.myTree.BinarySearchTree;
import studentCoursesBackup.myTree.Node;

import studentCoursesBackup.myTree.Operation;

/**
 * TreeBuilder --- Wrapper class, creating original node and its backup.
 * @author    Vinit Kadam
 */
public class TreeBuilder {
	public BinarySearchTree original_Tree;
	public BinarySearchTree backup_1_Tree;
	public BinarySearchTree backup_2_Tree;
	
	public TreeBuilder()
	{
		original_Tree = new BinarySearchTree();
		backup_1_Tree = new BinarySearchTree();
		backup_2_Tree = new BinarySearchTree();
	}
	
	/**
	   * ProcessFile(FileProcessor fileProcessor, boolean isDelete)
	   * reading from file line by line, created new node along with backup and also registered backup,
       	   * if node already exists, then we are making changes on original node and calling myNotifyAll method to reflect those changes on backup
	   * @param file: fileProcessor: to read file line by line, isDelete: to specify if its insert or delete file
	   * @return No return value.
	   */
	/**
	 * 
	 */
	public void ProcessFile(FileProcessor fileProcessor, boolean isDelete)
	{
		try
		{
			String currenLine = "";
			while((currenLine = fileProcessor.GetNextLine()) != null)
			{
				String parts[] = currenLine.split(":");
				String bnumber = parts[0].trim();
				String courseName = parts[1].trim();
				Node originalNode = new Node(bnumber, courseName);
				Node existingNode = original_Tree.find(originalNode);
				if(existingNode == null && !isDelete)
				{
					Node Backup_1_Node = new Node(originalNode.GetBnumber(), courseName);
					Node Backup_2_Node = new Node(originalNode.GetBnumber(), courseName);
					
					originalNode.register(Backup_1_Node);
					originalNode.register(Backup_2_Node);
					
					original_Tree.insert(originalNode);
					backup_1_Tree.insert(Backup_1_Node);
					backup_2_Tree.insert(Backup_2_Node);
				}
				else if(existingNode != null)
				{
					Operation operation = isDelete == true ? Operation.DELETE : Operation.UPDATE;
					existingNode.update(operation, courseName);
					
					existingNode.myNotifyAll(operation, courseName);
				}
			}
		}
		catch(IOException e)
		{
			System.out.println(e.toString());
			//IO Exception
		}
		finally
		{
			
		}
	}
}
