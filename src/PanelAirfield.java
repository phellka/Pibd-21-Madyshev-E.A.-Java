import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Objects;

public class PanelAirfield  extends JPanel {
    private Airfield<Plane, RadarOne> airfield;
    JButton buttonSetPlane = new JButton("<html>Припарковать<br>самолет");
    JButton buttonSetPlaneRadar = new JButton("<html>Припарковать<br>самолет с<br>радаром");
    JLabel labelGetPlane = new JLabel("Забрать самолет");
    JLabel labelPlace = new JLabel("Место: ");
    JButton buttonGetPlane = new JButton("Забрать");
    JFormattedTextField textFieldGetPlane;
    WindowMovePlane windowMovePlane = new WindowMovePlane();
    public class setGetPlaneListener implements ActionListener {   //реализация интерфейса
        @Override
        public void actionPerformed(ActionEvent event) {
            String actionCommand = event.getActionCommand();
            switch (actionCommand) {
                case "set plane":
                    Color planeColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);
                    var plane = new Plane(100, 1000, planeColor);
                    if ((airfield.plus(plane)) > -1) {
                        repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Аэродром переполнен");
                    }
                    break;
                case "set plane radar":
                    Color mainColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);
                    Color dopColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);
                    var planeRadar = new PlaneRadar(100, 1000, mainColor, dopColor,true, true);
                    if ((airfield.plus(planeRadar)) > -1) {
                        repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Аэродром переполнен");
                    }
                    break;
                case "get plane":
                    if (!Objects.equals(textFieldGetPlane.getText(), "")) {
                        var takenPlane = airfield.minus(Integer.parseInt(textFieldGetPlane.getText()));
                        if (takenPlane != null) {
                            windowMovePlane.SetPlane(takenPlane);
                            windowMovePlane.setVisible(true);
                        }
                        repaint();
                    }
                    break;
            }
        }
    }
    public void smthAdd(JComponent butt, int x, int y, int width, int height) {
        butt.setBounds(x, y, width, height);
        add(butt);
    }
    public PanelAirfield(){
        airfield = new Airfield<Plane, RadarOne>(900, 500);
        windowMovePlane.setModal(true);
        //panel
        setBackground(Color.white);
        setLayout(null);
        buttonSetPlane.setActionCommand("set plane");
        buttonSetPlaneRadar.setActionCommand("set plane radar");
        buttonGetPlane.setActionCommand("get plane");
        smthAdd(buttonSetPlane, 765, 5, 110, 35);
        smthAdd(buttonSetPlaneRadar, 765, 50, 110, 45);
        smthAdd(labelGetPlane, 765, 120, 100, 10);
        smthAdd(labelPlace, 765, 140, 100, 10);
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setAllowsInvalid(false);
        textFieldGetPlane = new JFormattedTextField(formatter);
        smthAdd(textFieldGetPlane, 810, 135, 30, 20);
        smthAdd(buttonGetPlane, 765, 155, 100, 40);
        buttonSetPlane.addActionListener(new setGetPlaneListener());
        buttonSetPlaneRadar.addActionListener(new setGetPlaneListener());
        buttonGetPlane.addActionListener(new setGetPlaneListener());
    }
    private void Draw(Graphics gr) {
        airfield.Draw(gr);
    }
    @Override
    public void paintComponent(Graphics gr){
        super.paintComponent(gr);
        Draw(gr);
    }
}
