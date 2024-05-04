package BUS;

import DAO.ChiTietPhieuXuatDAO;
// import DAO.SanPhamDAO;
import DAO.PhieuXuatDAO;
import DTO.ChiTietPhieuDTO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import DTO.PhieuXuatDTO;
import DTO.SanPhamDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class PhieuXuatBUS {

    private final PhieuXuatDAO phieuXuatDAO = PhieuXuatDAO.getInstance();

    private final ChiTietPhieuXuatDAO chiTietPhieuXuatDAO = ChiTietPhieuXuatDAO.getInstance();
    // private final SanPhamDAO chiTietSanPhamDAO = SanPhamDAO.getInstance();
    private final ArrayList<PhieuXuatDTO> listPhieuXuat;

    NhanVienBUS nvBUS = new NhanVienBUS();
    KhachHangBUS khBUS = new KhachHangBUS();

    public PhieuXuatBUS() {
        this.listPhieuXuat = phieuXuatDAO.selectAll();
    }

    public ArrayList<PhieuXuatDTO> getAll() {
        return this.listPhieuXuat;
    }

    public PhieuXuatDTO getSelect(int index) {
        return listPhieuXuat.get(index);
    }

    public void cancel(int px) {
        phieuXuatDAO.cancel(px);
    }

    public void remove(int px) {
        listPhieuXuat.remove(px);
    }

    public void insert(PhieuXuatDTO px, ArrayList<ChiTietPhieuDTO> ct) {
        phieuXuatDAO.insert(px); //ghi phieu xuat vao sql
        chiTietPhieuXuatDAO.insert(ct); //goi updatesoluongTon cua sanphamDAO để chỉnh số lượng trong kho -> ghi váo sql
    }

    public ArrayList<ChiTietPhieuDTO> selectCTP(int maphieu) {
        return chiTietPhieuXuatDAO.selectAll(Integer.toString(maphieu));
    }

    public int getMPMAX() {
        ArrayList<PhieuXuatDTO> listPhieuXuat = phieuXuatDAO.selectAll();
        int s = 1;
        for (PhieuXuatDTO i : listPhieuXuat) {
            if(i.getMP() > s) s = i.getMP();
        }
        return s;
    }
    //moi them, nó tương tự insert nhưng có check
    // public boolean add(PhieuXuatDTO phieu, ArrayList<ChiTietPhieuDTO> ctPhieu, HashMap<Integer, ArrayList<SanPhamDTO>> chitietsanpham) {
    //     boolean check = phieuXuatDAO.insert(phieu) != 0;
    //     if (check) {
    //         check = chiTietPhieuXuatDAO.insert(ctPhieu) != 0;
    //     }
    //     return check;
    // }

    public ChiTietPhieuDTO findCT(ArrayList<ChiTietPhieuDTO> ctphieu, int masp) {
        ChiTietPhieuDTO p = null;
        int i = 0;
        while (i < ctphieu.size() && p == null) {
            if (ctphieu.get(i).getMSP() == masp) {
                p = ctphieu.get(i);
            } else {
                i++;
            }
        }
        return p;
    }

    public ArrayList<PhieuXuatDTO> fillerPhieuXuat(int type, String input, int makh, int manv, Date time_s, Date time_e, String price_minnn, String price_maxxx) {
        Long price_min = !price_minnn.equals("") ? Long.valueOf(price_minnn) : 0L;
        Long price_max = !price_maxxx.equals("") ? Long.valueOf(price_maxxx) : Long.MAX_VALUE;
        Timestamp time_start = new Timestamp(time_s.getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time_e.getTime());

        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Timestamp time_end = new Timestamp(calendar.getTimeInMillis());

        ArrayList<PhieuXuatDTO> result = new ArrayList<>();
        for (PhieuXuatDTO phieuXuat : getAll()) {
            boolean match = false;
            switch (type) {
                case 0 -> {
                    if (Integer.toString(phieuXuat.getMP()).contains(input)
                            || khBUS.getTenKhachHang(phieuXuat.getMKH()).toLowerCase().contains(input)
                            || nvBUS.getNameById(phieuXuat.getMNV()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 1 -> {
                    if (Integer.toString(phieuXuat.getMP()).contains(input)) {
                        match = true;
                    }
                }
                case 2 -> {
                    if (khBUS.getTenKhachHang(phieuXuat.getMKH()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 3 -> {
                    if (nvBUS.getNameById(phieuXuat.getMNV()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
            }

            if (match
                    && (manv == 0 || phieuXuat.getMNV() == manv) && (makh == 0 || phieuXuat.getMKH() == makh)
                    && (phieuXuat.getTG().compareTo(time_start) >= 0)
                    && (phieuXuat.getTG().compareTo(time_end) <= 0)
                    && phieuXuat.getTIEN() >= price_min
                    && phieuXuat.getTIEN() <= price_max) {
                result.add(phieuXuat);
            }
        }

        return result;
    }

}
