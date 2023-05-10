package Object;

import java.awt.Graphics;

import UI.Canvas;

public abstract class Line {

    MyObject obj1, obj2;
    int startPortIndex, endPortIndex;
    Port startPort, endPort;
    Canvas canvas = Canvas.getInstance();

    public abstract void drawLine(Graphics g);

    public Line(MyObject obj1, MyObject obj2, int startPortIndex, int endPortIndex) {
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.startPortIndex = startPortIndex;
        this.endPortIndex = endPortIndex;
        this.startPort = obj1.getPorts()[startPortIndex];
        this.endPort = obj2.getPorts()[endPortIndex];
    }

    public void updateLine() {
        startPort = obj1.getPorts()[startPortIndex];
        endPort = obj2.getPorts()[endPortIndex];
        this.canvas.repaint();
    }
}
