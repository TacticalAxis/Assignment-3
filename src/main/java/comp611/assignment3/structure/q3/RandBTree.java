package comp611.assignment3.structure.q3;

import comp611.assignment3.structure.q1.BinarySearchTree;
import comp611.assignment3.structure.q1.Node;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class RandBTree<E extends Comparable<E>> extends BinarySearchTree<E> {

    private class Ancestor {
        Node<E> parent;
        Node<E> grandparent;

        public Ancestor(Node<E> parent, Node<E> grandparent) {
            this.parent = parent;
            this.grandparent = grandparent;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getGrandparent() {
            return grandparent;
        }
    }

    private final Deque<Node<E>> nodeStack;

    // make constructor
    public RandBTree() {
        super();

        this.nodeStack = new ArrayDeque<>();
    }

    public Ancestor getAncestors() {
        // if nodeStack is empty
        if(this.nodeStack.isEmpty() || this.nodeStack.size() < 2) {
            return null;
        }

        // get the grandparent of the current node (by popping 2 nodes)
        Node<E> current = this.nodeStack.pop();
        Node<E> parent = this.nodeStack.pop();

        if(this.nodeStack.isEmpty()) {
            return new Ancestor(parent, null);
        }

        Node<E> grandparent = this.nodeStack.pop();

        // push the nodes back onto the stack
        this.nodeStack.push(grandparent);
        this.nodeStack.push(parent);
        this.nodeStack.push(current);

        return new Ancestor(parent, grandparent);
    }

//    public Node<E> getGrandparent(Node<E> node) {
//        // start at the root
//        Node<E> current = this.getRoot();
//
//        for(Node<E> child : node.getGrandchildren()) {
//            if(child == node) {
//                return current;
//            }
//        }
//
//        for(Node<E> child : current.getChildren()) {
//            for(Node<E> c : child.getGrandchildren()) {
//                if(c == node) {
//                    return current;
//                } else {
//                    current = child;
//                }
//            }
//        }
//
//        return null;
//    }

    // fix this
//    public Node<E> getParent(Node<E> node) {
//        // start at the root
//        Node<E> current = this.getRoot();
//        System.out.println("Root children: " + current.getChildren());
//
//        for(Node<E> child : current.getChildren()) {
//            System.out.println("Searching for value: " + node.value + " in " + current.getChildren());
//            if(child.containsChild(node)) {
//                return current;
//            } else {
//                current = child;
//            }
//        }
//
//        return null;
//    }

//    public Node<E> getParent(Node<E> node, Node<E> current) {
//        // start at the root
//        if(current == null) {
//            current = this.getRoot();
//        }
//
//        for(Node<E> child : current.getChildren()) {
//            if(child.containsChild(node)) {
//                return current;
//            } else {
//                current = child;
//            }
//        }
//
//        return null;
//    }

    // function to go through every node and look for the parent
//    public Node<E> getParent(Node<E> node) {
//        // start at the root
//        Node<E> current = this.getRoot();
//
//        // if the root is the parent
//        if(current.containsChild(node)) {
//            return current;
//        }
//
//        // if the root is not the parent
//        for(Node<E> child : current.getChildren()) {
//            if(child.containsChild(node)) {
//                return current;
//            } else {
//                current = child;
//            }
//        }
//
//        return null;
//    }
//
//    // function to go through every node and look for the grandparent
//    public Node<E> getGrandparent(Node<E> node) {
//        // start at the root
//        Node<E> current = this.getRoot();
//
//        // if the root is the grandparent
//        if(current.containsChild(node)) {
//            return current;
//        }
//
//        // if the root is not the grandparent
//        for(Node<E> child : current.getChildren()) {
//            if(child.containsChild(node)) {
//                return current;
//            } else {
//                current = child;
//            }
//        }
//
//        return null;
//    }

    public void reBalanceInsert(Node<E> node) {
        if(getRoot() == node) {
            node.color = Node.TreeColor.BLACK;
            return;
        }

        // not the root

        // make it red
        node.color = Node.TreeColor.RED;

        Ancestor a = getAncestors();

        if(a != null) {
            Node<E> x = node;

            // set y to parent of x
//        Node<E> y = getParent(node);
            Node<E> y = a.getParent();

            // set z to grandparent of x
//        Node<E> z = getGrandparent(node);
            Node<E> z = a.getGrandparent();

            // if y (parent) is red
            if (y.getIsRed()) {
                // set s to sibling (parent.child that is not x)
                Node<E> s = null;
                for (Node<E> child : z.getChildren()) {
                    if (child != y) {
                        s = child;
                    }
                }

                if (s != null) {
                    if (!s.getIsRed()) {
                        rightRotate(x, y, z);
                    } else {
                        leftRotate(x, y, z);
                    }
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

    public boolean isBalanced() {
        // rule 1: root is black
        if(getRoot().color != Node.TreeColor.BLACK) {
            return false;
        }

        // rule 2: no red node has a red child (using the built in iterator)
        Iterator<Node<E>> iter = iterator();
        while(iter.hasNext()) {
            Node<E> node = iter.next();
            if(node.color == Node.TreeColor.RED) {
                for(Node<E> child : node.getChildren()) {
                    if(child.color == Node.TreeColor.RED) {
                        return false;
                    }
                }
            }
        }

        // rule 3: every path from root to leaf has the same number of black nodes
        // get the number of black nodes in the first path
        int blackNodes = 0;
        Node<E> current = getRoot();
        while(current != null) {
            if(current.color == Node.TreeColor.BLACK) {
                blackNodes++;
            }
            current = current.left;
        }

        return true;
//        for(Node<E> node : iterator()) {
//            if(node.color == Node.TreeColor.RED) {
//                for(Node<E> child : node.getChildren()) {
//                    if(child.color == Node.TreeColor.RED) {
//                        return false;
//                    }
//                }
//            }
//        }


//        return false;
    }

    public void reBalanceDelete(Node<E> node) {
        // if the node is red, just delete it
        if(node.color == Node.TreeColor.RED) {
            return;
        }

        // if the node is black
        // if the node is the root, just delete it
        if(node == getRoot()) {
            return;
        }

        // if the node is not the root
        // get the parent
        Node<E> parent = null;//getParent(node);

        // get the sibling
        Node<E> sibling = null;
        for(Node<E> child : parent.getChildren()) {
            if(child != node) {
                sibling = child;
            }
        }

        if(sibling == null) {
            return;
        }

        // if the sibling is red
        if(sibling.color == Node.TreeColor.RED) {
            // make the sibling black
            sibling.color = Node.TreeColor.BLACK;

            // make the parent red
            parent.color = Node.TreeColor.RED;

            // rotate the parent
            if(sibling == parent.left) {
                rightRotate(parent, sibling, node);
            } else {
                leftRotate(parent, sibling, node);
            }
        }

        // if the sibling is black
        if(sibling.color == Node.TreeColor.BLACK) {
            // if the sibling has a red child
            if(sibling.left.color == Node.TreeColor.RED) {
                // make the sibling red
                sibling.color = Node.TreeColor.RED;

                // make the sibling's left child black
                sibling.left.color = Node.TreeColor.BLACK;

                // rotate the sibling
                rightRotate(sibling, sibling.left, node);
            } else if(sibling.right.color == Node.TreeColor.RED) {
                // make the sibling red
                sibling.color = Node.TreeColor.RED;

                // make the sibling's right child black
                sibling.right.color = Node.TreeColor.BLACK;

                // rotate the sibling
                leftRotate(sibling, sibling.right, node);
            }
        }

        // if the sibling is black and has no red children
        if(sibling.color == Node.TreeColor.BLACK) {
            // make the sibling red
            sibling.color = Node.TreeColor.RED;

            // make the parent black
            parent.color = Node.TreeColor.BLACK;

            // re-balance the parent
            reBalanceDelete(parent);
        }
    }


    @Override
    public boolean add(E e) {
        boolean ret = super.add(e);
        Node<E> node = query(e);
        node.setColor(Node.TreeColor.RED);
        Ancestor a = getAncestors();

        if(a != null) {
            if (a.getParent() != null) {
                if(a.getParent().getIsRed() && node.getIsRed()) {
                    reBalanceInsert(node);
//                    if(a.getGrandparent() != null) {
//                        if(a.getGrandparent().getIsRed()) {
//                            // if the parent and grandparent are red
//                            // then the grandparent must be black
//                            a.getGrandparent().setColor(Node.TreeColor.BLACK);
//                            // the parent must be red
//                            a.getParent().setColor(Node.TreeColor.RED);
//                            // the node must be red
//                            node.setColor(Node.TreeColor.RED);
//                        }
//                    }
                }
            }
        }

//        if(a != null) {
//            if (a.getParent() != null) {
//                if(a.getParent().getIsRed() && a.getGrandparent() != null) {
//                    if(a.getGrandparent().getIsRed()) {
//                        if(a.getGrandparent().getLeft() != null) {
//                            if(a.getGrandparent().getLeft().getIsRed()) {
//                                a.getGrandparent().getLeft().setColor(Node.TreeColor.BLACK);
//                            }
//                        }
//                        if(a.getGrandparent().getRight() != null) {
//                            if(a.getGrandparent().getRight().getIsRed()) {
//                                a.getGrandparent().getRight().setColor(Node.TreeColor.BLACK);
//                            }
//                        }
//                        a.getGrandparent().setColor(Node.TreeColor.RED);
//                    }
//                }
//            }
//        }
        return ret;
    }

//    @Override
//    public boolean remove(E value) {
//        return super.remove(value);
//    }

    @Override
    public void hookNodeTrigger(Node<E> node) {
        if(nodeStack.peek() == null || nodeStack.peek().value.compareTo(node.value) != 0) {
            nodeStack.push(node);
        }
    }

    public static void main(String[] args) {  // create the binary search tree
        System.out.println("Running BST");
        RandBTree<String> tree = new RandBTree<>();

        // build the tree
//        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant"};
        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "greg", "owl", "pig", "rat", "sheep", "tiger", "wolf", "zebra"};

        for(String s : toAddV1) {
            System.out.println("Adding " + s + ": " + tree.add(s));
        }

        System.out.println("Tree: \n" + tree);

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