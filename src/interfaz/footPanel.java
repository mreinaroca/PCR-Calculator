package interfaz;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class footPanel extends JPanel{
    private JLabel lbCreator;
    public footPanel()
    {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(SwingConstructor.backGroundColor);
        lbCreator = SwingConstructor.create_A1_JLabel("Creado por: Miguel A. Reina");
        add(lbCreator);
    }
    
}
