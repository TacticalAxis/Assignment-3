package comp611.assignment3.structure.q1;

public abstract class BinarySearchTree<E extends Comparable<E>> {

    // root of the tree
    private Node root;

//    public Node getRoot() {
//        return this.root;
//    }
//
//    public void setRoot(Node root) {
//        this.root = root;
//    }

    // constructor
    public BinarySearchTree() {
        this.root = null;
    }

    // get size of the tree
    public int size() {
        int size = 0;
        // iterate through the tree and count the number of nodes
        Node current = root;

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

    private int size(Node node) {
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
        Node current = root;
        hook(current);
//        System.out.println(e + " reached " + current.value);

        while(true) {
            // check if the value is less than the current node
            if(e.compareTo(current.value) < 0) {
                if(current.left == null) {
                    // add the value to the left
                    current.left = new Node(e);
                    break;
                } else {
                    // move to the left child
                    current = current.left;
                }
            } else if(e.compareTo(current.value) > 0) {
                if (current.right == null) {
                    // add the value to the right
                    current.right = new Node(e);
                    break;
                } else {
                    // move to the right child
                    current = current.right;
                }
            } else {
                return false;
            }

            hook(current);
//            System.out.println(e + " reached " + current.value);
        }

        return true;
    }

    public boolean remove(E value) {
        // store ref to parent and current node
        Node parent = null;
        Node current = root;

        hook(current);
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
                hook(current);
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
            Node rightMost = current.left;
            Node rightMostParent = current;

            hook(rightMost);
//            System.out.println(value + " reached " + rightMost.value);

            // iterate through the right most node
            while(rightMost.right != null) {
                rightMostParent = rightMost;
                rightMost = rightMost.right;

                hook(rightMost);
//                System.out.println(value + " reached " + rightMost.value);
            }

            // replace the current node with the right most node
            current.value = rightMost.value;

            // if the right most node has a left child
            if(rightMostParent.right == rightMost) {
                rightMostParent.right = rightMost.left;

                hook(rightMostParent);
//                System.out.println(value + " reached " + rightMostParent.value);
            } else {
                rightMostParent.left = rightMost.left;

                hook(rightMostParent);
//                System.out.println(value + " reached " + rightMostParent.value);
            }

            hook(current);
//            System.out.println(value + " reached " + current.value);
        }

        return true;
    }

    public boolean contains(E value) {
        Node current = root;

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
    public abstract void hook(Node node);

    public class Node implements Comparable<E>{
        // every node has a value
        public E value;

        //every node has a left and right node
        public Node left;
        public Node right;

        public Node(E element) {
            this.value = element;
        }

//        public Node(Node node, Node left, Node right) {
//            this.value = node.value;
//            if (left != null) {
//                this.left = null;
//            }
//            if (right != null) {
//                this.right = null;
//            }
//        }

        public Node(Node node) {
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
        }

        @Override
        public int compareTo(E o) {
            return value.compareTo(o);
        }

        public String toFormattedString(int gap) {
            StringBuilder sb = new StringBuilder();
            sb.append(value).append(this.hashCode()).append("\n");
            if(left != null) {
                for(int i = 0; i < gap + 1; i++) {
                    sb.append("\t");
                }
                sb.append(left.toFormattedString(gap + 1));
            }
            if(right != null) {
                for(int i = 0; i < gap + 1; i++) {
                    sb.append("\t");
                }
                sb.append(right.toFormattedString(gap + 1));
            }
            return sb.toString();
        }

        @Override
        public String toString() {
            // return "" + (left != null ? "[" + left + "(" + getDepth() + ")]" : "") + value + (right != null ? "[" + right + "]" : "") + "";
            return value.toString();
        }
    }
}