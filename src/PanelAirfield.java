import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Objects;
import java.util.Stack;

public class PanelAirfield  extends JPanel {
    private Stack<Vehicle> stackAirfieldDelPlane = new Stack<Vehicle>();
    private final AirfieldCollection airfieldCollection;
    private DefaultListModel<String> defListBoxAirfields = new DefaultListModel<String>();
    JList<String> jListBoxAirfields;
    JButton buttonAddAirfield = new JButton("<html>добавить<br>аэропорт");
    JButton buttonDelAirfield = new JButton("<html>удалить<br>аэропорт");
    JTextField textFieldAddAirfield = new JTextField();
    JLabel labelAirfields = new JLabel("Аэропорты");
    JButton buttonSetPlane = new JButton("<html>Припарковать<br>самолет");
    JButton buttonSetPlaneRadar = new JButton("<html>Припарковать<br>самолет с<br>радаром");
    JButton buttonShowDelPlane = new JButton("Показать");
    JLabel labelGetPlane = new JLabel("Забрать самолет");
    JLabel labelPlace = new JLabel("Место: ");
    JButton buttonGetPlane = new JButton("Забрать");
    JFormattedTextField textFieldGetPlane;
    WindowMovePlane windowMovePlane = new WindowMovePlane();
    public class setGetPlaneListener implements ActionListener {   //реализация интерфейса
        @Override
        public void actionPerformed(ActionEvent event) {
            String actionCommand = event.getActionCommand();
            if (jListBoxAirfields.getSelectedValue() != null) {
                switch (actionCommand) {
                    case "set plane":
                        Color planeColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);
                        var plane = new Plane(100, 1000, planeColor);
                        if ((airfieldCollection.getAirfield(jListBoxAirfields.getSelectedValue()).plus(plane)) > -1) {
                            repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "Аэродром переполнен");
                        }
                        break;
                    case "set plane radar":
                        Color mainColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);
                        Color dopColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);
                        var planeRadar = new PlaneRadar(100, 1000, mainColor, dopColor, true, true);
                        if ((airfieldCollection.getAirfield(jListBoxAirfields.getSelectedValue()).plus(planeRadar)) > -1) {
                            repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "Аэродром переполнен");
                        }
                        break;
                    case "get plane":
                        if (!Objects.equals(textFieldGetPlane.getText(), "")) {
                            var takenPlane = airfieldCollection.getAirfield(jListBoxAirfields.getSelectedValue()).minus(Integer.parseInt(textFieldGetPlane.getText()));
                            if (takenPlane != null) {
                                stackAirfieldDelPlane.add(takenPlane);
                            }
                            repaint();
                        }
                        break;
                }
            }
        }
    }
    public class showDelPlaneListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            if (stackAirfieldDelPlane.size() > 0) {
                windowMovePlane.SetPlane(stackAirfieldDelPlane.pop());
                windowMovePlane.setVisible(true);
            }
        }
    }
    public class addDelAirfieldListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String actionCommand = event.getActionCommand();
            switch (actionCommand) {
                case "add airfield":
                    if (textFieldAddAirfield.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Введите название аэродрома", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        airfieldCollection.AddAirfield(textFieldAddAirfield.getText());
                        ReloadAirfields();
                    }
                    break;
                case "del airfield":
                    if (jListBoxAirfields.getSelectedValue() != null){
                        if (JOptionPane.showConfirmDialog(null, "Удалить аэродром " + jListBoxAirfields.getSelectedValue() + "?") == 0){
                            airfieldCollection.DelAirfield(jListBoxAirfields.getSelectedValue());
                            ReloadAirfields();
                        }
                    }
                    break;
            }
        }
    }
    public class listBoxChangeListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            repaint();
        }
    }
    public void smthAdd(JComponent butt, int x, int y, int width, int height) {
        butt.setBounds(x, y, width, height);
        add(butt);
    }
    public PanelAirfield(){
        airfieldCollection = new AirfieldCollection(900, 500);
        windowMovePlane.setModal(true);
        setBackground(Color.white);
        setLayout(null);
        smthAdd(labelAirfields, 765, 10, 70, 15);
        smthAdd(textFieldAddAirfield, 765, 30, 70, 25);
        buttonAddAirfield.setActionCommand("add airfield");
        buttonAddAirfield.addActionListener(new addDelAirfieldListener());
        smthAdd(buttonAddAirfield, 765, 60, 110, 40);
        jListBoxAirfields = new JList<String>(defListBoxAirfields);
        jListBoxAirfields.setPrototypeCellValue("Установленный размер");
        jListBoxAirfields.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jListBoxAirfields.addListSelectionListener(new listBoxChangeListener());
        smthAdd(new JScrollPane(jListBoxAirfields), 765, 110, 110, 90);
        buttonDelAirfield.setActionCommand("del airfield");
        buttonDelAirfield.addActionListener(new addDelAirfieldListener());
        smthAdd(buttonDelAirfield, 765, 210, 110, 40);
        buttonSetPlane.setActionCommand("set plane");
        buttonSetPlane.addActionListener(new setGetPlaneListener());
        smthAdd(buttonSetPlane, 765, 255, 110, 35);
        buttonSetPlaneRadar.setActionCommand("set plane radar");
        buttonSetPlaneRadar.addActionListener(new setGetPlaneListener());
        smthAdd(buttonSetPlaneRadar, 765, 300, 110, 45);
        smthAdd(labelGetPlane, 765, 360, 100, 10);
        smthAdd(labelPlace, 765, 380, 100, 10);
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setAllowsInvalid(false);
        textFieldGetPlane = new JFormattedTextField(formatter);
        smthAdd(textFieldGetPlane, 810, 375, 30, 20);
        buttonGetPlane.setActionCommand("get plane");
        buttonGetPlane.addActionListener(new setGetPlaneListener());
        smthAdd(buttonGetPlane, 765, 395, 110, 20);
        buttonShowDelPlane.addActionListener(new showDelPlaneListener());
        smthAdd(buttonShowDelPlane, 765, 420, 110, 20);
    }
    public void ReloadAirfields(){
        //defListBoxAirfields
        //jListBoxAirfields
        int index = jListBoxAirfields.getSelectedIndex();
        defListBoxAirfields.clear();
        for (int i = 0; i < airfieldCollection.Keys().size(); i++){
            defListBoxAirfields.addElement(airfieldCollection.Keys().get(i));
        }
        if (defListBoxAirfields.size() > 0 && (index == -1 || index >= defListBoxAirfields.size())){
            jListBoxAirfields.setSelectedIndex(0);
        }
        else{
            if (defListBoxAirfields.size() > 0 && index > -1 && index < defListBoxAirfields.size()){
                jListBoxAirfields.setSelectedIndex(index);
            }
        }
    }
    private void Draw(Graphics gr) {
        if (jListBoxAirfields.getSelectedValue() != null) {
            airfieldCollection.getAirfield(jListBoxAirfields.getSelectedValue()).Draw(gr);
        }
    }
    @Override
    public void paintComponent(Graphics gr){
        super.paintComponent(gr);
        Draw(gr);
    }
}