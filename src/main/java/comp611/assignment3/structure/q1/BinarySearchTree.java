package comp611.assignment3.structure.q1;

public abstract class BinarySearchTree<E extends Comparable<E>> {

    // root of the tree
    private Node<E> root;

    public Node<E> getRoot() {
        return this.root;
    }

    public void setRoot(Node<E> root) {
        this.root = root;
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

        return size;
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

    public boolean add(E e) {
        // if the tree is empty
        if (root == null) {
            root = new Node(e);
            return true;
        }

        // iterate through the tree
        Node<E> current = root;
        hookNodeTrigger(current);
//        System.out.println(e + " reached " + current.value);

        while(true) {
            // check if the value is less than the current node
            if(e.compareTo(current.value) < 0) {
                if(current.left == null) {
                    // add the value to the left
                    current.left = new Node<>(e);
                    break;
                } else {
                    // move to the left child
                    current = current.left;
                }
            } else if(e.compareTo(current.value) > 0) {
                if (current.right == null) {
                    // add the value to the right
                    current.right = new Node<>(e);
                    break;
                } else {
                    // move to the right child
                    current = current.right;
                }
            } else {
                return false;
            }

            hookNodeTrigger(current);
//            System.out.println(e + " reached " + current.value);
        }

        return true;
    }

    public boolean remove(E value) {
        // store ref to parent and current node
        Node<E> parent = null;
        Node<E> current = root;

        hookNodeTrigger(current);
//        System.out.println(value + " reached " + current.value);

        // iterate through the tree
        while(current != null) {
            // check if the value is less than the current node
            if(value.compareTo(current.value) < 0) {
                parent = current;
                current = current.left;
            } else if(value.compareTo(current.value) > 0) {
                parent = current;
                current = current.right;
            } else {
                break;
            }

            if(current != null) {
                hookNodeTrigger(current);
//                System.out.println(value + " reached " + current.value);
            }
        }

        // if the value is not found
        if(current == null) {
            return false;
        }

        if(current.left == null) {
            // if the current node has no left child
            if(parent == null) {
                root = current.right;
            } else {
                // if the value is less than the parent
                if(value.compareTo(parent.value) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        } else {
            // if the current node has a left child
            Node<E> rightMost = current.left;
            Node<E> rightMostParent = current;

            hookNodeTrigger(rightMost);
//            System.out.println(value + " reached " + rightMost.value);

            // iterate through the right most node
            while(rightMost.right != null) {
                rightMostParent = rightMost;
                rightMost = rightMost.right;

                hookNodeTrigger(rightMost);
//                System.out.println(value + " reached " + rightMost.value);
            }

            // replace the current node with the right most node
            current.value = rightMost.value;

            // if the right most node has a left child
            if(rightMostParent.right == rightMost) {
                rightMostParent.right = rightMost.left;

                hookNodeTrigger(rightMostParent);
//                System.out.println(value + " reached " + rightMostParent.value);
            } else {
                rightMostParent.left = rightMost.left;

                hookNodeTrigger(rightMostParent);
//                System.out.println(value + " reached " + rightMostParent.value);
            }

            hookNodeTrigger(current);
//            System.out.println(value + " reached " + current.value);
        }

        return true;
    }

    public boolean contains(E value) {
        Node<E> current = root;

        // iterate through the tree
        while(current != null) {
            if(value.compareTo(current.value) < 0) {
                current = current.left;
            } else if(value.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return root.toFormattedString(0) + "\n";
    }

    // hook method
    public abstract void hookNodeTrigger(Node<E> current);

    // add hook
    public abstract void hookAdd(Node<E> node);

    // remove hook
    public abstract void hookRemove();


}