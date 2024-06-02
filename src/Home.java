import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {

    JButton addEmployee, viewEmployee, updateEmployee, removeEmployee;

    Home() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Home.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 695, 665);
        add(image);

        JLabel heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        heading.setBounds(237, 80, 300, 50);
        image.add(heading);

        addEmployee = new JButton("Add New Employee");
        addEmployee.setBounds(242, 135, 200, 30);
        image.add(addEmployee);
        addEmployee.addActionListener(this);

        viewEmployee = new JButton("View Employees");
        viewEmployee.setBounds(242, 180, 200, 30);
        image.add(viewEmployee);
        viewEmployee.addActionListener(this);

        updateEmployee = new JButton("Update Employee");
        updateEmployee.setBounds(242, 225, 200, 30);
        image.add(updateEmployee);
        updateEmployee.addActionListener(this);

        removeEmployee = new JButton("Remove Employee");
        removeEmployee.setBounds(242, 270, 200, 30);
        image.add(removeEmployee);
        removeEmployee.addActionListener(this);

        setTitle("Home");
        setSize(700, 700);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addEmployee)
        {
            setVisible(false);
            new AddEmployee();
        }
        else if (ae.getSource() == viewEmployee)
        {
            setVisible(false);
            new ViewEmployee();
        }
        else if (ae.getSource() == updateEmployee)
        {
            setVisible(false);
            new UpdateEmployee();
        }
        else {
            setVisible(false);
            new RemoveEmployee();
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}
