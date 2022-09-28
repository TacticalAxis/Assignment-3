package comp611.assignment3.structure;

public class RandBTree {

    private class redBlackNode {
        public int key;
        public int value;

        public boolean isRed;

        public redBlackNode left;
        public redBlackNode right;
        public redBlackNode parent;

        public redBlackNode(int key, int value, boolean isRed, redBlackNode left, redBlackNode right, redBlackNode parent) {
            this.key = key;
            this.value = value;
            this.isRed = isRed;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }
}
