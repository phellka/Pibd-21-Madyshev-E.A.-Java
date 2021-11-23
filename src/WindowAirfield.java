import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowAirfield extends JFrame {
    PanelAirfield panelAirfield = new PanelAirfield();
    JMenuBar jMenuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("Файл");
    JMenuItem jMenuItemFileSave = new JMenuItem("Сохранить");
    JMenuItem jMenuItemFileLoad = new JMenuItem("Загрузить");
    JMenu airfieldMenu = new JMenu("Аэродром");
    JMenuItem jMenuItemAirfieldSave = new JMenuItem("Сохранить");
    JMenuItem jMenuItemAirfieldLoad = new JMenuItem("Загрузить");
    JFileChooser jFileChooser = new JFileChooser();
    public WindowAirfield(){
        setTitle("аэродром");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 520);
        setLocation(400, 400);
        add(panelAirfield);
        setVisible(true);
        fileMenu.add(jMenuItemFileSave);
        fileMenu.add(jMenuItemFileLoad);
        airfieldMenu.add(jMenuItemAirfieldSave);
        airfieldMenu.add(jMenuItemAirfieldLoad);
        jMenuBar.add(fileMenu);
        jMenuBar.add(airfieldMenu);
        setJMenuBar(jMenuBar);
        jMenuItemFileSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jFileChooser.showDialog(null, "Сохранить в файл")
                        == JFileChooser.APPROVE_OPTION) {
                    if (panelAirfield.saveAirfieldCollection(jFileChooser.getSelectedFile())){
                        JOptionPane.showMessageDialog(null, "Сохранение прошло успешно",
                                "Результат", JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Не сохранилось",
                                "Результат", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        jMenuItemFileLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jFileChooser.showDialog(null, "Загрузить из файла")
                        == JFileChooser.APPROVE_OPTION) {
                    if (panelAirfield.loadAirfieldCollection(jFileChooser.getSelectedFile())){
                        JOptionPane.showMessageDialog(null, "Загрузили",
                                "Результат", JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Не загрузили",
                                "Результат", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        jMenuItemAirfieldSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jFileChooser.showDialog(null, "Сохранить в файл")
                        == JFileChooser.APPROVE_OPTION) {
                    if (panelAirfield.saveAirfield(jFileChooser.getSelectedFile())){
                        JOptionPane.showMessageDialog(null, "Сохранение прошло успешно",
                                "Результат", JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Не сохранилось",
                                "Результат", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        jMenuItemAirfieldLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jFileChooser.showDialog(null, "Загрузить из файла")
                        == JFileChooser.APPROVE_OPTION) {
                    if (panelAirfield.loadAirfield(jFileChooser.getSelectedFile())){
                        JOptionPane.showMessageDialog(null, "Загрузили",
                                "Результат", JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Не загрузили",
                                "Результат", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    public static void main(String[] args){
        WindowAirfield mW = new WindowAirfield();
    }
}
//JOptionPane.showMessageDialog(null, "Введите название аэродрома", "Ошибка", JOptionPane.ERROR_MESSAGE);