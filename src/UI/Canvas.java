package UI;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import Mode.Mode;
import Object.MyObject;
import Object.Line;

public class Canvas extends JPanel {

    private static Canvas instance = null;
    private EventListener listener;
    private List<MyObject> Objects = new ArrayList<MyObject>();
    private List<Line> Lines = new ArrayList<Line>();
    private List<MyObject> selectedObjects = new ArrayList<MyObject>();
    public int curDepth = 0;
    // save the selected object to rename
    private MyObject selectedObj = null;
    private Point p1, p2;

    private Rectangle zone = null;
    private Mode curMode;
    // public JLabel testlabel=new JLabel("start");

    public Canvas() {
        refreshMode();
    }

    public static Canvas getInstance() {
        if (instance == null) {
            instance = new Canvas();
        }

        return instance;
    }

    // change the current mode
    public void setMode(Mode mode) {
        this.curMode = mode;
    }

    // refresh the mode when click the button at toolbox
    public void refreshMode() {
        this.removeMouseListener((MouseListener) this.listener);
        this.removeMouseMotionListener((MouseMotionListener) this.listener);
        this.listener = this.curMode;
        this.addMouseListener((MouseListener) this.listener);
        this.addMouseMotionListener((MouseMotionListener) listener);

    }

    // for all the objects on the canvas
    public void addObject(MyObject obj) {
        this.Objects.add(obj);
    }

    public void removeObject(MyObject obj) {
        this.Objects.remove(obj);
    }

    public List<MyObject> getObjectsList() {
        return this.Objects;
    }

    // To compare which selected object is the top one
    public void addSelectedObject(MyObject obj) {
        this.selectedObjects.add(obj);
    }

    public void clearSelectedList() {
        this.selectedObjects.clear();
    }

    public List<MyObject> getSelectedObjectsList() {
        return this.selectedObjects;
    }

    public void setSelectedObj(MyObject obj) {
        this.selectedObj = obj;
    }

    public MyObject getSelectedObj() {
        return this.selectedObj;
    }

    public void addLine(Line line) {
        this.Lines.add(line);
    }

    public List<Line> getLinesList() {
        return this.Lines;
    }

    // set the group zone
    public void setGroupPoint1(Point p) {
        this.p1 = p;
    }

    public void setGroupPoint2(Point p) {
        this.p2 = p;
    }

    public Point getGroupPoint1() {
        return this.p1;
    }

    public Point getGroupPoint2() {
        return this.p2;
    }

    public void setZone(Point p1, Point p2) {
        this.zone = new Rectangle(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p2.x - p1.x),
                Math.abs(p2.y - p1.y));
    }

    public Rectangle getZone() {
        return this.zone;
    }

    public void clearZone() {
        this.zone = null;
    }

    // reset the canvas
    public void reset() {
        this.selectedObjects.clear();
        this.selectedObj = null;
        this.zone = null;
        this.p1 = null;
        this.p2 = null;
    }

    // Call automatically when repaint()
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // set the background color
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        // set the object stroke
        ((Graphics2D) g).setStroke(new BasicStroke(2));

        Objects.sort((o1, o2) -> o1.depth - o2.depth);
        for (MyObject obj : this.Objects) {
            obj.draw(g);
            if (selectedObj == obj) {
                obj.showPort(g);
            } else if (selectedObjects.contains(obj)) {
                obj.showPort(g);
            }
        }
        // draw selected zone
        if (zone != null) {
            g.setColor(new Color(0, 0, 153, 80));
            g.fillRect(zone.x, zone.y, zone.width, zone.height);
        }
        // draw the lines
        for (Line line : this.Lines) {
            line.drawLine(g);
        }
    }
}