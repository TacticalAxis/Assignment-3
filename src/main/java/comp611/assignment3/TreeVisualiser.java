package comp611.assignment3;

import comp611.assignment3.structure.q1.BinarySearchTree;

public class TreeVisualiser<E extends Comparable<E>> {

    public void visualiseTree(BinarySearchTree<E> tree) {
        System.out.println(tree.getRoot().toFormattedString(0));
    }
}
