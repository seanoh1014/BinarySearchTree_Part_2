import java.util.*;

public class BinarySearchTree <T extends Comparable<T>> {

    private Node<T> root;

    public BinarySearchTree() {
        root = null;
    }

    // add your methods here
    public void add(T data) {
        root = add(data, root);
    }

    private Node<T> add(T data, Node<T> current) {
        if (current == null) {
            return new Node<T>(data);
        }

        if (data.compareTo(current.data) < 0) {
            current.left = add(data, current.left);
        }
        if (data.compareTo(current.data) > 0) {
            current.right = add(data, current.right);
        }
        return current;
    }

	// inOrder
	public String inOrder() {
		String str = inOrder(root);
		return "[" + str.substring(0, str.length() - 2) + "]";
	}

	private String inOrder(Node<T> current) {
		if (current == null) {
			return "";
		}
		return inOrder(current.left) 
			 + current.data + ", " 
			 + inOrder(current.right);
	}

	// preOrder
	public String preOrder() {
		String str = preOrder(root);
		return "[" + str.substring(0, str.length() - 2) + "]";
	}

	private String preOrder(Node<T> current) {
		if (current == null) {
			return "";
		}
		return current.data + ", " 
			 + preOrder(current.left) 
			 + preOrder(current.right);
	}


	// Post-Order
	public String postOrder() {
		String str = postOrder(root);
		return "[" + str.substring(0, str.length() - 2) + "]";
	}

	private String postOrder(Node<T> current) {
		if (current == null) {
			return "";
		}
		return postOrder(current.left) 
			 + postOrder(current.right)
			 + current.data + ", ";
	}	

	/**
	* returns the number of levels from root.left
	* plus the number of levels from root.right
	* plus one.
	*/
	public int getWidth() {
		return getNumLevels(root.left) + getNumLevels(root.right) + 1;
	}

	/**
	* returns the number of leaves in the tree. A 
	* leaf is a node that does not have a child 
	* node(s).
	*/
	public int getNumLeaves() {
		return getNumLeaves(root);
	}

	private int getNumLeaves(Node<T> current) {
		if (current == null) {
			return 0;
		} else if (current.left == null && current.right == null) {
			return 1;
		}

		return getNumLeaves(current.left) + getNumLeaves(current.right);
	}
	
	/**
	* returns the number of levels in the tree
	*/
	public int getNumLevels() {
		return getNumLevels(root);
	}

	private int getNumLevels (Node<T> current) {
		if (current == null) {
			return 0;
		} else {
			int numLeft = getNumLevels(current.left);
			int numRight = getNumLevels(current.right);
			if (numLeft > numRight) {
				return 1 + numLeft;
			} else {
				return 1 + numRight;
			}
		}
	}

	/**
	* returns the height of the tree which is 
	* the number of levels in the tree less one.
	*/
	public int getHeight() { 
		return getNumLevels() - 1;
	}

	/** 
	* returns the number of nodes in the tree
	*/
	public int size() {
		return size(root) - 1;
	}

	private int size(Node<T> current) {
		if (current == null) {
			return 1;
		}

		return size(current.left) + size(current.right);
	}

    // method copy end

    // START METHOD
    /**
    * returns the lowest value in the tree according
    * to the classes natural ordering. Returns null 
    * if the tree is empty.
    */
    public T getLowest() {
        Node<T> result = getLowest(root);
        if (result == null) { return null; }
        return result.getValue();
    }
    private Node<T> getLowest(Node<T> current) {
        if (current == null) {
            return null;
        }

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    /**
    * returns the highest value in the tree according
    * to the classes natural ordering. Returns null 
    * if the tree is empty.
    */
    public T getHighest() {
        Node<T> result = getHighest(root);
        if (result == null) { return null; }
        return result.getValue();
    }
    private Node<T> getHighest(Node<T> current) {
        if (current == null) {
            return null;
        }

        while (current.right != null) {
            current = current.right;
        }

        return current;
    }

    /**
    * returns true if the number of levels to the
    * left and right of every parent node differs
    * by 1 or less. Otherwise, it returns false.
    */
    public boolean isBalanced() {
        return isBalanced(root);
    }
    private boolean isBalanced(Node<T> current) {
        if (current == null) {
			return true;
		}

        int numLeft = getNumLevels(current.left);
        int numRight = getNumLevels(current.right);
        
        if (Math.abs(numLeft - numRight) <= 1 && isBalanced(current.left) && isBalanced(current.right)) {
            return true;
        }

        return false;        
    }

    /**
    * returns a levelOrder representation of the
    * tree. You don't need a recursive algorithm 
    * if you implement a queue (FIFO) type
    * data structure for this.
    */
    public String levelOrder() {
        if (root == null) {
            return "";
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        String str = "";

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            str += current.getValue() + ", ";

            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }

        return "[" + str.substring(0, str.length() - 2) + "]";
    }


	
    // END METHOD
    
    public String toString() {
        return inOrder();
    }

    public String printTree(int maxLevels) {
        return printTree(root, maxLevels);
    }

    public String printTree(Node<T> root, int maxLevels) {

        List<List<String>> allLevels = new ArrayList<>();
        List<Node<T>> currentLevel = new ArrayList<>();
        List<Node<T>> nextLevel = new ArrayList<>();

        currentLevel.add(root);
        int widestNode = 0;

        // build level order list of lists
        boolean hasNextLevel = true;
        while (hasNextLevel && allLevels.size() < maxLevels) {
            List<String> line = new ArrayList<>();

            hasNextLevel = false;
            for (Node<T> current : currentLevel) {
                if (current == null) {
                    line.add(null);
                    nextLevel.add(null);
                    nextLevel.add(null);
                } else {
                    line.add(current.toString());
                    if (current.toString().length() > widestNode) {
                        widestNode = current.toString().length();
                    }
                    nextLevel.add(current.left);
                    nextLevel.add(current.right);

                    if (current.left != null || current.right != null) {
                        hasNextLevel = true;
                    }
                }
            }
            allLevels.add(line);
            currentLevel = nextLevel;
            nextLevel = new ArrayList<>();
        }

        // build result string
        String result = "Binary Search Tree (Maximum of " + maxLevels + " levels shown.\n";

        // Set output line width using the last level, 
        widestNode += (widestNode % 2 == 1) ? 1 : 0;
        int sizeFactor = (allLevels.size() < 6) ? 4 : 2;
        int width = (int)Math.pow(2, allLevels.size() - 1) * (widestNode + sizeFactor);

        for (int i = 0; i < allLevels.size(); i++) {

            List<String> line = allLevels.get(i);
            int half = width / 2 - 1;
            String spaces = String.format("%" + half +"s", "");
            String dashes = String.format("%" + half +"s", "").replace(" ", "-");
            String lineOutput = "";
            String valueOutput = "";

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // build line to add up arrow beneath parent nodes
                    lineOutput += (j % 2 == 1 && (line.get(j - 1) != null || 
                                                  line.get(j) != null)) ? "^" : " ";

                    // build line to add spaces, vertical bars, and lines to add to result string
                    if (line.get(j) == null) {
                        lineOutput += String.format("%" + (width - 1) + "s", "");
                    } else {
                        lineOutput += (j % 2 == 0) ? spaces + "|" + dashes : dashes + "|" + spaces;
                    }
                }
                result += lineOutput.replaceAll("\\s+$", "") + "\n";
            }

            // add node values to the result string
            for (String item : line) {
                int gap = (item == null) ? width / 2 : width / 2 - item.length() / 2;
                valueOutput += String.format("%" + gap +"S%s%" + gap + "S", 
                                             "", (item == null) ? "" : item, "");
            }
            result += valueOutput.replaceAll("\\s+$", "") + "\n";
            width /= 2;
        }
        return result;
    }
}
