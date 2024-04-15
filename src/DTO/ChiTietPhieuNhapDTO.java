package DTO;


// import DTO.ChiTietPhieuDTO;

public class ChiTietPhieuNhapDTO extends ChiTietPhieuDTO{
    private int TIENNHAP;
    private int HINHTHUC;

    public ChiTietPhieuNhapDTO(int TIENNHAP, int HINHTHUC) {
        this.TIENNHAP = TIENNHAP;
        this.HINHTHUC = HINHTHUC;
    }

    public ChiTietPhieuNhapDTO(int MP, int MSP, int SL, int TIENNHAP, int HINHTHUC) {
        super(MP, MSP, SL);
        this.TIENNHAP = TIENNHAP;
        this.HINHTHUC = HINHTHUC;
    }

    public int getTIENNHAP() {
        return HINHTHUC;
    }

    public void setTIENHHAP(int TIENNHAP) {
        this.TIENNHAP = TIENNHAP;
    }

    public int getHINHTHUC() {
        return HINHTHUC;
    }

    public void setHINHTHUC(int HINHTHUC) {
        this.HINHTHUC = HINHTHUC;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.HINHTHUC;
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
        final ChiTietPhieuNhapDTO other = (ChiTietPhieuNhapDTO) obj;
        if (this.TIENNHAP != other.TIENNHAP) {
            return false;
        }
        return this.HINHTHUC == other.HINHTHUC;
    }
} 
