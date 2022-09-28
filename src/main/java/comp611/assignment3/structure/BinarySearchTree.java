package comp611.assignment3.structure;

import java.util.Collection;

public abstract class BinarySearchTree<E> {

    private Node<E> root, current;

    public BinarySearchTree(Node<E> root, Node<E> current) {
        this.root = null;
        this.current = null;
    }

    public BinarySearchTree(Collection<? extends E> c) {
        this();
        for (E element : c)
            add(element);
    }

    public static void main(String[] args) {  // create the binary search tree
        BinarySearchTree<String> tree = new BinarySearchTree<String>("Keanna") {
            @Override
            public void hook(Node<String> node) {
                // do nothing
            }
        };
        // build the tree
        tree.add("cow");
        tree.add("fly");
        tree.add("dog");
        tree.add("bat");
        tree.add("fox");
        tree.add("cat");
        tree.add("eel");
        tree.add("ant");
        System.out.println("Original Tree: " + tree.toString());
        tree.remove("owl");
        tree.remove("cow");
        tree.add("owl");
//            System.out.println("Modified Tree: " + tree);
//            SortedSet<String> subtree = tree.subSet("cat", "fox");
//            System.out.print("Subtree iteration: ");
//            Iterator<String> i = subtree.iterator();
//            while (i.hasNext()) {
//                System.out.print(i.next());
//                if (i.hasNext()) System.out.print(", ");
//            }
//            System.out.println();
//            System.out.println("first element in subtree: " + subtree.first());
//            System.out.println("last element in subtree: " + subtree.last());
    }

    //add
    public void add(E e) {
        if(root == null) {
            root = new Node<>(e);
        } else {
            Node<E> current = root;
            while(true) {
                if(e.compareTo(current.element) < 0) {
                    if(current.left == null) {
                        current.left = new Node<>(e);
                        break;
                    } else {
                        current = current.left;
                    }
                } else if(e.compareTo(current.element) > 0) {
                    if(current.right == null) {
                        current.right = new Node<>(e);
                        break;
                    } else {
                        current = current.right;
                    }
                } else {
                    break;
                }
            }
        }

    }

    // remove
    public void remove(E e) {

    }

    // contains
    public boolean contains(E e) {


        return false;
    }

    // hook method
    public abstract void hook(Node<E> node);

    // toString
    public String toString() {
        return root.toString();
    }

    private static class Node<E> {
        //every node has e
        public E element;
        //every node has a left and right node
        public Node<E> left;
        public Node<E> right;

        public Node(E element) {
            this.element = element;
        }

        public Node(E element, Node<E> left, Node<E> right) {
            this.element = element;
            this.left = left;
            this.right = right;//code
        }

        // NO PARENT NODE
    }
}
