package BUS;

import DAO.NhaXuatBanDAO;
import DTO.NhaXuatBanDTO;
import java.util.ArrayList;

public class NhaXuatBanBUS {

    private final NhaXuatBanDAO NccDAO = new NhaXuatBanDAO();
    private ArrayList<NhaXuatBanDTO> listNcc = new ArrayList<>();

    public NhaXuatBanBUS() {
        this.listNcc = NccDAO.selectAll();
    }

    public ArrayList<NhaXuatBanDTO> getAll() {
        return this.listNcc;
    }

    public NhaXuatBanDTO getByIndex(int index) {
        return this.listNcc.get(index);
    }

    public boolean add(NhaXuatBanDTO ncc) {
        boolean check = NccDAO.insert(ncc) != 0;
        if (check) {
            this.listNcc.add(ncc);
        }
        return check;
    }

    public boolean delete(NhaXuatBanDTO ncc, int index) {
        boolean check = NccDAO.delete(Integer.toString(ncc.getManxb())) != 0;
        if (check) {
            this.listNcc.remove(index);
        }
        return check;
    }

    public boolean update(NhaXuatBanDTO ncc) {
        boolean check = NccDAO.update(ncc) != 0;
        if (check) {
            this.listNcc.set(getIndexByMaNCC(ncc.getManxb()), ncc);
        }
        return check;
    }

    public int getIndexByMaNCC(int mancc) {
        int i = 0;
        int vitri = -1;
        while (i < this.listNcc.size() && vitri == -1) {
            if (listNcc.get(i).getManxb() == mancc) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public ArrayList<NhaXuatBanDTO> search(String txt, String type) {
        ArrayList<NhaXuatBanDTO> result = new ArrayList<>();
        txt = txt.toLowerCase();
        switch (type) {
            case "Tất cả" -> {
                for (NhaXuatBanDTO i : listNcc) {
                    if (Integer.toString(i.getManxb()).contains(txt) || i.getTennxb().contains(txt) || i.getDiachi().contains(txt) || i.getEmail().contains(txt) || i.getSdt().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Mã nhà xuất bản" -> {
                for (NhaXuatBanDTO i : listNcc) {
                    if (Integer.toString(i.getManxb()).contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Tên nhà xuất bản" -> {
                for (NhaXuatBanDTO i : listNcc) {
                    if (i.getTennxb().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Địa chỉ" -> {
                for (NhaXuatBanDTO i : listNcc) {
                    if (i.getDiachi().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Số điện thoại" -> {
                for (NhaXuatBanDTO i : listNcc) {
                    if (i.getSdt().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
            case "Email" -> {
                for (NhaXuatBanDTO i : listNcc) {
                    if (i.getEmail().toLowerCase().contains(txt)) {
                        result.add(i);
                    }
                }
            }
        }
        return result;
    }

    public String[] getArrTenNhaXuatBan() {
        int size = listNcc.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listNcc.get(i).getTennxb();
        }
        return result;
    }

    public String getTenNhaXuatBan(int mancc) {
        return this.listNcc.get(getIndexByMaNCC(mancc)).getTennxb();
    }

    public NhaXuatBanDTO findCT(ArrayList<NhaXuatBanDTO> ncc, String tenncc) {
        NhaXuatBanDTO p = null;
        int i = 0;
        while (i < ncc.size() && p == null) {
            if (ncc.get(i).getTennxb().equals(tenncc)) {
                p = ncc.get(i);
            } else {
                i++;
            }
        }
        return p;
    }
}
