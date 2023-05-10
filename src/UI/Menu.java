package UI;

import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Object.Group;
import Object.MyObject;

public class Menu extends JMenuBar {
    Canvas canvas = Canvas.getInstance();
    private JMenu file = new JMenu("File");
    private JMenu edit = new JMenu("Edit");
    private JMenuItem newItem = new JMenuItem("New");
    private JMenuItem clearItem = new JMenuItem("Clear");

    private JMenuItem changeName = new JMenuItem("Change object name");
    private JMenuItem group = new JMenuItem("Group");
    private JMenuItem UnGroup = new JMenuItem("UnGroup");

    // private Group group = new Group();
    public Menu() {
        this.add(this.file);
        this.add(this.edit);
        this.file.add(this.newItem);
        this.file.add(this.clearItem);
        // change object name
        this.edit.add(this.changeName);
        // action listener can't use this.canvas, because it is in the Menu class, not
        // the Action
        this.changeName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (canvas.getSelectedObj() == null) {
                    JOptionPane.showMessageDialog(null, "Please select an object");
                    return;
                }
                String name = JOptionPane.showInputDialog("Please input the new name");
                if (name == null)
                    return;
                canvas.getSelectedObj().setName(name);
                canvas.repaint();
            }
        });
        // group object
        this.edit.add(this.group);
        this.group.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (canvas.getSelectedObjectsList().size() <= 1) {
                    JOptionPane.showMessageDialog(null, "Please select more than one object");
                    return;
                }
                Group group = new Group();
                canvas.addObject(group);
                canvas.repaint();
            }
        });
        // UnGroup object
        this.edit.add(this.UnGroup);
        this.UnGroup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MyObject tmp = canvas.getSelectedObj();
                if (tmp instanceof Group) {
                    ((Group) tmp).unGroup();
                } else
                    JOptionPane.showMessageDialog(null, "Please select a group object");
                canvas.repaint();
            }
        });
    }
};