import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class DeleteRecord extends JFrame {

    JTextField id;
    JButton delete;

    public DeleteRecord() {

        setTitle("Delete Student Record");
        setExtendedState(JFrame.MAXIMIZED_BOTH);   // Full screen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ===== GET SCREEN SIZE =====
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // ===== LOAD & SCALE BACKGROUND IMAGE =====
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/delete.jpeg"));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);

        JLabel background = new JLabel(new ImageIcon(scaledImg));
        background.setLayout(null);
        setContentPane(background);

        // ===== TITLE =====
        JLabel title = new JLabel("DELETE STUDENT RECORD", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 80, screenWidth, 70);
        background.add(title);

        // ===== CENTER PANEL =====
        int panelWidth = 400;
        int panelHeight = 220;

        int centerX = (screenWidth - panelWidth) / 2;
        int centerY = (screenHeight - panelHeight) / 2;

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(centerX, centerY, panelWidth, panelHeight);
        panel.setBackground(new Color(255, 255, 255, 220));
        background.add(panel);

        // Label
        JLabel l1 = new JLabel("Enter Student ID to Delete");
        l1.setBounds(70, 30, 250, 30);

        // TextField
        id = new JTextField();
        id.setBounds(70, 70, 250, 35);

        // Button
        delete = new JButton("Delete");
        delete.setBounds(120, 130, 150, 40);

        panel.add(l1);
        panel.add(id);
        panel.add(delete);

        setVisible(true);

        // ===== BUTTON ACTION =====
        delete.addActionListener(e -> deleteStudent());
    }

    private void deleteStudent() {

        if (id.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Student ID");
            return;
        }

        try {

            Connection con = DatabaseConnection.getConnection();

            if (con == null) {
                JOptionPane.showMessageDialog(this, "Database Not Connected");
                return;
            }

            String sql = "DELETE FROM students_info WHERE student_id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id.getText()));

            int rows = ps.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Record Deleted Successfully");
                id.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "No Record Found With That ID");
            }

            ps.close();
            con.close();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID must be a number");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}