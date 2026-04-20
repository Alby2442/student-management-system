import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddRecord extends JFrame {

    JTextField id, name;
    JComboBox<String> courseBox, deptBox, semBox;
    JButton save;

    public AddRecord() {

        setTitle("Add Student Record");
        setExtendedState(JFrame.MAXIMIZED_BOTH);   // Full screen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ===== GET SCREEN SIZE =====
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // ===== LOAD & SCALE BACKGROUND IMAGE =====
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/add.jpeg"));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(scaledImg));
        background.setLayout(null);
        setContentPane(background);

        // ===== TITLE =====
        JLabel title = new JLabel("ADD STUDENT RECORD", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 80, screenWidth, 70);
        background.add(title);

        // ===== CENTER FORM PANEL =====
        int panelWidth = 400;
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

        save = new JButton("Save");

        // Set bounds inside panel
        l1.setBounds(40, 30, 150, 30);
        id.setBounds(200, 30, 150, 30);

        l2.setBounds(40, 70, 150, 30);
        name.setBounds(200, 70, 150, 30);

        l3.setBounds(40, 110, 150, 30);
        courseBox.setBounds(200, 110, 150, 30);

        l4.setBounds(40, 150, 150, 30);
        deptBox.setBounds(200, 150, 150, 30);

        l5.setBounds(40, 190, 150, 30);
        semBox.setBounds(200, 190, 150, 30);

        save.setBounds(120, 250, 160, 45);

        panel.add(l1); panel.add(id);
        panel.add(l2); panel.add(name);
        panel.add(l3); panel.add(courseBox);
        panel.add(l4); panel.add(deptBox);
        panel.add(l5); panel.add(semBox);
        panel.add(save);

        setVisible(true);

        save.addActionListener(e -> addStudent());
    }

    private void addStudent() {

        try {

            Connection con = DatabaseConnection.getConnection();

            String sql = "INSERT INTO students_info (student_id, student_name, course, department, semester) VALUES (?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id.getText()));
            ps.setString(2, name.getText());
            ps.setString(3, courseBox.getSelectedItem().toString());
            ps.setString(4, deptBox.getSelectedItem().toString());
            ps.setString(5, semBox.getSelectedItem().toString());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Record Added Successfully");

            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}