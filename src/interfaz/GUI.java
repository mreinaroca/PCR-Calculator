package interfaz;

import java.awt.*;
import javax.swing.*;

import interfaz.PCR_Panel.PCR_Panel;
public class GUI extends JFrame {


    public static void main(String[] args)
    {
        new GUI();
        

    }

    public GUI()
    {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setTitle("PCR Calculator");
        add(new Navbar(), BorderLayout.NORTH);
        add(new PCR_Panel(), BorderLayout.CENTER);
        add(new footPanel(),BorderLayout.SOUTH);
        //setUndecorated(true);
        
        setVisible(true);
    }


}
