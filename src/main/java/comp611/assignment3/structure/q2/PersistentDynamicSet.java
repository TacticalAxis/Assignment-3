package comp611.assignment3.structure.q2;

import comp611.assignment3.structure.Version;
import comp611.assignment3.structure.q1.BinarySearchTree;
import comp611.assignment3.structure.q1.Node;

import java.util.*;

@SuppressWarnings({"unused", "DuplicatedCode"})
public class PersistentDynamicSet <E extends Comparable<E>> extends BinarySearchTree<E> {

    private final Map<Version, Node<E>> thingymabob;

    public PersistentDynamicSet() {
        this.thingymabob = new HashMap<>();
        thingymabob.put(new Version(), getRoot());
    }

    public Node<E> getVersion(int version) {
        return thingymabob.get(new Version(version));
    }

    @Override
    public boolean add(E e) {
        System.out.println("Adding " + e + " to the tree");
        return super.add(e);
    }

    @Override
    public void hookNodeTrigger(Node<E> current) {
//        System.out.println("Hooking node trigger");
    }

    @Override
    public Node<E> getSubtree(Node<E> node) {
        return node.copy();
    }

    @Override
    public void setRoot(Node<E> modifiedRoot) {
        // get the last version
        Version lastVersion = new Version(thingymabob.size() - 1);
        Node<E> lastRoot = thingymabob.get(lastVersion);

        // if they are the same, don't add a new version
        if(compareNodesDeep(lastRoot, modifiedRoot) == 0) {
//            System.out.println("No change detected, not adding a new version");
            return;
        }

        thingymabob.put(new Version(), modifiedRoot);

        super.setRoot(modifiedRoot);
    }

    public static void main(String[] args) {  // create the binary search tree
        System.out.println("Running BST");
        PersistentDynamicSet<String> tree = new PersistentDynamicSet<>();

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant"};
//        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "greg", "owl", "pig", "rat", "sheep", "tiger", "wolf", "zebra"};

        for(String s : toAddV1) {
            System.out.println("Adding " + s + ": " + tree.add(s));
        }

        // test remove
        System.out.println("Removing owl: " + tree.remove("owl"));
        System.out.println("Removing dog: " + tree.remove("dog"));

        // test contains
        System.out.println("Contains dog: " + tree.contains("dog"));
        System.out.println("Contains owl: " + tree.contains("owl"));

        for(Map.Entry<Version, Node<String>> entry : tree.thingymabob.entrySet()) {
            if(entry != null && entry.getValue() != null) {
                System.out.println("Version: " + entry.getKey().getNumber() + " - " + entry.getValue().toLinearString());
            }
        }
    }
}