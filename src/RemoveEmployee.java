import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class RemoveEmployee extends JFrame implements ActionListener {
    private final JTextField idField;
    private final JButton removeButton;
    private final JButton goToViewButton;

    private final JButton homeButton;

    public RemoveEmployee() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("REMOVE EMPLOYEE DETAILS");
        heading.setBounds(125, 10, 650, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        heading.setForeground(Color.RED);
        add(heading);

        JLabel idLabel = new JLabel("Enter Employee ID to remove: ");
        idLabel.setBounds(10, 100, 250, 30);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(230, 100, 300, 30);
        add(idField);

        removeButton = new JButton("Remove");
        removeButton.setBounds(70, 170, 250, 50);
        removeButton.addActionListener(this);
        add(removeButton);

        goToViewButton = new JButton("View");
        goToViewButton.setBounds(370, 170, 250, 50);
        goToViewButton.addActionListener(this);
        add(goToViewButton);

        homeButton = new JButton("Home");
        homeButton.setBounds(220, 240, 250, 50);
        homeButton.addActionListener(this);
        add(homeButton);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Remove.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 335, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 330, 700, 335);
        add(image);

        setTitle("Remove Employee Details");
        setSize(700,700);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeButton) {
            String id = idField.getText().trim();
            if (!id.isEmpty()) {
                removeEmployeeById(id);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter an Employee ID.");
            }
        }
        else if (e.getSource() == homeButton)
        {
            setVisible(false);
            new Home();
        }
        else
        {
            setVisible(false);
            new ViewEmployee();
        }
    }

    private void removeEmployeeById(String id) {
        File inputFile = new File("employees.txt");
        File tempFile = new File("employees_temp.txt");

        boolean employeeFound = false;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (!data[0].equals(id)) {
                    bw.write(line);
                    bw.newLine();
                } else {
                    employeeFound = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (employeeFound) {
            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
                JOptionPane.showMessageDialog(this, "Employee with ID " + id + " removed successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Error occurred while removing the employee.");
            }
        } else {
            tempFile.delete();
            JOptionPane.showMessageDialog(this, "Employee with ID " + id + " not found.");
        }
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}
