import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AddEmployee extends JFrame implements ActionListener {

    JTextField idField, nameField, fatherNameField, dobField, addressField, phoneField, emailField, nidNumberField, designationField, salaryField;
    JComboBox<String> highestEducationField;
    JButton addDetails, back;

    public AddEmployee() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("ADD EMPLOYEE DETAILS");
        heading.setBounds(160, 10, 650, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        heading.setForeground(Color.BLACK);
        add(heading);

        JLabel id = new JLabel("Employee ID");
        id.setBounds(50, 40 + 40, 100, 30);
        add(id);

        idField = new JTextField();
        idField.setBounds(200, 40 + 40, 200, 30);
        add(idField);

        JLabel name = new JLabel("Name");
        name.setBounds(50, 80 + 40, 100, 30);
        add(name);

        nameField = new JTextField();
        nameField.setBounds(200, 80 + 40, 200, 30);
        add(nameField);

        JLabel fatherName = new JLabel("Father's Name");
        fatherName.setBounds(50, 120 + 40, 100, 30);
        add(fatherName);

        fatherNameField = new JTextField();
        fatherNameField.setBounds(200, 120 + 40, 200, 30);
        add(fatherNameField);

        JLabel dob = new JLabel("Date of Birth");
        dob.setBounds(50, 160 + 40, 100, 30);
        add(dob);

        dobField = new JTextField("DD/MM/YYYY");
        dobField.setBounds(200, 160 + 40, 200, 30);
        add(dobField);

        JLabel address = new JLabel("Address");
        address.setBounds(50, 200 + 40, 100, 30);
        add(address);

        addressField = new JTextField();
        addressField.setBounds(200, 200 + 40, 200, 30);
        add(addressField);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(50, 240 + 40, 100, 30);
        add(phone);

        phoneField = new JTextField();
        phoneField.setBounds(200, 240 + 40, 200, 30);
        add(phoneField);

        JLabel email = new JLabel("Email");
        email.setBounds(50, 280 + 40, 100, 30);
        add(email);

        emailField = new JTextField();
        emailField.setBounds(200, 280 + 40, 200, 30);
        add(emailField);

        JLabel nidNumber = new JLabel("NID Number");
        nidNumber.setBounds(50, 320 + 40, 100, 30);
        add(nidNumber);

        nidNumberField = new JTextField();
        nidNumberField.setBounds(200, 320 + 40, 200, 30);
        add(nidNumberField);

        JLabel designation = new JLabel("Designation");
        designation.setBounds(50, 360 + 40, 100, 30);
        add(designation);

        designationField = new JTextField();
        designationField.setBounds(200, 360 + 40, 200, 30);
        add(designationField);

        JLabel highestEducation = new JLabel("Highest Education");
        highestEducation.setBounds(50, 400 + 40, 150, 30);
        add(highestEducation);

        String[] education = {"BSc", "MSc", "BA", "MA", "BCom", "MCom", "PHD"};
        highestEducationField = new JComboBox<>(education);
        highestEducationField.setBounds(200, 400 + 40, 200, 30);
        add(highestEducationField);

        JLabel salary = new JLabel("Salary");
        salary.setBounds(50, 440 + 40, 100, 30);
        add(salary);

        salaryField = new JTextField();
        salaryField.setBounds(200, 440 + 40, 200, 30);
        add(salaryField);

        addDetails = new JButton("Add Details");
        addDetails.setBounds(150, 500 + 40, 150, 40);
        addDetails.addActionListener(this);
        add(addDetails);

        back = new JButton("Back");
        back.setBounds(320, 500 + 40, 150, 40);
        back.addActionListener(this);
        add(back);

        setTitle("Add Employee Details");
        setSize(700,700);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addDetails) {
            String id = idField.getText();
            if (isIDAvailable(id)) {
                try (FileWriter fw = new FileWriter("employees.txt", true);
                     BufferedWriter bw = new BufferedWriter(fw);
                     PrintWriter out = new PrintWriter(bw)) {
                    out.println(id + "," + nameField.getText() + "," + fatherNameField.getText() + "," + dobField.getText() + "," +
                            addressField.getText() + "," + phoneField.getText() + "," + emailField.getText() + "," +
                            nidNumberField.getText() + "," + designationField.getText() + "," +
                            highestEducationField.getSelectedItem() + "," + salaryField.getText());
                    JOptionPane.showMessageDialog(null, "Employee Details Added with ID: " + id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "ID not available. Please enter a unique ID.");
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Home();
        }
    }

    private boolean isIDAvailable(String id) {
        try (BufferedReader br = new BufferedReader(new FileReader("employees.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(id)) {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}
