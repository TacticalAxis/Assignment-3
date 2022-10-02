package comp611.assignment3.structure.q3;

import comp611.assignment3.structure.q1.BinarySearchTree;
import comp611.assignment3.structure.q1.Node;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

@SuppressWarnings("unused")
public class RandBTree<E extends Comparable<E>> extends BinarySearchTree<E> {

    private Node<E> getParent(Node<E> node) {
        // start at the root
        Node<E> current = getRoot();

        // if the node is the root, return null
        if (current == node) {
            return null;
        }

        // while the current node is not null
        while (current != null) {
            // if the current node is the parent of the node, return the current node
            if (current.left == node || current.right == node) {
                return current;
            }

            // if the node is less than the current node, go left
            if (node.compareTo(current.value) < 0) {
                current = current.left;
            }
            // if the node is greater than the current node, go right
            else {
                current = current.right;
            }
        }

        // if the node is not found, return null
        return null;
    }

    private Node<E> getGrandparent(Node<E> node) {
        // get the parent of the node
        Node<E> parent = getParent(node);

        // if the parent is null, return null
        if (parent == null) {
            return null;
        }

        // return the parent of the parent
        return getParent(parent);
    }

    private Node<E> getUncle(Node<E> node) {
        // get the parent of the node
        Node<E> parent = getParent(node);

        // if the parent is null, return null
        if (parent == null) {
            return null;
        }

        // get the grandparent of the node
        Node<E> grandparent = getGrandparent(node);

        // if the grandparent is null, return null
        if (grandparent == null) {
            return null;
        }

        // if the parent is the left child of the grandparent, return the right child of the grandparent
        if (parent == grandparent.left) {
            return grandparent.right;
        }
        // if the parent is the right child of the grandparent, return the left child of the grandparent
        else {
            return grandparent.left;
        }
    }

    private Node<E> getUncle(Node<E> parent, Node<E> grandparent) {
        // if the parent is null, return null
        if (parent == null || grandparent == null) {
            return null;
        }
        // if the parent is the left child of the grandparent, return the right child of the grandparent
        if (parent == grandparent.left) {
            return grandparent.right;
        }
        // if the parent is the right child of the grandparent, return the left child of the grandparent
        else {
            return grandparent.left;
        }
    }

    private final Deque<Node<E>> nodeStack;

    // make constructor
    public RandBTree() {
        super();
        this.nodeStack = new ArrayDeque<>();
    }

    public void reBalanceInsert(Node<E> node, Node<E> parent, Node<E> grandparent, Node<E> uncle) {
        if(parent != null && grandparent != null) {
            if(parent.color == Node.TreeColor.RED) {
                System.out.println("Double red violation at " + node.value + " with parent " + parent.value + " and grandparent " + grandparent.value);
                if(uncle != null) {
                    parent.setColor(Node.TreeColor.BLACK);
                    uncle.setColor(Node.TreeColor.BLACK);
                    grandparent.setColor(Node.TreeColor.RED);
                }
            }

            // recolor the grandparent
            Node<E> newParent = getParent(grandparent);
            Node<E> newGrandparent = getGrandparent(grandparent);
            Node<E> newUncle = getUncle(newParent, newGrandparent);

            // if the new parent is not null, call reBalanceInsert with the new node, new parent, new grandparent, and new uncle
            if(newParent != null) {
                reBalanceInsert(grandparent, newParent, newGrandparent, newUncle);
            }
        }
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
        reBalanceInsert(c, getParent(c), getGrandparent(c), null);
    }

    private boolean isTriangle(Node<E> current, Node<E> parent, Node<E> grandparent) {
        if(current == null || parent == null || grandparent == null) {
            return false;
        }
        if(current == parent.left && parent == grandparent.right) {
            return true;
        }

        return current == parent.right && parent == grandparent.left;
    }

    private void resolveTriangle(Node<E> current, Node<E> parent, Node<E> grandparent) {
        if(current == null || parent == null || grandparent == null) {
            return;
        }

        if(current == parent.left && parent == grandparent.right) {
            current.right = parent;
            parent.left = null;
            grandparent.right = current;
        } else if(current == parent.right && parent == grandparent.left) {
            current.left = parent;
            parent.right = null;
            grandparent.left = current;
        }
    }

    private boolean isLine(Node<E> current, Node<E> parent, Node<E> grandparent) {
        if(current == null || parent == null || grandparent == null) {
            return false;
        }
        if(current == parent.left && parent == grandparent.left) {
            return true;
        }

        return current == parent.right && parent == grandparent.right;
    }

    private void resolveLine(Node<E> current, Node<E> parent, Node<E> grandparent) {
        if(current == null || parent == null || grandparent == null) {
            return;
        }

        if(current == parent.right && parent == grandparent.right) {
            System.out.println("first case");
            grandparent.right = parent.left;
            parent.left = grandparent;

            // if grandparent is the root, set the root to parent
            if(grandparent == getRoot()) {
                setRoot(parent);
                grandparent.setColor(Node.TreeColor.RED);
                return;
            }

            // get the parent of the grandparent
            Node<E> grandparentParent = getParent(grandparent);
            if(grandparentParent == null) {
                return;
            }

            // if the grandparent is the left child of the grandparent's parent, set the grandparent's parent's left child to parent
            if(grandparentParent.left == grandparent) {
                grandparentParent.left = parent;
            } else {
                grandparentParent.right = parent;
            }

            System.out.println("WOOOO: " + current.value);
            current.setColor(Node.TreeColor.RED);
        } else if(current == parent.left && parent == grandparent.left) {
            System.out.println("second case");
            grandparent.left = parent.right;
            parent.right = grandparent;

            // if grandparent is the root, set the root to parent
            if(grandparent == getRoot()) {
                setRoot(parent);
                grandparent.setColor(Node.TreeColor.RED);
                return;
            }

            // get the parent of the grandparent
            Node<E> grandparentParent = getParent(grandparent);
            if(grandparentParent == null) {
                return;
            }

            // if the grandparent is the left child of the grandparent's parent, set the grandparent's parent's left child to parent
            if(grandparentParent.left == grandparent) {
                grandparentParent.left = parent;
            } else {
                grandparentParent.right = parent;
            }

            System.out.println("WOOOO: " + current.value);
            current.setColor(Node.TreeColor.RED);
        }
    }

    @Override
    public boolean add(E e) {
        boolean root = getRoot() == null;
        boolean ret = super.add(e);
        Node<E> node = query(e);
//        System.out.println("Added " + e + " to the tree. Grandparent is " + getGrandparent(node) + " and parent is " + getParent(node));
        node.setColor(root ? Node.TreeColor.BLACK : Node.TreeColor.RED);
//
        Node<E> parent = getParent(node);
        Node<E> grandparent = getGrandparent(node);
        Node<E> uncle = getUncle(parent, grandparent);

        System.out.println();
        System.out.println("Is triangle? " + isTriangle(node, parent, grandparent));
        System.out.println("Is line? " + isLine(node, parent, grandparent));

        // if is triangle, make node take the place of parent and parent be the opposite child of node
        if(isTriangle(node, parent, grandparent)) {
            System.out.println("Resolving triangle");
            resolveTriangle(node, parent, grandparent);

            if(isLine(parent, node, grandparent)) {
                System.out.println("Resolving line1");
                resolveLine(parent, node, grandparent);
            }
        }

        if(isLine(node, parent, grandparent)) {
            System.out.println("Resolving line2");
            resolveLine(node, parent, grandparent);
        }

//        System.out.println("Is triangle? " + isTriangle(node, parent, grandparent));
//        System.out.println("Is line? " + isLine(node, parent, grandparent));
//            if(node == parent.left) {
////                parent.left = node.right;
//                node.right = parent;
//            } else {
//                parent.right = node.left;
//                node.left = parent;
//            }
//        }

//        if(isTriangle(node, parent, grandparent)) {
//            if(node == parent.left) {
//                rightRotate(parent, node, grandparent);
//            }
//            else {
//                leftRotate(parent, node, grandparent);
//            }
//        }
//        else if(isLine(node, parent, grandparent)) {
//            if(node == parent.left) {
//                rightRotate(grandparent, parent, node);
//            }
//            else {
//                leftRotate(grandparent, parent, node);
//            }
//        }

//        if(isTriangle(node, parent, grandparent)) {
//            System.out.println("Triangle violation at " + node.value + " with parent " + parent.value + " and grandparent " + grandparent.value);
//            if(node == parent.left) {
//                System.out.println("Rotating right");
//                rightRotate(parent, node, grandparent);
//            }
//            else {
//                System.out.println("Rotating left");
//                leftRotate(parent, node, grandparent);
//            }
//        }
//        else if(isLine(node, parent, grandparent)) {
//            System.out.println("Line violation at " + node.value + " with parent " + parent.value + " and grandparent " + grandparent.value);
//            if(node == parent.left) {
//                System.out.println("Rotating right");
//                rightRotate(parent, node, grandparent);
//            }
//            else {
//                System.out.println("Rotating left");
//                leftRotate(parent, node, grandparent);
//            }
//        }
//        else {
//            reBalanceInsert(node, parent, grandparent, uncle);
//        }

//        System.out.println("Added " + e + " to the tree. Grandparent is " + grandparent + " and parent is " + parent + " and uncle is " + uncle);

//        reBalanceInsert(node, parent, grandparent, uncle);

//        boolean isBalanced = isBalanced();
//
//        if(!isBalanced) {
//            System.out.println("Tree is not balanced. Re-balancing...");
//            reBalanceInsert(node, parent, grandparent, uncle);
//        }

//        reBalanceInsert(node, parent, grandparent, uncle);

        // get root and set it to black
        getRoot().setColor(Node.TreeColor.BLACK);

//        System.out.println("Tree is balanced: " + isBalanced());

//        if(parent != null && grandparent != null) {
//            if(parent.color == Node.TreeColor.RED) {
//                System.out.println("Double red violation at " + node.value + " with parent " + parent.value + " and grandparent " + grandparent.value);
//                if(uncle != null) {
//                    parent.setColor(Node.TreeColor.BLACK);
//                    uncle.setColor(Node.TreeColor.BLACK);
//                    grandparent.setColor(Node.TreeColor.RED);
//                }
////                reBalanceInsert(node, parent, grandparent);
//            }
//        }

        return ret;
    }

    @Override
    public void hookNodeTrigger(Node<E> node) {
        if(nodeStack.peek() == null || nodeStack.peek().value.compareTo(node.value) != 0) {
            nodeStack.push(node);
        }
    }

    public boolean isBalanced() {
        // step 1: check if the root is black
        if(getRoot().color != Node.TreeColor.BLACK) {
            System.out.println("Root is not black");
            return false;
        }

        // step 2 & 3: check if there are any double red violations
        Iterator<Node<E>> iterator = iterator();
        while(iterator.hasNext()) {
            Node<E> node = iterator.next();
            // if the node is red, check if both children are black
            if(node.color == Node.TreeColor.RED) {
                if(node.left != null && node.left.color == Node.TreeColor.RED) {
                    System.out.println("Double red violation at " + node.value + " with left child " + node.left.value);
                    return false;
                }
                if(node.right != null && node.right.color == Node.TreeColor.RED) {
                    System.out.println("Double red violation at " + node.value + " with right child " + node.right.value);
                    return false;
                }
            }

            Node<E> parent = getParent(node);
            if(parent != null && parent.color == Node.TreeColor.RED && node.color == Node.TreeColor.RED) {
                System.out.println("Double red violation at " + node.value + " with parent " + parent.value);
                return false;
            }
        }

        // step 4: check if there are any black height violations
        int blackHeight = 0;
        Node<E> node = getRoot();
        while(node != null) {
            if(node.color == Node.TreeColor.BLACK) {
                blackHeight++;
            }
            node = node.left;
        }

        iterator = iterator();
        while(iterator.hasNext()) {
            node = iterator.next();
            if(node.left == null && node.right == null) {
                int currentBlackHeight = 0;
                Node<E> current = node;
                while(current != null) {
                    if(current.color == Node.TreeColor.BLACK) {
                        currentBlackHeight++;
                    }
                    current = getParent(current);
                }
                if(currentBlackHeight != blackHeight) {
                    System.out.println("Black height violation at " + node.value + " with black height " + currentBlackHeight);
                    return false;
                }
            }
        }

        // if all checks pass, return true
        return true;
    }

    public static void main(String[] args) {  // create the binary search tree
        System.out.println("Running BST");
        RandBTree<String> tree = new RandBTree<>();

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog", "eel"};//, "fox", "cat", "eel", "ant"};
//        String[] toAddV1 = {"cow", "fly", "bat", "ant"};
//        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "greg", "owl", "pig", "rat", "sheep", "tiger", "wolf", "zebra"};

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