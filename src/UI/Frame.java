package UI;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Frame extends JFrame {
    private static Canvas canvas = new Canvas();
    private Toolbox toolbox = new Toolbox();
    private Menu menulist = new Menu();

    public Frame() {
        canvas = Canvas.getInstance();

        this.setTitle("UML editor");
        this.setBounds(10, 10, 1000, 720);
        this.setLayout(new BorderLayout());
        this.getContentPane().add(canvas, BorderLayout.CENTER);
        this.add(menulist, BorderLayout.NORTH);
        this.add(toolbox, BorderLayout.WEST);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}