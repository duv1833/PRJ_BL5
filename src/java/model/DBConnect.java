/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.servlet.jsp.jstl.sql.Result;
import jakarta.servlet.jsp.jstl.sql.ResultSupport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * @author Duv
 */
public class DBConnect {
    public Connection conn = null; // quan ly ket noi den DB
    String URL ="jdbc:sqlserver://localhost:1433;databaseName=Northwind";
    String userName="duvisme1";
    String password = "duvisme1";
    public DBConnect (String URL, String userName, String password){
        try {
            // call driver

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
               conn = DriverManager.getConnection(URL,userName,password);
            System.out.println("Connected");
        } catch (ClassNotFoundException ex) {
           ex.printStackTrace();
        }
          catch (SQLException ex) {
                Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
     
    }

    public DBConnect() {
        this("jdbc:sqlserver://localhost:1433;databaseName=Northwind", "duvisme1", "duvisme1");
    }
    
    public ResultSet getData(String sql){
        ResultSet rs = null;
        try {
            Statement state = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
 }
    public static void main(String[] args) {
        new DBConnect();
    }
}
