/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Duv
 */

import Entity.Products;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOProduct extends DBConnect {
    
    
    public int changeUnitsInStock (int id, int quantity){
        int n=0;
        Products pro = new Products(id, userName, quantity, id, userName, quantity, quantity, id, id, true);
        String sql = "String sql=\"UPDATE [dbo].[Products]\\n\" +\n" +
"\"   SET [UnitsInStock] = ?\\n\" +\n" +
"\" WHERE ProductID = ?\";";
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(sql);
                    pre.setString(1,pro.getProductName());
                            n = pre.executeUpdate();

            System.out.println(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        //pre.setDatatype();
        return n;
    }
    
    public int changePrice(int id,int price){
        int n =0;
        
        return n;
    }
public int updateProduct(Products pro){
    int n=0;
    String sql="UPDATE [dbo].[Products]\n" +
"   SET [ProductName] =?\n" +
"      ,[SupplierID] = ?\n" +
"      ,[CategoryID] = ?\n" +
"      ,[QuantityPerUnit] = ?\n" +
"      ,[UnitPrice] = ?\n" +
"      ,[UnitsInStock] = ?\n" +
"      ,[UnitsOnOrder] = ?\n" +
"      ,[ReorderLevel] = ?\n" +
"      ,[Discontinued] = ?\n" +
" WHERE ProductID = ?";
    
    try {
        PreparedStatement pre = conn.prepareStatement(sql);
        //pre.setDatatype();
        pre.setString(1, pro.getProductName());
        pre.setInt(2, pro.getSupplierID());
        pre.setInt(3, pro.getCategoryID());
        pre.setString(4,pro.getQuantityPerUnit());
        pre.setDouble(5, pro.getUnitPrice());
        pre.setInt(6,pro.getUnitsInStock());
        pre.setInt(7, pro.getUnitsOnOrder());
        pre.setInt(8, pro.getReorderLevel());
        pre.setInt(9, (pro.isDiscontinued()==true?1:0));
        pre.setInt(10, pro.getProductID());
        n = pre.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
    }
    return n;
}

public int removeProduct(int id){
    int n=0 ;
    ResultSet rs=
            getData("select * from [Order Details] where ProductID="+id);
    String sql = "DELETE FROM Products WHERE ProductID ="+id;
        try {
            if(rs.next()){
                return n;
            }  
        Statement state = conn.createStatement();
        n=state.executeUpdate(sql);
                } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    return n;
}
public int insertProduct(Products pro){
    int n=0;
        String sql = "INSERT INTO [dbo].[Products]\n" +
"           ([ProductName]\n" +
"           ,[SupplierID]\n" +
"           ,[CategoryID]\n" +
"           ,[QuantityPerUnit]\n" +
"           ,[UnitPrice]\n" +
"           ,[UnitsInStock]\n" +
"           ,[UnitsOnOrder]\n" +
"           ,[ReorderLevel]\n" +
"           ,[Discontinued])\n" +
"     VALUES(?,?,?,?,?,?,?,?,? )";
        
       
    try {
        PreparedStatement pre = conn.prepareStatement(sql);
        //pre.setDatatype();
        pre.setString(1, pro.getProductName());
        pre.setInt(2, pro.getSupplierID());
        pre.setInt(3, pro.getCategoryID());
        pre.setString(4,pro.getQuantityPerUnit());
        pre.setDouble(5, pro.getUnitPrice());
        pre.setInt(6,pro.getUnitsInStock());
        pre.setInt(7, pro.getUnitsOnOrder());
        pre.setInt(8, pro.getReorderLevel());
        pre.setInt(9, (pro.isDiscontinued()==true?1:0));
        n = pre.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
    }
    return n;
}
    DBConnect dbconnect = new DBConnect();
public int addProduct(Products pro){
    int n =0;
    String sql = "INSERT INTO [dbo].[Products]\n" +
"           ([ProductName]\n" +
"           ,[SupplierID]\n" +
"           ,[CategoryID]\n" +
"           ,[QuantityPerUnit]\n" +
"           ,[UnitPrice]\n" +
"           ,[UnitsInStock]\n" +
"           ,[UnitsOnOrder]\n" +
"           ,[ReorderLevel]\n" +
"           ,[Discontinued])\n" +
"     VALUES('"+pro.getProductName()+"',"+pro.getSupplierID()+
            ","+pro.getCategoryID()+ 
            ",'"+ pro.getQuantityPerUnit()+
            "',"+pro.getUnitPrice()+
        ","+pro.getUnitsInStock()+","+pro.getUnitsOnOrder()+","+pro.getReorderLevel()+","+(pro.isDiscontinued()==true?1:0)+")\n";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
                    n=state.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    return n;
}
    public Vector<Products> getProducts(String sql) {
        Vector<Products> vector = new Vector<Products>();
        try {
            ResultSet rs = getData(sql);
           // Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int ProductID = rs.getInt(1);
                String ProductName = rs.getString(2);
                int SupplierID = rs.getInt(3);
                int CategoryID = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                double UnitPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7), UnitsOnOrder = rs.getInt(8), ReorderLevel = rs.getInt(9);
                boolean Discontinued = (rs.getInt(10) == 1 ? true : false);

                Products pro = new Products(ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                vector.add(pro);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public void displayAll(String sql) {

        try {
            Statement state = conn.createStatement();// statement la doi tuong nhan cau lenh sql
            // gui/nhan sql qua conn
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int ProductID = rs.getInt(1);
                String ProductName = rs.getString(2);
                int SupplierID = rs.getInt(3);
                int CategoryID = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                double UnitPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7), UnitsOnOrder = rs.getInt(8), ReorderLevel = rs.getInt(9);
                boolean Discontinued = (rs.getInt(10) == 1 ? true : false);

                Products pro = new Products(ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                System.out.println(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
        int n = dao.removeProduct(82);
       Vector<Products> Vector = dao.getProducts("select * from Products");
       if(n>0){
           System.out.println("inserted");
       }
       for (Products pro:Vector){
           System.out.println(pro);
       }
               
    }
}
