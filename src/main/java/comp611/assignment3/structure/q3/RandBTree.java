package comp611.assignment3.structure.q3;

public class RandBTree {

    private class RBNode<E extends Comparable<E>> {
        public int key;
        public int value;

        public boolean isRed;

        public RBNode<E> left;
        public RBNode<E> right;
        public RBNode<E> parent;

        public RBNode(int key, int value, boolean isRed, RBNode<E> left, RBNode<E> right, RBNode<E> parent) {
            this.key = key;
            this.value = value;
            this.isRed = isRed;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }
}