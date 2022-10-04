package comp611.assignment3.structure.task;

import comp611.assignment3.structure.task.model.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

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

    private Node<E> getParentFromRoot(Node<E> node) {
        if(node == null) {
            return null;
        }

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

    @Override
    public boolean remove(E value) {
        nodeStack.clear();

        if(value == null) {
            return false;
        }

        Node<E> node = query(value);
        if(node == null) {
            return false;
        }

        // if the node is external and is red, then just remove it
        if(!node.isInternal() && node.isRed()) {
            System.out.println("Case 1");
            return super.remove(value);
        }

        // if the node is black and has one child that is red
        if(node.isInternal() && node.hasOneChild() && !node.isRed()) {
            System.out.println("Case 2");
            Node<E> child = node.left != null ? node.left : node.right;
            E valueToSet = child.value;
            boolean removed = super.remove(value);
            if(removed) {
                node.value = valueToSet;
            }
            return removed;
        }

        // if it is red, and has two black children, then do find min on right subtree, set the current node's value to the min value, and remove the min value
        if(node.isRed() && node.hasTwoChildren()) {
            System.out.println("Case 3");
            // given that the children are both black
            Node<E> minNode = query(findMin(node.right));
            deleteAndReplace(node);

            if(!minNode.isInternal()) {
                Node<E> p = getParentFromRoot(minNode);
                if(p != null) {
                    p.right = null;
                }
            } else {
                Node<E> p = getParentFromRoot(minNode);
                if(p != null) {
                    p.right = minNode.right;
                }
            }

            return true;
        }

        // if the node is black, and has two children
        // go into the right subtree, find the min value, set the current node's value to the min value, and remove the min value
        // but if the min value node had a right child, then set the min value node's parent's left child to the min value node's right child
        if(!node.isRed() && node.hasTwoChildren()) {
            System.out.println("Case 4");
            Node<E> minNode = query(findMin(node.right));
            E valueToSet = minNode.value;
            boolean removed = super.remove(valueToSet);
            if(removed) {
                node.value = valueToSet;
            }

            // set min node's parent's left child to min node's right child if it exists
            Node<E> minNodeParent = getParent(minNode);
            if(minNode.right != null) {
                if(minNodeParent != null) {
                    minNodeParent.left = minNode.right;
                }
            } else {
                if(minNodeParent != null) {
                    minNodeParent.left = null;
                }
            }

            return removed;
        }

        return true;
    }

    public void deleteAndReplace(Node<E> node) {
        // step 1: find the in-order successor
        Node<E> successor = node.right;
        while(successor.left != null) {
            successor = successor.left;
        }

        // step 2: swap the values
        E temp = node.value;
        node.value = successor.value;
        successor.value = temp;

        // step 3: remove the successor
        remove(successor.value);
    }

    private void checkRRConflict(Node<E> current, Node<E> parent) {
        if(current == null || parent == null) {
            return;
        }

        // if the parent is black, return
        if(!parent.isRed()) {
            return;
        }

        // check uncle
        Node<E> uncle = getUncle(current);
        if(uncle == null) {
            // do suitable rotation and recoloring
            Node<E> grandparent = getGrandparent(current);
            if(grandparent == null) {
                return;
            }

            // depending on the position of the current node, do the rotation
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
            if(uncle.isRed()) {
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

                // depending on the position of the current node, do the rotation
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
        // print all the values of the stack in the order they were added
        boolean contains = false;
        for(Node<E> node : nodeStack) {
            if(node.value == current.value) {
                contains = true;
                break;
            }
        }

        // if the stack does not contain the current node, then add it
        if(!contains) {
            nodeStack.push(current);
        }
    }

    public static void main(String[] args) {
        // TEST WITH STRINGS
        System.out.println("Running BST - With String");
        BalancedPersistentDynamicSet<String> bpdsStr = new BalancedPersistentDynamicSet<>();

        // build the tree
        String[] testStrValues = {"cow", "fly", "dog", "bat", "fox", "cat", "eel", "ant", "pig", "owl", "rat"};

        for(String s : testStrValues) {
            System.out.println("Adding " + s + ": " + bpdsStr.add(s));
        }

        System.out.println(bpdsStr);
        System.out.println("Size: " + bpdsStr.size());
        System.out.println("Versions: " + bpdsStr.getRevisions());

        // test contains
        System.out.println("Contains dog: " + bpdsStr.contains("dog")); // true
        System.out.println("Contains owl: " + bpdsStr.contains("owl")); // true

        // test remove
        System.out.println("Removing owl: " + bpdsStr.remove("owl")); // true

        System.out.println(bpdsStr);

        // TEST WITH INTEGERS
        System.out.println("Running BST - With Integers");
        BalancedPersistentDynamicSet<Integer> bpdsInt = new BalancedPersistentDynamicSet<>();

        // build the tree
        Integer[] testIntValues = {1,14,23,11,5,8,15,7,22,17,13,16,18,10,21,12,3,6,2,4,20,9,0,19};

        for(Integer s : testIntValues) {
            System.out.println("Adding " + s + ": " + bpdsInt.add(s));
        }

        System.out.println(bpdsInt);
        System.out.println("Size: " + bpdsInt.size());
        System.out.println("Versions: " + bpdsInt.getRevisions());

        // test contains
        System.out.println("Contains 33: " + bpdsInt.contains(33));
        System.out.println("Contains 22: " + bpdsInt.contains(22));

        // test remove
        System.out.println("Removing 27: " + bpdsInt.remove(27)); // test case 1
        System.out.println("Removing 6: " + bpdsInt.remove(6)); // test case 2
        System.out.println("Removing 18: " + bpdsInt.remove(18)); // test case 3
        System.out.println("Removing 1: " + bpdsInt.remove(1)); // test case 4

        // test tree printing out
        System.out.println(bpdsInt);
    }
}