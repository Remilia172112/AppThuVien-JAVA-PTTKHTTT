package DTO;

import java.sql.Timestamp;

public class PhieuNhapDTO extends PhieuDTO{
    private int MNCC;

    public PhieuNhapDTO(int MNCC) {
        this.MNCC = MNCC;
    }

    public PhieuNhapDTO(int MNCC, int MP, int MNV, Timestamp TG, long TIEN, int TT) {
        super(MP, MNV, TG, TIEN, TT);
        this.MNCC = MNCC;
    }

    public int getMNCC() {
        return MNCC;
    }

    public void setMNCC(int MNCC) {
        this.MNCC = MNCC;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.MNCC;
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
        final PhieuNhapDTO other = (PhieuNhapDTO) obj;
        return this.MNCC == other.MNCC;
    }

    @Override
    public String toString() {
        return "PhieuNhapDTO{" + "MNCC=" + MNCC +'}'+super.toString();
    }
}
