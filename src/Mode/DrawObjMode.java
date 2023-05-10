package Mode;

import java.awt.Point;
import java.awt.event.MouseEvent;

import Object.Class;
import Object.MyObject;
import Object.UseCase;

public class DrawObjMode extends Mode {
    String objName;

    public DrawObjMode(String name) {
        this.objName = name;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        Point p = e.getPoint();
        MyObject obj = null;
        switch (this.objName) {
            case "Class":
                obj = new Class(p.x, p.y);
                break;
            case "UseCase":
                obj = new UseCase(p.x, p.y);
                break;
            default:
                break;
        }
        this.canvas.addObject(obj);
        // update the canvas to show the new element
        this.canvas.repaint();
    }
}