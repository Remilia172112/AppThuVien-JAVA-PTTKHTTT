/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

public class ChiTietPhieuDTO {
    private int MP;
    private int MSP;
    private int SL;
    

    public ChiTietPhieuDTO() {
    }

    public ChiTietPhieuDTO(int MP, int MSP, int SL) {
        this.MP = MP;
        this.MSP = MSP;
        this.SL = SL;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public int getMSP() {
        return MSP;
    }

    public void setMSP(int MSP) {
        this.MSP = MSP;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int SL) {
        this.SL = SL;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.MP;
        hash = 19 * hash + this.MSP;
        hash = 19 * hash + this.SL;
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
        final ChiTietPhieuDTO other = (ChiTietPhieuDTO) obj;
        if (this.MP != other.MP) {
            return false;
        }
        if (this.MSP != other.MSP) {
            return false;
        }
        if (this.SL != other.SL) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ChiTietPhieuDTO{" + "MP=" + MP + ", MSP=" + MSP + ", SL=" + SL + '}';
    }

    
}
