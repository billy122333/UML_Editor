package Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import UI.Canvas;

public class Group extends MyObject {
    List<MyObject> groupObjects = new ArrayList<MyObject>();
    List<Group> groupGroups = new ArrayList<Group>();
    Canvas canvas = Canvas.getInstance();

    public Group() {
        disablePort();
        // add the selected objects to the group
        for (MyObject obj : canvas.getSelectedObjectsList()) {
            obj.disablePort();
            add(obj);
            canvas.getObjectsList().remove(obj);
        }
        // after grouped, clear the selected objects
        canvas.getSelectedObjectsList().clear();
        setBounded();
    }

    @Override
    public void draw(Graphics g) {
        for (MyObject obj : groupObjects) {
            obj.draw(g);
        }
        g.setColor(new Color(0, 0, 200, 10));
        g.fillRect(this.x, this.y, width, height);
    }

    @Override
    public Boolean contains(Point p) {
        return p.x <= this.x + this.width && p.x >= this.x && p.y <= this.y + this.height && p.y >= this.y;
    }

    public void add(MyObject obj) {
        if (obj instanceof Group) {
            // If the object to be added is a Group, add its objects recursively
            Group group = (Group) obj;
            groupGroups.add(group);
            for (MyObject obj2 : group.getGroupObjects()) {
                groupObjects.add(obj2);
            }
        } else {
            groupObjects.add(obj);
            obj.disablePort();
        }
    }

    public void setBounded() {
        int minX = groupObjects.get(0).x, minY = groupObjects.get(0).y;
        int maxX = 0, maxY = 0;
        // find the start point and end point of the groupzone
        for (MyObject obj : groupObjects) {
            if (obj.x < minX) {
                minX = obj.x;
            }
            if (obj.y < minY) {
                minY = obj.y;
            }
            if (obj.x + obj.width > maxX) {
                maxX = obj.x + obj.width;
            }
            if (obj.y + obj.height > maxY) {
                maxY = obj.y + obj.height;
            }
        }
        this.x = minX;
        this.y = minY;
        this.width = maxX - minX;
        this.height = maxY - minY;
    }

    public List<MyObject> getGroupObjects() {
        return groupObjects;
    }

    public void move(Point p) {
        this.x = p.x - relativex;
        this.y = p.y - relativey;
        for (MyObject obj : groupObjects) {
            obj.move(p);
        }
        this.canvas.repaint();
    }

    public void unGroup() {
        for (Group group : groupGroups) {
            canvas.getObjectsList().add(group);
        }
        for (MyObject obj : groupObjects) {
            if (obj instanceof Group) {
                // If the object to be added is a Group, add its objects recursively
                System.out.println("ungroup");
                canvas.getObjectsList().add(obj);
            } else {
                obj.enablePort();
                canvas.getObjectsList().add(obj);
            }
        }
        canvas.getObjectsList().remove(this);
        canvas.repaint();
    }
}
