package interfaz.PCR_Panel;

import java.awt.Color;

import javax.swing.JPanel;

import interfaz.SwingConstructor;

public class InputPCR_Panel extends JPanel{
    
    public InputPCR_Panel()
    {
        setBackground(Color.WHITE);
        add(SwingConstructor.create_A1_JLabel("left"));
    }
}
