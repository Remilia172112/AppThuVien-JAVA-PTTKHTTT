package GUI.Dialog;

import BUS.NhanVienBUS;
import BUS.TaiKhoanBUS;
import DAO.NhanVienDAO;
import DAO.TaiKhoanDAO;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Component.MenuTaskbar;
import GUI.Component.NumericDocumentFilter;
import helper.BCrypt;
import helper.Validation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import java.awt.Frame;




public class RegisterDialog extends JDialog implements ActionListener {

    ButtonCustom save, cancel;
    HeaderTitle title;
    JPanel top, center, bottom;
    InputForm  phone, EMAIL, password, confirm;
    NhanVienDTO nv;
    TaiKhoanBUS tkbus;
    NhanVienBUS nvbus;
    MenuTaskbar menuTaskbar;
    JLabel[] jl;
    JPanel[] panel;
    JLabel change;
    JPanel pn_1 , pn_2 , pn_3;
    InputForm current_pass , new_pass, confirm_pass;
    public RegisterDialog(Frame parent, boolean modal) {
        super(parent, modal);
        initComponent();
        setLocationRelativeTo(null);
    }

    public void initComponent() {
        // tkbus = new TaiKhoanBUS();
        // nvbus = new NhanVienBUS();
        // this.menuTaskbar = menutaskbar;
        this.setSize(400, 550);
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(Color.WHITE);
        this.setResizable(false);
        // nv = menuTaskbar.nhanVienDTO;
        top = new JPanel();
        top.setBackground(new Color(0x279C40));
        top.setLayout(new FlowLayout(0, 0, 0));
        title = new HeaderTitle("ĐĂNG KÍ TÀI KHOẢN");
        title.setColor("279C40");
        top.add(title);
        this.add(top, BorderLayout.NORTH);

        center = new JPanel(new GridLayout(4,1));
        center.setBorder(new EmptyBorder(20, 10,20, 10));
        center.setBackground(Color.WHITE);
        String opt[] = {"Số điện thoại", "Email", "Mật khẩu", "Xác nhận mật khẩu"};
        panel = new JPanel[4];
        panel[0] = new JPanel(new GridLayout(1, 1));
        panel[0].setPreferredSize(new Dimension(400, 100));
        phone = new InputForm(opt[0]);
        PlainDocument phonex = (PlainDocument) phone.getTxtForm().getDocument();
        phonex.setDocumentFilter((new NumericDocumentFilter())); // dòng này và trên để chỉ nhập số
        panel[0].add(phone);
        // for(int i = 1 ; i < opt.length ; i++) {
            // panel[i] = new JPanel(new GridLayout(1,1));
            // panel[i].setPreferredSize(new Dimension(400,100));
        // }
        panel[1] = new JPanel(new GridLayout(1, 1));
        panel[1].setPreferredSize(new Dimension(400, 100));
        EMAIL = new InputForm(opt[1]);
        panel[1].add(EMAIL);
        panel[2] = new JPanel(new GridLayout(1, 1));
        panel[2].setPreferredSize(new Dimension(400, 100));
        password = new InputForm(opt[2], "password");
        panel[2].add(password);
        panel[3] = new JPanel(new GridLayout(1 , 1));
        panel[3].setPreferredSize(new Dimension(400, 100));
        confirm = new InputForm(opt[3],"password");
        panel[3].add(confirm);


        center.add(panel[0]);
        center.add(panel[1]);
        center.add(panel[2]);
        center.add(panel[3]);
        this.add(center, BorderLayout.CENTER);

        bottom = new JPanel(new GridLayout(1,1));
        bottom.setBackground(new Color(0x66F284));

        save = new ButtonCustom("Đăng kí", "register", 15);
        save.addActionListener(this);
        bottom.add(save);
        this.add(bottom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == save) {
                            TaiKhoanDTO tkdto = tkbus.getTaiKhoan(tkbus.getTaiKhoanByMaNV(nv.getMNV()));
                            if (Validation.isEmpty(current_pass.getPass())) {
                                JOptionPane.showMessageDialog(this, "Mật khẩu hiện tại không được rỗng", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                            } else if (Validation.isEmpty(new_pass.getPass())||new_pass.getPass().length()<6) {
                                JOptionPane.showMessageDialog(this, "Mật khẩu mới không được rỗng và có ít nhất 6 ký tự", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                            } else if (Validation.isEmpty(confirm_pass.getPass())) {
                                JOptionPane.showMessageDialog(this, "Mật khẩu nhập lại không được rỗng", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                                return;
                            } else if (!new_pass.getPass().equals(confirm_pass.getPass()) ) {
                                JOptionPane.showMessageDialog(this, "Mật khẩu nhập lại không khớp với mật khẩu mới", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                                return;
                            } else {
                                if (BCrypt.checkpw(current_pass.getPass(), tkdto.getMK())) {
                                    String pass = BCrypt.hashpw(confirm_pass.getPass(), BCrypt.gensalt(12));
                                    TaiKhoanDAO.getInstance().updatePass(nv.getEMAIL(), pass);
                                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                                    current_pass.setPass("");
                                    new_pass.setPass("");
                                    confirm_pass.setPass("");
                                    center.removeAll();
                                } else {
                                    JOptionPane.showMessageDialog(this, "Mật khẩu hiện tại không đúng", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                String text_phone = phone.getText(); 
                String text_email = EMAIL.getText(); 
                
                if(text_phone.equals(nv.getSDT()) &&  text_email.equals(nv.getEMAIL()) ) JOptionPane.showMessageDialog(this, "Đã sửa gì đâu mà lưu ?" , "Chỉnh sửa PHONE and EMAIL", JOptionPane.WARNING_MESSAGE);
                else {
                    boolean changed = false;
                    if (!text_phone.equals(nv.getSDT()) ) {
                        if (Validation.isEmpty(phone.getText()) || phone.getText().length() != 10) {
                        JOptionPane.showMessageDialog(this, "Số điện thoại không được rỗng và phải có 10 ký tự sô", "Chỉnh sửa số điện thoại", JOptionPane.WARNING_MESSAGE);
                    } else {
                        NhanVienDTO nvdto = new NhanVienDTO(nv.getMNV(), nv.getHOTEN(), nv.getGIOITINH(), nv.getNGAYSINH(), text_phone, nv.getTT(), nv.getEMAIL());
                        NhanVienDAO.getInstance().update(nvdto);
                        changed = true;
                        }
                    }

                    if (!text_email.equals(nv.getEMAIL()) ) {
                        if (Validation.isEmpty(EMAIL.getText()) || !Validation.isEmail(EMAIL.getText())) {
                        JOptionPane.showMessageDialog(this, "Email không được rỗng và phải đúng định dạng", "Chỉnh sửa EMAIL", JOptionPane.WARNING_MESSAGE);
                        } else {
                            NhanVienDTO nvdto = new NhanVienDTO(nv.getMNV(), nv.getHOTEN(), nv.getGIOITINH(), nv.getNGAYSINH(), nv.getSDT(), nv.getTT(), text_email);
                            NhanVienDAO.getInstance().update(nvdto);
                            changed = true;
                        }
                    }
                    if(changed) {
                        JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                        this.dispose();
                    }
                }
            }
        menuTaskbar.resetChange();
    }
}
