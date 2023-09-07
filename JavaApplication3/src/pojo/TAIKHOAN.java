/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author HP
 */
public class TAIKHOAN {
    String userName;
    String pass;
    int quyen;
    String TrangThai;

    public TAIKHOAN() {
    }

    public TAIKHOAN(String userName, String pass, int quyen, String TrangThai) {
        this.userName = userName;
        this.pass = pass;
        this.quyen = quyen;
        this.TrangThai = TrangThai;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

   

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getQuyen() {
        return quyen;
    }

    public void setQuyen(int quyen) {
        this.quyen = quyen;
    }
    
    
}
