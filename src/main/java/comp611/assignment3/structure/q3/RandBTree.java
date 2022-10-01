package comp611.assignment3.structure.q3;

import comp611.assignment3.structure.Version;
import comp611.assignment3.structure.q1.BinarySearchTree;
import comp611.assignment3.structure.q1.Node;

import java.util.ArrayList;
import java.util.List;

public class RandBTree<E extends Comparable<E>> extends BinarySearchTree<E> {

    public Node<E> getGrandparent(Node<E> node) {
        // start at the root
        Node<E> current = this.getRoot();

        for(Node<E> child : node.getGrandchildren()) {
            if(child == node) {
                return current;
            }
        }

        for(Node<E> child : current.getChildren()) {
            for(Node<E> c : child.getGrandchildren()) {
                if(c == node) {
                    return current;
                } else {
                    current = child;
                }
            }
        }

        return null;
    }

    public Node<E> getParent(Node<E> node) {
        // start at the root
        Node<E> current = this.getRoot();
        System.out.println("Root children: " + current.getChildren());

        for(Node<E> child : current.getChildren()) {
            System.out.println("Searching for value: " + node.value + " in " + current.getChildren());
            if(child.containsChild(node)) {
                return current;
            } else {
                current = child;
            }
        }

        return null;
    }

        // iterate through all the children recursively
//        this.


//        while(current != null) {
//            // if any contains it, return that node
//
//            // check gChildren of current
//
//            // set current to right, check granchidlrned
//
//            // set current to left, check grnadhicldren
//
//            if (current.value.compareTo(node.value) == 0) {
//                return current;
//            }
//
//            if (current.value.compareTo(node.value) > 0) {
//                current = current.left;
//            } else {
//                current = current.right;
//            }
//        }
//    }

    public void reBalanceInsert(Node<E> node) {
        if(getRoot() == node) {
            node.color = Node.TreeColor.BLACK;
            return;
        }

        // not the root

        // make it red
        node.color = Node.TreeColor.RED;

        // set y to parent of x
        Node<E> y = getParent(node);

        // set z to grandparent of x
        Node<E> z = getGrandparent(node);

        // if y (parent) is red
        if(y.getIsRed()) {
            // set s to sibling (parent.child that is not x)
            Node<E> s = null;
            for (Node<E> child : z.getChildren()) {
                if (child != y) {
                    s = child;
                }
            }

            if(s != null) {
                if (!s.getIsRed()) {
                    rightRotate(node, y, z);
                } else {
                    leftRotate(node, y, z);
                }
            }
        }

        // if x is black
        //


        // get parent

        // get grandparent

        // get sibling


//        if (getRoot() == null) {
////            super.add(e);
//            query(e).setColor(Node.TreeColor.BLACK);
//            return true;
//        } else {
//            Node<E> node = new Node<>(e, Node.TreeColor.RED);
//        }
    }

    public void rightRotate(Node<E> a, Node<E> b, Node<E> c) {
        c.left = b.right;
        b.right = c;
        a.setColor(Node.TreeColor.RED);
        b.setColor(Node.TreeColor.BLACK);
        c.setColor(Node.TreeColor.RED);
    }

    public void leftRotate(Node<E> a, Node<E> b, Node<E> c) {
        c.right = b.left;
        b.left = c;
        a.setColor(Node.TreeColor.RED);
        b.setColor(Node.TreeColor.BLACK);
        c.setColor(Node.TreeColor.RED);
        reBalanceInsert(c);
    }


    @Override
    public boolean add(E e) {
        super.add(e);
        query(e).setColor(Node.TreeColor.RED);
        reBalanceInsert(query(e));


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
    public void hookNodeTrigger(Node<E> node) {

    }

    public static void main(String[] args) {  // create the binary search tree
        System.out.println("Running BST");
        RandBTree<String> tree = new RandBTree<>();

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant"};
//        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "greg", "owl", "pig", "rat", "sheep", "tiger", "wolf", "zebra"};

        for(String s : toAddV1) {
            System.out.println("Adding " + s + ": " + tree.add(s));
        }

        System.out.println("Tree: " + tree);

        // test remove
//        System.out.println("Removing owl: " + tree.remove("owl"));
//        System.out.println("Removing dog: " + tree.remove("dog"));
//
//        // test contains
//        System.out.println("Contains dog: " + tree.contains("dog"));
//        System.out.println("Contains owl: " + tree.contains("owl"));
//
//        for(Map.Entry<Version, Node<String>> entry : tree.thingymabob.entrySet()) {
//            if(entry != null && entry.getValue() != null) {
//                System.out.println("Version: " + entry.getKey().getNumber() + " - " + entry.getValue().toLinearString());
//            }
//        }
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