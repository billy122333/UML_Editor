package UI;

// import javax.swing.JPanel;

// import java.awt.Dimension;

// import javax.swing.JButton;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Mode.*;
import Object.MyButton;

public class Toolbox extends JPanel {
    private MyButton curBtn = null;
    private int Toolnum = 6;
    private Canvas canvas = new Canvas();
    private final MyButton[] Buttonlist = new MyButton[Toolnum];

    private final String[] ButtonName = { "select", "association line", "generalization line", "composition line",
            "class", "use case" };

    // TODO : Change the case to switch
    private final Mode[] CurrentMode = { new SelectMode(), new DrawLineMode("Association"),
            new DrawLineMode("Generalization"),
            new DrawLineMode("Composition"),
            new DrawObjMode("Class"),
            new DrawObjMode("UseCase") };

    public Toolbox() {
        this.canvas = Canvas.getInstance();
        this.setLayout(new GridLayout(Toolnum, 1, 20, 0));
        this.setBackground(Color.white);

        for (int i = 0; i < Toolnum; i++) {
            this.Buttonlist[i] = new MyButton();
            this.Buttonlist[i].setButton(this.ButtonName[i], this.CurrentMode[i]);
            this.Buttonlist[i].addActionListener(new BtnAct());
            this.add(this.Buttonlist[i]);
        }
    }

    // implement ActionListener
    class BtnAct implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (curBtn != null)
                curBtn.BtnOff();
            curBtn = (MyButton) e.getSource();
            curBtn.BtnOn();
            canvas.setMode(curBtn.getMode());
            canvas.refreshMode();
            canvas.reset();
            canvas.repaint();
        }
    }
};