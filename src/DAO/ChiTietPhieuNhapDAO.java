package DAO;

import DTO.ChiTietPhieuNhapDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;


public class ChiTietPhieuNhapDAO implements ChiTietInterface<ChiTietPhieuNhapDTO> {

    public static ChiTietPhieuNhapDAO getInstance() {
        return new ChiTietPhieuNhapDAO();
    }

    @Override
    public int insert(ArrayList<ChiTietPhieuNhapDTO> t) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = (Connection) JDBCUtil.getConnection();
                String sql = "INSERT INTO `CTPHIEUNHAP` (`MPN`, `MSP`, `SL`, `TIENNHAP`, `HINHTHUC`) VALUES (?,?,?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getMP());
                pst.setInt(2, t.get(i).getMSP());
                pst.setInt(3, t.get(i).getSL());
                pst.setInt(4, t.get(i).getTIEN());
                pst.setInt(5, t.get(i).getHINHTHUC());
                result = pst.executeUpdate();
                JDBCUtil.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            SanPhamDAO.getInstance().updateSoLuongTon(t.get(i).getMSP(), t.get(i).getSL());
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM CTPHIEUNHAP WHERE MPN = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(ArrayList<ChiTietPhieuNhapDTO> t, String pk) {
        int result = this.delete(pk);
        if (result != 0) {
            result = this.insert(t);
        }
        return result;
    }

    @Override
    public ArrayList<ChiTietPhieuNhapDTO> selectAll(String t) {
        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM CTPHIEUNHAP WHERE MPN = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maphieu = rs.getInt("MPN");
                int masp = rs.getInt("MSP");
                int soluong = rs.getInt("SL");
                int tiennhap = rs.getInt("TIENNHAP");
                int hinhthucnhap = rs.getInt("HINHTHUC");
                ChiTietPhieuNhapDTO ctphieu = new ChiTietPhieuNhapDTO(maphieu, masp,  soluong, tiennhap, hinhthucnhap);
                result.add(ctphieu);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

}
