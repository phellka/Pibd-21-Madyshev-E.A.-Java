import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
public class MainField extends JPanel{
    private PlaneRadar plane;
    JButton buttonCreate = new JButton("создать");
    JButton buttonUp = new JButton(new ImageIcon("src/smallUp.png"));
    JButton buttonDown = new JButton(new ImageIcon("src/smallDown.png"));
    JButton buttonLeft = new JButton(new ImageIcon("src/smallLeft.png"));
    JButton buttonRight = new JButton(new ImageIcon("src/smallRight.png"));

    public class clickListener implements  ActionListener{   //реализация интерфейса
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
            draw();
        }
    }
    public void buttonAdd(JButton butt, int x, int y, int width, int heidht) {
        butt.setBounds(x, y, width, heidht);
        add(butt);
    }
    private void draw() {
        plane.DrawTransport(getGraphics());
        paintComponents(getGraphics());
    }
    public MainField(){
        setBackground(Color.white);
        setLayout(null);
        buttonRight.setActionCommand("right");
        buttonDown.setActionCommand("down");
        buttonUp.setActionCommand("up");
        buttonLeft.setActionCommand("left");
        buttonAdd(buttonCreate, 5, 5, 88, 20);
        buttonAdd(buttonRight, 830, 400, 45, 45);
        buttonAdd(buttonDown, 780, 400, 45, 45);
        buttonAdd(buttonUp, 780, 350, 45, 45);
        buttonAdd(buttonLeft, 730, 400, 45, 45);
        buttonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rnd = new Random();
                plane = new PlaneRadar();
                plane.Init(rnd.nextInt(3000) + 1000, rnd.nextInt(2000) + 10000, Color.blue,
                        Color.yellow, true, true);
                plane.SetPosition(rnd.nextInt(100), rnd.nextInt(100), getWidth(), getHeight());
                draw();
            }
        });
        buttonUp.addActionListener(new clickListener());
        buttonDown.addActionListener(new clickListener());
        buttonLeft.addActionListener(new clickListener());
        buttonRight.addActionListener(new clickListener());
    }
    /*
    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
    }*/
}
//panel.getGraphics();
//https://csharpcoderr.com/5377/
//https://www.youtube.com/watch?v=VnogOoOQZIE