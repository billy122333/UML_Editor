package Object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import UI.Canvas;

public abstract class MyObject {
    protected Canvas canvas = Canvas.getInstance();
    protected List<Line> relativeLines = new ArrayList<Line>();
    private int portNum = 4;
    protected Port[] ports = new Port[portNum];

    // the position of the object
    protected int x, y;
    protected int width, height;
    protected Font font = new Font(Font.DIALOG, Font.BOLD, 12);

    // change to protected if needed
    public int depth = 0;
    public String objectName = "";

    protected int relativex, relativey;
    protected boolean isPortEnable = true;

    public MyObject() {
        // Set the x and y position of the object
        this.x = 0;
        this.y = 0;
        // 物件深度之後用
        this.depth = canvas.curDepth++;
    }

    public MyObject(int x, int y) {
        // Set the x and y position of the object
        this.x = x;
        this.y = y;
        // 物件深度之後用
        this.depth = canvas.curDepth++;
    }

    // class and use case must override this method
    public abstract void draw(Graphics g);

    public abstract Boolean contains(Point p);

    // if object is selected, show the ports
    public void showPort(Graphics g) {
        if (!this.isPortEnable) {
            return;
        }
        g.setColor(Color.red);
        for (Port port : this.ports) {
            g.fillRect(port.x, port.y, port.portEdge, port.portEdge);
        }
        g.setColor(Color.black);
    }

    public void updateDepth() {
        this.depth = canvas.curDepth++;
    }

    public void setName(String name) {
        this.objectName = name;
    }

    public void setPort() {
        // to set the position of the ports
        this.ports[0] = new Port(this.x + width / 2 - 3, this.y - 4); // up
        this.ports[1] = new Port(this.x + width / 2 - 3, this.y + height - 4); // down

        this.ports[2] = new Port(this.x - 4, this.y + height / 2 - 3); // left
        this.ports[3] = new Port(this.x - 4 + width, this.y + height / 2 - 3); // right
    }

    public void disablePort() {
        this.isPortEnable = false;
    }

    public void enablePort() {
        this.isPortEnable = true;
    }

    // record the relative distance of the mouse and the object start
    public void countRelativeDis(Point p) {
        this.relativex = p.x - this.x;
        this.relativey = p.y - this.y;
    }

    // add the line to the relativeLines list
    public void addRelativeLine(Line line) {
        this.relativeLines.add(line);
    }

    public Port[] getPorts() {
        return this.ports;
    }

    // move the object and reset the position of the ports
    public void move(Point p) {
        this.x = p.x - relativex;
        this.y = p.y - relativey;
        setPort();
        for (Line line : this.relativeLines) {
            line.updateLine();
        }

    }

    public boolean intersects(Rectangle rect) {
        return rect.x < this.x && rect.x + rect.width > this.x + this.width && rect.y < this.y
                && rect.y + rect.height > this.y + this.height;
    }

    // Debuging
    public int getNearbyPortIndex(Point p) {
        if (!this.isPortEnable) {
            return -1;
        }
        double minDis = 100000;
        int index = 0;
        for (int i = 0; i < this.portNum; i++) {
            double tmp = this.ports[i].countDis(p);
            if (tmp < minDis) {
                index = i;
                minDis = tmp;

            }
        }
        return index;
    }
}