package comp611.assignment3.structure;

import java.util.Arrays;

@SuppressWarnings({"unused", "unchecked", "ManualArrayCopy"})
public class PersistentDynamicSet<E extends Comparable<E>> {

    private Node<E>[] rootNodes;
    private static int DEF_SIZE = 5;

    public PersistentDynamicSet() {
        this.rootNodes = new Node[DEF_SIZE];
    }

    private void increaseSize() {
        Node<E>[] newRootNodes = new Node[rootNodes.length * 2];
        for (int i = 0; i < rootNodes.length; i++) {
            newRootNodes[i] = rootNodes[i];
        }
        this.rootNodes = newRootNodes;
    }

    private boolean add(E e) {
        return findNode(e) == null;
        // here's the part where you search through the tree to find the right position
        // once find the right position, get that node, then depending if higher, right or lower left
        // node.left or node.right = new Node(e);
    }

    private boolean remove(E e) {
        return findNode(e) != null;
        // look through the node to see if node.value = e, by comparing like above
        // once find node where either left or right child is e, node.left or node.right = child
    }

    private Node<E> findNode(E e) {
        // start at root
        for(Node<E> root : rootNodes)  {
            Node<E> current = root;
            if(current.left == null && current.right == null) {
                return null;
            }

            // compare left and right or return current
            while(current != null) {
                if(current.value.compareTo(e) == 0) {
                    return current;
                } else if(current.value.compareTo(e) > 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
        }

        return null;
    }

    private boolean contains(E e) {
        return findNode(e) != null;
    }

    @Override
    public String toString() {
        return "PersistentDynamicSet{" + "rootNodes=" + Arrays.toString(rootNodes) + '}';
    }

    private static class Node<E extends Comparable<E>> {
        private final E value;
        private Node<E> left;
        private Node<E> right;

        public Node(E value) {
            this.value = value;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        public E getValue() {
            return value;
        }
    }
}