/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.DIEM;
import pojo.TAIKHOAN;

/**
 *
 * @author HP
 */
public class QL_TaiKhoanDAO {

    public static ArrayList<TAIKHOAN> layDanhSachTaiKhoan() {
        ArrayList<TAIKHOAN> dsTK = new ArrayList<TAIKHOAN>();
        dsTK.clear();
        try {
            String sql = "select*from TAIKHOAN";
            SQLSeverDataProvider provider = new SQLSeverDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                TAIKHOAN tk = new TAIKHOAN();
                tk.setUserName(rs.getString("User"));
                tk.setPass(rs.getString("Password"));
                tk.setQuyen(rs.getInt("Quyen"));
                tk.setTrangThai(rs.getString("TrangThai"));
                dsTK.add(tk);
            }
        } catch (Exception e) {
        }
        return dsTK;
    }

    public static String layTenTK() {

        String ten = "";
        try {
            String sql = "select [User] from TAIKHOAN where [TrangThai]='On'";
            SQLSeverDataProvider provider = new SQLSeverDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                ten = rs.getString("User");
            }
        } catch (Exception e) {
        }
        return ten;
    }

    public static int layCapGame() {

        int capchoi = 0;
        try {
            String sql = "select [MaCD] from CheDo where [TrangThai]='On'";
            SQLSeverDataProvider provider = new SQLSeverDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                capchoi = rs.getInt("MACD");
            }
        } catch (Exception e) {
        }
        return capchoi;
    }

    public static int layQuyen() {

        int capchoi = 0;
        try {
            String sql = "select [Quyen] from TaiKhoan where [TrangThai]='On'";
            SQLSeverDataProvider provider = new SQLSeverDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                capchoi = rs.getInt("Quyen");
            }
        } catch (Exception e) {
        }
        return capchoi;
    }

    public static int layTimeGame() {

        int time = 0;
        try {
            String sql = "select [ThoiGian] from CheDo where [MaCD]=" + layCapGame() + " ";
            SQLSeverDataProvider provider = new SQLSeverDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                time = rs.getInt("ThoiGian");
            }
        } catch (Exception e) {
        }
        return time;
    }

    public static ArrayList<TAIKHOAN> timKiemTaiKhoanTheoTen(String username) {
        TAIKHOAN tk = null;
        ArrayList<TAIKHOAN> dsTK = new ArrayList<TAIKHOAN>();
        try {
            String sql = "SELECT * FROM TaiKhoan WHERE [User] like '%" + username + "%'";
            SQLSeverDataProvider provider = new SQLSeverDataProvider();

            provider.open();

            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                tk = new TAIKHOAN();
                tk.setUserName(rs.getString("User"));
                tk.setPass(rs.getString("Password"));
                tk.setQuyen(rs.getInt("Quyen"));
                dsTK.add(tk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QL_TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsTK;
    }

    public static boolean themTaiKhoan(TAIKHOAN tk) {
        boolean kq = false;
        String sql = String.format("INSERT INTO [dbo].[TAIKHOAN] values('" + tk.getUserName() + "','" + tk.getPass() + "','" + tk.getQuyen() + "','Off');");
        SQLSeverDataProvider provider = new SQLSeverDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
            layDanhSachTaiKhoan();

        }
        provider.close();

        return kq;
    }

    public static boolean xoaTaiKhoan(String username) {
        boolean kq = false;
        String sql = String.format("DELETE FROM TaiKhoan WHERE [User] = '" + username + "' ");
        SQLSeverDataProvider provider = new SQLSeverDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
            layDanhSachTaiKhoan();

        }
        provider.close();
        return kq;
    }

    public static boolean capNhatTaiKhoan(TAIKHOAN tk) {
        boolean kq = false;
        String sql = String.format("UPDATE TaiKhoan SET Password = '" + tk.getPass() + "' WHERE [User] ='" + tk.getUserName() + "'");
        SQLSeverDataProvider provider = new SQLSeverDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
            layDanhSachTaiKhoan();

        }
        provider.close();
        return kq;
    }

    public static boolean capNhatTrangThai(String username, String tt) {
        boolean kq = false;
        String sql = String.format("UPDATE TaiKhoan SET [TrangThai] = '" + tt + "' Where [User] = '" + username + "'");
        SQLSeverDataProvider provider = new SQLSeverDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;

        }
        provider.close();
        return kq;
    }

    public static TAIKHOAN dangNhap(String username, String tt) {
        TAIKHOAN tk = null;
        try {
            String sql = String.format("SELECT * FROM TaiKhoan WHERE [User] = '" + username + "' and [password] = '%s'", tt);
            SQLSeverDataProvider provider = new SQLSeverDataProvider();
            provider.open();

            ResultSet resultSet = provider.executeQuery(sql);
            if (resultSet.next()) {
                tk = new TAIKHOAN();
                tk.setUserName(resultSet.getString("[User]"));
                tk.setPass(resultSet.getString("Password"));

            }
            provider.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tk;
    }

    public static TAIKHOAN layTaiKhoan(String username) {
        TAIKHOAN tk = null;
        try {
            String sql = String.format("SELECT * FROM TaiKhoan WHERE [User] = '" + username + "'");
            SQLSeverDataProvider provider = new SQLSeverDataProvider();
            provider.open();

            ResultSet resultSet = provider.executeQuery(sql);
            if (resultSet.next()) {
                tk = new TAIKHOAN();
                tk.setUserName(resultSet.getString("[User]"));
                tk.setPass(resultSet.getString("Password"));

            }
            provider.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tk;
    }

    public static boolean capNhatTrangThaiOnGame(int cd, String tt) {
        boolean kq = false;
        String sql = String.format("UPDATE CheDo SET [TrangThai] = '" + tt + "' Where [MaCD] = " + cd + "");
        SQLSeverDataProvider provider = new SQLSeverDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        provider.close();
        return kq;
    }

    public static boolean ghiKetQua(int diem, int thgian) {
        boolean kq = false;
        String sql = String.format("INSERT INTO [dbo].[DIEM] ([Player],[MaCD],[KETQUA],[THOIGIAN])values('" + layTenTK() + "','" + layCapGame() + "','" + diem + "','" + thgian + "');");
        SQLSeverDataProvider provider = new SQLSeverDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        if (n == 1) {
            kq = true;

        }
        provider.close();

        return kq;
    }

}
