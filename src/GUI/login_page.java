package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import GUI.Dialog.QuenMatKhau;
import javax.swing.border.EmptyBorder;
import GUI.Component.InputForm;
import helper.BCrypt;
import java.awt.event.MouseAdapter;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;


public class login_page extends JFrame implements KeyListener{

    private JPanel login_nhap;
    private JLabel lb1 , lb2, lb_img_1, lb_img_2;
    private InputForm txtUsername, txtPassword;
    private JButton bt;

    public login_page() {
        init();
        txtUsername.setText("admin");
        txtPassword.setPass("123456");
        this.setVisible(true);
    }

    private void init() { 
        this.setTitle("Đăng nhập" );
        this.setSize(new Dimension(900 , 500));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(0 , 0));
        JFrame jf = this ;

        login_nhap = new JPanel();
        login_nhap.setLayout(new BoxLayout(login_nhap, BoxLayout.Y_AXIS)); // Vertical BoxLayout
        login_nhap.setBackground(Color.WHITE);
        login_nhap.setLayout(new GridLayout(5 , 2 , 5 , 5));
        login_nhap.setBorder(new EmptyBorder(0,0,0,15));
        login_nhap.setPreferredSize(new Dimension(400 , 500));
        
        GridBagConstraints gbc = new GridBagConstraints();
        lb1 = new JLabel("LOGIN FORM");
        lb1.setFont(new Font("Tahoma", Font.BOLD , 24));
        lb1.setForeground(Color.BLACK); // Set text color
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.fill = GridBagConstraints.HORIZONTAL; // Center horizontally
        lb1.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text horizontally
        login_nhap.add(lb1);
        txtUsername = new InputForm("Tên đăng nhập");
        txtUsername.setPreferredSize(new Dimension(300, 40)); // Adjust dimensions
        txtUsername.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Add borde
        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        login_nhap.add(txtUsername);
        txtPassword = new InputForm("Mật khẩu", "password");
        txtPassword.setPreferredSize(new Dimension(300, 40)); // Adjust dimensions
        txtPassword.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Add border
        gbc.gridy++;
        gbc.gridx = 0;

        login_nhap.add(txtPassword);
        txtUsername.getTxtForm().addKeyListener(this);
        txtPassword.getTxtPass().addKeyListener(this);
        lb2 = new JLabel("<html><u><i style='font-size: 14px;'>Quên mật khẩu ?</i></u></html>", JLabel.RIGHT);
        lb2.setPreferredSize(new Dimension(300,30));
        lb2.setForeground(Color.BLUE); // Set text color
        lb2.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        lb2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lb2.setForeground(new Color(173, 216, 230));
            }
            @Override
            public void mouseClicked(MouseEvent evt) {
                QuenMatKhau qmk = new QuenMatKhau(jf, true);
                qmk.setVisible(true);
            }
            public void mouseExited(MouseEvent e) {
                lb2.setForeground(Color.BLACK);
            }
        });

        login_nhap.add(lb2);

        JPanel buttonPanel = new JPanel(); 
        buttonPanel.setBackground(Color.WHITE);   
        bt = new JButton("Đăng nhập");
        bt.setPreferredSize(new Dimension(300, 50));
        bt.setBackground(new Color(0,153,255));
        bt.setFont(new Font("Tahoma", Font.BOLD, 16)); // Adjust font and size
        bt.setForeground(Color.WHITE);
        buttonPanel.add(bt);
        bt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bt.setBackground(new Color(138, 43, 226));
            }
            public void mousePressed(MouseEvent evt) {
                try {
                    checkLogin();
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(login_page.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            public void mouseExited(MouseEvent e) {
                bt.setBackground(Color.BLACK);
            }
        });

        login_nhap.add(buttonPanel); 
        this.add(login_nhap , BorderLayout.CENTER);

        lb_img_1 = new JLabel(new ImageIcon("./src/img/trai.png"));
        // lb_img_1.setBorder(new EmptyBorder(30,0,30,80));
        
        lb_img_1.setBackground(Color.WHITE);
        lb_img_1.setPreferredSize(new Dimension(400,150));
        lb_img_1.setAlignmentX(Component.CENTER_ALIGNMENT); // Centerr
        this.add(lb_img_1 , BorderLayout.WEST);

        // lb_img_2 = new JLabel(new ImageIcon("./src/img/tra.png"));
        // // lb_img_2.setBorder(new EmptyBorder(30,300,30,1));
        // lb_img_2.setBackground(Color.WHITE);
        // lb_img_2.setPreferredSize(new Dimension(300,50));
        // this.add(lb_img_2 , BorderLayout.EAST);

    }
        public void checkLogin() throws UnsupportedLookAndFeelException {
        String usernameCheck = txtUsername.getText();
        String passwordCheck = txtPassword.getPass();
        if (usernameCheck.equals("") || passwordCheck.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
        } else {
            TaiKhoanDTO tk = TaiKhoanDAO.getInstance().selectByUser(usernameCheck);
            if (tk == null) {
                JOptionPane.showMessageDialog(this, "Tài khoản của bạn không tồn tại trên hệ thống", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (tk.getTT() == 0) {
                    JOptionPane.showMessageDialog(this, "Tài khoản của bạn đang bị khóa", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (BCrypt.checkpw(passwordCheck, tk.getMK())) {
                        try {
                            this.dispose();
                            Main main = new Main(tk);
                            main.setVisible(true);
                        } catch (UnsupportedLookAndFeelException ex) {
                            Logger.getLogger(login_page.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Mật khẩu không khớp", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
    }
    

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();

        UIManager.put("PasswordField.showRevealButton", true);
        new login_page();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                checkLogin();
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(login_page.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}