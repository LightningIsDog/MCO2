import javax.swing.*;
import java.awt.*;

/** This class represents a custom JButton with a background color.
 * It is used in the main menu and submenus of the program.
 @author Justin Miguel Agustin L. Lotilla
 @author Maurienne Marie M. Mojica
 @version 2.0
 */

public class ButtonBg extends JButton {
    
    /**
     * The background color of the custom button.
     */
    private Color bgColor; 

    /** This constructor initializes the button with a default size and color.
     * It is used for the main menu buttons.
     * @param text The text to be displayed on the button
     */
    public ButtonBg(String text) {
        this(text, new Dimension(180, 50), Color.RED); 
    }

    /** This constructor initializes the button with a specified size and color.
     * It is used for the submenus and allows customization of the button's appearance.
     * @param text The text to be displayed on the button
     * @param size The preferred size of the button
     * @param backgroundColor The background color of the button
     */
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

    /** This method paints the button with the specified background color.
     * It overrides the paintComponent method to customize the button's appearance.
     * @param g The Graphics object used to draw the button
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(bgColor);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();

        super.paintComponent(g);
    }

    /** This method creates a horizontal panel containing multiple buttons.
     * It is used to arrange buttons in a horizontal layout with specified spacing.
     * @param buttons The ButtonBg buttons to be added to the panel
     * @return A JPanel containing the buttons arranged horizontally
     */
    public static JPanel createHorizontalButtonPanel(ButtonBg... buttons) {
        JPanel panel = new JPanel();
        panel.setOpaque(false); 
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        for (ButtonBg button : buttons) 
        {
            panel.add(button);
        }

        return panel;
    }
}