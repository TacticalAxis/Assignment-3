package comp611.assignment3.structure.q1;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"DuplicatedCode", "unused"})
public class Node<E extends Comparable<E>> implements Comparable<E> {

    public enum TreeColor {RED, BLACK}

    // data stored in the node
    public E value;

    // left child of the node
    public Node<E> left;

    // right child of the node
    public Node<E> right;

    // colour
    public TreeColor color;

    // binary tree node constructor
    public Node(E value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.color = null;
    }

    // binary tree node constructor with L/R
    public Node(E value, Node<E> left, Node<E> right) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.color = null;
    }

    // rb tree node constructor
    public Node(E value, TreeColor color) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.color = color;
    }

    // rb tree node constructor with L/R
    public Node(E value, Node<E> left, Node<E> right, TreeColor color) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.color = color;
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
    public Node<E> getLeft() {
        return this.left;
    }

    // set left child of the node
    public void setLeft(Node<E> left) {
        this.left = left;
    }

    // get right child of the node
    public Node<E> getRight() {
        return this.right;
    }

    // set right child of the node
    public void setRight(Node<E> right) {
        this.right = right;
    }

    public TreeColor getColor() {
        return color;
    }

    public void setColor(TreeColor color) {
        this.color = color;
    }

    public boolean getIsRed() {
        return this.color == TreeColor.RED;
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
        sb.append(value).append("\n");
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
        return value.toString();
    }

    public String toLinearString() {
        return "" + (left != null ? "[" + left.toLinearString() + "]" : "") + value + (right != null ? "[" + right.toLinearString() + "]" : "") + "";
    }

    public Node<E> copy() {
        return new Node<>(this.value, this.left, this.right, this.color);
    }

    public boolean containsChild(Node<E> child) {
        return (left != null && left.equals(child)) || (right != null && right.equals(child));
    }

    public boolean containsChild(E child) {
        return (left != null && left.value.equals(child)) || (right != null && right.value.equals(child));
    }

    public List<Node<E>> getChildren() {
        List<Node<E>> list = new ArrayList<>();
        if (this.left != null) {
            list.add(this.left);
        }
        if (this.right != null) {
            list.add(this.right);
        }

        return list;
    }

    public List<Node<E>> getGrandchildren() {
        List<Node<E>> gChildren = new ArrayList<>();
        if (this.left != null) {
            gChildren.addAll(this.left.getChildren());
        }
        if (this.right != null) {
            gChildren.addAll(this.right.getChildren());
        }

        return gChildren;
    }
}