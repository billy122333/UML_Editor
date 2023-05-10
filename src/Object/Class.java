package Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Class extends MyObject {

    public Class(int x, int y) {
        // call the constructor of MyObject
        super(x, y);
        this.width = 80;
        this.height = 100;
        this.objectName = "Class Name";
        // set ports, the ports will show when the object is selected
        setPort();

        // System.out.println("Class Depth" + this.depth);
    }

    // check if the point is in the class
    @Override
    public Boolean contains(Point p) {
        // x in [x, x + width] and y in [y, y + height]
        return p.x <= this.x + this.width && p.x >= this.x && p.y <= this.y + this.height && p.y >= this.y;
    };

    @Override
    public void draw(Graphics g) {
        // Fill the rectangle with the fill color
        g.setColor(Color.white);
        g.fillRect(this.x, this.y, this.width, this.height);
        // Draw the rectangle's frame
        g.setColor(Color.black);
        g.drawRect(this.x, this.y, this.width, this.height);

        // Draw the line in the class
        int Distance = this.height / 3;
        g.drawLine(this.x, this.y + Distance, this.x + width, this.y + Distance);
        g.drawLine(this.x, this.y + Distance * 2, this.x + width, this.y + Distance * 2);

        int stringWidth = g.getFontMetrics(this.font).stringWidth(this.objectName);
        int toCenter = (this.width - stringWidth) / 2;
        g.setFont(this.font);
        g.drawString(this.objectName, this.x + toCenter, this.y + 25);
    }

}