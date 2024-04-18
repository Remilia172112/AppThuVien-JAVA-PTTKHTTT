package GUI.Panel;

import BUS.KhuVucSachBUS;
import BUS.SanPhamBUS;
import DAO.KhuVucSachDAO;
import DTO.KhuVucSachDTO;
import DTO.SanPhamDTO;
import java.awt.*;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import helper.JTableExporter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import GUI.Main;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
import GUI.Component.itemTaskbar;
import GUI.Dialog.KhuVucSachDialog;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class KhuVucSach extends JPanel implements ActionListener, ItemListener {

    PanelBorderRadius main, functionBar;
    JPanel contentCenter, right;
    JTable tableKhuvuc;
    JScrollPane scrollPane;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    IntegratedSearch search;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    Color BackgroundColor = new Color(240, 247, 250);
    DefaultTableModel tblModel;
    Main m;
    public KhuVucSachBUS kvkBUS = new KhuVucSachBUS();
    public SanPhamBUS spBUS = new SanPhamBUS();

    public ArrayList<KhuVucSachDTO> listKVK = kvkBUS.getAll();
    public ArrayList<SanPhamDTO> listSP = spBUS.getAll();

    private void initComponent() {
        tableKhuvuc = new JTable();
        scrollTableSanPham = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã khu vực", "Tên khu vực", "Ghi chú"};
        tblModel.setColumnIdentifiers(header);
        tableKhuvuc.setModel(tblModel);
        scrollTableSanPham.setViewportView(tableKhuvuc);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = tableKhuvuc.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(0).setPreferredWidth(2);
        columnModel.getColumn(2).setPreferredWidth(300);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        columnModel.getColumn(2).setCellRenderer(centerRenderer);
        tableKhuvuc.setFocusable(false);

        tableKhuvuc.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = tableKhuvuc.getSelectedRow();
                if (index != -1) {
                    ArrayList<SanPhamDTO> listSP = spBUS.getByMakhuvuc(listKVK.get(index).getMakhuvuc());
                    ListCustomersInDePot(listSP);
                }
            }
        });

        this.setBackground(BackgroundColor);
        this.setLayout(new GridLayout(1, 1));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setOpaque(true);

        // pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4 chỉ để thêm contentCenter ở giữa mà contentCenter không bị dính sát vào các thành phần khác
        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(10, 10));
        this.add(contentCenter);

        // functionBar là thanh bên trên chứa các nút chức năng như thêm xóa sửa, và tìm kiếm
        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] action = {"create", "update", "delete", "import", "export"};
        mainFunction = new MainFunction(m.user.getMNQ(), "khuvucsach", action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }
        functionBar.add(mainFunction);

        search = new IntegratedSearch(new String[]{"Tất cả", "Mã khu vực sách", "Tên khu vực sách"});
        search.cbxChoose.addItemListener(this);
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                listKVK = kvkBUS.search(txt, type);
                loadDataTable(listKVK);
            }
        });

        search.btnReset.addActionListener(this);
        functionBar.add(search);
        contentCenter.add(functionBar, BorderLayout.NORTH);
        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
//        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentCenter.add(main, BorderLayout.CENTER);
        main.add(scrollTableSanPham);

        right = new JPanel();
        right.setBackground(BackgroundColor);
        right.setLayout(new FlowLayout(0, 4, 10));
        right.setPreferredSize(new Dimension(400, 800));
        JLabel tit = new JLabel("Danh sách sản phẩm trong khu vực");
        tit.setFont(new java.awt.Font(FlatRobotoFont.FAMILY, 1, 16));
        right.add(tit);
//        right.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm trong kho", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)));
        scrollPane = new JScrollPane(right, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        scrollPane.setBackground(BackgroundColor);
        contentCenter.add(scrollPane, BorderLayout.EAST);
    }

    public KhuVucSach(Main m) {
        this.m = m;
        initComponent();
        tableKhuvuc.setDefaultEditor(Object.class, null);
        loadDataTable(listKVK);
    }

    public void loadDataTable(ArrayList<KhuVucSachDTO> result) {
        tblModel.setRowCount(0);
        for (KhuVucSachDTO kvk : result) {
            tblModel.addRow(new Object[]{
                kvk.getMakhuvuc(), kvk.getTenkhuvuc(), kvk.getGhichu()
            });
        }
    }

    public void importExcel() {
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelJTableImport = null;
        ArrayList<KhuVucSachDTO> listExcel = new ArrayList<KhuVucSachDTO>();
        JFileChooser jf = new JFileChooser();
        int result = jf.showOpenDialog(null);
        jf.setDialogTitle("Open file");
        Workbook workbook = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = jf.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelJTableImport = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);
                for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
                    XSSFRow excelRow = excelSheet.getRow(row);
                    int id = KhuVucSachDAO.getInstance().getAutoIncrement();
                    String tenkvk = excelRow.getCell(0).getStringCellValue();
                    String ghichu = excelRow.getCell(1).getStringCellValue();
                    kvkBUS.add(new KhuVucSachDTO(id, tenkvk, ghichu));
                    tblModel.setRowCount(0);
                    loadDataTable(listKVK);
                }
                JOptionPane.showMessageDialog(this, "Nhập thành công");
            } catch (FileNotFoundException ex) {
                System.out.println("Lỗi đọc file");
            } catch (IOException ex) {
                System.out.println("Lỗi đọc file");
            }
        }

        loadDataTable(listKVK);
    }

    public void ListCustomersInDePot(ArrayList<SanPhamDTO> result) {
        right.removeAll();
        JLabel tit = new JLabel("Danh sách sản phẩm đang có ở khu vực");
        tit.setFont(new java.awt.Font(FlatRobotoFont.FAMILY, 1, 16));
        right.add(tit);
        itemTaskbar listItem[] = new itemTaskbar[result.size()];
        int i = 0;
        for (SanPhamDTO sp : result) {
            if (sp.getSL() != 0) {
                listItem[i] = new itemTaskbar(sp.getHINHANH(), sp.getTEN(), sp.getSL());
                right.add(listItem[i]);
                i++;
            }
        }

        if (i == 0) {
            if (result.isEmpty()) {
                JLabel lblIcon = new JLabel("Không có sản phẩm");
                lblIcon.setPreferredSize(new Dimension(380, 300));
                lblIcon.setIcon(new FlatSVGIcon("./icon/null.svg"));
                lblIcon.setHorizontalTextPosition(SwingConstants.CENTER);
                lblIcon.setVerticalTextPosition(SwingConstants.TOP);
                right.add(lblIcon);
            }
        }
        right.repaint();
        right.validate();
    }

    public int getRowSelected() {
        int index = tableKhuvuc.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khu vực sách");
        }
        return index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btn.get("create")) {
            KhuVucSachDialog kvkDialog = new KhuVucSachDialog(this, owner, "Thêm khu vực sách", true, "create");
        } else if (e.getSource() == mainFunction.btn.get("update")) {
            int index = getRowSelected();
            if (index != -1) {
                KhuVucSachDialog kvkDialog = new KhuVucSachDialog(this, owner, "Chỉnh sửa khu vực sách", true, "update", listKVK.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            int index = getRowSelected();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa khu vực!", "Xóa khu vực sách",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    int check = 0;
                    for (SanPhamDTO i : listSP) {
                        if (listKVK.get(index).getMakhuvuc() == i.getMKVS()) {
                            check++;
                            break;
                        }
                    }
                    if (check == 0) {
                        kvkBUS.delete(listKVK.get(index), index);
                        loadDataTable(listKVK);
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "Không thể xóa khu vực vì vẫn còn sản phẩm trong khu vực.");
                    }
                }
            }
        } else if (e.getSource() == search.btnReset) {
            search.txtSearchForm.setText("");
            listKVK = kvkBUS.getAll();
            loadDataTable(listKVK);
        } else if (e.getSource() == mainFunction.btn.get("import")) {
            importExcel();
        } else if (e.getSource() == mainFunction.btn.get("export")) {
            try {
                JTableExporter.exportJTableToExcel(tableKhuvuc);
            } catch (IOException ex) {
                Logger.getLogger(KhuVucSach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String type = (String) search.cbxChoose.getSelectedItem();
        String txt = search.txtSearchForm.getText();
        listKVK = kvkBUS.search(txt, type);
        loadDataTable(listKVK);
    }
}
