package comp611.assignment3.structure.q3;

import comp611.assignment3.structure.Version;
import comp611.assignment3.structure.q1.Node;
import comp611.assignment3.structure.q2.PersistentDynamicSet;

import java.util.Map;

@SuppressWarnings({"unused", "CommentedOutCode"})
public class BalancedPersistentDynamicSet<E extends Comparable<E>> extends PersistentDynamicSet<E> {

    private Node<E> getParent(Node<E> node) {
        // start at the root
        Node<E> current = getRoot();

        // if the node is the root, return null
        if (current == node) {
            return null;
        }

        // while the current node is not null
        while (current != null) {
            // if the current node is the parent of the node, return the current node
            if (current.left == node || current.right == node) {
                return current;
            }

            // if the node is less than the current node, go left
            if (node.compareTo(current.value) < 0) {
                current = current.left;
            }
            // if the node is greater than the current node, go right
            else {
                current = current.right;
            }
        }

        // if the node is not found, return null
        return null;
    }
    private Node<E> getGrandparent(Node<E> node) {
        // get the parent of the node
        Node<E> parent = getParent(node);

        // if the parent is null, return null
        if (parent == null) {
            return null;
        }

        // return the parent of the parent
        return getParent(parent);
    }
    private Node<E> getUncle(Node<E> node) {
        // get the parent of the node
        Node<E> parent = getParent(node);

        // if the parent is null, return null
        if (parent == null) {
            return null;
        }

        // get the grandparent of the node
        Node<E> grandparent = getGrandparent(node);

        // if the grandparent is null, return null
        if (grandparent == null) {
            return null;
        }

        // if the parent is the left child of the grandparent, return the right child of the grandparent
        if (parent == grandparent.left) {
            return grandparent.right;
        }
        // if the parent is the right child of the grandparent, return the left child of the grandparent
        else {
            return grandparent.left;
        }
    }
    private Node<E> getUncle(Node<E> parent, Node<E> grandparent) {
        // if the parent is null, return null
        if (parent == null || grandparent == null) {
            return null;
        }
        // if the parent is the left child of the grandparent, return the right child of the grandparent
        if (parent == grandparent.left) {
            return grandparent.right;
        }
        // if the parent is the right child of the grandparent, return the left child of the grandparent
        else {
            return grandparent.left;
        }
    }

    public BalancedPersistentDynamicSet() {
        super();
    }

    public void rotateLRTriangle(Node<E> node, Node<E> parent, Node<E> grandparent) {
        if (node == null || parent == null || grandparent == null) {
            return;
        }

        node.left = parent;
        parent.right = null;
        grandparent.left = node;

        Node<E> gGrandparent = getParent(grandparent);
        if(gGrandparent != null) {
            if(grandparent == gGrandparent.right) {
                grandparent.left = null;
                node.right = grandparent;
                gGrandparent.right = node;
            } else if(grandparent == gGrandparent.left) {
                grandparent.right = null;
                node.left = grandparent;
                gGrandparent.left = node;
            }

            grandparent.setColor(Node.TreeColor.RED);
            parent.setColor(Node.TreeColor.RED);
            node.setColor(Node.TreeColor.BLACK);
        }
    }

    public void rotateRLTriangle(Node<E> node, Node<E> parent, Node<E> grandparent) {
        if (node == null || parent == null || grandparent == null) {
            return;
        }

        node.right = parent;
        parent.left = null;
        grandparent.right = node;

        Node<E> gGrandparent = getParent(grandparent);
        if(gGrandparent != null) {
            if(grandparent == gGrandparent.right) {
                grandparent.right = null;
                node.left = grandparent;
                gGrandparent.right = node;
            } else if(grandparent == gGrandparent.left) {
                grandparent.left = null;
                node.right = grandparent;
                gGrandparent.right = node;
            }

            grandparent.setColor(Node.TreeColor.RED);
            parent.setColor(Node.TreeColor.RED);
            node.setColor(Node.TreeColor.BLACK);
        }
    }

    public void rotateRRLine(Node<E> node, Node<E> parent, Node<E> grandparent) {
        if (node == null || parent == null || grandparent == null) {
            return;
        }

        Node<E> gGrandparent = getParent(grandparent);
        System.out.println("gGrandparent: " + gGrandparent);
        if(gGrandparent != null) {
            grandparent.right = parent.left;
            parent.left = grandparent;
            if(grandparent == gGrandparent.right) {
                gGrandparent.right = parent;
            } else if(grandparent == gGrandparent.left) {
                gGrandparent.left = parent;
            }
        }

        grandparent.recolour();
        parent.recolour();
    }

    public void rotateLLLine(Node<E> node, Node<E> parent, Node<E> grandparent) {
        if (node == null || parent == null || grandparent == null) {
            return;
        }

        Node<E> gGrandparent = getParent(grandparent);
        System.out.println("gGrandparent: " + gGrandparent);
        if(gGrandparent != null) {
            grandparent.left = parent.right;
            parent.right = grandparent;
            if(grandparent == gGrandparent.right) {
                gGrandparent.right = parent;
            } else if(grandparent == gGrandparent.left) {
                gGrandparent.left = parent;
            }
        }

        grandparent.recolour();
        parent.recolour();
    }

    @Override
    public boolean add(E e) {
        boolean root = getRoot() == null;
        boolean added = super.add(e);

        Node<E> current = query(e);

        if(current == null) {
            return false;
        }

        // set color of the node to black if it is the root
        if (root) {
            current.setColor(Node.TreeColor.BLACK);
            return added;
        } else {
            // set color of the node to red
            current.setColor(Node.TreeColor.RED);
        }

        // check parent
        Node<E> parent = getParent(current);
        if(parent == null) {
            return added;
        }

        checkRRConflict(current, parent, false);

        return added;
    }

    private void checkRRConflict(Node<E> current, Node<E> parent, boolean inRecursion) {
        if(current == null || parent == null) {
            return;
        }


        // if the parent is black, return
        if(!parent.getIsRed()) {
            return;
        }

        if(inRecursion) {
            Node<E> grandparent = getGrandparent(current);

            if(grandparent != null) {
                Node<E> gGrandparent = getParent(grandparent);

                if(current == parent.right && parent == grandparent.right) {
                    grandparent.right = parent.left;
                    parent.left = grandparent;

                    if(gGrandparent != null) {
                        if(grandparent == gGrandparent.left) {
                            gGrandparent.left = parent;
                        } else if (grandparent == gGrandparent.right) {
                            gGrandparent.right = parent;
                        }
                    } else {
                        nonOverrideSetRoot(parent);
                    }
                } else if (current == parent.left && parent == grandparent.left) {
                    grandparent.left = parent.right;
                    parent.right = grandparent;

                    if(gGrandparent != null) {
                        if(grandparent == gGrandparent.left) {
                            gGrandparent.left = parent;
                        } else if (grandparent == gGrandparent.right) {
                            gGrandparent.right = parent;
                        }
                    } else {
                        nonOverrideSetRoot(parent);
                    }
                }

                parent.recolour();
                grandparent.recolour();
            }

            return;
        }

        System.out.println("Checking RR conflict on val:" + current + "," + parent);

        // check uncle
        Node<E> uncle = getUncle(current);
        if(uncle == null) {
            System.out.println("THE THINGY UNCLE IS NOT NULL");
            // do suitable rotation and recoloring
            Node<E> grandparent = getGrandparent(current);
            if(grandparent == null) {
                return;
            }

            if(current == parent.right && parent == grandparent.left) {
                System.out.println("our current situation");
                rotateLRTriangle(current, parent, grandparent);
            } else if(current == parent.left && parent == grandparent.right) {
                System.out.println("our current situation2");
                rotateRLTriangle(current, parent, grandparent);
            } else if(current == parent.right && parent == grandparent.right) {
                System.out.println("our current situation3");
                rotateRRLine(current, parent, grandparent);
            } else if(current == parent.left && parent == grandparent.left) {
                System.out.println("our current situation4");
                rotateLLLine(current, parent, grandparent);
            }
        } else {
            // if the uncle is red, recolor
            if(uncle.getIsRed()) {
                parent.setColor(Node.TreeColor.BLACK);
                uncle.setColor(Node.TreeColor.BLACK);
                Node<E> grandparent = getGrandparent(current);
                if(grandparent != null) {
                    if(grandparent != getRoot()) {
                        grandparent.setColor(Node.TreeColor.RED);
                    }

                    Node<E> gGrandparent = getParent(grandparent);
                    System.out.println("Checking conflict with: " + grandparent + "," + gGrandparent);
                    checkRRConflict(grandparent, getParent(grandparent), true);
                }
            }
        }
    }

    @Override
    public void hookNodeTrigger(Node<E> current) {
//        System.out.println("Hooking node trigger");
    }

    public static void main(String[] args) {  // create the binary search tree
        System.out.println("Running BST");
        BalancedPersistentDynamicSet<Integer> tree = new BalancedPersistentDynamicSet<>();

        // build the tree
//        String[] toAddV1 = {"cow", "fly", "dog", "eel"};
        Integer[] toAddV1 = {10, 18, 7, 15, 16, 30, 25, 40, 60, 2, 1, 70};
//        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant"};
//        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "greg", "owl", "pig", "rat", "sheep", "tiger", "wolf", "zebra"};

        for(Integer s : toAddV1) {
            System.out.println("Adding " + s + ": " + tree.add(s));
        }

        // test remove
//        System.out.println("Removing owl: " + tree.remove("owl"));
//        System.out.println("Removing dog: " + tree.remove("dog"));
//
//        // test contains
//        System.out.println("Contains dog: " + tree.contains("dog"));
//        System.out.println("Contains owl: " + tree.contains("owl"));

        for(Map.Entry<Version, Node<Integer>> entry : tree.getRootNodes().entrySet()) {
            if(entry != null && entry.getValue() != null) {
                System.out.println("Version: " + entry.getKey().getNumber() + " - " + entry.getValue().toLinearString());
            }
        }

        System.out.println("Tree: \n" + tree);
    }
}