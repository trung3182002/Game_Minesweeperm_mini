/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.sql.Time;

/**
 *
 * @author HP
 */
public class DIEM {

    public DIEM(String player, int maCD, int ketQua, Time thoiGian) {
        this.player = player;
        this.maCD = maCD;
        this.ketQua = ketQua;
        this.thoiGian = thoiGian;
    }
    String player;
    int maCD;
    int ketQua;
    Time thoiGian;

    public DIEM() {
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getMaCD() {
        return maCD;
    }

    public void setMaCD(int maCD) {
        this.maCD = maCD;
    }

    public int getKetQua() {
        return ketQua;
    }

    public void setKetQua(int ketQua) {
        this.ketQua = ketQua;
    }

    public Time getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Time thoiGian) {
        this.thoiGian = thoiGian;
    }
    
    
    
}
