import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelMovePlane extends JPanel{
    private ITransport plane;
    JButton buttonUp = new JButton(new ImageIcon("src/smallUp.png"));
    JButton buttonDown = new JButton(new ImageIcon("src/smallDown.png"));
    JButton buttonLeft = new JButton(new ImageIcon("src/smallLeft.png"));
    JButton buttonRight = new JButton(new ImageIcon("src/smallRight.png"));
    public class clickListener implements ActionListener {   //реализация интерфейса
        @Override
        public void actionPerformed(ActionEvent event) {
            String actionCommand = event.getActionCommand();
            switch (actionCommand){
                case "up":
                    plane.MoveTransport(Direction.Up);
                    break;
                case "down":
                    plane.MoveTransport(Direction.Down);
                    break;
                case "left":
                    plane.MoveTransport(Direction.Left);
                    break;
                case "right":
                    plane.MoveTransport(Direction.Right);
                    break;
            }
            //paintComponent(getGraphics());
            repaint();
        }
    }
    public void buttonAdd(JButton butt, int x, int y, int width, int height) {
        butt.setBounds(x, y, width, height);
        add(butt);
    }
    public void SetPlane(ITransport plane) {
        this.plane = plane;
        this.plane.SetPosition(50, 50, 880, 480);
    }
    public void Draw(Graphics gr){
        //getGraphics().clearRect(0, 0, 900, 500);
        plane.DrawTransport(gr);
    }
    // @Override
    public void paintComponent(Graphics gr){
        super.paintComponent(gr);
        Draw(gr);
    }
    public PanelMovePlane(){
        setBackground(Color.white);
        setLayout(null);
        buttonAdd(buttonRight, 830, 400, 45, 45);
        buttonAdd(buttonDown, 780, 400, 45, 45);
        buttonAdd(buttonUp, 780, 350, 45, 45);
        buttonAdd(buttonLeft, 730, 400, 45, 45);
        buttonRight.setActionCommand("right");
        buttonDown.setActionCommand("down");
        buttonUp.setActionCommand("up");
        buttonLeft.setActionCommand("left");
        buttonUp.addActionListener(new clickListener());
        buttonDown.addActionListener(new clickListener());
        buttonLeft.addActionListener(new clickListener());
        buttonRight.addActionListener(new clickListener());
    }
}
