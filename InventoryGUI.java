
import javax.swing.*;
import java.awt.*;

public class InventoryGUI extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public InventoryGUI() {
        setTitle("Le Velour Closet");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        createSplashScreen();
        createWelcomeScreen();

        add(mainPanel);
        cardLayout.show(mainPanel, "SPLASH");

        Timer timer = new Timer(2000, e -> {
            cardLayout.show(mainPanel, "WELCOME");
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void createSplashScreen() {
        JPanel splashPanel = new JPanel(new GridBagLayout());
        splashPanel.setBackground(new Color(40, 25, 40));

        JLabel storeName = new JLabel("LE VELOUR");
        storeName.setFont(new Font("Serif", Font.BOLD, 48));

        JLabel subtitle = new JLabel("C L O S E T");
        subtitle.setFont(new Font("Serif", Font.PLAIN, 22));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        storeName.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(storeName);
        content.add(Box.createVerticalStrut(10));
        content.add(subtitle);

        splashPanel.add(content);
        mainPanel.add(splashPanel, "SPLASH");
    }

    private void createWelcomeScreen() {
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setBackground(new Color(245, 220, 232));

        JLabel storeName = new JLabel("LE VELOUR CLOSET");
        storeName.setFont(new Font("Serif", Font.BOLD, 42));

        JLabel welcome = new JLabel("Welcome to Your Inventory Management System");
        welcome.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        storeName.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(storeName);
        content.add(Box.createVerticalStrut(20));
        content.add(welcome);

        welcomePanel.add(content);
        mainPanel.add(welcomePanel, "WELCOME");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InventoryGUI gui = new InventoryGUI();
            gui.setVisible(true);
        });
    }
}
