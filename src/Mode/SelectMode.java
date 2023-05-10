package Mode;

import java.awt.Point;
import java.awt.event.MouseEvent;

import UI.Canvas;
import Object.Group;
import Object.MyObject;

public class SelectMode extends Mode {
    Canvas canvas = Canvas.getInstance();

    public SelectMode() {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        canvas.setSelectedObj(null);
        canvas.clearSelectedList();
        // put all the object at the point into a list
        canvas.getObjectsList().forEach(obj -> {
            if (obj.contains(p)) {
                canvas.addSelectedObject(obj);
            }
        });
        MyObject tmp = null;
        // sort the list by depth and select the top one
        if (canvas.getSelectedObjectsList().size() > 1) {
            canvas.getSelectedObjectsList().sort((o1, o2) -> o2.depth - o1.depth);
            tmp = canvas.getSelectedObjectsList().get(0);
            // get the top one and remove the rest
            canvas.clearSelectedList();
        } else if (canvas.getSelectedObjectsList().size() == 1) {
            tmp = canvas.getSelectedObjectsList().get(0);
        } else {
            // if no object is selected, set the group zone
            canvas.setGroupPoint1(p);
        }
        // update depth so that the object will be on the top
        if (tmp != null) {
            canvas.setSelectedObj(tmp);
            tmp.countRelativeDis(p);
            /*
             * if the object is a group, record the relative distance of each objects in it.
             * Can't update at the group class, because we only want to count it once when I
             * clicked.
             */
            if (tmp instanceof Group) {
                for (MyObject obj : ((Group) tmp).getGroupObjects()) {
                    // record the relative distance in the class MyObject
                    obj.countRelativeDis(p);
                }
            }
            tmp.updateDepth();
        }
        canvas.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point p = e.getPoint();
        if (canvas.getSelectedObj() != null) {
            MyObject tmp = canvas.getSelectedObj();
            tmp.move(p);
        } else {
            canvas.setGroupPoint2(p);
            Point p1 = canvas.getGroupPoint1();
            Point p2 = p;

            canvas.setZone(p2, p1);
        }

        this.canvas.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (canvas.getZone() != null) {
            canvas.getObjectsList().forEach(obj -> {
                if (obj.intersects(canvas.getZone())) {
                    canvas.addSelectedObject(obj);
                }
            });
        }
        canvas.clearZone();
        canvas.repaint();
    }
}
