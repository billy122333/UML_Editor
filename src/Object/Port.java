package Object;

import java.awt.Point;
import java.awt.Rectangle;

public class Port extends Rectangle {
    public int portEdge = 10;
    public int x, y;

    public Port(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Debuging
    public double countDis(Point p) {
        return Math.sqrt(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2));
    }
}
