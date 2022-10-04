package comp611.assignment3.structure.task;

import comp611.assignment3.structure.task.model.Node;
import comp611.assignment3.structure.task.model.Version;

import java.util.*;

@SuppressWarnings({"unused", "DuplicatedCode"})
public class PersistentDynamicSet <E extends Comparable<E>> extends BinarySearchTree<E> {

    private final Map<Version, Node<E>> thingymabob;

    public Map<Version, Node<E>> getRootNodes() {
        return thingymabob;
    }

    @Override
    public int getRevisions() {
        return thingymabob.size() - 2;
    }

    public PersistentDynamicSet() {
        this.thingymabob = new HashMap<>();
        thingymabob.put(new Version(), getRoot());
    }

    public Node<E> getVersion(int version) {
        return thingymabob.get(new Version(version));
    }

    @Override
    public void hookNodeTrigger(Node<E> current) {/* not required */}

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
            return;
        }

        thingymabob.put(new Version(), modifiedRoot);
        super.setRoot(modifiedRoot);
    }

    public void nonOverrideSetRoot(Node<E> root) {
        // replace the node of the latest version with this root
        thingymabob.put(new Version(thingymabob.size() - 1), root);

        super.setRoot(root);
    }

    public static void main(String[] args) {  // create the binary search tree
        System.out.println("Running PDS");
        PersistentDynamicSet<String> tree = new PersistentDynamicSet<>();

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant"};

        for(String s : toAddV1) {
            System.out.println("Adding " + s + ": " + tree.add(s));
        }

        System.out.println("Size: " + tree.size());

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