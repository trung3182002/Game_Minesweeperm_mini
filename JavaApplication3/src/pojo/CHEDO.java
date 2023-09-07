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
public class CHEDO {
    int maCD;
    String tenCD;
    String TrangThai;
    int ThoiGian;

    public CHEDO() {
    }

    public CHEDO(int maCD, String tenCD, String TrangThai, int ThoiGian) {
        this.maCD = maCD;
        this.tenCD = tenCD;
        this.TrangThai = TrangThai;
        this.ThoiGian = ThoiGian;
    }

    public int getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(int ThoiGian) {
        this.ThoiGian = ThoiGian;
    }

    
    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    

    public int getMaCD() {
        return maCD;
    }

    public void setMaCD(int maCD) {
        this.maCD = maCD;
    }

    public String getTenCD() {
        return tenCD;
    }

    public void setTenCD(String tenCD) {
        this.tenCD = tenCD;
    }

}
