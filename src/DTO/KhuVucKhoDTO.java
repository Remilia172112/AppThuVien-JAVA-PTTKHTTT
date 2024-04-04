package DTO;

import java.util.Objects;

public class KhuVucKhoDTO {

    private int MKVK;
    private String TEN;
    private String GHICHU;

    public KhuVucKhoDTO() {
    }

    public KhuVucKhoDTO(int MKVK, String TEN, String GHICHU) {
        this.MKVK = MKVK;
        this.TEN = TEN;
        this.GHICHU = GHICHU;
    }

    public int getMakhuvuc() {
        return MKVK;
    }

    public void setMakhuvuc(int MKVK) {
        this.MKVK = MKVK;
    }

    public String getTenkhuvuc() {
        return TEN;
    }

    public void setTenkhuvuc(String TEN) {
        this.TEN = TEN;
    }

    public String getGhichu() {
        return GHICHU;
    }

    public void setGhichu(String GHICHU) {
        this.GHICHU = GHICHU;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.MKVK;
        hash = 97 * hash + Objects.hashCode(this.TEN);
        hash = 97 * hash + Objects.hashCode(this.GHICHU);
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
        final KhuVucKhoDTO other = (KhuVucKhoDTO) obj;
        if (this.getMakhuvuc() != other.getMakhuvuc()) {
            return false;
        }
        if (!Objects.equals(this.getTenkhuvuc(), other.getTenkhuvuc())) {
            return false;
        }
        return Objects.equals(this.getGhichu(), other.getGhichu());
    }

    @Override
    public String toString() {
        return "KhuVucKho{" + "Ma khu vuc kho = " + MKVK + ", Ten khu vuc kho = " + TEN + ", Ghi chu = " + GHICHU + '}';
    }
    
}
