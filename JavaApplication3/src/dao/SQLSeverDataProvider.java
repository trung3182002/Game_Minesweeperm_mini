/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Administrator
 */
public class SQLSeverDataProvider {
    
   public Connection connection = null;
    
    public void open(){
        
    String strSever = "DESKTOP-PTQBDTF";
    String strDatabase= "QL_GAME4";
    String strUser= "sa";
    String strPass= "123";
        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(driver);
            
            String connectURL ="jdbc:sqlserver://"+strSever
                    +":1433;databaseName = "+strDatabase
                    +";user = "+strUser
                    +";password="+strPass;
            connection=DriverManager.getConnection(connectURL);
            if(connection!=null)
            {
                System.out.println("ket noi thanh cong");
            }
            else {
                System.out.println("Ket noi khong thanh cong");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }
    public void close(){
        try {
            this.connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public ResultSet executeQuery(String sql){
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (Exception e) {
        }
        return resultSet;
    }
    public int executeUpdate(String sql)
    {
        int n =-1;
        try {
            Statement statement = connection.createStatement();
            n=statement.executeUpdate(sql);
        } catch (Exception e) {
        }
        return n;
    }
    
}
