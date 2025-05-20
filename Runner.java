public class Runner {

    public static void main(String[] args) {

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.add(60);
        tree.add(25);
        tree.add(15);
        tree.add(40);
        tree.add(80);
        tree.add(10);
        tree.add(70);
        tree.add(40);
        tree.add(55);
        tree.add(20);
        tree.add(35);
        tree.add(45);
        tree.add(75);

        System.out.println();
        System.out.println("In order     " + tree.inOrder());
        System.out.println("Pre order    " + tree.preOrder());
        System.out.println("Post order   " + tree.postOrder());
        System.out.println("Level Order  " + tree.levelOrder());
        System.out.println("Width        " + tree.getWidth());
        System.out.println("Levels       " + tree.getNumLevels());
        System.out.println("Lowest       " + tree.getLowest());
        System.out.println("Highest      " + tree.getHighest());
        System.out.println("isBalanced   " + tree.isBalanced());
        System.out.println();
        System.out.println(tree.printTree(4));
        
    }
}
