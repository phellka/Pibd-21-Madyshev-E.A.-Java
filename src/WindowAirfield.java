import javax.swing.*;

public class WindowAirfield extends JFrame {
    PanelAirfield panelAirfield = new PanelAirfield();
    public WindowAirfield(){
        setTitle("аэродром");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocation(400, 400);
        add(panelAirfield);
        setVisible(true);
    }

    public static void main(String[] args){
        WindowAirfield mW = new WindowAirfield();
    }
}
