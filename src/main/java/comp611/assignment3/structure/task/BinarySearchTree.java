package comp611.assignment3.structure.task;

import comp611.assignment3.structure.task.model.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@SuppressWarnings("unused")

// https://www.baeldung.com/java-print-binary-tree-diagram - I used (Keanna)
/*
* Tas, Y. (2019, December 16). How to Print a Binary Tree Diagram
*           Baeldung. Www.baeldung.com
*           https://www.baeldung.com/java-print-binary-tree-diagram
* */

public abstract class BinarySearchTree<E extends Comparable<E>>{

    // default revisions without overriding
    private static final int DEFAULT_REV = 1;

    // root of the tree
    private Node<E> root;

    public int getRevisions() {return DEFAULT_REV;}

    private static final String COLOUR_RED = "\u001B[31m";
    private static final String COLOUR_CYAN = "\u001B[36m";
    private static final String COLOUR_RESET = "\u001B[0m";

    public Node<E> getRoot() {
        return this.root;
    }

    public void setRoot(Node<E> root) {
        this.root = root;
    }

    public Node<E> getSubtree(Node<E> node) {
        return node;
    }

    // constructor
    public BinarySearchTree() {
        this.root = null;
    }

    // get size of the tree
    public int size() {
        int size = 0;
        // iterate through the tree and count the number of nodes
        Node<E> current = root;

        // if current children are null
        if(current.left == null && current.right == null) {
            return 0;
        }

        // if current has a left child
        if(current.left != null) {
            size += size(current.left);
        }

        // if current has a right child
        if(current.right != null) {
            size += size(current.right);
        }

        return size + 1;
    }

    private int size(Node<E> node) {
        int size = 1;

        // add the size of the left child
        if(node.left != null) {
            size += size(node.left);
        }

        // add the size of the right child
        if(node.right != null) {
            size += size(node.right);
        }

        return size;
    }

    public Node<E> query(E value) {
        Node<E> current = root;

        while(current != null) {
            if(current.value.compareTo(value) == 0) {
                return current;
            }

            if(current.value.compareTo(value) > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public Node<E> triggeredQuery(E value) {
        Node<E> current = root;
        hookNodeTrigger(current);

        while(current != null) {
            if(current.value.compareTo(value) == 0) {
                return current;
            }

            if(current.value.compareTo(value) > 0) {
                current = current.left;
                hookNodeTrigger(current);
            } else {
                current = current.right;
                hookNodeTrigger(current);
            }
        }

        return null;
    }

    public boolean contains(E value) {
        return query(value) != null;
    }

    public boolean add(E value) {
        if(contains(value)) {
            return false;
        }

        Node<E> newRoot = add(root, value);
        setRoot(newRoot);
        return newRoot != null;
    }

    private Node<E> add(Node<E> node, E value) {
        if(node == null) {
            if(root == null) {
                root = new Node<>(value);
                hookNodeTrigger(root);
                return root;
            } else {
                Node<E> attachNode = new Node<>(value);
                hookNodeTrigger(attachNode);
                return attachNode;
            }
        }

        Node<E> updatedNode = getSubtree(node);
        hookNodeTrigger(updatedNode);
        if(node.value.compareTo(value) > 0) {
            updatedNode.left = add(node.left, value);
        } else if(node.value.compareTo(value) < 0) {
            updatedNode.right = add(node.right, value);
        } else {
            return updatedNode;
        }

        return updatedNode;
    }

    public boolean remove(E value) {
        if(!contains(value)) {
            return false;
        }

        Node<E> newRoot = remove(root, value);
        setRoot(newRoot);
        return newRoot != null;
    }

    private Node<E> remove(Node<E> node, E value) {
        if(node == null) {
            return null;
        }

        Node<E> updatedNode = getSubtree(node);
        hookNodeTrigger(updatedNode);

        if(node.value.compareTo(value) > 0) {
            updatedNode.left = remove(node.left, value);
        } else if(node.value.compareTo(value) < 0) {
            updatedNode.right = remove(node.right, value);
        } else {
            if(node.left == null) {
                return node.right;
            } else if(node.right == null) {
                return node.left;
            } else {
                E minNodeVal = findMin(node.right);
                updatedNode.value = minNodeVal;
                updatedNode.right = remove(node.right, minNodeVal);
            }
        }

        return updatedNode;
    }

    private Node<E> findSuccessor(Node<E> node) {
        if(node.left == null) {
            return node;
        }

        return findSuccessor(node.left);
    }

    public E findMin(Node<E> node) {
        if(node.left == null) {
            return node.value;
        }

        return findMin(node.left);
    }

    @Override
    public String toString() {
        if(root == null) {
            return "[]";
        }

        return traversePreOrder(root);
    }

    public String toLinearString() {
        return "[" + root.toLinearString() + "]";
    }

    public List<E> toList() {return  toList(root, new ArrayList<>());}
    public List<E> toList(Node<E> node, List<E> list) {
        if(node == null) {
            return list;
        }

        toList(node.left, list);
        list.add(node.value);
        toList(node.right, list);

        return list;
    }

    // create iterator to iterate through the tree
    public Iterator<Node<E>> iterator() {
        return new InorderIterator();
    }

    // inner class to iterate through the tree
    protected class InorderIterator implements Iterator<Node<E>> {
        // store the elements in a list
        private final List<Node<E>> list = new ArrayList<>();
        private int current = 0; // point to the current element in list

        public InorderIterator() {
            inorder(); // traverse binary tree and store elements in list
        }

        // inorder traversal from the root
        private void inorder() {
            inorder(root);
        }

        // inorder traversal from a subtree
        private void inorder(Node<E> root) {
            if(root == null) {
                return;
            }

            inorder(root.left);
            list.add(root);
            inorder(root.right);
        }

        @Override
        public boolean hasNext() {
            return current < list.size();
        }

        @Override
        public Node<E> next() {
            // no such element
            if(!hasNext()) {
                throw new NoSuchElementException();
            }

            return list.get(current++);
        }
    }

    // hook method
    public abstract void hookNodeTrigger(Node<E> current);

    public void addAll(List<E> list) {
        for(E e : list) {
            add(e);
        }
    }

    public void addAll(E[] array) {
        for(E e : array) {
            add(e);
        }
    }

    public void containsAll(List<E> list) {
        for(E e : list) {
            contains(e);
        }
    }

    public void containsAll(E[] array) {
        for(E e : array) {
            contains(e);
        }
    }

    public void removeAll(List<E> list) {
        for(E e : list) {
            remove(e);
        }
    }

    public void removeAll(E[] array) {
        for(E e : array) {
            remove(e);
        }
    }

    public String traversePreOrder(Node<E> root) {

        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        // check if tree contains any colours aka red and black tree
        if (root.color == null) {
            sb.append(COLOUR_RESET);
            // if root is red
        } else if(root.color == Node.TreeColor.RED) {
            sb.append(COLOUR_RED);
            // if root is black
        } else {
            sb.append(COLOUR_RESET);
        }
        sb.append(root.getValue());
        // set branches to cyan
        sb.append(COLOUR_CYAN);

        // add right branch
        String pointerRight = "└──";
        // add left branch
        String pointerLeft = (root.getRight() != null) ? "├──" : "└──";

        traverseNodes(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
        traverseNodes(sb, "", pointerRight, root.getRight(), false);

        sb.append(COLOUR_RESET);

        return sb.toString();
    }

    public void traverseNodes(StringBuilder sb, String padding, String pointer, Node<E> node, boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);

            // check if tree contains any colours aka red and black tree
            if (node.color == null) {
                sb.append(COLOUR_RESET);
                // if root is red
            } else if(node.color == Node.TreeColor.RED) {
                sb.append(COLOUR_RED);
                // if root is black
            } else {
                sb.append(COLOUR_RESET);
            }

            sb.append(node.getValue());
            // set branches to cyan
            sb.append(COLOUR_CYAN);

            StringBuilder paddingBuilder = new StringBuilder(padding);
            // set padding of branches
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            // add right branch
            String pointerRight = "└──";
            // add left branch
            String pointerLeft = (node.getRight() != null) ? "├──" : "└──";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
        }
    }

    public static void main(String[] args) {  // create the binary search tree
        System.out.println("Running BST");
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
        System.out.println("Original Tree: \n" + tree.toLinearString());

        System.out.println("Size: " + tree.size());

        // test remove
        System.out.println("Removing owl: " + tree.remove("owl"));
        System.out.println("Removing dog: " + tree.remove("dog"));

        // test contains
        System.out.println("Contains dog: " + tree.contains("dog"));
        System.out.println("Contains owl: " + tree.contains("owl"));

        // final tree
        System.out.println("Modified Tree: \n" + tree);
    }

    public int compareNodesDeep(Node<E> node1, Node<E> node2) {
        if(node1 == null && node2 == null) {return 0;}
        if(node1 == null || node2 == null) {return -1;}
        String node1Str = node1.toLinearString().trim();
        String node2Str = node2.toLinearString().trim();
        return node1Str.compareTo(node2Str);
    }
}