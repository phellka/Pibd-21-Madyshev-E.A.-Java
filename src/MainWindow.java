import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow(){
        setTitle("самолет");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocation(400, 400);
        add(new MainField());
        setVisible(true);
    }

    public static void main(String[] args){
        MainWindow mW = new MainWindow();
    }
}
