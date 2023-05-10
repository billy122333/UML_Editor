package Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class UseCase extends MyObject {

    public UseCase(int x, int y) {
        // call the constructor of MyObject
        super(x, y);
        this.width = 70;
        this.height = 60;
        this.objectName = "Use Case";
        // set ports, the ports will show when the object is selected
        setPort();

        // System.out.println("UseCase Depth" + this.depth);
    }

    // check if the point is in the useCase
    @Override
    public Boolean contains(Point p) {
        double a = this.width / 2;
        double b = this.height / 2;
        double x = p.x - this.x - a;
        double y = p.y - this.y - b;
        // x^2/a^2 + y^2/b^2 <= 1
        return Math.pow(x, 2) / Math.pow(a, 2) + Math.pow(y, 2) / Math.pow(b, 2) <= 1;
    };

    @Override
    public void draw(Graphics g) {
        // fill the oral with the fill color
        g.setColor(Color.WHITE);
        g.fillOval(this.x, this.y, this.width, this.height);
        // draw the oral's frame
        g.setColor(Color.BLACK);
        g.drawOval(this.x, this.y, this.width, this.height);
        // draw the string
        g.setFont(font);
        // caculate the position of the string
        int fontx = x + ((this.width - g.getFontMetrics().stringWidth(objectName)) / 2) + 2;
        int fonty = y + this.height / 2 + 4;
        g.drawString(objectName, fontx, fonty);
    }

}
