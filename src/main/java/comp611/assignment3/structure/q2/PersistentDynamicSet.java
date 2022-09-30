package comp611.assignment3.structure.q2;

import comp611.assignment3.structure.Version;
import comp611.assignment3.structure.q1.BinarySearchTree;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unused", "rawtypes"})
public class PersistentDynamicSet<E extends Comparable<E>> extends BinarySearchTree<E> {

    private final Map<Version, Node> thingymabob;
    private Node currentRoot;
    private Node currentNode;

    public PersistentDynamicSet() {
        this.thingymabob = new HashMap<>();
        this.currentNode = null;
        this.currentRoot = null;
    }

    @Override
    public void hook(Node node) {
//        if(node.value != currentRoot.value) {
            if(currentNode != null) {
                // if currentNode.left is node
                // then set currentNode.left to new Node(node);
                // then set currentNode to currentNode.left
                if (currentNode.left == node) {
                    currentNode.left = new Node(node);
                    currentNode = currentNode.left;
                }

                // if currentNode.right is node
                // then set currentNode.right to new Node(node);
                // then set currentNode to currentNode.right
                if (currentNode.right == node) {
                    currentNode.right = new Node(node);
                    currentNode = currentNode.right;
                }
            }
//        }
//        System.out.println("adding hook: " + node.value);
//        if (currentRoot == null) {
//            System.out.println("was null");
//            currentRoot = new Node(node);
//            currentNode = currentRoot;
//            return;
//        }
//
//        if (node == currentNode.right) {
//            currentNode.right = new Node(node);
//            currentNode = currentNode.right;
//        } else {
//            currentNode.left = new Node(node);
//            currentNode = currentNode.left;
//        }
    }

    @Override
    public boolean add(E e) {
        System.out.println("Adding: " + e);

//        if(getRoot() != null) {
//            // duplicate root
//            this.currentRoot = new Node(getRoot());
//
//            // set root to new root
//            System.out.println("Old Root: " + getRoot() + getRoot().hashCode());
//            setRoot(this.currentRoot);
//            System.out.println("New Root: " + getRoot() + getRoot().hashCode());
//        } else {
//            this.currentRoot = getRoot();
//        }

        this.currentNode = currentRoot;

        boolean ret = super.add(e);

        Version version = new Version();
//        System.out.println("Adding " + e + " in version: " + version.getNumber() + " to root: " + getRoot() + "" + getRoot().hashCode());
//        thingymabob.putIfAbsent(version, getRoot());

        currentNode = null;
        currentRoot = null;

//        if(getRoot() == null) {
//            setRoot(new Node(e));
//            this.currentRoot = getRoot();
//        } else {
//        this.currentRoot = new Node(getRoot());
//        }

        return ret;
    }

    @Override
    public String toString() {
        Node selected = thingymabob.get(new Version(thingymabob.size() - 1));
        return selected.toFormattedString(0) + "\n";
    }

    public Map<Version, BinarySearchTree<E>.Node> getAllVersions() {
        return thingymabob;
    }
}