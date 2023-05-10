package Object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class CompositionLine extends Line {
    int diamondSize = 20;

    public CompositionLine(MyObject obj1, MyObject obj2, int startPortIndex, int endPortIndex) {
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

        // draw the diamond
        int pointDisx = centerEndPortX - centerStartPortX;
        int pointDisy = centerEndPortY - startPort.y;
        double angle = Math.atan2(pointDisy, pointDisx);
        int point1X = (int) (centerEndPortX - diamondSize / 2 * Math.cos(angle));
        int point1Y = (int) (centerEndPortY - diamondSize / 2 * Math.sin(angle));
        int point2X = (int) (centerEndPortX + diamondSize / 2 * Math.cos(angle + Math.PI / 2));
        int point2Y = (int) (centerEndPortY + diamondSize / 2 * Math.sin(angle + Math.PI / 2));
        int point3X = (int) (centerEndPortX + diamondSize / 2 * Math.cos(angle));
        int point3Y = (int) (centerEndPortY + diamondSize / 2 * Math.sin(angle));
        int point4X = (int) (centerEndPortX + diamondSize / 2 * Math.cos(angle - Math.PI / 2));
        int point4Y = (int) (centerEndPortY + diamondSize / 2 * Math.sin(angle - Math.PI / 2));
        int[] xPoints = { point1X, point2X, point3X, point4X };
        int[] yPoints = { point1Y, point2Y, point3Y, point4Y };
        int nPoints = 4;
        g.setColor(Color.BLACK);
        g.drawPolygon(xPoints, yPoints, nPoints); // first arrowhead
        // draw the arrowhead's fill color
        g.setColor(Color.WHITE);
        g.fillPolygon(xPoints, yPoints, nPoints); // first arrowhead
        // draw the arrowhead's outline
    }

}
