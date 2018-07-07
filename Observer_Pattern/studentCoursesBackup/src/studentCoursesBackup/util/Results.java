package studentCoursesBackup.util;

import studentCoursesBackup.myTree.BinarySearchTree;

/**
 * Results --- Responsible for printing.
 * @author    Vinit Kadam
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface{
	
	private FileProcessor fileProcessor;

	/**
	   * Results()
	   * default constructor which initializes fileProcessor
	   * @return No return value.
	   */
	public Results()
	{
		fileProcessor = new FileProcessor();
	}
	
	/**
	   * write(TreeBuilder treeBuilder, String output1, String output2, String output3)
	   * write from FileDisplayInterface - it will call writeToFile using 3 instances of tree and output file
	   * @param file: treeBuilder: which contains instance of 3 trees, output: 3 output files to print data of 3 trees
	   * @return No return value.
	   */
	public void write(TreeBuilder treeBuilder, String output1, String output2, String output3)
	{
		writeToFile(treeBuilder.original_Tree, output1);
		writeToFile(treeBuilder.backup_1_Tree, output2);
		writeToFile(treeBuilder.backup_2_Tree, output3);
	}
	
	/**
	   * write(TreeBuilder treeBuilder)
	   * write from StdoutDisplayInterface - It is used for debugging purpose
	   * @param file: treeBuilder: which contains instance of 3 trees
	   * @return No return value.
	   */
	public void write(TreeBuilder treeBuilder)
	{
		writeToConsole(treeBuilder.original_Tree);
		writeToConsole(treeBuilder.backup_1_Tree);
		writeToConsole(treeBuilder.backup_2_Tree);
	}
	
	/**
	   * writeToFile(BinarySearchTree tree, String outputFile)
	   * traversing tree and writing result to specified file
	   * @param file: tree: whose data needs to be printed, outputFile: output file info
	   * @return No return value.
	   */
	private void writeToFile(BinarySearchTree tree, String outputFile)
	{
		String str = tree.toString();
		fileProcessor.writeToFile(str, outputFile);
	}
	
	/**
	   * writeToConsole(BinarySearchTree tree)
	   * It is used for debugging purpose
	   * @param file: tree: whose data needs to be printed on console for debugging
	   * @return No return value.
	   */
	/**
	 * It is used for debugging purpose
	 */
	public void writeToConsole(BinarySearchTree tree)
	{
		System.out.println(tree.displayTree());
		System.out.println();
	}
}
