package interfaz.PCR_Panel;
import java.awt.*;
import javax.swing.*;
public class PCR_Panel extends JPanel{
    
    public PCR_Panel()
    {
        setLayout(new BorderLayout());
        add(new InputPCR_Panel(), BorderLayout.WEST);
        add(new OutputPCR_Panel(), BorderLayout.CENTER);
    }
    
}
