package interfaz;
import java.awt.*;
import javax.swing.*;
public class SwingConstructor {

    public static final Font textFont = new Font(Font.SANS_SERIF,Font.PLAIN, 12);
    public static final Font boldFont = new Font(Font.SANS_SERIF,Font.PLAIN,12);
    public static final Color backGroundColor = Color.DARK_GRAY;

    public static JLabel create_empty()
    {
        return new JLabel(" ");
    }

    public static JButton create_He_JButton(String text)
    {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.GRAY);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setFont(boldFont);
        return button;
    }

    public static JLabel create_A1_JLabel(String text)
    {
        JLabel lb = new JLabel(text);
        lb.setFont(textFont);
        lb.setForeground(Color.WHITE);
        return lb;
    }

    public static JTextField create_Entry_JTextField()
    {
        JTextField txt = new JTextField();
        txt.setFont(textFont);
        txt.setMaximumSize(new Dimension(5, 10));
        return txt;
    }
}
