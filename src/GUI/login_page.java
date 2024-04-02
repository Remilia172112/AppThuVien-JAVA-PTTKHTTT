package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.font.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.InputForm;
import java.awt.image.*;

import java.awt.font.*;

public class login_page extends JFrame {

    private JPanel login_nhap;
    private JLabel lb1;
    private InputForm txtUsername;
    private InputForm txtPassword;
    private JLabel lb2;
    private JButton bt;
    private JLabel lb_img_1;
    private JLabel lb_img_2;

    public login_page() {
        init();
        this.setVisible(true);
    }

    private void init() { 
        this.setTitle("Đăng nhập" );
        this.setSize(new Dimension(1000 , 500));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(0 , 0));

        login_nhap = new JPanel();
        login_nhap.setBackground(Color.WHITE);
        login_nhap.setLayout(new GridLayout(7 , 1 , 10 , 0));
        login_nhap.setBorder(new EmptyBorder(0,0,0,15));
        login_nhap.setPreferredSize(new Dimension(400 , 500));

        lb1 = new JLabel("Đăng nhập vào BEST BOOK");
        lb1.setFont(new Font("Arial", Font.BOLD , 28));
        login_nhap.add(lb1);
        txtUsername = new InputForm("Tên đăng nhập");
        login_nhap.add(txtUsername);
        txtPassword = new InputForm("Mật khẩu", "password");
        login_nhap.add(txtPassword);

        lb2 = new JLabel("<html><u><i style='font-size: 14px;'>Quên mật khẩu ?</i></u></html>", JLabel.RIGHT);

        login_nhap.add(lb2);
        lb2.setPreferredSize(new Dimension(300,30));

        JPanel buttonPanel = new JPanel(); 
        buttonPanel.setBackground(Color.WHITE);   
        bt = new JButton("Đăng nhập");
        bt.setPreferredSize(new Dimension(200, 50));
        bt.setBackground(Color.BLACK);
        bt.setForeground(Color.WHITE);
        buttonPanel.add(bt);

        login_nhap.add(buttonPanel); 
        this.add(login_nhap , BorderLayout.CENTER);

        lb_img_1 = new JLabel(new ImageIcon("./img/gai.png"));
        // lb_img_1.setBorder(new EmptyBorder(30,0,30,80));
        lb_img_1.setBackground(Color.WHITE);
        lb_img_1.setPreferredSize(new Dimension(300,50));
        this.add(lb_img_1 , BorderLayout.WEST);

        lb_img_2 = new JLabel(new ImageIcon("./img/trai.png"));
        // lb_img_2.setBorder(new EmptyBorder(30,300,30,1));
        lb_img_2.setBackground(Color.WHITE);
        lb_img_2.setPreferredSize(new Dimension(300,50));
        this.add(lb_img_2 , BorderLayout.EAST);

    }

    public static void main(String[] args) {
        new login_page();
    }
}