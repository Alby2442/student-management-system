import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;

public class ViewRecords extends JFrame {

    private Image backgroundImage;

    public ViewRecords() {

        setTitle("View Records");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ===== LOAD IMAGE =====
        backgroundImage = new ImageIcon(
                getClass().getResource("/images/view.jpeg")
        ).getImage();

        // ===== BACKGROUND PANEL =====
        JPanel backgroundPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0,
                        getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        // ===== TABLE MODEL =====
        String[] columnNames = {
                "ID", "Name", "Course", "Department", "Semester"
        };

        DefaultTableModel model =
                new DefaultTableModel(columnNames, 0);

        JTable table = new JTable(model);

        // ===== MAKE TABLE TRANSPARENT =====
        table.setOpaque(false);
        table.setBackground(new Color(0,0,0,0));
        table.setForeground(Color.RED);   // 🔴 DATA COLOR
        table.setFont(new Font("Arial", Font.BOLD, 16));
        table.setRowHeight(28);
        table.setShowGrid(false);
        table.setFillsViewportHeight(true);

        // ===== TABLE HEADER STYLE =====
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.BLACK);
        header.setForeground(Color.RED);
        header.setFont(new Font("Arial", Font.BOLD, 18));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        backgroundPanel.add(scrollPane, BorderLayout.CENTER);

        // ===== DATABASE =====
        try {

            Connection con =
                    DatabaseConnection.getConnection();

            Statement st = con.createStatement();
            ResultSet rs =
                    st.executeQuery("SELECT * FROM students_info");

            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getString("course"),
                        rs.getString("department"),
                        rs.getString("semester")
                });
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Database Error: " + ex.getMessage());
        }

        setVisible(true);
    }
}