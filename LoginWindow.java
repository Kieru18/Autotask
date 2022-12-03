import javax.swing.*;

public class LoginWindow extends JFrame {
    JLabel titleL, loginL, passL, resultL;
    JTextField loginTF;
    JPasswordField passPF;
    JButton summitB, clearB;

    LoginWindow()
    {
        this.setTitle("AutoTask Login Window");
        this.setLayout(null);
        // this.setLocationRelativeTo(null);
        this.setLocation(0,0);
        this.setSize(800, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }



    public static void main(String[] args) {
        LoginWindow lw = new LoginWindow();
    }
}
