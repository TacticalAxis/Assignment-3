package comp611.assignment3.structure.q3;

import comp611.assignment3.structure.Version;
import comp611.assignment3.structure.q1.Node;
import comp611.assignment3.structure.q2.PersistentDynamicSet;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Map;

@SuppressWarnings("CommentedOutCode")
public class BalancedPersistentDynamicSet<E extends Comparable<E>> extends PersistentDynamicSet<E> {

    private final Deque<Node<E>> nodeStack;

    public BalancedPersistentDynamicSet() {
        super();
        nodeStack = new ArrayDeque<>();
    }

    public Node<E> getParent(Node<E> node) {
        if(!nodeStack.contains(node)) {
            return null;
        }

        if(nodeStack.size() == 1) {
            return null;
        }

        ArrayList<Node<E>> oldNodes = new ArrayList<>();
        Node<E> current = null;
        Node<E> parent = null;
        while (current != node) {
            current = nodeStack.pop();
            oldNodes.add(current);
            parent = nodeStack.peek();
        }

        for (int i = oldNodes.size() - 1; i >= 0; i--) {
            nodeStack.push(oldNodes.get(i));
        }

        return parent;
    }
    private Node<E> getGrandparent(Node<E> node) {
        Node<E> parent = getParent(node);
        if(parent == null) {
            return null;
        }
        return getParent(parent);
    }
    private Node<E> getUncle(Node<E> node) {
        Node<E> parent = getParent(node);
        Node<E> grandparent = getGrandparent(node);
        if(parent == null || grandparent == null) {
            return null;
        }
        if(parent == grandparent.left) {
            return grandparent.right;
        }
        return grandparent.left;
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
                grandparent.left = null;
                node.right = grandparent;
                gGrandparent.left = node;
            }

            grandparent.setColor(Node.TreeColor.RED);
            parent.setColor(Node.TreeColor.RED);
            node.setColor(Node.TreeColor.BLACK);
        } else {
            System.out.println("LMAO");
            System.out.println("node: " + node + " node.left: " + node.left + " node.right: " + node.right);
            System.out.println("parent: " + parent + " parent.left: " + parent.left + " parent.right: " + parent.right);
            System.out.println("grandparent: " + grandparent + " grandparent.left: " + grandparent.left + " grandparent.right: " + grandparent.right);

            node.right = grandparent;
            if(node == grandparent.right) {
                grandparent.right = null;
            } else if(node == grandparent.left) {
                grandparent.left = null;
            }

            grandparent.setColor(Node.TreeColor.RED);
            parent.setColor(Node.TreeColor.RED);
            node.setColor(Node.TreeColor.BLACK);

            nonOverrideSetRoot(node);
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
                grandparent.right = null;
                node.left = grandparent;
                gGrandparent.left = node;
            }

            grandparent.setColor(Node.TreeColor.RED);
            parent.setColor(Node.TreeColor.RED);
            node.setColor(Node.TreeColor.BLACK);
        } else {
            node.left = grandparent;
            if(node == grandparent.right) {
                grandparent.right = null;
            } else if(node == grandparent.left) {
                grandparent.left = null;
            }

            grandparent.setColor(Node.TreeColor.RED);
            parent.setColor(Node.TreeColor.RED);
            node.setColor(Node.TreeColor.BLACK);

            nonOverrideSetRoot(node);
        }
    }

    public void rotateRRLine(Node<E> node, Node<E> parent, Node<E> grandparent) {
        if (node == null || parent == null || grandparent == null) {
            return;
        }

        Node<E> gGrandparent = getParent(grandparent);
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
        nodeStack.clear();

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

        checkRRConflict(current, parent);

        return added;
    }

    private void checkRRConflict(Node<E> current, Node<E> parent) {
        if(current == null || parent == null) {
            return;
        }

        // if the parent is black, return
        if(!parent.getIsRed()) {
            return;
        }

//        System.out.println("Checking RR conflict on val:" + current + "," + parent);

        // check uncle
        Node<E> uncle = getUncle(current);
        if(uncle == null) {
            // do suitable rotation and recoloring
            Node<E> grandparent = getGrandparent(current);
            if(grandparent == null) {
                return;
            }

            if(current == parent.right && parent == grandparent.left) {
                rotateLRTriangle(current, parent, grandparent);
            } else if(current == parent.left && parent == grandparent.right) {
                rotateRLTriangle(current, parent, grandparent);
            } else if(current == parent.right && parent == grandparent.right) {
                rotateRRLine(current, parent, grandparent);
            } else if(current == parent.left && parent == grandparent.left) {
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

                    checkRRConflict(grandparent, getParent(grandparent));
                }
            } else {
                // if the uncle is black, do suitable rotation and recoloring
                Node<E> grandparent = getGrandparent(current);
                if(grandparent == null) {
                    return;
                }

                if(current == parent.right && parent == grandparent.left) {
                    rotateLRTriangle(current, parent, grandparent);
                } else if(current == parent.left && parent == grandparent.right) {
                    rotateRLTriangle(current, parent, grandparent);
                } else if(current == parent.right && parent == grandparent.right) {
                    rotateRRLine(current, parent, grandparent);
                } else if(current == parent.left && parent == grandparent.left) {
                    rotateLLLine(current, parent, grandparent);
                }
            }
        }
    }

    @Override
    public void hookNodeTrigger(Node<E> current) {
        nodeStack.push(current);
    }

    public static void main(String[] args) {  // create the binary search tree
        System.out.println("Running BST");
        BalancedPersistentDynamicSet<String> tree = new BalancedPersistentDynamicSet<>();

        // build the tree
        String[] toAddV1 = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "pig", "owl", "rat"};

        for(String s : toAddV1) {
            System.out.println("Adding " + s + ": " + tree.add(s));
        }

        // test remove
//        System.out.println("Removing owl: " + tree.remove("owl"));
//        System.out.println("Removing dog: " + tree.remove("dog"));
//
//        // test contains
        System.out.println("Contains dog: " + tree.contains("dog"));
        System.out.println("Contains owl: " + tree.contains("owl"));

        for(Map.Entry<Version, Node<String>> entry : tree.getRootNodes().entrySet()) {
            if(entry != null && entry.getValue() != null) {
                System.out.println("Version: " + entry.getKey().getNumber() + " - " + entry.getValue().toLinearString());
            }
        }

        System.out.println("Tree: \n" + tree);
    }
}