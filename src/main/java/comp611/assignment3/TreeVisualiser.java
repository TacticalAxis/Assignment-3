package comp611.assignment3;

import comp611.assignment3.structure.q1.BinarySearchTree;
import comp611.assignment3.structure.q1.Node;
import comp611.assignment3.structure.q2.PersistentDynamicSet;
import comp611.assignment3.structure.q3.BalancedPersistentDynamicSet;

public class TreeVisualiser<E extends Comparable<E>> {



    private BinarySearchTree<String> getTree() {

//        BinarySearchTree<String> tree = new BinarySearchTree<String>() {
//            @Override public void hookNodeTrigger(Node<String> node) {/* not required for bst */}
//        };

//        PersistentDynamicSet<String> tree = new PersistentDynamicSet<String>() {
//            @Override
//            public void hookNodeTrigger(Node<String> current) {/*not required*/}
//        };

        BalancedPersistentDynamicSet<String> tree = new BalancedPersistentDynamicSet<>();

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant"};
//        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "greg", "owl", "pig", "rat", "sheep", "tiger", "wolf", "zebra"};

        for(String s : toAddV1) {
            tree.add(s);
        }

        return tree;
    }

    public void visualiseTree(BinarySearchTree<E> tree) {
        tree.print(System.out);
    }

    public static void main(String[] args) {
        TreeVisualiser<String> tv = new TreeVisualiser<>();
        tv.visualiseTree(tv.getTree());
    }
}
