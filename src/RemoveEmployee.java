import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class RemoveEmployee extends JFrame implements ActionListener {

    JTextField idField;
    JButton removeButton, cancelButton;

    public RemoveEmployee() {
        setTitle("Remove Employee");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only close this window on dispose

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel idLabel = new JLabel("Employee ID:");
        idField = new JTextField();
        panel.add(idLabel);
        panel.add(idField);

        removeButton = new JButton("Remove");
        removeButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        panel.add(removeButton);
        panel.add(cancelButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeButton) {
            String id = idField.getText().trim();
            if (!id.isEmpty()) {
                removeEmployee(id);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter an Employee ID to remove.");
            }
        } else if (e.getSource() == cancelButton) {
            dispose(); // Close this window
        }
    }

    private void removeEmployee(String id) {
        try (BufferedReader br = new BufferedReader(new FileReader("employees.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt"))) {

            String line;
            boolean removed = false;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(id)) {
                    removed = true;
                    continue; // Skip writing this line to temp file
                }
                bw.write(line + "\n"); // Write to temp file
            }

            if (removed) {
                JOptionPane.showMessageDialog(this, "Employee with ID: " + id + " removed successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "No employee found with ID: " + id);
            }

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while removing the employee.");
        }

        // Delete the original file and rename the temp file
        File originalFile = new File("employees.txt");
        File tempFile = new File("temp.txt");
        if (tempFile.renameTo(originalFile)) {
            System.out.println("File renamed successfully");
        } else {
            System.out.println("Failed to rename file");
        }

        dispose(); // Close this window
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}
