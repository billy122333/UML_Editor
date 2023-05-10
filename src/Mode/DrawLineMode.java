package Mode;

import Object.*;
import UI.Canvas;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DrawLineMode extends Mode {
    protected List<MyObject> containedObjects = new ArrayList<MyObject>();;
    protected Port port1, port2;
    Canvas canvas = Canvas.getInstance();
    MyObject obj1, obj2;
    int port1Index, port2Index;
    String lineName;

    public DrawLineMode(String name) {
        this.lineName = name;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        // put all the object at the point into a list
        if (getTopObject(p) != null) {
            this.obj1 = getTopObject(p);
            this.port1Index = this.obj1.getNearbyPortIndex(p);
        }
        // save two ports and draw it with line

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point p = e.getPoint();
        System.out.println(getTopObject(p));
        if (getTopObject(p) != null && this.obj1 != null) {
            this.obj2 = getTopObject(p);
            port2Index = this.obj2.getNearbyPortIndex(p);
            if (port1Index == -1 || port2Index == -1) {
                return;
            }
            Line tmpLine = null;
            switch (this.lineName) {
                case "Association":
                    tmpLine = new AssociationLine(this.obj1, this.obj2, this.port1Index, this.port2Index);
                    break;
                case "Generalization":
                    tmpLine = new GeneralizationLine(this.obj1, this.obj2, this.port1Index, this.port2Index);
                    break;
                case "Composition":
                    tmpLine = new CompositionLine(this.obj1, this.obj2, this.port1Index, this.port2Index);
                    break;
                default:
                    break;
            }
            this.canvas.addLine(tmpLine);
            this.obj1.addRelativeLine(tmpLine);
            this.obj2.addRelativeLine(tmpLine);
        }

        this.clear();
        this.canvas.repaint();
    }

    public void clear() {
        this.obj1 = null;
        this.obj2 = null;
        this.port1 = null;
        this.port2 = null;
        this.port1Index = -1;
        this.port2Index = -1;
        containedObjects.clear();
    }

    // get the top object at the point
    public MyObject getTopObject(Point p) {
        MyObject tmp = null;
        canvas.getObjectsList().forEach(obj -> {
            if (obj.contains(p)) {
                containedObjects.add(obj);
            }
        });
        // sort the list by depth and select the top one
        if (containedObjects.size() > 1) {
            containedObjects.sort((o1, o2) -> o2.depth - o1.depth);
            tmp = containedObjects.get(0);
            // get the top one and remove the rest
            containedObjects.clear();
        } else if (containedObjects.size() == 1) {
            tmp = containedObjects.get(0);
        }
        return tmp;
    }
}
