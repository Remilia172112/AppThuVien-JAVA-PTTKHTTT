package BUS;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import java.util.ArrayList;

public class SanPhamBUS {

    public final SanPhamDAO spDAO = new SanPhamDAO();
    private ArrayList<SanPhamDTO> listSP = new ArrayList<>();

    public SanPhamBUS() {
        listSP = spDAO.selectAll();
    }

    public ArrayList<SanPhamDTO> getAll() {
        
        return this.listSP;
    }

    public SanPhamDTO getByIndex(int index) {
        return this.listSP.get(index);
    }

    public SanPhamDTO getByMaSP(int masp) {
        int vitri = -1;
        int i = 0;
        while (i <= this.listSP.size() && vitri == -1) {
            if (this.listSP.get(i).getMSP() == masp) {
                vitri = i;
            } else {
                i++;
            }
        }
        return this.listSP.get(vitri);
    }

    public int getIndexByMaSP(int masanpham) {
        int i = 0;
        int vitri = -1;
        while (i < this.listSP.size() && vitri == -1) {
            if (listSP.get(i).getMSP() == masanpham) {
                vitri = i;
            } else {
                i++;
            }
        }
        return vitri;
    }

    public Boolean add(SanPhamDTO lh) {
        boolean check = spDAO.insert(lh) != 0;
        if (check) {
            this.listSP.add(lh);
        }
        return check;
    }

    public Boolean delete(SanPhamDTO lh) {
        boolean check = spDAO.delete(Integer.toString(lh.getMSP())) != 0;
        if (check) {
            this.listSP.remove(lh);
        }
        return check;
    }

    public Boolean update(SanPhamDTO lh) {
        boolean check = spDAO.update(lh) != 0;
        if (check) {
            this.listSP.set(getIndexByMaSP(lh.getMSP()), lh);
        }
        return check;
    }

    public ArrayList<SanPhamDTO> getByMakhuvuc(int makv) {
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        for (SanPhamDTO i : this.listSP) {
            if (i.getMKVS() == makv) {
                result.add(i);
            }
        }
        return result;
    }

    public ArrayList<SanPhamDTO> search(String text, String type) {
        text = text.toLowerCase();
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        switch (type) {
            case "Tất cả" -> {
                for (SanPhamDTO i : this.listSP) {
                    if (Integer.toString(i.getMSP()).toLowerCase().contains(text) || i.getTEN().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Mã sản phẩm" -> {
                for (SanPhamDTO i : this.listSP) {
                    if (Integer.toString(i.getMSP()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case "Tên sản phẩm" -> {
                for (SanPhamDTO i : this.listSP) {
                    if (i.getTEN().toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
        }
        
        return result;
    }

    public SanPhamDTO getSp(int danhmuc) {
        return spDAO.selectByDanhMuc(danhmuc + "");
    }

    public int getQuantity() {
        int n = 0;
        for(SanPhamDTO i : this.listSP) {
            if (i.getSL() != 0) {
                n += i.getSL();
            }
        }
        return n;
    }

    public boolean checkISBN(String ISBN) {
        for(SanPhamDTO i : this.listSP) {
            if(i.getISBN().equals(ISBN)) return false;
        }
        System.out.println(ISBN);
        return true;
    }

    public SanPhamDTO getSPbyISBN(String ISBN) {
        for(SanPhamDTO i : this.listSP) {
            if(i.getISBN().equals(ISBN)) return i;
        }
        return null;
    }
}
