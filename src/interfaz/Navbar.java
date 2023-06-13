package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Navbar extends JPanel implements ActionListener{
    private Color navbarColor = Color.DARK_GRAY;
    private JButton lbPCR; public static final String PCR_VIEW="PCR_VIEW";
    private JButton lbSettings; public static final String SETTINGS_VIEW="SETTINGS_VIEW";

    public Navbar()
    {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(navbarColor);
        createPCRButton();
        createSettingsButton();
        

    }

    private void createPCRButton()
    {
        lbPCR = SwingConstructor.create_He_JButton("PCR Calculator");
        lbPCR.addActionListener(this);
        lbPCR.setActionCommand(PCR_VIEW);
        this.add(lbPCR);
    }

    private void createSettingsButton()
    {
        lbSettings= SwingConstructor.create_He_JButton("Settings");
        lbSettings.addActionListener(this);
        lbSettings.setActionCommand(SETTINGS_VIEW);
        this.add(lbSettings);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals(PCR_VIEW))
        {
            System.out.println("PCR_VIEW");
        } else if(command.equals(SETTINGS_VIEW))
        {
            System.out.println("SETTINGS_VIEW");
        }
        
    }
}
