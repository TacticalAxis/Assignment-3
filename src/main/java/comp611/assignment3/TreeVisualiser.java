package comp611.assignment3;

import comp611.assignment3.structure.q1.BinarySearchTree;
import comp611.assignment3.structure.q1.Node;

public class TreeVisualiser<E extends Comparable<E>> {

    private BinarySearchTree<String> getTree() {
        BinarySearchTree<String> tree = new BinarySearchTree<String>() {
            @Override public void hookNodeTrigger(Node<String> node) {/* not required for bst */}
        };

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant"};
//        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "greg", "owl", "pig", "rat", "sheep", "tiger", "wolf", "zebra"};

        for(String s : toAddV1) {
            System.out.println("Adding " + s + ": " + tree.add(s));
            System.out.println(tree.toLinearString());
        }

        // display tree
//        System.out.println("Original Tree: \n" + tree.toLinearString());

        // test remove
//        System.out.println("Removing owl: " + tree.remove("owl"));
//        System.out.println("Removing dog: " + tree.remove("dog"));

        // test contains
//        System.out.println("Contains dog: " + tree.contains("dog"));
//        System.out.println("Contains owl: " + tree.contains("owl"));

        // final tree
//        System.out.println("Modified Tree: \n" + tree);

//        TreeVisualiser<String> tv = new TreeVisualiser<>();
//        tv.visualiseTree(tree);

        return tree;
    }


    public void visualiseTree(BinarySearchTree<E> tree) {
        //            dog
        //      fly
        //            greg
        // cow
        //            ant
        //      bat
        //            cat

        //cow
        //--bat
        //	L---ant
        //	L---cat
        //	fly
        //		eel
        //		fox

        System.out.println("---------------------------\n");
        System.out.println(tree.getRoot().toFormattedString(0));
        System.out.println("---------------------------\n");
        System.out.println(tree.getRoot().toPretty(0));
    }

    public static void main(String[] args) {
        TreeVisualiser<String> tv = new TreeVisualiser<>();
        tv.visualiseTree(tv.getTree());
    }
}
