package comp611.assignment3.structure.task.testing;

import comp611.assignment3.structure.task.BalancedPersistentDynamicSet;
import comp611.assignment3.structure.task.BinarySearchTree;
import comp611.assignment3.structure.task.PersistentDynamicSet;
import comp611.assignment3.structure.task.model.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BinarySearchTreeTest {

    @Test
    public void testAllTrees() {
//        testTree(new BinarySearchTree<>());
//        testTree(new PersistentDynamicSet<>());
//        testTree(new BalancedPersistentDynamicSet<>());
    }

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
        // check that the remove method works
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
        assertTrue(tree.remove("cow"));
    }

    @Test
    public void containsAssertBST() {
        // check that the contains method works
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
        assertTrue(tree.contains("pig"));
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
        // check that the remove method works
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
        assertTrue(tree.remove("wolf"));
    }

    @Test
    public void containsAssertPDS() {
        // check that the contains method works
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
        assertTrue(tree.contains("eel"));
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
        // check that the remove method works
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
        assertTrue(tree.remove("dog"));
    }

    @Test
    public void containsAssertRBT() {
        // check that the contains method works
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
        assertTrue(tree.contains("tiger"));
    }

    @Test
    public void versionCheckerPDS() {
        // check that the version checker works for PDS
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
        // check that the version checker works for RBT
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
