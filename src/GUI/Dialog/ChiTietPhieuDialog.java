package GUI.Dialog;

import BUS.SanPhamBUS;
import BUS.PhieuNhapBUS;
import BUS.PhieuXuatBUS;
import BUS.PhieuTraBUS;
import DAO.KhachHangDAO;
import DAO.NhaCungCapDAO;
import DAO.NhanVienDAO;
import DAO.SanPhamDAO;
import DTO.ChiTietPhieuDTO;
import DTO.SanPhamDTO;
import DTO.PhieuNhapDTO;
import DTO.PhieuXuatDTO;
import DTO.PhieuTraDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import helper.Formater;
import helper.writePDF;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public final class ChiTietPhieuDialog extends JDialog implements ActionListener {

    HeaderTitle titlePage;
    JPanel pnmain, pnmain_top, pnmain_bottom, pnmain_btn; //bỏ pnmain_bottom_right, pnmain_bottom_left 
    InputForm txtMaPhieu, txtNhanVien, txtNhaCungCap, txtThoiGian;
    DefaultTableModel tblModel;
    JTable table, tblImei;
    JScrollPane scrollTable;

    PhieuNhapDTO phieunhap;
    PhieuXuatDTO phieuxuat;
    PhieuTraDTO phieutra;
    SanPhamBUS spBus = new SanPhamBUS();
    PhieuNhapBUS phieunhapBus;
    PhieuXuatBUS phieuxuatBus;
    PhieuTraBUS phieutraBus;

    ButtonCustom btnPdf, btnHuyBo, btnDuyet;

    ArrayList<ChiTietPhieuDTO> chitietphieu;

    // HashMap<Integer, ArrayList<SanPhamDTO>> chitietsanpham = new HashMap<>();
    //phieu nhap
    public ChiTietPhieuDialog(JFrame owner, String title, boolean modal, PhieuNhapDTO phieunhapDTO) {
        super(owner, title, modal);
        this.phieunhap = phieunhapDTO;
        phieunhapBus = new PhieuNhapBUS();
        chitietphieu = phieunhapBus.getChiTietPhieu_Type(phieunhapDTO.getMP());
        initComponent(title);
        if(phieunhapDTO.getTT() != 2) {
            btnDuyet.setEnabled(false);
        }
        initPhieuNhap();
        loadDataTableChiTietPhieu(chitietphieu);
        this.setVisible(true);
    }
    //phieu xuat
    public ChiTietPhieuDialog(JFrame owner, String title, boolean modal, PhieuXuatDTO phieuxuatDTO) {
        super(owner, title, modal);
        this.phieuxuat = phieuxuatDTO;
        phieuxuatBus = new PhieuXuatBUS();
        chitietphieu = phieuxuatBus.selectCTP(phieuxuatDTO.getMP());
        initComponent(title);
        if(phieuxuatDTO.getTT() != 2) {
            btnDuyet.setEnabled(false);
        }
        initPhieuXuat();
        loadDataTableChiTietPhieu(chitietphieu);
        this.setVisible(true);
    }

    public ChiTietPhieuDialog(JFrame owner, String title, boolean modal, PhieuTraDTO phieutraDTO) {
        super(owner, title, modal);
        this.phieutra = phieutraDTO;
        phieutraBus = new PhieuTraBUS();
        chitietphieu = phieutraBus.getChiTietPhieu_Type(phieutraDTO.getMP());
        initComponent(title);
        if(phieutraDTO.getTT() != 2) {
            btnDuyet.setEnabled(false);
        }
        initPhieuTra();
        loadDataTableChiTietPhieu(chitietphieu);
        this.setVisible(true);
    }

    public void initPhieuNhap() {
        txtMaPhieu.setText("PN" + Integer.toString(this.phieunhap.getMP()));
        txtNhaCungCap.setText(NhaCungCapDAO.getInstance().selectById(phieunhap.getMNCC() + "").getTenncc());
        txtNhanVien.setText(NhanVienDAO.getInstance().selectById(phieunhap.getMNV() + "").getHOTEN());
        txtThoiGian.setText(Formater.FormatTime(phieunhap.getTG()));
    }

    public void initPhieuXuat() {
        txtMaPhieu.setText("PX" + Integer.toString(this.phieuxuat.getMP()));
        txtNhaCungCap.setTitle("Khách hàng");
        txtNhaCungCap.setText(KhachHangDAO.getInstance().selectById(phieuxuat.getMKH() + "").getHoten());
        txtNhanVien.setText(NhanVienDAO.getInstance().selectById(phieuxuat.getMNV() + "").getHOTEN());
        txtThoiGian.setText(Formater.FormatTime(phieuxuat.getTG()));
    }

    public void initPhieuTra() {
        txtMaPhieu.setText("PX" + Integer.toString(this.phieutra.getMP()));
        txtNhaCungCap.setTitle("Khách hàng");
        txtNhaCungCap.setText(KhachHangDAO.getInstance().selectById(phieutra.getMKH() + "").getHoten());
        txtNhanVien.setText(NhanVienDAO.getInstance().selectById(phieutra.getMNV() + "").getHOTEN());
        txtThoiGian.setText(Formater.FormatTime(phieutra.getTG()));
    }

    public void loadDataTableChiTietPhieu(ArrayList<ChiTietPhieuDTO> ctPhieu) {
        tblModel.setRowCount(0);
        for (int i = 0; i < ctPhieu.size(); i++) {
            SanPhamDTO sp = spBus.getByMaSP(ctPhieu.get(i).getMSP());
            tblModel.addRow(new Object[]{
                i + 1, sp.getMSP(), SanPhamDAO.getInstance().selectById(sp.getMSP()+"").getTEN(), 
                Formater.FormatVND(ctPhieu.get(i).getTIEN()), ctPhieu.get(i).getSL()
            });
        }
    }

    public void initComponent(String title) {
        this.setSize(new Dimension(1100, 500));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());

        pnmain = new JPanel(new BorderLayout());

        pnmain_top = new JPanel(new GridLayout(1, 4));
        txtMaPhieu = new InputForm("Mã phiếu");
        txtNhanVien = new InputForm("Nhân viên nhập");
        txtNhaCungCap = new InputForm("Nhà cung cấp");
        txtThoiGian = new InputForm("Thời gian tạo");

        txtMaPhieu.setEditable(false);
        txtNhanVien.setEditable(false);
        txtNhaCungCap.setEditable(false);
        txtThoiGian.setEditable(false);

        pnmain_top.add(txtMaPhieu);
        pnmain_top.add(txtNhanVien);
        pnmain_top.add(txtNhaCungCap);
        pnmain_top.add(txtThoiGian);

        pnmain_bottom = new JPanel(new GridLayout(1, 5));
        pnmain_bottom.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnmain_bottom.setBackground(Color.WHITE);

        // pnmain_bottom_left = new JPanel(new GridLayout(1, 1));
        table = new JTable();
        scrollTable = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã SP", "Tên SP", "Đơn giá", "Số lượng"};
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);
        table.setFocusable(false);
        scrollTable.setViewportView(table);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
    
        pnmain_bottom.add(scrollTable);

        pnmain_btn = new JPanel(new FlowLayout());
        pnmain_btn.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnmain_btn.setBackground(Color.white);
        btnPdf = new ButtonCustom("Xuất file PDF", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        btnDuyet = new ButtonCustom("Duyệt phiếu", "success", 14);
        btnDuyet.addActionListener(this);
        btnPdf.addActionListener(this);
        btnHuyBo.addActionListener(this);
        pnmain_btn.add(btnDuyet);
        pnmain_btn.add(btnPdf);
        pnmain_btn.add(btnHuyBo);

        pnmain.add(pnmain_top, BorderLayout.NORTH);
        pnmain.add(pnmain_bottom, BorderLayout.CENTER);
        pnmain.add(pnmain_btn, BorderLayout.SOUTH);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnHuyBo) {
            dispose();
        }
        if (source == btnPdf) {
            writePDF w = new writePDF();
            if (this.phieuxuat != null) {
                w.writePX(phieuxuat.getMP());
            }
            if (this.phieunhap != null) {
                w.writePN(phieunhap.getMP());
            }
            if (this.phieutra != null) {
                w.writePT(phieutra.getMP());
            }
        }
        if(source == btnDuyet) {
            if (this.phieuxuat != null) {
                if(!phieuxuatBus.checkSLPx(phieuxuat.getMP())) JOptionPane.showMessageDialog(null, "Không đủ số lượng để tạo phiếu!");
                else {
                    phieuxuat.setTT(1);
                    phieuxuatBus.update(phieuxuat);
                }

            }
            if (this.phieunhap != null) {

            }
        }
    }
}
