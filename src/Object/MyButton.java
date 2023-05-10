package Object;

import javax.swing.*;
import java.awt.*;
import Mode.Mode;

public class MyButton extends JButton {
    private Mode mode = new Mode();
    private String name = new String();

    public MyButton() {

    }

    public void setButton(String name, Mode mode) {
        this.mode = mode;
        this.name = name;
        this.setBackground(Color.white);
        // this.setSize(300, 300);
        // this.setText(name);

        // set button
        ImageIcon imageIcon = new ImageIcon("src\\Image\\" + name + ".png");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(150, 100, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        this.setIcon(imageIcon);
    }

    public Mode getMode() {
        return this.mode;
    }

    public void BtnOn() {
        this.setBackground(Color.gray);
    }

    public void BtnOff() {
        this.setBackground(Color.white);
    }

}
