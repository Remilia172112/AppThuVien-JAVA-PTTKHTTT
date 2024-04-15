package DTO;

import java.util.Objects;

public class SanPhamDTO {

    private int MSP;
    private String TEN;
    private String HINHANH;
    private String DANHMUC;
    private int NAMXB;
    private int MNXB;
    private String TENTG;
    private int MKVK;
    private int TIENX;
    private int TIENN;
    private int SL;
    private String ISBN;

    public SanPhamDTO() {

    }
    
    public SanPhamDTO(int mSP, String tEN, String hINHANH, String dANHMUC, int nAMXB, int mNXB, String tENTG, int mKVK,
            int tIENX, int tIENN, int sL, String iSBN) {
        MSP = mSP;
        TEN = tEN;
        HINHANH = hINHANH;
        DANHMUC = dANHMUC;
        NAMXB = nAMXB;
        MNXB = mNXB;
        TENTG = tENTG;
        MKVK = mKVK;
        TIENX = tIENX;
        TIENN = tIENN;
        SL = sL;
        ISBN = iSBN;
    }

    
    public int getMSP() {
        return MSP;
    }

    public void setMSP(int mSP) {
        MSP = mSP;
    }

    public String getTEN() {
        return TEN;
    }

    public void setTEN(String tEN) {
        TEN = tEN;
    }

    public String getHINHANH() {
        return HINHANH;
    }

    public void setHINHANH(String hINHANH) {
        HINHANH = hINHANH;
    }

    public String getDANHMUC() {
        return DANHMUC;
    }

    public void setDANHMUC(String dANHMUC) {
        DANHMUC = dANHMUC;
    }

    public int getNAMXB() {
        return NAMXB;
    }

    public void setNAMXB(int nAMXB) {
        NAMXB = nAMXB;
    }

    public int getMNXB() {
        return MNXB;
    }

    public void setMNXB(int mNXB) {
        MNXB = mNXB;
    }

    public String getTENTG() {
        return TENTG;
    }

    public void setTENTG(String tENTG) {
        TENTG = tENTG;
    }

    public int getMKVK() {
        return MKVK;
    }

    public void setMKVK(int mKVK) {
        MKVK = mKVK;
    }

    public int getTIENX() {
        return TIENX;
    }

    public void setTIENX(int tIENX) {
        TIENX = tIENX;
    }

    public int getTIENN() {
        return TIENN;
    }

    public void setTIENN(int tienn) {
        TIENN = tienn;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int sL) {
        SL = sL;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.MSP;
        hash = 53 * hash + Objects.hashCode(this.TEN);
        hash = 53 * hash + Objects.hashCode(this.HINHANH);
        hash = 53 * hash + Objects.hashCode(this.DANHMUC);
        hash = 53 * hash + this.NAMXB;
        hash = 53 * hash + this.MNXB;
        hash = 53 * hash + Objects.hashCode(this.TENTG);
        hash = 53 * hash + this.MKVK;
        hash = 53 * hash + this.TIENX;
        hash = 53 * hash + this.SL;
        hash = 53 * hash + Objects.hashCode(this.ISBN);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SanPhamDTO other = (SanPhamDTO) obj;
        if (this.MSP != other.MSP) {
            return false;
        }
        if (this.NAMXB != other.NAMXB) {
            return false;
        }
        if (this.MNXB != other.MNXB) {
            return false;
        }
        if (this.MKVK != other.MKVK) {
            return false;
        }
        if (this.SL != other.SL) {
            return false;
        }
        if (this.TIENX != other.TIENX) {
            return false;
        }
        if (this.TIENN != other.TIENN) {
            return false;
        }
        if (this.ISBN != other.ISBN) {
            return false;
        }
        if (!Objects.equals(this.TEN, other.TEN)) {
            return false;
        }
        if (!Objects.equals(this.HINHANH, other.HINHANH)) {
            return false;
        }
        if (!Objects.equals(this.DANHMUC, other.DANHMUC)) {
            return false;
        }
        return Objects.equals(this.TENTG, other.TENTG);
    }

    @Override
    
    public String toString() {
        return "SanPhamDTO{" + "Ma san pham = " + MSP + ", Ten san pham = " + TEN + ", Hinh anh = " + HINHANH + ", Danh muc = " + DANHMUC + ", Nam xuat ban = " + NAMXB + ", Ma nha xuat ban = " + MNXB  + ", Ten tac gia = " + TENTG + ", Ma khu vuc kho = " + MKVK + ", Tien xuat = " + TIENX + ", Tien nhap = " + TIENN + ", So luong = " + SL + ", ISBN=" + ISBN + ", MKVK=" + MKVK + ", TIENX=" + TIENX + ", ISBN=" + ISBN + '}';
    }
}
