package GUI.Panel;

import java.util.ArrayList;
import java.util.Vector;
import java.awt.*;
// import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import BUS.KhachHangBUS;
import BUS.PhieuXuatBUS;
import BUS.SanPhamBUS;
import DTO.ChiTietPhieuDTO;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;
import GUI.Main;
import GUI.Component.ButtonCustom;
import GUI.Component.CustomComboCheck;
import GUI.Component.InputForm;
import GUI.Component.PanelBorderRadius;
import GUI.Component.SelectForm;

public final class TaoPhieuXuat extends JPanel {
    SanPhamBUS phienBanspBus = new SanPhamBUS();
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this); //gọi phương thức compoment tổ tiên có kiểu window của compoment hiện tại
    // kiểu như cái listKhachHang thì cho owner dô sẽ gọi đc cái jframe của listkhachhang
    PanelBorderRadius right, left;
    JPanel  contentCenter, left_top, content_btn, left_bottom; //là cái main cữ
    JTable tablePhieuXuat, tableSanPham;
    JScrollPane scrollTablePhieuNhap, scrollTableSanPham;
    DefaultTableModel tblModel, tblModelSP; //table co san 
    ButtonCustom btnAddSp, btnEditSP, btnDelete, btnNhapHang;
    InputForm txtMaphieu, txtNhanVien, txtMaSp, txtTenSp, txtMaGiamGia, txtGiaGiam, txtSoLuongSPnhap;
    // SelectForm cbxDanhMuc; ko dùng nữa
    JTextField txtTimKiem;
    Color BackgroundColor = new Color(240, 247, 250);
    
    int sum; //do ctpxuất ko có sẵn tính tiền 
    int maphieu;
    int manv;
    int makh = -1;
    String type;

    ArrayList<SanPhamDTO> ctpb;
    SanPhamBUS spBUS = new SanPhamBUS();
    PhieuXuatBUS phieuXuatBUS = new PhieuXuatBUS();
    SanPhamBUS chiTietSanPhamBUS = new SanPhamBUS();
    KhachHangBUS khachHangBUS = new KhachHangBUS();
    ArrayList<DTO.SanPhamDTO> listSP = spBUS.getAll();
    private ArrayList<SanPhamDTO> ch = new ArrayList<>();

    ArrayList<ChiTietPhieuDTO> chitietphieu = new ArrayList<>();
    ArrayList<SanPhamDTO> chitietsanpham = new ArrayList<>();

    TaiKhoanDTO tk;
    // private int mapb;
    private JLabel lbltongtien;
    private JTextField txtKh;
    private Main mainChinh;
    private ButtonCustom btnQuayLai;
    // private ButtonCustom chonImei;
    private InputForm txtGiaXuat;

    public TaoPhieuXuat(Main mainChinh, TaiKhoanDTO tk, String type) {
        this.mainChinh = mainChinh;
        this.tk = tk;
        this.type = type;
        maphieu = phieuXuatBUS.getMPMAX() + 1;
        initComponent(type);
        loadDataTalbeSanPham(listSP);
    }

    // public void initPadding(){
    // }

    private void initComponent(String type) {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        // Phiếu nhập
        tablePhieuXuat = new JTable();
        scrollTablePhieuNhap = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã SP", "Tên sản phẩm", "Đơn giá", "Số lượng"};
        tblModel.setColumnIdentifiers(header);
        tablePhieuXuat.setModel(tblModel);
        scrollTablePhieuNhap.setViewportView(tablePhieuXuat);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = tablePhieuXuat.getColumnModel();
        for (int i = 0; i < 5; i++) {
            if (i != 2) {
                columnModel.getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        tablePhieuXuat.getColumnModel().getColumn(2).setPreferredWidth(300);
        tablePhieuXuat.setFocusable(false);
        tablePhieuXuat.setDefaultEditor(Object.class, null);
        scrollTablePhieuNhap.setViewportView(tablePhieuXuat);

        // Table sản phẩm
        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();
        tblModelSP = new DefaultTableModel();
        String[] headerSP = new String[]{"Mã SP", "Tên sản phẩm", "Số lượng tồn"};
        tblModelSP.setColumnIdentifiers(headerSP);
        tableSanPham.setModel(tblModelSP);
        scrollTableSanPham.setViewportView(tableSanPham);
        tableSanPham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(1).setPreferredWidth(300);
        tableSanPham.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        tableSanPham.setFocusable(false);
        scrollTableSanPham.setViewportView(tableSanPham);



    }









}