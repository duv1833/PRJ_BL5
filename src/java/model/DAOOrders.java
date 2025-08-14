/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Duv
 */
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entity.Orders;
import java.util.Vector;
import org.apache.tomcat.dbcp.dbcp2.PoolingConnection;
public class DAOOrders extends DBConnect{
    
    public int insertOrders(Orders order){
        int n=0;
        String sql = "INSERT INTO [dbo].[Orders]\n" +
"           ([CustomerID]\n" +
"           ,[EmployeeID]\n" +
"           ,[OrderDate]\n" +
"           ,[RequiredDate]\n" +
"           ,[ShippedDate]\n" +
"           ,[ShipVia]\n" +
"           ,[Freight]\n" +
"           ,[ShipName]\n" +
"           ,[ShipAddress]\n" +
"           ,[ShipCity]\n" +
"           ,[ShipRegion]\n" +
"           ,[ShipPostalCode]\n" +
"           ,[ShipCountry])\n" +
"     VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println(sql);
        try{
            PreparedStatement pre = conn.prepareStatement(sql);
             pre.setString(1,order.getCustomerID());
             pre.setInt(2, order.getEmployeeID());
             pre.setString(3, order.getOrderDate());
             pre.setString(4, order.getRequiredDate());
             pre.setString(5, order.getShippedDate());
             pre.setStr
             
        
                     }
        catch (SQLException ex){
                    Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);

        }
        return n;
    }
    
    DBConnect dbconnect = new DBConnect();
    public int addOrders(Orders order){
        int n=0;
        String sql = "INSERT INTO [dbo].[Orders]\n" +
"           ([CustomerID]\n" +
"           ,[EmployeeID]\n" +
"           ,[OrderDate]\n" +
"           ,[RequiredDate]\n" +
"           ,[ShippedDate]\n" +
"           ,[ShipVia]\n" +
"           ,[Freight]\n" +
"           ,[ShipName]\n" +
"           ,[ShipAddress]\n" +
"           ,[ShipCity]\n" +
"           ,[ShipRegion]\n" +
"           ,[ShipPostalCode]\n" +
"           ,[ShipCountry])\n" +
"     VALUES ('"+order.getCustomerID()+
                "',"+order.getEmployeeID()+",'"+order.getOrderDate()+
                "','"+order.getRequiredDate()+"','"+order.getShippedDate()+
                "',"+order.getShipVia()+","+order.getFreight()+",'"+order.getShipName()+
                "','"+order.getShipAddress()+"','"+order.getShipCity()+"','"+order.getShipRegion()+
                "'\n" +
"	 ,'"+order.getShipPostalCode()+"','"+order.getShipCountry()+"')";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n= state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public Vector<Orders> getOrders(String sql){
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
             while (rs.next()){
                  int OrderID = rs.getInt(1);
     String CustomerID = rs.getString(2);
     int EmployeeID = rs.getInt(3);
     String OrderDate = rs.getString(4);
     String RequiredDate = rs.getString(5);
     String ShippedDate = rs.getString(6);
     int ShipVia = rs.getInt(7);
     double Freight = rs.getDouble(8);
     String ShipName = rs.getString(9);
     String ShipAddress = rs.getString(10);
     String ShipCity = rs.getString(11), ShipRegion= rs.getString(12), ShipPostalCode = rs.getString(13), ShipCountry = rs.getString(14);
     Orders order = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, ShipVia, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
         vector.add(order);
             }

            
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
     return vector;   
    }
    
    public void displayAll(String sql){
        
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()){
                  int OrderID = rs.getInt(1);
     String CustomerID = rs.getString(2);
     int EmployeeID = rs.getInt(3);
     String OrderDate = rs.getString(4);
     String RequiredDate = rs.getString(5);
     String ShippedDate = rs.getString(6);
     int ShipVia = rs.getInt(7);
     double Freight = rs.getDouble(8);
     String ShipName = rs.getString(9);
     String ShipAddress = rs.getString(10);
     String ShipCity = rs.getString(11), ShipRegion= rs.getString(12), ShipPostalCode = rs.getString(13), ShipCountry = rs.getString(14);
     Orders order = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, ShipVia, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                System.out.println(order);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        
        DAOOrders daoO = new DAOOrders();
        int n = daoO.addOrders(new Orders(1,"FOLKO",1,"1996-07-04 00:00:00.000","1996-08-01 00:00:00.000","1996-07-12 00:00:00.000",3,36.36,"Furia Bacalhau e Frutos do Mar","Hoa Thanh Que","Hanh Thoa","VN1","EC2 5NT","VN"));
        Vector<Orders> vector = daoO.getOrders("select * from Orders");
        if(n>0){
           System.out.println("inserted");
       }
        for (Orders order : vector){
            System.out.println(order);
        }
    }
}
