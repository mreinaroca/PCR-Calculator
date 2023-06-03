package interfaz.PCR_Panel;

import java.awt.Color;

import javax.swing.JPanel;

import interfaz.SwingConstructor;

public class OutputPCR_Panel extends JPanel{

    public OutputPCR_Panel()
    {
        setBackground(Color.blue);
        add(SwingConstructor.create_A1_JLabel("RIGHT"));
    }

}
