package interfaz;
import java.awt.*;
import javax.swing.*;
public class SwingConstructor {

    public static final Font textFont = new Font(Font.SANS_SERIF,Font.PLAIN, 12);
    public static final Font boldFont = new Font(Font.SANS_SERIF,Font.PLAIN,12);
    public static final Color backGroundColor = Color.DARK_GRAY;

    public static JButton createStyledJButton(String text)
    {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.GRAY);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
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
}
