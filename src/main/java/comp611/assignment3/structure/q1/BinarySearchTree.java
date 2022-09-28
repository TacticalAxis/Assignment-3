package comp611.assignment3.structure.q1;

import comp611.assignment3.structure.iface.SearchTree;

public abstract class BinarySearchTree<E extends Comparable<E>> implements SearchTree<E> {

    private Node<E> root;

    public BinarySearchTree() {
        this.root = null;
    }

    @Override
    public int size() {
        int size = 0;
        Node<E> current = root;
        if(current.left == null && current.right == null) {
            return 0;
        }
        if(current.left != null) {
            size += size(current.left);
        }
        if(current.right != null) {
            size += size(current.right);
        }
        return size;
    }

    private int size(Node<E> node) {
        int size = 1;
        if(node.left != null) {
            size += size(node.left);
        }
        if(node.right != null) {
            size += size(node.right);
        }
        return size;
    }

    @Override
    public boolean add(E e) {
        if (root == null) {
            root = new Node<>(e);
            return true;
        }

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
                if (current.right == null) {
                    current.right = new Node<>(e);
                    break;
                } else {
                    current = current.right;
                }
            }
        }

        return true;
    }

    @Override
    public boolean remove(E value) {
        Node<E> parent = null;
        Node<E> current = root;

        while(current != null) {
            if(value.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if(value.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break;
            }
        }

        if(current == null) {
            return false;
        }

        if(current.left == null) {
            if(parent == null) {
                root = current.right;
            } else {
                if(value.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        } else {
            Node<E> parentOfRightMost = current;
            Node<E> rightMost = current.left;

            while(rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }

            current.element = rightMost.element;

            if(parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else {
                parentOfRightMost.left = rightMost.left;
            }
        }

        return true;
    }

    @Override
    public boolean contains(E value) {
        Node<E> current = root;

        while(current != null) {
            if(value.compareTo(current.element) < 0) {
                current = current.left;
            } else if(value.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "[" + root.toString() + "]";
    }

    public static void main(String[] args) {  // create the binary search tree
        BinarySearchTree<String> tree = new BinarySearchTree<String>() {
            @Override
            public void hook(Node<String> node) {
                System.out.println(node);
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
        System.out.println("Original Tree: " + tree);
        tree.remove("owl");
        tree.remove("cow");
        System.out.println("Contains owl: " + tree.contains("owl"));
        tree.add("owl");
        System.out.println("Contains owl: " + tree.contains("owl"));
        System.out.println("Modified Tree: " + tree);
    }

    // hook method
    public abstract void hook(Node<E> node);

    private static class Node<E extends Comparable<E>> implements Comparable<E>{
        //every node has e
        public E element;

        //every node has a left and right node
        public Node<E> left;
        public Node<E> right;

        public Node(E element) {
            this.element = element;
        }

        @Override
        public int compareTo(E o) {
            return element.compareTo(o);
        }

        @Override
        public String toString() {
            return "" + (left != null ? "[" + left + "]" : "") + element + (right != null ? "[" + right + "]" : "") + "";
        }
    }
}