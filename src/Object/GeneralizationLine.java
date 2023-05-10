package Object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GeneralizationLine extends Line {
    int arrowLength = 20;

    public GeneralizationLine(MyObject obj1, MyObject obj2, int startPortIndex, int endPortIndex) {
        super(obj1, obj2, startPortIndex, endPortIndex);
    }

    @Override
    public void drawLine(Graphics g) {
        ((Graphics2D) g).setStroke(new BasicStroke(3));
        int halfEdge = startPort.portEdge / 2;
        int centerStartPortX = startPort.x + halfEdge;
        int centerStartPortY = startPort.y + halfEdge;
        int centerEndPortX = endPort.x + halfEdge;
        int centerEndPortY = endPort.y + halfEdge;
        // draw the main line
        g.setColor(Color.BLACK);
        g.drawLine(centerStartPortX, centerStartPortY, centerEndPortX, centerEndPortY);

        // draw the arrow
        int pointDisx = centerEndPortX - centerStartPortX;
        int pointDisy = centerEndPortY - startPort.y;
        double angle = Math.atan2(pointDisy, pointDisx);
        int[] xPoints = { centerEndPortX, (int) (centerEndPortX - arrowLength * Math.cos(angle - Math.PI / 6)),
                (int) (centerEndPortX - arrowLength * Math.cos(angle + Math.PI / 6)) };
        int[] yPoints = { centerEndPortY, (int) (centerEndPortY - arrowLength * Math.sin(angle - Math.PI / 6)),
                (int) (centerEndPortY - arrowLength * Math.sin(angle + Math.PI / 6)) };
        int nPoints = 3;
        // TODO Auto-generated method stub
        g.setColor(Color.BLACK);
        g.drawPolygon(xPoints, yPoints, nPoints); // first arrowhead
        // draw the arrowhead's fill color
        g.setColor(Color.WHITE);
        g.fillPolygon(xPoints, yPoints, nPoints); // first arrowhead
    }
}
