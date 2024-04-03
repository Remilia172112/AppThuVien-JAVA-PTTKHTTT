package DTO;

import java.util.Date;
import java.util.Objects;

public class KhachHangDTO {

    private int MKH;
    private String HOTEN;
    private String SDT;
    private String DIACHI;
    private Date NGAYTHAMGIA;

    public KhachHangDTO() {
    }

    public KhachHangDTO(int MKH, String HOTEN, String SDT, String DIACHI) {
        this.MKH = MKH;
        this.HOTEN = HOTEN;
        this.SDT = SDT;
        this.DIACHI = DIACHI;
    }
    
    public KhachHangDTO(int MKH, String HOTEN, String SDT, String DIACHI,Date NGAYTHAMGIA) {
        this.MKH = MKH;
        this.HOTEN = HOTEN;
        this.SDT = SDT;
        this.DIACHI = DIACHI;
        this.NGAYTHAMGIA = NGAYTHAMGIA;
    }

    public Date getNgaythamgia() {
        return NGAYTHAMGIA;
    }

    public void setNgaythamgia(Date NGAYTHAMGIA) {
        this.NGAYTHAMGIA = NGAYTHAMGIA;
    }

    public int getMaKH() {
        return MKH;
    }

    public void setMaKH(int MKH) {
        this.MKH = MKH;
    }

    public String getHoten() {
        return HOTEN;
    }

    public void setHoten(String HOTEN) {
        this.HOTEN = HOTEN;
    }

    public String getSdt() {
        return SDT;
    }

    public void setSdt(String SDT) {
        this.SDT = SDT;
    }

    public String getDiachi() {
        return DIACHI;
    }

    public void setDiachi(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.MKH);
        hash = 79 * hash + Objects.hashCode(this.HOTEN);
        hash = 79 * hash + Objects.hashCode(this.SDT);
        hash = 79 * hash + Objects.hashCode(this.DIACHI);
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
        final KhachHangDTO other = (KhachHangDTO) obj;
        if (other.getMaKH() != this.getMaKH()) {
            return false;
        }
        if (other.getHoten() != this.getHoten()) {
            return false;
        }
        if (other.getSdt() != this.getSdt()) {
            return false;
        }
        if (other.getDiachi() != this.getDiachi()) {
            return false;
        }
        if (other.getNgaythamgia() != this.getNgaythamgia()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "Ma khach hang = " + MKH + ", Ho ten = " + HOTEN + ", So dien thoai=" + SDT + ", Dia chi=" + DIACHI + '}';
    }

}
