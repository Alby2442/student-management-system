import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {

    public HomePage() {

        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== BACKGROUND IMAGE =====
        JLabel background = new JLabel(
                new ImageIcon(getClass().getResource("/images/home.jpg"))
        );
        background.setLayout(null);
        setContentPane(background);

        // ===== TITLE =====
        JLabel title = new JLabel("HOME PAGE", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 60, 900, 60);
        background.add(title);

        // ===== BUTTONS =====
        JButton add = new JButton("Add Record");
        JButton update = new JButton("Update Record");
        JButton delete = new JButton("Delete Record");
        JButton view = new JButton("View Records");
        JButton logout = new JButton("Logout");

        int buttonWidth = 250;
        int buttonHeight = 45;
        int centerX = (900 - buttonWidth) / 2;

        add.setBounds(centerX, 150, buttonWidth, buttonHeight);
        update.setBounds(centerX, 210, buttonWidth, buttonHeight);
        delete.setBounds(centerX, 270, buttonWidth, buttonHeight);
        view.setBounds(centerX, 330, buttonWidth, buttonHeight);
        logout.setBounds(centerX, 390, buttonWidth, buttonHeight);

        background.add(add);
        background.add(update);
        background.add(delete);
        background.add(view);
        background.add(logout);

        setVisible(true);

        // ===== BUTTON ACTIONS =====
        add.addActionListener(e -> new AddRecord());
        update.addActionListener(e -> new UpdateRecord());
        delete.addActionListener(e -> new DeleteRecord());
        view.addActionListener(e -> new ViewRecords());

        logout.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });
    }
}