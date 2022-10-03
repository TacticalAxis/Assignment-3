package comp611.assignment3.structure;

import comp611.assignment3.structure.q1.BinarySearchTree;
import comp611.assignment3.structure.q1.Node;
import comp611.assignment3.structure.q2.PersistentDynamicSet;
import comp611.assignment3.structure.q3.BalancedPersistentDynamicSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestingUnit {

    @Test
    public void addAssertBDT() {
        // check that the add method works
        BinarySearchTree<String> tree = new BinarySearchTree<String>() {
            @Override
            public void hookNodeTrigger(Node<String> current) {/*not required*/}
        };

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "greg", "owl", "pig", "rat", "sheep", "tiger", "wolf", "zebra"};
        for(String s : toAddV1) {
            tree.add(s);
        }

        // assertEquals
        assertEquals(16, tree.toList().size());
    }

    @Test
    public void removeAssertBST() {
        BinarySearchTree<String> tree = new BinarySearchTree<String>() {
            @Override
            public void hookNodeTrigger(Node<String> current) {/*not required*/}
        };

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "greg", "owl", "pig", "rat", "sheep", "tiger", "wolf", "zebra"};

        for(String s : toAddV1) {
            tree.add(s);
        }

        // if item is not in the tree, return false
        assertEquals(true, tree.remove("cow"));
    }

    @Test
    public void containsAssertBST() {
        BinarySearchTree<String> tree = new BinarySearchTree<String>() {
            @Override
            public void hookNodeTrigger(Node<String> current) {/*not required*/}
        };

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "greg", "owl", "pig", "rat", "sheep", "tiger", "wolf", "zebra"};

        for(String s : toAddV1) {
            tree.add(s);
        }

        // if item is not in the tree, return false
        assertEquals(true, tree.contains("pig"));
    }

    @Test
    public void addAssertPDS() {
        // check that the add method works
        PersistentDynamicSet<String> tree = new PersistentDynamicSet<String>() {
            @Override
            public void hookNodeTrigger(Node<String> current) {/*not required*/}
        };

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "greg", "owl", "pig", "rat", "sheep", "tiger", "wolf", "zebra"};
        for(String s : toAddV1) {
            tree.add(s);
        }

        // assertEquals
        assertEquals(16, tree.toList().size());
    }

    @Test
    public void removeAssertPDS() {
        PersistentDynamicSet<String> tree = new PersistentDynamicSet<String>() {
            @Override
            public void hookNodeTrigger(Node<String> current) {/*not required*/}
        };

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "greg", "owl", "pig", "rat", "sheep", "tiger", "wolf", "zebra"};

        for(String s : toAddV1) {
            tree.add(s);
        }

        // if item is not in the tree, return false
        assertEquals(true, tree.remove("wolf"));
    }

    @Test
    public void containsAssertPDS() {
        PersistentDynamicSet<String> tree = new PersistentDynamicSet<String>() {
            @Override
            public void hookNodeTrigger(Node<String> current) {/*not required*/}
        };

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "greg", "owl", "pig", "rat", "sheep", "tiger", "wolf", "zebra"};

        for(String s : toAddV1) {
            tree.add(s);
        }

        // if item is not in the tree, return false
        assertEquals(true, tree.contains("eel"));
    }


    @Test
    public void addAssertRBT() {
        // check that the add method works
        BalancedPersistentDynamicSet<String> tree = new BalancedPersistentDynamicSet<String>() {
            @Override
            public void hookNodeTrigger(Node<String> current) {/*not required*/}
        };

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "greg", "owl", "pig", "rat", "sheep", "tiger", "wolf", "zebra"};
        for(String s : toAddV1) {
            tree.add(s);
        }

        // assertEquals
        assertEquals(16, tree.toList().size());
    }

    @Test
    public void removeAssertRBT() {
        BalancedPersistentDynamicSet<String> tree = new BalancedPersistentDynamicSet<String>() {
            @Override
            public void hookNodeTrigger(Node<String> current) {/*not required*/}
        };

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "greg", "owl", "pig", "rat", "sheep", "tiger", "wolf", "zebra"};

        for(String s : toAddV1) {
            tree.add(s);
        }

        // if item is not in the tree, return false
        assertEquals(true, tree.remove("dog"));
    }

    @Test
    public void containsAssertRBT() {
        BalancedPersistentDynamicSet<String> tree = new BalancedPersistentDynamicSet<String>() {
            @Override
            public void hookNodeTrigger(Node<String> current) {/*not required*/}
        };

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "greg", "owl", "pig", "rat", "sheep", "tiger", "wolf", "zebra"};

        for(String s : toAddV1) {
            tree.add(s);
        }



        // if item is not in the tree, return false
        assertEquals(true, tree.contains("tiger"));
    }

    @Test
    public void versionCheckerPDS() {
        PersistentDynamicSet<String> tree = new PersistentDynamicSet<String>() {
            @Override
            public void hookNodeTrigger(Node<String> current) {/*not required*/}
        };

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog"};

        for(String s : toAddV1) {
            tree.add(s);
        }

        // get the version
        assertEquals(3, tree.size());
    }

    @Test
    public void versionCheckerRBT() {
        BalancedPersistentDynamicSet<String> tree = new BalancedPersistentDynamicSet<String>() {
            @Override
            public void hookNodeTrigger(Node<String> current) {/*not required*/}
        };

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog"};

        for(String s : toAddV1) {
            tree.add(s);
        }

        // get the version
        assertEquals(3, tree.size());
    }
}
