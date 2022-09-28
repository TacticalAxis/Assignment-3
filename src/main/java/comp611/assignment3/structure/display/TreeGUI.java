package comp611.assignment3.structure.display;

import javax.swing.*;

// class that shows a picture of a tree
public class TreeGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    public TreeGUI() {
        super("Tree");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        // add an image to the frame
        JLabel label = new JLabel(new ImageIcon("src/main/resources/tree.jpg"));
        add(label);

        setVisible(true);
    }

    public static void main(String[] args) {
        new TreeGUI();
    }
}