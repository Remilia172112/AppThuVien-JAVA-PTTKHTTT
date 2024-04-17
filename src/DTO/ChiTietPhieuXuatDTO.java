// package DTO;



// public class ChiTietPhieuXuatDTO extends ChiTietPhieuDTO{
//     private int HINHTHUC;

//     public ChiTietPhieuXuatDTO(int TIENNHAP, int HINHTHUC) {
//         this.HINHTHUC = HINHTHUC;
//     }

//     public ChiTietPhieuXuatDTO(int MP, int MSP, int SL, int TIENXUAT, int HINHTHUC) {
//         super(MP, MSP, SL, TIENXUAT);
//         this.HINHTHUC = HINHTHUC;
//     }

//     public int getHINHTHUC() {
//         return HINHTHUC;
//     }

//     public void setHINHTHUC(int HINHTHUC) {
//         this.HINHTHUC = HINHTHUC;
//     }

//     @Override
//     public int hashCode() {
//         int hash = 7;
//         hash = 59 * hash + this.HINHTHUC;
//         return hash;
//     }

//     @Override
//     public boolean equals(Object obj) {
//         if (this == obj) {
//             return true;
//         }
//         if (obj == null) {
//             return false;
//         }
//         if (getClass() != obj.getClass()) {
//             return false;
//         }
//         final ChiTietPhieuXuatDTO other = (ChiTietPhieuXuatDTO) obj;
    
//         return this.HINHTHUC == other.HINHTHUC;
//     }
// } 
