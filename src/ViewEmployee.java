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

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel searchLabel = new JLabel("Search by Employee ID");
        searchLabel.setBounds(20, 20, 150, 30);
        add(searchLabel);

        searchField = new JTextField(10);
        searchField.setBounds(180, 20, 150, 30);
        add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(350, 20, 100, 30);
        searchButton.addActionListener(this);
        add(searchButton);

        removeButton = new JButton("Remove");
        removeButton.setBounds(470, 20, 100, 30);
        removeButton.addActionListener(this);
        add(removeButton);

        backButton = new JButton("Back");
        backButton.setBounds(590, 20, 80, 30);
        backButton.addActionListener(this);
        add(backButton);

        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 70, 660, 580);
        add(scrollPane);

        String[] columnNames = {"Employee ID", "Name", "Father's Name", "Date of Birth", "Address", "Phone", "Email", "NID Number", "Designation", "Highest Education", "Salary"};
        model.setColumnIdentifiers(columnNames);

        loadEmployeeData(model);

        setTitle("View Employee Details");
        setSize(700,700);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            if (e.getSource() == removeButton) {
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
        new ViewEmployee();
    }
}
