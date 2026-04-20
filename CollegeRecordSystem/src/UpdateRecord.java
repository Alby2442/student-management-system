import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class UpdateRecord extends JFrame {

    JTextField id, name;
    JComboBox<String> courseBox, deptBox, semBox;
    JButton update;

    public UpdateRecord() {

        setTitle("Update Student Record");
        setExtendedState(JFrame.MAXIMIZED_BOTH);   // Full screen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ===== GET SCREEN SIZE =====
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // ===== LOAD & SCALE BACKGROUND IMAGE =====
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/update.jpeg"));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);

        JLabel background = new JLabel(new ImageIcon(scaledImg));
        background.setLayout(null);
        setContentPane(background);

        // ===== TITLE =====
        JLabel title = new JLabel("UPDATE STUDENT RECORD", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 80, screenWidth, 70);
        background.add(title);

        // ===== CENTER PANEL =====
        int panelWidth = 450;
        int panelHeight = 350;

        int centerX = (screenWidth - panelWidth) / 2;
        int centerY = (screenHeight - panelHeight) / 2;

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(centerX, centerY, panelWidth, panelHeight);
        panel.setBackground(new Color(255, 255, 255, 220));
        background.add(panel);

        // Labels
        JLabel l1 = new JLabel("Student ID");
        JLabel l2 = new JLabel("Student Name");
        JLabel l3 = new JLabel("Course");
        JLabel l4 = new JLabel("Department");
        JLabel l5 = new JLabel("Semester");

        id = new JTextField();
        name = new JTextField();

        String[] courses = {"BCA", "BSc", "BCom", "BA", "BBA"};
        courseBox = new JComboBox<>(courses);

        String[] departments = {"Computer Science", "Commerce", "Mathematics", "Physics", "English"};
        deptBox = new JComboBox<>(departments);

        String[] semesters = {"Sem 1", "Sem 2", "Sem 3", "Sem 4", "Sem 5"};
        semBox = new JComboBox<>(semesters);

        update = new JButton("Update");

        // Set bounds inside panel
        l1.setBounds(60, 40, 150, 30);
        id.setBounds(220, 40, 170, 30);

        l2.setBounds(60, 80, 150, 30);
        name.setBounds(220, 80, 170, 30);

        l3.setBounds(60, 120, 150, 30);
        courseBox.setBounds(220, 120, 170, 30);

        l4.setBounds(60, 160, 150, 30);
        deptBox.setBounds(220, 160, 170, 30);

        l5.setBounds(60, 200, 150, 30);
        semBox.setBounds(220, 200, 170, 30);

        update.setBounds(140, 260, 160, 45);

        panel.add(l1); panel.add(id);
        panel.add(l2); panel.add(name);
        panel.add(l3); panel.add(courseBox);
        panel.add(l4); panel.add(deptBox);
        panel.add(l5); panel.add(semBox);
        panel.add(update);

        setVisible(true);

        update.addActionListener(e -> updateStudent());
    }

    private void updateStudent() {

        try {

            Connection con = DatabaseConnection.getConnection();

            String sql = "UPDATE students_info SET student_name=?, course=?, department=?, semester=? WHERE student_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name.getText());
            ps.setString(2, courseBox.getSelectedItem().toString());
            ps.setString(3, deptBox.getSelectedItem().toString());
            ps.setString(4, semBox.getSelectedItem().toString());
            ps.setInt(5, Integer.parseInt(id.getText()));

            int rows = ps.executeUpdate();

            if (rows > 0)
                JOptionPane.showMessageDialog(this, "Record Updated Successfully");
            else
                JOptionPane.showMessageDialog(this, "No Record Found");

            ps.close();
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}