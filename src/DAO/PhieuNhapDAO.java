package DAO;

import DTO.ChiTietPhieuNhapDTO;
import DTO.SanPhamDTO;
import DTO.PhieuNhapDTO;
import BUS.SanPhamBUS;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhieuNhapDAO implements DAOinterface<PhieuNhapDTO> {

    public static PhieuNhapDAO getInstance() {
        return new PhieuNhapDAO();
    }
    
    
    @Override
    public int insert(PhieuNhapDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `PHIEUNHAP` (`MNV`, `MNCC`, `TIEN`, `TG`, `TT`) VALUES (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMNV());
            pst.setInt(2, t.getMNCC());
            pst.setDouble(3, t.getTIEN());
            pst.setTimestamp(4, t.getTG());
            pst.setInt(5, t.getTT());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(PhieuNhapDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `PHIEUNHAP` SET `TG`=?,`MNCC`=?,`TIEN`=?,`TT`=? WHERE `MPN`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setTimestamp(1, t.getTG());
            pst.setInt(2, t.getMNCC());
            pst.setLong(3, t.getTIEN());
            pst.setInt(4, t.getTT());
            pst.setInt(5, t.getMP());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE PHIEUNHAP SET TT = 0 WHERE MPN = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<PhieuNhapDTO> selectAll() {
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM PHIEUNHAP ORDER BY MPN DESC";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MPN = rs.getInt("MPN");
                Timestamp TG = rs.getTimestamp("TG");
                int MNCC = rs.getInt("MNCC");
                int MNV = rs.getInt("MNV");
                long TIENN = rs.getLong("TIEN");
                int TT = rs.getInt("TT");
                PhieuNhapDTO phieunhap = new PhieuNhapDTO(MNCC, MPN, MNV, TG, TIENN, TT);
                result.add(phieunhap);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public PhieuNhapDTO selectById(String t) {
        PhieuNhapDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM PHIEUNHAP WHERE MPN=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MPN = rs.getInt("MPN");
                Timestamp TG = rs.getTimestamp("TG");
                int MNCC = rs.getInt("MNCC");
                int MNV = rs.getInt("MNV");
                long TIENN = rs.getLong("TIEN");
                int TT = rs.getInt("TT");
                result = new PhieuNhapDTO(MNCC, MPN, MNV, TG, TIENN, TT);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    public ArrayList<PhieuNhapDTO> statistical(long min, long max) {
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM PHIEUNHAP WHERE TIEN BETWEEN ? AND ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setLong(1, min);
            pst.setLong(2,max);

            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int MPN = rs.getInt("MPN");
                Timestamp TG = rs.getTimestamp("TG");
                int MNCC = rs.getInt("MNCC");
                int MNV = rs.getInt("MNV");
                long TIENN = rs.getLong("TIEN");
                int TT = rs.getInt("TT");
                PhieuNhapDTO phieunhap = new PhieuNhapDTO(MNCC, MPN, MNV, TG, TIENN, TT);
                result.add(phieunhap);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }
    
    public boolean checkSLPn(int maphieu) {
        SanPhamBUS spBus = new SanPhamBUS();
        ArrayList<SanPhamDTO> SP = new ArrayList<SanPhamDTO>();
        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM CTPHIEUNHAP WHERE MPN=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maphieu);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int masp = rs.getInt("MSP");
                int soluong = rs.getInt("SL");
                int tiennhap = rs.getInt("TIENNHAP");
                int hinhthucnhap = rs.getInt("HINHTHUC");
                ChiTietPhieuNhapDTO ct = new ChiTietPhieuNhapDTO(maphieu, masp,  soluong, tiennhap, hinhthucnhap);
                result.add(ct);
                SP.add(spBus.spDAO.selectById(ct.getMSP() + ""));
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        for (int i = 0; i < SP.size(); i++) {
            if(result.get(i).getSL() > SP.get(i).getSL()){
                return false;
            }
        }
        return true;
    }
    
    public int cancelPhieuNhap(int maphieu){
        int result = 0;
        ArrayList<ChiTietPhieuNhapDTO> arrCt = ChiTietPhieuNhapDAO.getInstance().selectAll(Integer.toString(maphieu));
        for (ChiTietPhieuNhapDTO chiTietPhieuNhapDTO : arrCt) {
            SanPhamDAO.getInstance().updateSoLuongTon(chiTietPhieuNhapDTO.getMSP(), -(chiTietPhieuNhapDTO.getSL()));
        }
        ChiTietPhieuNhapDAO.getInstance().delete(Integer.toString(maphieu));
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM PHIEUNHAP WHERE MPN = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maphieu);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlycuahangsach' AND TABLE_NAME   = 'PHIEUNHAP'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs2.next()) {
                    result = rs2.getInt("AUTO_INCREMENT");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
