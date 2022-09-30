package comp611.assignment3;

import comp611.assignment3.structure.Version;
import comp611.assignment3.structure.q1.BinarySearchTree;
import comp611.assignment3.structure.q2.PersistentDynamicSet;

import java.util.Map;

public class Main {

    public static void runBST() {  // create the binary search tree
        System.out.println("Running BST");
        BinarySearchTree<String> tree = new BinarySearchTree<String>() {
            @Override public void hook(Node node) {/* not required for bst */}
        };

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant"};
        //  String[] toAddV2 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "greg", "owl", "pig", "rat", "sheep", "tiger", "wolf", "zebra"};

        for(String s : toAddV1) {
            System.out.println("Adding " + s + ": " + tree.add(s));
        }

        // display tree
        System.out.println("Original Tree: \n" + tree);

        // test remove
        System.out.println("Removing owl:" + tree.remove("owl"));
        System.out.println("Removing dog:" + tree.remove("dog"));

        // test contains
        System.out.println("Contains owl: " + tree.contains("owl"));
        System.out.println("Contains owl: " + tree.contains("owl"));

        // final tree
        System.out.println("Modified Tree: \n" + tree);
    }

    public static void runRBT() {
        System.out.println("Running RBT");
    }

    public static void runPDS() {
        System.out.println("Running PDSet");
        PersistentDynamicSet<String> tree = new PersistentDynamicSet<>();

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog"};//, "fly", "dog", "bat", "fox", "cat", "eel", "ant"};
        //  String[] toAddV2 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "greg", "owl", "pig", "rat", "sheep", "tiger", "wolf", "zebra"};

        for(String s : toAddV1) {
//            System.out.println("Adding " + s + ": " + tree.add(s));
            tree.add(s);
        }

        // display tree
//        System.out.println("Tree: \n" + tree + "--");

        Map<Version, BinarySearchTree<String>.Node> versions = tree.getAllVersions();

        System.out.println("Versions Found: " + versions.size());

        // display all versions
        for(Map.Entry<Version, BinarySearchTree<String>.Node> entry : versions.entrySet()) {
            System.out.println("Version: " + entry.getKey().getNumber());
            System.out.println(entry.getValue().toFormattedString(0));
            System.out.println("================");
        }

        // test remove
//        System.out.println("Removing owl:" + tree.remove("owl"));
//        System.out.println("Removing dog:" + tree.remove("dog"));
//
//        // test contains
//        System.out.println("Contains owl: " + tree.contains("owl"));
//        System.out.println("Contains owl: " + tree.contains("owl"));

        // final tree
//        System.out.println("Modified Tree: \n" + tree);
    }

    public static void runBPDS() {
        System.out.println("Running BPDSet");
    }

    public static void main(String[] args) {

        // run binary search tree
//        runBST();

        // run red black tree
//        runRBT();

        // run persistent dynamic set
        runPDS();

        // run balanced persistent dynamic set
//        runBPDS();

//        TreeGUI.main(args);
    }
}