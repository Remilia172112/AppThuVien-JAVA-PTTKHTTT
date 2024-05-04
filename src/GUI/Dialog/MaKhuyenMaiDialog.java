package GUI.Dialog;

import DAO.MaKhuyenMaiDAO;
import DTO.ChiTietMaKhuyenMaiDTO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.MaKhuyenMaiDTO;
import DTO.SanPhamDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputDate;
import GUI.Component.InputForm;
import GUI.Component.PanelBorderRadius;
import GUI.Panel.MaKhuyenMai;
import helper.Validation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import BUS.SanPhamBUS;

public final class MaKhuyenMaiDialog extends JDialog implements ItemListener, ActionListener {

    PanelBorderRadius left, right;
    private MaKhuyenMai jpkvk;
    private HeaderTitle titlePage;
    JTable tableSanPham;
    JScrollPane scrollTableSanPham;
    DefaultTableModel tblModelSP;
    private JPanel pnmain, pnbottom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm makhuyenmai;
    InputDate dateStart, dateEnd;
    private MaKhuyenMaiDTO mkm;

    SanPhamBUS spBUS = new SanPhamBUS();
    ArrayList<SanPhamDTO> listSP = spBUS.getAll(); // list ben kho 
    ArrayList<SanPhamDTO> listSP_tmp = new ArrayList<>(); 
    ArrayList<ChiTietMaKhuyenMaiDTO> chitietmkm;

    public MaKhuyenMaiDialog(MaKhuyenMai jpkvk, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpkvk = jpkvk;
        initComponents(title, type);
        loadDataTalbeSanPham(listSP);
    }

    public MaKhuyenMaiDialog(MaKhuyenMai jpkvk, JFrame owner, String title, boolean modal, String type, MaKhuyenMaiDTO mkm) {
        super(owner, title, modal);
        this.jpkvk = jpkvk;
        this.mkm = mkm;
        initComponents(title, type);
        loadDataTalbeSanPham(listSP);
    }

    public void initComponents(String title, String type) {
        this.setSize(new Dimension(1000, 800));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnmain = new JPanel(new GridLayout(2, 2, 20, 0));
        pnmain.setBackground(Color.white);
        makhuyenmai = new InputForm("Mã khuyến mãi");
        dateStart = new InputDate("Từ ngày");
        dateEnd = new InputDate("Đến ngày");


        pnmain.add(makhuyenmai);
        pnmain.add(dateStart);
        pnmain.add(dateEnd);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm mã khuyến mãi", "success", 14);
        btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);

        //Add MouseListener btn
        btnThem.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnHuyBo.addActionListener(this);

        switch (type) {
            case "create" -> pnbottom.add(btnThem);
            case "update" -> {
                pnbottom.add(btnCapNhat);
                initInfo();
            }
            default -> throw new AssertionError();
        }
        pnbottom.add(btnHuyBo);
        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.add(pnbottom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); //phương thức để định dạng văn bản, màu sắc, căn chỉnh và các thuộc tính hiển thị khác cho các ô trong bản
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();
        tblModelSP = new DefaultTableModel();
        String[] headerSP = new String[]{"Mã SP", "Tên sản phẩm", "Số lượng tồn"};
        tblModelSP.setColumnIdentifiers(headerSP);
        tableSanPham.setModel(tblModelSP);
        scrollTableSanPham.setViewportView(tableSanPham);
        tableSanPham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(1).setPreferredWidth(300);
        tableSanPham.setDefaultEditor(Object.class, null);
        tableSanPham.setFocusable(false);
        scrollTableSanPham.setViewportView(tableSanPham);

        tableSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = tableSanPham.getSelectedRow();
                if (index != -1) {
                    
                }
            }
        });
        left = new PanelBorderRadius();
        left.setLayout(new BorderLayout(0, 5));
        left.setBackground(Color.white);

        left.add(scrollTableSanPham, BorderLayout.CENTER);

        pnmain.add(left);


    }

    public void initInfo() {
        makhuyenmai.setText(mkm.getMKM());
    }

       boolean Validation(){
        if (Validation.isEmpty(makhuyenmai.getText())) {
            JOptionPane.showMessageDialog(this, "Tên khu vực sách không được rỗng", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
            return false;
         }
          return true;
    }

    public void loadDataTalbeSanPham(ArrayList<SanPhamDTO> result) {
        tblModelSP.setRowCount(0);
        for (SanPhamDTO sp : result) {
            tblModelSP.addRow(new Object[]{sp.getMSP(), sp.getTEN(), sp.getSL()});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThem && Validation()) {
            int makhuvuc = MaKhuyenMaiDAO.getInstance().getAutoIncrement();
            String tenkhuvuc = this.makhuyenmai.getText();
            String ghichu = this.ghichu.getText();
            jpkvk.kvkBUS.add(new MaKhuyenMaiDTO(makhuvuc, tenkhuvuc, ghichu));
            jpkvk.loadDataTable(jpkvk.listKVK);
            dispose();
        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat && Validation()) {
            String tenkhuvuc = this.makhuyenmai.getText();
            String ghichu = this.ghichu.getText();
            jpkvk.kvkBUS.update(new MaKhuyenMaiDTO(mkm.getMakhuvuc(), tenkhuvuc, ghichu));
            jpkvk.loadDataTable(jpkvk.listKVK);
            dispose();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'itemStateChanged'");
    }

}
