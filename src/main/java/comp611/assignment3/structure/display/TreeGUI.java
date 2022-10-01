package comp611.assignment3.structure.display;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// class that shows a picture of a tree
public class TreeGUI extends JFrame implements MouseListener {
    private static final long serialVersionUID = 1L;

    public TreeGUI() {
        super("Tree");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        // add an image to the frame
        JLabel label = new JLabel(new ImageIcon("src/main/resources/tree.jpg"));
        add(label);

        setVisible(true);

        addMouseListener(this);
    }

    public static void main(String[] args) {
        new TreeGUI();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // open dialog box
        JOptionPane.showMessageDialog(this, "KEANNA BULLY ME 😀😀 she tol me to get good grades");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mousePressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("mouseEntered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("mouseExited");
    }
}