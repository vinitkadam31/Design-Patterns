package studentCoursesBackup.myTree;

/**
 * BinarySearchTree --- Tree implementation.
 * @author    Vinit Kadam
 */
public class BinarySearchTree {
	public  Node root;
	public String writer;
	public BinarySearchTree(){
		this.root = null;
	}
	
	/**
	 * find node - to find existing node
	 */
	public Node find(Node node){
		Node current = root;
		while(current!=null){
			if(current.GetBnumber().equals(node.GetBnumber())){
				return current;
			}else if(Integer.parseInt(current.GetBnumber())> Integer.parseInt(node.GetBnumber())){
				current = current.left;
			}else{
				current = current.right;
			}
		}
		return current;
	}
	
	/**
	 * Delete node - not used in this project
	 */
	public boolean delete(String bnumber){
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
		while(current.GetBnumber()!=bnumber){
			parent = current;
			if(Integer.parseInt(current.GetBnumber())>Integer.parseInt(bnumber)){
				isLeftChild = true;
				current = current.left;
			}else{
				isLeftChild = false;
				current = current.right;
			}
			if(current ==null){
				return false;
			}
		}
		//if i am here that means we have found the node
		//Case 1: if node to be deleted has no children
		if(current.left==null && current.right==null){
			if(current==root){
				root = null;
			}
			if(isLeftChild ==true){
				parent.left = null;
			}else{
				parent.right = null;
			}
		}
		//Case 2 : if node to be deleted has only one child
		else if(current.right==null){
			if(current==root){
				root = current.left;
			}else if(isLeftChild){
				parent.left = current.left;
			}else{
				parent.right = current.left;
			}
		}
		else if(current.left==null){
			if(current==root){
				root = current.right;
			}else if(isLeftChild){
				parent.left = current.right;
			}else{
				parent.right = current.right;
			}
		}else if(current.left!=null && current.right!=null){
			
			//now we have found the minimum element in the right sub tree
			Node successor	 = getSuccessor(current);
			if(current==root){
				root = successor;
			}else if(isLeftChild){
				parent.left = successor;
			}else{
				parent.right = successor;
			}			
			successor.left = current.left;
		}		
		return true;		
	}
	
	public Node getSuccessor(Node deleleNode){
		Node successsor =null;
		Node successsorParent =null;
		Node current = deleleNode.right;
		while(current!=null){
			successsorParent = successsor;
			successsor = current;
			current = current.left;
		}
		//check if successor has the right child, it cannot have left child for sure
		// if it does have the right child, add it to the left of successorParent.
//		successsorParent
		if(successsor!=deleleNode.right){
			successsorParent.left = successsor.right;
			successsor.right = deleleNode.right;
		}
		return successsor;
	}
	
	/**
	 * insert node - to insert new node
	 */
	public void insert(Node newNode){
		//Node newNode = new Node(bnumber, courseName);
		if(root==null){
			root = newNode;
			return;
		}
		Node current = root;
		Node parent = null;
		while(true){
			parent = current;
			if(Integer.parseInt(newNode.GetBnumber())<Integer.parseInt(current.GetBnumber())){				
				current = current.left;
				if(current==null){
					parent.left = newNode;
					return;
				}
			}else{
				current = current.right;
				if(current==null){
					parent.right = newNode;
					return;
				}
			}
		}
	}
	
	/**
	 * toString 
	 */
	public String toString()
	{
		return displayTree();
	}
	
	/**
	 * Display tree - inorder traversal
	 */
	public String displayTree() {
		writer = "";
		if(root!=null){
			dispayNode(root);
		}
		return writer;
	}
	
	private void dispayNode(Node root){
		if(root!=null){
			dispayNode(root.left);
			dispay(root);
			dispayNode(root.right);
		}
	}
	
	private void dispay(Node root)
	{
		writer+= root.toString();
		writer += "\n";
	}
}
