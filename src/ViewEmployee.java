import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ViewEmployee extends JFrame implements ActionListener {

    JTable table;
    JTextField searchField;
    JButton searchButton, removeButton, backButton;

    public ViewEmployee() {
        setTitle("View Employee Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        JLabel searchLabel = new JLabel("Search by Employee ID");
        topPanel.add(searchLabel);

        searchField = new JTextField(10);
        topPanel.add(searchField);

        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        topPanel.add(searchButton);

        removeButton = new JButton("Remove");
        removeButton.addActionListener(this);
        topPanel.add(removeButton);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        topPanel.add(backButton);

        getContentPane().add(topPanel, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        String[] columnNames = {"Employee ID", "Name", "Father's Name", "Date of Birth", "Address", "Phone", "Email", "NID Number", "Designation", "Highest Education", "Salary"};
        model.setColumnIdentifiers(columnNames);

        loadEmployeeData(model);

        setVisible(true);
    }

    private void loadEmployeeData(DefaultTableModel model) {
        try (BufferedReader br = new BufferedReader(new FileReader("employees.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                model.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String searchId = searchField.getText().trim();
            if (!searchId.isEmpty()) {
                searchEmployeeById(searchId);
            }
        } else if (e.getSource() == removeButton) {
            String searchId = searchField.getText().trim();
            if (!searchId.isEmpty()) {
                setVisible(false);
                new RemoveEmployee();
            }
        } else if (e.getSource() == backButton) {

            setVisible(false);
            new Home();
        }
    }

    private void searchEmployeeById(String id) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try (BufferedReader br = new BufferedReader(new FileReader("employees.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(id)) {
                    model.addRow(data);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewEmployee::new);
    }
}
