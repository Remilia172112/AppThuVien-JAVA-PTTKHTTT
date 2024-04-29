package GUI.Panel;

import java.awt.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


import BUS.NhaCungCapBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import DTO.ChiTietPhieuNhapDTO;
import DTO.NhanVienDTO;
import DTO.SanPhamDTO;
import GUI.Main;
import GUI.Component.ButtonCustom;
import GUI.Component.InputForm;
import GUI.Component.PanelBorderRadius;
import GUI.Component.SelectForm;


public final class TaoPhieuNhap extends JPanel implements ItemListener, ActionListener {
    
    PanelBorderRadius left, right;
    JTable tablePhieuNhap, tableSanPham; //tablePhieuNhap ở left_bottom chứa sp của phiếu, tableSanPham chứa sp đang bán
    JScrollPane scrollTablePhieuNhap, scrollTableSanPham;
    JPanel contentCenter, left_top, left_bottom, content_btn;
    DefaultTableModel tblModel, tblModelSP; //table co san 
    ButtonCustom btnAddSp, btnEditSP, btnDelete, btnImport, btnNhapHang;
    InputForm txtMaphieu, txtNhanVien, txtMaSp, txtTenSp, txtDongia, txtMaISBN, txtSoLuongSPnhap ;
    SelectForm cbxNhaCungCap; //, cbxDanhMuc
    JTextField txtTimKiem;
    JLabel  lbltongtien;

    Main m;
    Color BackgroundColor = new Color(240,247,250);

    SanPhamBUS spBUS = new SanPhamBUS();
    NhaCungCapBUS nccBus = new NhaCungCapBUS();
    PhieuNhapBUS phieunhapBus = new PhieuNhapBUS();
    NhanVienDTO nvDto;

    ArrayList<SanPhamDTO> listSP = spBUS.getAll(); // list ben kho 
    // ArrayList<SanPhamDTO> spdto = new ArrayList<>(); 
    ArrayList<ChiTietPhieuNhapDTO> chitietphieu;
    HashMap<Integer, ArrayList<SanPhamDTO>> chitietsanpham = new HashMap<>();
    int maphieunhap;
    int rowPhieuSelect = -1;

    public TaoPhieuNhap(NhanVienDTO nv, String type, Main m ){
        this.nvDto = nv;
        this.m = m;
        maphieunhap = phieunhapBus.phieunhapDAO.getAutoIncrement();
        chitietphieu = new ArrayList<>();
        initComponent();
        loadDataTalbeSanPham(listSP);
    }

    // public void initPadding(){
    // }

    public void initComponent(){
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);
        
        //Phieu Nhap
        tablePhieuNhap = new JTable();
        scrollTablePhieuNhap = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã SP", "Tên sản phẩm", "Đơn giá", "Số lượng"};
        tblModel.setColumnIdentifiers(header); //thiết lập tiêu đề cột, nhận một tham số là một mảng các chuỗi
        tablePhieuNhap.setModel(tblModel);
        scrollTablePhieuNhap.setViewportView(tablePhieuNhap);//thiết lập thành phần hiển thị cho viewport. Thành phần hiển thị là một component có thể cuộn (scrollable)
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); //phương thức để định dạng văn bản, màu sắc, căn chỉnh và các thuộc tính hiển thị khác cho các ô trong bản
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = tablePhieuNhap.getColumnModel();
        for (int i = 0; i < 5; i++) {
            if (i != 2) {
                columnModel.getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        tablePhieuNhap.getColumnModel().getColumn(2).setPreferredWidth(300);
        tablePhieuNhap.setDefaultEditor(Object.class, null);
        tablePhieuNhap.setFocusable(false);
        scrollTablePhieuNhap.setViewportView(tablePhieuNhap);

        tablePhieuNhap.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = tablePhieuNhap.getSelectedRow();
                if (index != -1) {
                    setFormChiTietPhieu(chitietphieu.get(index));
                    rowPhieuSelect = index;
                    actionbtn("update");
                }
            }
        });

        //Table san pham
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
                    resetForm();
                    setInfoSanPham(listSP.get(index));
                    ChiTietPhieuNhapDTO ctp = checkTonTai();
                    if (ctp == null) {
                        actionbtn("add");
                    } else {
                        actionbtn("update");
                        setFormChiTietPhieu(ctp);
                    }
                }
            }
        });

    }

    public void actionbtn(String type) {
        boolean val_1 = type.equals("add");
        boolean val_2 = type.equals("update");
        btnAddSp.setEnabled(val_1);
        btnImport.setEnabled(val_1);
        btnEditSP.setEnabled(val_2);
        btnDelete.setEnabled(val_2);
        content_btn.revalidate();
        content_btn.repaint();
    }

    public void setFormChiTietPhieu(ChiTietPhieuNhapDTO phieu) {
        
    }















    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'itemStateChanged'");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    
}