import javax.swing.*;
import java.awt.*;

public class ButtonBg extends JButton {

    private Color bgColor; // Store background color

    // Constructor for Main Menu
    public ButtonBg(String text) {
        this(text, new Dimension(180, 50), Color.RED); // default to red background
    }

    // Constructor for Submenus (custom size/color)
    public ButtonBg(String text, Dimension size, Color backgroundColor) {
        super(text);

        this.bgColor = backgroundColor;

        setFont(new Font("Arial", Font.BOLD, 18));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
        setPreferredSize(size);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setBorderPainted(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(bgColor);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();

        super.paintComponent(g);
    }
}