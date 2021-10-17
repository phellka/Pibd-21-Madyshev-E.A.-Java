import javax.swing.*;

public class WindowMovePlane extends JDialog {
    PanelMovePlane panelMovePlane = new PanelMovePlane();
    public void SetPlane(ITransport plane) {
        panelMovePlane.SetPlane(plane);
    }
    public WindowMovePlane(){
        setTitle("самолет");
        setSize(900, 500);
        setLocation(400, 400);
        add(panelMovePlane);
    }
}
