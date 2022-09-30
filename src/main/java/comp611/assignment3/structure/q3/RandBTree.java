package comp611.assignment3.structure.q3;

import comp611.assignment3.structure.q1.BinarySearchTree;
import comp611.assignment3.structure.q1.Node;

public class RandBTree <E extends Comparable<E>> extends BinarySearchTree<E>{

    @Override
    public boolean add(E e) {
        // if root is not null, create root with colour black, and value e, null left and right nodes
//        if (root != null) {
//            root = new Node<>(e);
//            root.colour = Node.Colour.BLACK;
//            root.left = null;
//            root.right = null;
//        }
        // add code

        // after add: check if its a leaf, if it is, set colour to black




        return super.add(e);
    }

    @Override
    public boolean remove(E value) {
        return super.remove(value);
    }

    @Override
    public boolean contains(E value) {
        return super.contains(value);
    }

    @Override
    public void hookNodeTrigger(Node<E> node) {

    }

    @Override
    public void hookAdd(Node<E> node) {

    }

    @Override
    public void hookRemove() {

    }

}
//<E extends Comparable<E>> extends BinarySearchTree<E> {
//
//    public RandBTree() {
//        super();
//    }
//
//    @Override
//    public boolean add(E e) {
//        //Let x and y are the root and leaf node of the Red-Black Tree.
//
//        //Check whether the root node is empty or not. If the root node is not empty, the inserting node will be added as a root node with color bit 1, i.e., black.
////        if (getRoot() == null) {
////            Node root = new Node(e);
////            root.isRed = false;
////            return true;
////        }
//
//        //Else, we will compare the root node element with the inserting node element. If it is greater than the root node element, traverse through the right subtree else, traverse the left subtree.
//        //Repeat steps 3 until the leaf node is not reached.
//        //Make leaf node's parent as a parent of inserting node.
//        //If the leaf node element is lesser than the inserting node element, make inserting node as leftChild.
//        //Else, make inserting node as rightChild.
//        //For the right and left children of that inserting node, we assign NULL to both of them.
//        //Set bit 0, i.e., Red color to that newly inserted node.
//        //Maintain the property of the red-black tree if violated by performing rotation and changing color bits.
//        return super.add(e);
//    }
//
//    @Override
//    public boolean remove(E value) {
//        return super.remove(value);
//    }
//
//    @Override
//    public boolean contains(E value) {
//        return super.contains(value);
//    }
//
//    //    private static final boolean red   = true;
////    private static final boolean black = false;
//
//    // is node x red; false if x is null
////    private boolean isRed(RBNode node) {
////        if (node == null) {
////            return false;
////        }
////
////        return node.isRed;
////    }
//
//    // balance the tree
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    private class RBNode extends Node {
//        private boolean isRed;
//        private RBNode left, right;
//        private int size;
//        private RBNode root;
//
//        public RBNode(E element, RBNode left, RBNode right, boolean isRed, int size, RBNode root) {
//            super(element);
//            this.left = left;
//            this.right = right;
//            this.isRed = isRed;
//            this.size = size;
//            this.root = root;
//        }
//    }
//
//    @Override
//    public void hookNodeTrigger(BinarySearchTree<E>.Node node) {
//
//    }
//}