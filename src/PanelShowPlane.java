import javax.swing.*;
import java.awt.*;
public class PanelShowPlane  extends JPanel {
    private ITransport plane;
    public void paintComponent(Graphics gr) {
        if (plane != null) plane.DrawTransport(gr);
    }
    public ITransport getPlane() {
        return plane;
    }
    public void setPlane(ITransport plane) {
        this.plane = plane;
    }
}
