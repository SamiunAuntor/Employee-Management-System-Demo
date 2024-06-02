import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateEmployee extends JFrame implements ActionListener {

    JButton homeButton;
    UpdateEmployee()
    {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel label = new JLabel("COMING SOON");
        label.setBounds(150, 10,650,50);
        label.setFont(new Font("serif", Font.BOLD, 50));
        label.setForeground(Color.BLACK);
        add(label);

        homeButton = new JButton("Go back to Home");
        homeButton.setBounds(220, 240, 250, 50);
        homeButton.addActionListener(this);
        add(homeButton);


        setTitle("Update Employee");
        setSize(700,700);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == homeButton) {
            setVisible(false);
            new Home();
        }
    }
    public static void main(String[] args) {
        new UpdateEmployee();
    }
}
