import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

    JTextField user_name;
    JPasswordField pass_word;

    Login() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel username = new JLabel("Username");
        username.setBounds(50, 460, 300, 30);
        username.setFont(new Font("serif", Font.BOLD, 20));
        username.setForeground(Color.BLACK);
        add(username);

        user_name = new JTextField();
        user_name.setBounds(180, 465, 300, 20);
        add(user_name);

        JLabel password = new JLabel("Password");
        password.setBounds(50, 510, 300, 30);
        password.setFont(new Font("serif", Font.BOLD, 20));
        password.setForeground(Color.BLACK);
        add(password);

        pass_word = new JPasswordField();
        pass_word.setBounds(180, 515, 300, 20);
        add(pass_word);

        JButton login = new JButton("LOGIN");
        login.setBounds(110, 575, 500, 50);
        add(login);
        login.addActionListener(this);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Login.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 695, 695);
        add(image);

        setSize(700, 700);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent ae)
    {
        try
        {
            String currentUsername = user_name.getText();
            String currentPassord = new String(pass_word.getPassword());

            Connection c = new Connection();

            if (c.authenticate(currentUsername, currentPassord))
            {
                setVisible(false);
                new Home();
            }
            else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
