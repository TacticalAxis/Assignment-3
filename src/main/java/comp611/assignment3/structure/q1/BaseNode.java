package comp611.assignment3.structure.q1;

public abstract class BaseNode<E extends Comparable<E>> implements Comparable<E> {

    // data stored in the node
    protected E value;

    // left child of the node
    protected BaseNode<E> left;

    // right child of the node
    protected BaseNode<E> right;

    // constructor
    public BaseNode(E value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    // get data stored in the node
    public E getValue() {
        return this.value;
    }

    // set data stored in the node
    public void setValue(E value) {
        this.value = value;
    }

    // get left child of the node
    public BaseNode<E> getLeft() {
        return this.left;
    }

    // set left child of the node
    public void setLeft(BaseNode<E> left) {
        this.left = left;
    }

    // get right child of the node
    public BaseNode<E> getRight() {
        return this.right;
    }

    // set right child of the node
    public void setRight(BaseNode<E> right) {
        this.right = right;
    }

    // get the height of the node
    public int getSize() {
        int height = 0;

        // if the node has no children
        if(this.left == null && this.right == null) {
            return 0;
        }

        // if the node has a left child
        if(this.left != null) {
            height += this.left.getSize();
        }

        // if the node has a right child
        if(this.right != null) {
            height += this.right.getSize();
        }

        return height;
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
