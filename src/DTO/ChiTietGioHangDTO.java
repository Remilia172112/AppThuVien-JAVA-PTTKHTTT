package DTO;

import java.util.Objects;

public class ChiTietGioHangDTO {
    int MKH;
    int MSP;
    int SL;
    Long TIENGIO;

    public ChiTietGioHangDTO (int MKH, int MSP, int SL, Long TIENGIO){
        this.MKH = MKH;
        this.MSP = MSP;
        this.SL = SL;
        this.TIENGIO = TIENGIO;
    }

    public ChiTietGioHangDTO(){}

    public int getMKH() {
        return MKH;
    }

    public void setMKH(int mKH) {
        MKH = mKH;
    }

    public int getMSP() {
        return MSP;
    }

    public void setMSP(int mSP) {
        MSP = mSP;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int sL) {
        SL = sL;
    }

    public Long getTIENGIO() {
        return TIENGIO;
    }

    public void setTIENGIO(Long tIEN) {
        TIENGIO = tIEN;
    };

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
        final ChiTietGioHangDTO other = (ChiTietGioHangDTO) obj;
        
        if (this.MKH != other.MKH) {
            return false;
        }
        if (this.TIENGIO != other.TIENGIO) {
            return false;
        }
    
        return Objects.equals(this.SL, other.SL);
    }

}
