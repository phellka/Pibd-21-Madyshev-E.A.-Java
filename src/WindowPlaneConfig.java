import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class WindowPlaneConfig  extends JFrame {
    JPanel mainPanel = new JPanel();
    private Vehicle plane;
    PanelShowPlane panelShowPlane = new PanelShowPlane();
    JLabel labelCharacteristics = new JLabel("");
    JLabel labelPlaneType = new JLabel("Тип самолета");
    JLabel labelParameters = new JLabel("Параметры");
    JLabel labelColors = new JLabel("Цвета");
    JLabel labelPlane = new JLabel("<html>обычный<br>самолет");
    JLabel labelPlaneRadar = new JLabel("<html>самолет с<br>радаром");
    JLabel mainColor = new JLabel("Основной цвет");
    JLabel dopColor = new JLabel("Доп. цвет");
    JLabel formRadar = new JLabel("форма радара");
    JLabel labelFRadar = new JLabel("полукруглый");
    JLabel labelSRadar = new JLabel("круглый");
    JLabel labelTRadar = new JLabel("утонченный");
    JCheckBox checkBoxEngine = new JCheckBox("есть двигатель");
    JCheckBox checkBoxRadar = new JCheckBox("есть радар");
    JSpinner spinnerMaxSpeed = new JSpinner(new SpinnerNumberModel(100, 100, 1000, 1));
    JSpinner spinnerWeight = new JSpinner(new SpinnerNumberModel(100, 100, 1000, 1));
    JButton createPlane = new JButton("создать");
    JButton closePlane = new JButton("закрыть");
    WindowPlaneConfig(PanelAirfield parentPanel){
        setTitle("характеристика самолета");
        setSize(900, 500);
        setLocation(500, 300);
        add(mainPanel);
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(null);
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 0));
        labelPlane.setBorder(border);
        labelPlaneRadar.setBorder(border);
        panelShowPlane.setBorder(border);
        mainColor.setBorder(border);
        dopColor.setBorder(border);
        formRadar.setBorder(border);
        labelFRadar.setBorder(border);
        labelSRadar.setBorder(border);
        labelTRadar.setBorder(border);
        smthAdd(labelPlaneType, 10, 0, 100, 20);
        smthAdd(labelPlane, 10, 20, 70, 50);
        smthAdd(labelPlaneRadar, 10, 75, 70, 50);
        smthAdd(panelShowPlane, 110, 5, 200, 120);
        smthAdd(labelCharacteristics, 110, 5, 200, 120);
        smthAdd(labelColors, 350, 0, 50, 20);
        smthAdd(mainColor, 350, 25, 110, 20);
        smthAdd(dopColor, 500, 25, 110, 20);
        smthAdd(labelParameters, 10, 130, 70, 20);
        smthAdd(checkBoxEngine, 10, 150, 120, 20);
        smthAdd(checkBoxRadar, 10, 180, 120, 20);
        smthAdd(spinnerMaxSpeed, 150, 150, 50, 20);
        smthAdd(spinnerWeight, 150, 180, 50, 20);
        smthAdd(formRadar, 70, 210, 100, 20);
        smthAdd(labelFRadar, 10, 240, 60, 20);
        smthAdd(labelSRadar, 75, 240, 80, 20);
        smthAdd(labelTRadar, 160, 240, 80, 20);
        smthAdd(createPlane, 470, 170, 90, 20);
        smthAdd(closePlane, 470, 195, 90, 20);
        JPanel panelRed = new JPanel();
        panelRed.setBackground(Color.RED);
        panelRed.setBorder(border);
        JPanel panelYellow = new JPanel();
        panelYellow.setBackground(Color.YELLOW);
        panelYellow.setBorder(border);
        JPanel panelGreen = new JPanel();
        panelGreen.setBackground(Color.GREEN);
        panelGreen.setBorder(border);
        JPanel panelWhite = new JPanel();
        panelWhite.setBackground(Color.WHITE);
        panelWhite.setBorder(border);
        JPanel panelBlack = new JPanel();
        panelBlack.setBackground(Color.BLACK);
        panelBlack.setBorder(border);
        JPanel panelPink = new JPanel();
        panelPink.setBackground(Color.PINK);
        panelPink.setBorder(border);
        JPanel panelBlue = new JPanel();
        panelBlue.setBackground(Color.BLUE);
        panelBlue.setBorder(border);
        JPanel panelOrange = new JPanel();
        panelOrange.setBackground(Color.ORANGE);
        panelOrange.setBorder(border);
        smthAdd(panelRed, 365, 50, 50, 50);
        smthAdd(panelYellow, 425, 50, 50, 50);
        smthAdd(panelGreen, 485, 50, 50, 50);
        smthAdd(panelWhite, 545, 50, 50, 50);
        smthAdd(panelBlack, 365, 110, 50, 50);
        smthAdd(panelPink, 425, 110, 50, 50);
        smthAdd(panelBlue, 485, 110, 50, 50);
        smthAdd(panelOrange, 545, 110, 50, 50);
        var typeDragMouseAdapter = new DragMouseAdapter();
        var colorDragMouseAdapter = new DragMouseAdapter();
        var radarDragMouseAdapter = new DragMouseAdapter();
        labelPlane.addMouseListener(typeDragMouseAdapter);
        labelPlane.setTransferHandler(new TransferHandler("text"));
        labelPlaneRadar.addMouseListener(typeDragMouseAdapter);
        labelPlaneRadar.setTransferHandler(new TransferHandler("text"));
        mainColor.addMouseListener(colorDragMouseAdapter);
        mainColor.setTransferHandler(new TransferHandler("background"));
        dopColor.addMouseListener(colorDragMouseAdapter);
        dopColor.setTransferHandler(new TransferHandler("background"));
        labelFRadar.addMouseListener(radarDragMouseAdapter);
        labelFRadar.setTransferHandler(new TransferHandler("text"));
        labelFRadar.setDropTarget(null);
        labelSRadar.addMouseListener(radarDragMouseAdapter);
        labelSRadar.setTransferHandler(new TransferHandler("text"));
        labelSRadar.setDropTarget(null);
        labelTRadar.addMouseListener(radarDragMouseAdapter);
        labelTRadar.setTransferHandler(new TransferHandler("text"));
        labelTRadar.setDropTarget(null);
        formRadar.addMouseListener(radarDragMouseAdapter);
        formRadar.setTransferHandler(new TransferHandler("text"));
        panelRed.addMouseListener(colorDragMouseAdapter);
        panelRed.setTransferHandler(new TransferHandler("background"));
        panelRed.setDropTarget(null);
        panelBlack.addMouseListener(colorDragMouseAdapter);
        panelBlack.setTransferHandler(new TransferHandler("background"));
        panelBlack.setDropTarget(null);
        panelBlue.addMouseListener(colorDragMouseAdapter);
        panelBlue.setTransferHandler(new TransferHandler("background"));
        panelBlue.setDropTarget(null);
        panelGreen.addMouseListener(colorDragMouseAdapter);
        panelGreen.setTransferHandler(new TransferHandler("background"));
        panelGreen.setDropTarget(null);
        panelOrange.addMouseListener(colorDragMouseAdapter);
        panelOrange.setTransferHandler(new TransferHandler("background"));
        panelOrange.setDropTarget(null);
        panelYellow.addMouseListener(colorDragMouseAdapter);
        panelYellow.setTransferHandler(new TransferHandler("background"));
        panelYellow.setDropTarget(null);
        panelPink.addMouseListener(colorDragMouseAdapter);
        panelPink.setTransferHandler(new TransferHandler("background"));
        panelPink.setDropTarget(null);
        panelWhite.addMouseListener(colorDragMouseAdapter);
        panelWhite.setTransferHandler(new TransferHandler("background"));
        panelWhite.setDropTarget(null);
        labelCharacteristics.addMouseListener(typeDragMouseAdapter);
        labelCharacteristics.setTransferHandler(new TransferHandler("text"));
        PropertyChangeListener typeListener = propertyChangeEvent -> {
            if (Objects.equals(labelCharacteristics.getText(), "<html>обычный<br>самолет")) {
                setPlane();
            }
            if (Objects.equals(labelCharacteristics.getText(), "<html>самолет с<br>радаром")){
                setPlaneRadar();
            }
            labelCharacteristics.setText("");
        };
        PropertyChangeListener mainColorListener = propertyChangeEvent -> {
            if (plane != null) {
                plane.setMainColor(mainColor.getBackground());
                panelShowPlane.setPlane(plane);
                repaint();
            }
        };
        PropertyChangeListener dopColorListener = propertyChangeEvent -> {
            if (plane != null && plane.getClass().getSimpleName().equals("PlaneRadar")) {
                PlaneRadar bufPlane = (PlaneRadar)plane;
                bufPlane.setDopColor(dopColor.getBackground());
                plane = bufPlane;
                panelShowPlane.setPlane(plane);
                repaint();
            }
        };
        PropertyChangeListener formRadarListener = propertyChangeEvent -> {
            if (plane != null && plane.getClass().getSimpleName().equals("PlaneRadar")) {
                PlaneRadar bufPlane = (PlaneRadar)plane;
                if (Objects.equals(formRadar.getText(), "полукруглый")) {
                    bufPlane.setRadars(new RadarOne());
                }
                if (Objects.equals(formRadar.getText(), "круглый")) {
                    bufPlane.setRadars(new RadarTwo());
                }
                if (Objects.equals(formRadar.getText(), "утонченный")) {
                    bufPlane.setRadars(new RadarThree());
                }
                plane = bufPlane;
                panelShowPlane.setPlane(plane);
                repaint();
            }
        };
        labelCharacteristics.addPropertyChangeListener("text", typeListener);
        mainColor.addPropertyChangeListener("background", mainColorListener);
        dopColor.addPropertyChangeListener("background", dopColorListener);
        formRadar.addPropertyChangeListener("text", formRadarListener);
        closePlane.addActionListener(actionEvent -> dispose());
        createPlane.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (plane != null){
                    parentPanel.addPlane(plane);
                }
                dispose();
            }
        });
    }
    public void smthAdd(JComponent smth, int x, int y, int width, int height) {
        smth.setBounds(x, y, width, height);
        mainPanel.add(smth);
    }
    public void setPlane(){
        plane = new Plane((Integer)spinnerMaxSpeed.getValue(), (Integer)spinnerWeight.getValue(), mainColor.getBackground());
        panelShowPlane.setPlane(plane);
        panelShowPlane.getPlane().SetPosition(10, 10, 200, 200);
        repaint();
    }
    public void setPlaneRadar(){
        plane = new PlaneRadar((Integer)spinnerMaxSpeed.getValue(), (Integer)spinnerWeight.getValue(),
                mainColor.getBackground(), dopColor.getBackground(), checkBoxRadar.isSelected(), checkBoxEngine.isSelected());
        panelShowPlane.setPlane(plane);
        panelShowPlane.getPlane().SetPosition(10, 10, 200, 200);
        repaint();
    }
}
