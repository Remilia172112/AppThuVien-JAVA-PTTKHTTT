package BUS;

import DAO.MaKhuyenMaiDAO;
import DTO.MaKhuyenMaiDTO;
import DTO.ChiTietMaKhuyenMaiDTO;
import java.util.ArrayList;

public class MaKhuyenMaiBUS {

    private final MaKhuyenMaiDAO mkmDAO = new MaKhuyenMaiDAO();
    public ArrayList<MaKhuyenMaiDTO> listMKM = new ArrayList<>();
    public ArrayList<ChiTietMaKhuyenMaiDTO> listctMKM = new ArrayList<>();

    public MaKhuyenMaiBUS() {
        listMKM = mkmDAO.selectAll();
    }

    public ArrayList<MaKhuyenMaiDTO> getAll() {
        return this.listMKM;
    }

    public ArrayList<ChiTietMaKhuyenMaiDTO> getAllct() {
        return this.listctMKM;
    } 

    public MaKhuyenMaiDTO getByIndex(int index) {
        return this.listMKM.get(index);
    }

    public int getIndexByMKM(String makhachhang) {
        int i = 0;
        int vitri = -1;
        while (i < this.listMKM.size() && vitri == -1) {
            if (listMKM.get(i).getMKM() == makhachhang) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public Boolean add(MaKhuyenMaiDTO kh) {
        boolean check = mkmDAO.insert(kh) != 0;
        if (check) {
            this.listMKM.add(kh);
        }
        return check;
    }

    public Boolean delete(MaKhuyenMaiDTO kh) {
        boolean check = mkmDAO.delete(kh.getMKM()) != 0;
        if (check) {
            this.listMKM.remove(getIndexByMKM(kh.getMKM()));
        }
        return check;
    }

    public Boolean update(MaKhuyenMaiDTO kh) {
        boolean check = mkmDAO.update(kh) != 0;
        if (check) {
            this.listMKM.set(getIndexByMKM(kh.getMKM()), kh);
        }
        return check;
    }

    public ArrayList<MaKhuyenMaiDTO> search(String text) {
        ArrayList<MaKhuyenMaiDTO> result = new ArrayList<>();
        text = text.toLowerCase();
        for (MaKhuyenMaiDTO i : this.listMKM) {
            if (i.getMKM().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }

    public MaKhuyenMaiDTO selectKh(int makh) {
        return mkmDAO.selectById(makh + "");
    }

}
