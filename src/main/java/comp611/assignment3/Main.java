package comp611.assignment3;

public class Main {

    public static void runBST() {
        System.out.println("Running BST");
    }

    public static void runRBT() {
        System.out.println("Running RBT");
    }

    public static void runPDS() {
        System.out.println("Running PDSet");
    }

    public static void runBPDS() {
        System.out.println("Running BPDSet");
    }

    public static void main(String[] args) {
        // run binary search tree
        runBST();

        // run red black tree
        runRBT();

        // run persistent dynamic set
        runPDS();

        // run balanced persistent dynamic set
        runBPDS();
    }
}