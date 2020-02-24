/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

import edu.gestudent.entities.cours;
import edu.gestudent.utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CHIKHAOUI NOUHA
 */
public class coursCRUD {

    private Connection con;
    private Statement ste;

    public coursCRUD() {
        con =  DataBase.getInstance().getConnection();

    }


    public boolean ajouter(cours c)  
    {
        try {
            PreparedStatement pre=con.prepareStatement("INSERT INTO `cours` ( `name`, `lesson`, `duration`) VALUES ( ?, ?, ?);");
            pre.setString(1, c.getName());
            pre.setString(2, c.getLesson());
            pre.setInt(3, c.getDuration());
            pre.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(coursCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
            

    
    
        
        
            public boolean supprimercour(int idcour) throws SQLException {

        PreparedStatement pre = con.prepareStatement("Delete from cours where idcour=? ;");
try{
        pre.setInt(1, idcour);
        if (pre.executeUpdate() != 0) 
            {
            System.out.println("idcour Deleted");

            
           return true;
        }
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("id cours not found!!!");
        return false;

    
    }
            
                     
 
     public boolean modifiercours(int duration, int idcour) throws SQLException {
        try {
            PreparedStatement pre = con.prepareStatement("update cours set duration =? where idcour=? ;");

            pre.setInt(1, duration);
            pre.setInt(2, idcour);

            if (pre.executeUpdate() != 0) {
                System.out.println(" updated");
                return true;
            }
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            System.out.println("id  not found!!!");
            return false;


    }        
            
            


    
    
    public boolean update(cours c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
    public List<cours> readAll()  {
    List<cours> arr=new ArrayList<>();
    
    try{
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select idcour,name,lesson,duration from cours");
     while (rs.next()) {                
               int idcour=rs.getInt("idcour");
               String name=rs.getString("name");
               String lesson=rs.getString("lesson");
               int duration=rs.getInt("duration");
               
               
               cours c=new cours( name,idcour,lesson, duration);
     arr.add(c);
     }
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return arr;
    }

   

    
}
 

