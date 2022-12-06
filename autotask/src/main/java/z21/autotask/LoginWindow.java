package z21.autotask;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame implements ActionListener {
    JLabel titleL, loginL, passL, resultL;
    JTextField loginTF;
    JPasswordField passPF;
    JButton submitB, clearB;
    JCheckBox passCB;

    LoginWindow()
    {
        this.setTitle("AutoTask Login Window");
        this.setLayout(null);
        // this.setLocationRelativeTo(null);
        this.setLocation(0,0);
        this.setSize(1000, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        passL = new JLabel("Password:");
        passL.setBounds(120, 250, 80,40);
        this.add(passL);

        loginL = new JLabel("Login:");
        loginL.setBounds(120, 210, 80,40);
        this.add(loginL);

        passPF = new JPasswordField();
        passPF.setBounds(200,250,200,40);
        this.add(passPF);

        loginTF = new JTextField();
        loginTF.setBounds(200,210,200,40);
        this.add(loginTF);

        passCB = new JCheckBox("visible");
        passCB.setBounds(400, 250, 100, 40);
        passCB.addActionListener(this);
        this.add(passCB);

        titleL = new JLabel();
        titleL.setText("Welcome to AutoTask!");
        titleL.setBounds(200,150, 200,40);
        this.add(titleL);

        submitB = new JButton("submit");
        submitB.setBounds(200, 290, 100, 40);
        submitB.addActionListener(this);
        this.add(submitB);

        clearB = new JButton("Clear");
        clearB.setBounds(300, 290, 100,40);
        clearB.addActionListener(this);
        this.add(clearB);

        resultL = new JLabel("Here will be your result");
        resultL.setBounds(200,330, 200, 40);
        this.add(resultL);



        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == submitB) {
            String correctLogin = "Fineasz";
            String correctPassword = "ZlotaRybka";
            String inputLogin = loginTF.getText();
            String inputPassword = new String(passPF.getPassword());
            System.out.println(inputPassword);

            String result = "Login Details Invalid!"; // could be initialized in else statement

            if (inputLogin.equals(correctLogin) && inputPassword.equals(correctPassword))
            {
                result = "Correct Login Details";
            }
            resultL.setText(result);
        }
        else if (e.getSource() == clearB)
        {
            loginTF.setText("");
            passPF.setText("");
        }
        else if (e.getSource() == passCB)
        {
            if(passCB.isSelected())
            {
                passPF.setEchoChar((char)0);
            }
            else
            {
                passPF.setEchoChar('●');
            }
        }

    }
    public static void main(String[] args) {
        LoginWindow lw = new LoginWindow();
    }
}
