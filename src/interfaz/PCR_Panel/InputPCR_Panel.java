package interfaz.PCR_Panel;

import java.awt.*;

import javax.swing.*;

import interfaz.SwingConstructor;

public class InputPCR_Panel extends JPanel{
    private JTextField txVolume;
    private JTextField txNumReactions;
    private JButton btSubmit;
    
    public InputPCR_Panel()
    {   
        setBackground(Color.GRAY);
        setLayout(new GridLayout(3,1));
        add(SwingConstructor.create_empty());
        add(createComponents());
        add(SwingConstructor.create_empty());
    }

    private JPanel createComponents() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        
        JLabel lbVolume = new JLabel("Volume of Reaction:");
        JLabel lbNumReactions = new JLabel("Number of Reactions:");
        txVolume = SwingConstructor.create_Entry_JTextField();
       
        txNumReactions = SwingConstructor.create_Entry_JTextField();
        
        btSubmit = SwingConstructor.create_He_JButton("Submit");
        contentPane.add(lbVolume);
        contentPane.add(txVolume);
        contentPane.add(lbNumReactions);
        contentPane.add(txNumReactions);
        contentPane.add(SwingConstructor.create_empty());
        contentPane.add(btSubmit);

        
        return contentPane;
    }
}
