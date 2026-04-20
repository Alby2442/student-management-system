import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {

        setSize(1000, 650);   // Good laptop size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background Image
        JLabel background = new JLabel(new ImageIcon(getClass().getResource("/images/login.jpg")));
        background.setLayout(null);
        setContentPane(background);

        int frameWidth = 1000;

        // ===== MAIN TITLE =====
        JLabel title = new JLabel("COLLEGE RECORD SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 48));   // MORE BIG
        title.setForeground(Color.WHITE);
        title.setBounds(0, 100, frameWidth, 70);
        background.add(title);

        // ===== SUB TITLE =====
        JLabel subtitle = new JLabel("Login Page", SwingConstants.CENTER);
        subtitle.setFont(new Font("Arial", Font.BOLD, 30));  // Bigger
        subtitle.setForeground(Color.WHITE);
        subtitle.setBounds(0, 170, frameWidth, 50);
        background.add(subtitle);

        // ===== LOGIN PANEL (CENTER CALCULATION) =====
        int panelWidth = 320;
        int panelHeight = 230;

        int centerX = (frameWidth - panelWidth) / 2;
        int centerY = 270;

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(centerX, centerY, panelWidth, panelHeight);
        panel.setBackground(new Color(255, 255, 255, 220));
        background.add(panel);

        // Username
        JTextField username = new JTextField();
        username.setBounds(40, 40, 240, 40);
        panel.add(username);

        // Password
        JPasswordField password = new JPasswordField();
        password.setBounds(40, 100, 240, 40);
        panel.add(password);

        // Login Button
        JButton login = new JButton("Login");
        login.setBounds(40, 160, 240, 45);
        panel.add(login);

        setVisible(true);

        // Login Action
        login.addActionListener(e -> {

            if (username.getText().equals("admin") &&
                String.valueOf(password.getPassword()).equals("1234")) {

                new HomePage();
                dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Invalid Login");
            }
        });
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}