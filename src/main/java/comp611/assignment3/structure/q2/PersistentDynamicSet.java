package comp611.assignment3.structure.q2;

import comp611.assignment3.structure.Version;
import comp611.assignment3.structure.q1.BinarySearchTree;

import java.util.*;

@SuppressWarnings({"unused"})
public class PersistentDynamicSet<E extends Comparable<E>> extends BinarySearchTree<E> {

    private final Map<Version,Node> thingymabob;
    private Node currentRoot;
    private Node currentNode;

    public PersistentDynamicSet() {
        this.thingymabob = new HashMap<>();
        this.currentNode = null;
        this.currentRoot = null;
    }

    @Override
    public boolean add(E e) {
        // call the super, which calls the hook stuff
        // when hook called on the next node,
        boolean ret = super.add(e);

        currentNode = null;
        currentRoot = null;

        System.out.println("Versions: " + thingymabob.size());

        return ret;
    }

//    private boolean add(E e) {
//        return findNode(e) == null;
//        // here's the part where you search through the tree to find the right position
//        // once find the right position, get that node, then depending if higher, right or lower left
//        // node.left or node.right = new Node(e);
//    }
//
//    private boolean remove(E e) {
//        return findNode(e) != null;
//        // look through the node to see if node.value = e, by comparing like above
//        // once find node where either left or right child is e, node.left or node.right = child
//    }

//    private Node<E> findNode(E e) {
//        // start at root
//        for(Node<E> root : rootNodes.values())  {
//            Node<E> current = root;
//            if(current.left == null && current.right == null) {
//                return null;
//            }
//
//            // compare left and right or return current
//            while(current != null) {
//                if(current.value.compareTo(e) == 0) {
//                    return current;
//                } else if(current.value.compareTo(e) > 0) {
//                    current = current.left;
//                } else {
//                    current = current.right;
//                }
//            }
//        }
//
//        return null;
//    }

    @Override
    public String toString() {
        return "PersistentDynamicSet{" + "rootNodes=" + Arrays.toString(thingymabob.values().toArray()) + '}';
    }

    @Override
    public void hook(Node node) {
        // check if current node is null, if it is, set it to new Node(node), then return
        if (currentNode == null) {
            currentNode = new Node(node);
            currentRoot = currentNode;
            return;
        }

        // given current node is not null, check if node is left or right child of current node
        if (currentNode.left == node) {
            currentNode = new Node(currentNode, node, null);
        } else {
            currentNode = new Node(currentNode, null, node);
        }

        // add to version thingymabob
        thingymabob.put(new Version(), currentRoot);

        System.out.println("thing node: " + thingymabob.size());
//        System.out.println("Located node: " + node.value);
    }

    public void getAllVersion(){

    }
}