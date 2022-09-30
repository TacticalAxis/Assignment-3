package comp611.assignment3;

import comp611.assignment3.structure.q1.BinarySearchTree;
import comp611.assignment3.structure.q1.Node;

public class Main {

    public static BinarySearchTree<String> runBST() {  // create the binary search tree
        System.out.println("Running BST");
        BinarySearchTree<String> tree = new BinarySearchTree<String>() {
            @Override public void hookNodeTrigger(Node<String> node) {/* not required for bst */}
            @Override public void hookAdd(Node<String> node) {/* not required for bst */}
            @Override public void hookRemove() {/* not required for bst */}
        };

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant"};
        //  String[] toAddV2 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "greg", "owl", "pig", "rat", "sheep", "tiger", "wolf", "zebra"};

        for(String s : toAddV1) {
            tree.add(s);
//            System.out.println("Adding " + s + ": " + tree.add(s));
        }

        // display tree
//        System.out.println("Original Tree: \n" + tree);

        // test remove
        System.out.println("Removing owl: " + tree.remove("owl"));
        System.out.println("Removing dog: " + tree.remove("dog"));

        // test contains
        System.out.println("Contains owl: " + tree.contains("owl"));
        System.out.println("Contains owl: " + tree.contains("owl"));

        // final tree
//        System.out.println("Modified Tree: \n" + tree);

        return tree;
    }

    public static void runRBT() {
        System.out.println("Running RBT");
    }

    public static void runPDS() {
//        System.out.println("Running PDSet");
//        PersistentDynamicSet<String> tree = new PersistentDynamicSet<>();
//
//        // build the tree
//        String[] toAddV1 = {"cow", "fly", "dog"};//, "fly", "dog", "bat", "fox", "cat", "eel", "ant"};
//        //  String[] toAddV2 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "greg", "owl", "pig", "rat", "sheep", "tiger", "wolf", "zebra"};
//
//        for(String s : toAddV1) {
////            System.out.println("Adding " + s + ": " + tree.add(s));
//            tree.add(s);
//        }
//
//        // display tree
////        System.out.println("Tree: \n" + tree + "--");
//
//        Map<Version, BinarySearchTree<String>.Node> versions = tree.getAllVersions();
//
//        System.out.println("Versions Found: " + versions.size());
//
//        // display all versions
//        for(Map.Entry<Version, BinarySearchTree<String>.Node> entry : versions.entrySet()) {
//            System.out.println("Version: " + entry.getKey().getNumber());
//            System.out.println(entry.getValue().toFormattedString(0));
//            System.out.println("================");
//        }

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
//        char[] toRun = {8, 5, 12, 12, 15, 11, 5, 1, 14, 14 ,1, 8, 15, 23, 1, 18, 5, 25, 15, 21};

//        for(int i = 0; i < toRun.length; i++) {
//            System.out.print((char) (toRun[i] + 64));
//        }

//        System.out.println();

        // run binary search tree
        BinarySearchTree<String> bstTree = runBST();

        // run red black tree
//        runRBT();

        // run persistent dynamic set
//        runPDS();

        // run balanced persistent dynamic set
//        runBPDS();

//        TreeGUI.main(args);
        System.out.println("Visualising Tree");
        TreeVisualiser<String> tv = new TreeVisualiser<>();
        tv.visualiseTree(bstTree);
    }
}