import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.NumberFormat;
import java.util.Objects;
import java.util.Stack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    JButton buttonShowDelPlane = new JButton("Показать");
    JLabel labelGetPlane = new JLabel("Забрать самолет");
    JLabel labelPlace = new JLabel("Место: ");
    JButton buttonGetPlane = new JButton("Забрать");
    JFormattedTextField textFieldGetPlane;
    WindowMovePlane windowMovePlane = new WindowMovePlane();
    WindowPlaneConfig windowPlaneConfig;
    Logger logger;
    JButton buttonSort = new JButton("Сортировать");
    public class setGetPlaneListener implements ActionListener {   //реализация интерфейса
        @Override
        public void actionPerformed(ActionEvent event) {
            String actionCommand = event.getActionCommand();
            if (jListBoxAirfields.getSelectedValue() != null) {
                switch (actionCommand) {
                    case "set plane":
                        configurePlane();
                        break;
                    case "get plane":
                        if (!Objects.equals(textFieldGetPlane.getText(), "")) {
                            try {
                                var takenPlane = airfieldCollection.getAirfield(jListBoxAirfields.getSelectedValue()).minus(Integer.parseInt(textFieldGetPlane.getText()));
                                if (takenPlane != null) {
                                    stackAirfieldDelPlane.add(takenPlane);
                                }
                                repaint();
                                logger.info("Изъят самолет " + takenPlane + "с места" + jListBoxAirfields.getSelectedValue());
                            }
                            catch(AirfieldVehicleNotFoundException ex){
                                logger.warn(ex.toString());
                                JOptionPane.showMessageDialog(null, ex.getMessage(), "Не найден", JOptionPane.ERROR_MESSAGE);
                            }
                            catch (Exception ex){
                                logger.fatal(ex.toString());
                                JOptionPane.showMessageDialog(null, ex.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                            }
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
                        logger.info("Добавили аэродром " + textFieldAddAirfield.getText());
                        airfieldCollection.AddAirfield(textFieldAddAirfield.getText());
                        ReloadAirfields();
                    }
                    break;
                case "del airfield":
                    if (jListBoxAirfields.getSelectedValue() != null){
                        if (JOptionPane.showConfirmDialog(null, "Удалить аэродром " + jListBoxAirfields.getSelectedValue() + "?") == 0){
                            airfieldCollection.DelAirfield(jListBoxAirfields.getSelectedValue());
                            logger.info("Удалили аэродром" + jListBoxAirfields.getSelectedValue());
                            ReloadAirfields();
                        }
                    }
                    break;
            }
        }
    }
    protected void configurePlane(){
        windowPlaneConfig = new WindowPlaneConfig(this);
        windowPlaneConfig.setVisible(true);
    }
    public void addPlane(Vehicle plane){
        try {
            if ((airfieldCollection.getAirfield(jListBoxAirfields.getSelectedValue()).plus(plane)) > -1) {
                logger.info("Добавлен самолет" + plane);
                repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Аэродром переполнен");
            }
        }
        catch(AirfieldOverflowException ex){
            logger.warn(ex.toString());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Переполнение", JOptionPane.ERROR_MESSAGE);
        }
        catch(AirfieldAlreadyHaveException ex){
            logger.warn(ex.toString());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Дублирование", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception ex){
            logger.fatal(ex.toString());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
    public class listBoxChangeListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (jListBoxAirfields.getSelectedValue() != null) {
                logger.info("Перешли на аэродром " + jListBoxAirfields.getSelectedValue());
            }
            repaint();
        }
    }
    public void smthAdd(JComponent butt, int x, int y, int width, int height) {
        butt.setBounds(x, y, width, height);
        add(butt);
    }
    public PanelAirfield(){
        logger = LogManager.getLogger("myLogger");
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
        smthAdd(buttonSort, 765, 325, 110, 20);
        buttonSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jListBoxAirfields.getSelectedValue() != null){
                    airfieldCollection.getAirfield(jListBoxAirfields.getSelectedValue()).sort();
                    logger.info("Сортировка уровней");
                    repaint();
                }
            }
        });
    }
    public void ReloadAirfields(){
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
    public void saveAirfieldCollection(File saveFile){
        try {
            airfieldCollection.saveData(saveFile);
            JOptionPane.showMessageDialog(null, "Сохранение прошло успешно",
                    "Результат", JOptionPane.WARNING_MESSAGE);
            logger.info("Сохранено в файл " + saveFile);
        }
        catch(Exception ex){
            logger.fatal(ex.toString());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Неизвестная ошибка при сохранении", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void loadAirfieldCollection(File loadFile){
        try {
            airfieldCollection.loadData(loadFile);
            JOptionPane.showMessageDialog(null, "Загрузили",
                    "Результат", JOptionPane.WARNING_MESSAGE);
            logger.info("Загружено из файла " + loadFile);
        }
        catch (FileFormatException ex) {
            logger.warn(ex.toString());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка формата", JOptionPane.ERROR_MESSAGE);
        }
        catch (AirfieldOverflowException ex) {
            logger.error(ex.toString());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Переполнение при загрузке", JOptionPane.ERROR_MESSAGE);
        }
        catch(AirfieldNotFoundException ex){
            logger.error(ex.toString());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Не найден файл", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception ex) {
            logger.fatal(ex.toString());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Неизвестная ошибка при загрузке", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            ReloadAirfields();
            repaint();
        }
    }
    public void saveAirfield(File saveFile){
        try {
            if (jListBoxAirfields.getSelectedValue() != null) {
                airfieldCollection.saveAirfield(saveFile, jListBoxAirfields.getSelectedValue());
                JOptionPane.showMessageDialog(null, "Сохранение прошло успешно",
                        "Результат", JOptionPane.WARNING_MESSAGE);
                logger.info("Сохранено в файл " + saveFile);
            }
        }
        catch(AirfieldNotFoundException ex){
            logger.error(ex.toString());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Не найден файл", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception ex){
            logger.fatal(ex.toString());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Неизвестная ошибка при сохранении", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void loadAirfield(File loadFile){
        try {
            airfieldCollection.loadAirfield(loadFile);
            JOptionPane.showMessageDialog(null, "Загрузили",
                    "Результат", JOptionPane.WARNING_MESSAGE);
            logger.info("Загружено из файла " + loadFile);
        }
        catch (FileFormatException ex) {
            logger.warn(ex.toString());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка формата", JOptionPane.ERROR_MESSAGE);
        }
        catch (AirfieldOverflowException ex) {
            logger.warn(ex.toString());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Переполнение при загрузке", JOptionPane.ERROR_MESSAGE);
        }
        catch (StackOverflowError ex) {
            logger.error(ex.toString());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Переполнение при загрузке", JOptionPane.ERROR_MESSAGE);
        }
        catch(AirfieldNotFoundException ex){
            logger.error(ex.toString());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Не найден файл", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception ex) {
            logger.fatal(ex.toString());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Неизвестная ошибка при загрузке", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            ReloadAirfields();
            repaint();
        }
    }
}