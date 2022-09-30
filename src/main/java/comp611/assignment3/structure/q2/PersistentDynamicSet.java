package comp611.assignment3.structure.q2;

import comp611.assignment3.structure.Version;
import comp611.assignment3.structure.q1.BinarySearchTree;
import comp611.assignment3.structure.q1.Node;

import java.util.*;

@SuppressWarnings({"unused"})
public class PersistentDynamicSet <E extends Comparable<E>> extends BinarySearchTree<E> {

    private final Map<Version, Node<E>> rootMap;
    private final Stack<Node<E>> nodeStack;

    public PersistentDynamicSet() {
        // call super
        super();

        this.rootMap = new HashMap<>();
        rootMap.put(new Version(), getRoot());

        this.nodeStack = new Stack<>();
    }

    @Override
    public boolean add(E e) {


        return super.add(e);
    }

    @Override
    public boolean remove(E value) {
        return super.remove(value);
    }

    @Override
    public boolean contains(E value) {
        return super.contains(value);
    }

    @Override
    public void hookAdd(Node<E> node) {

    }

    @Override
    public void hookRemove() {

    }

    @Override
    public void hookNodeTrigger(Node<E> current) {

    }
}
//
//}<E extends Comparable<E>> extends BinarySearchTree<E> {
//
//    private final Map<Version, Node> thingymabob;
//    private Node currentRoot;
//    private Node currentNode;
//
//    public PersistentDynamicSet() {
//        this.thingymabob = new HashMap<>();
//        this.currentNode = null;
//        this.currentRoot = null;
//    }
//
//    @Override
//    public void hookNodeTrigger(Node node) {
////        if(node.value != currentRoot.value) {
////            if(currentNode != null) {
////                // if currentNode.left is node
////                // then set currentNode.left to new Node(node);
////                // then set currentNode to currentNode.left
////                if (currentNode.left == node) {
////                    currentNode.left = new Node(node);
////                    currentNode = currentNode.left;
////                }
////
////                // if currentNode.right is node
////                // then set currentNode.right to new Node(node);
////                // then set currentNode to currentNode.right
////                if (currentNode.right == node) {
////                    currentNode.right = new Node(node);
////                    currentNode = currentNode.right;
////                }
////            }
////        }
//        System.out.println("adding hook: " + node.value);
////        if (currentRoot == null) {
////            System.out.println("was null");
////            currentRoot = new Node(node);
////            currentNode = currentRoot;
////            return;
////        }
////
////        if (node == currentNode.right) {
////            currentNode.right = new Node(node);
////            currentNode = currentNode.right;
////        } else {
////            currentNode.left = new Node(node);
////            currentNode = currentNode.left;
////        }
//    }
//
//    @Override
//    public boolean add(E e) {
//        System.out.println("Adding: " + e);
//
////        if(getRoot() != null) {
////            // duplicate root
////            this.currentRoot = new Node(getRoot());
////
////            // set root to new root
////            System.out.println("Old Root: " + getRoot() + getRoot().hashCode());
////            setRoot(this.currentRoot);
////            System.out.println("New Root: " + getRoot() + getRoot().hashCode());
////        } else {
////            this.currentRoot = getRoot();
////        }
//
////        this.currentNode = currentRoot;
//
//        // set up a new root node, complete duplicate of other one
//
//        // this will call hook multiple times
//        boolean ret = super.add(e);
//
////        Version version = new Version();
////        System.out.println("Adding " + e + " in version: " + version.getNumber() + " to root: " + getRoot() + "" + getRoot().hashCode());
////        thingymabob.putIfAbsent(version, getRoot());
//
////        currentNode = null;
////        currentRoot = null;
//
////        if(getRoot() == null) {
////            setRoot(new Node(e));
////            this.currentRoot = getRoot();
////        } else {
////        this.currentRoot = new Node(getRoot());
////        }
//
//        System.out.println("Finished adding: " + e);
//        return ret;
//    }
//
//    @Override
//    public String toString() {
//        return getRoot().toFormattedString(0);
////        Node selected = thingymabob.get(new Version(thingymabob.size() - 1));
////        return selected.toFormattedString(0) + "\n";
//    }
//
//    public Map<Version, BinarySearchTree<E>.Node> getAllVersions() {
//        return thingymabob;
//    }
//}