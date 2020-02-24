/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

import edu.gestudent.entities.Menu;
import edu.gestudent.entities.meal;
import edu.gestudent.utils.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class Mealcrud {

    Connection con;
    Statement st;

    public Mealcrud() {
        con = DataBase.getInstance().getConnection();
    }

    public void ajouterMeal(meal m) {
        String reqeute = "insert into meal (image,name,type,rating) values(?,?,?,?);";
        try {
            PreparedStatement pst = con.prepareStatement(reqeute);
//            pst.setInt(1, m.getId());
            pst.setString(1, m.getImage());
            pst.setString(2, m.getName());
            pst.setString(3, m.getType());
            pst.setInt(4, m.getRating());
            pst.executeUpdate();

            System.out.println("Meal added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /*public void ajouterRating(String nom, int rating) {
        String reqeute = "update meal set rating=? where name=?;";
        try {
            PreparedStatement pst = con.prepareStatement(reqeute);
            pst.setInt(1, rating);
            pst.setString(2, nom);
            pst.executeUpdate();
            System.out.println("rating added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/

    public boolean delete(meal m) throws SQLException {

        String reqeute = "delete from meal  where (name = ?) ;";
        try {
            PreparedStatement pst = con.prepareStatement(reqeute);
            pst.setString(1, m.getName());
            // pst.setString(2, m.setDescription(reqeute));
            if (pst.executeUpdate() != 0) {
                System.out.println("Meal deleted");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;
//To change body of generated methods, choose Tools | Templates.
    }

    public boolean Update(String name, String image, String type, int rating) {
        String reqeute = "UPDATE meal SET  image= ? , type= ? , rating=?  where name = ? ;";
        try {
            PreparedStatement pst = con.prepareStatement(reqeute);
//            pst.setInt(1, m.getId());
            pst.setString(1, image);
            pst.setString(2, type);
            pst.setInt(3, rating);
            pst.setString(4, name);
            
            
            if (pst.executeUpdate() != 0) {
                System.out.println("Meal Updated");
            } else {
                System.out.println("non");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }

    public ObservableList<meal> afficherMeal() {
        ObservableList<meal> lsp = FXCollections.observableArrayList();
        String req = "Select * from meal";
        try {
            PreparedStatement st2 = con.prepareStatement(req);
            ResultSet rs = st2.executeQuery();
            while (rs.next()) {
                meal m = new meal();

                m.setId_meal(rs.getInt("id_meal"));
                m.setImage(rs.getString("image"));

                m.setName(rs.getString("name"));
                m.setType(rs.getString("type"));
                m.setRating(rs.getInt("rating"));
                lsp.add(m);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lsp;
    }

    public boolean rechercherMeal(String name) {

        return afficherMeal().stream().anyMatch(e -> e.getName().equals(name));
    }

    public TreeSet<meal> trierEquipesParNom() {
        return afficherMeal().stream().collect(Collectors.toCollection(() -> new TreeSet<>((a, b) -> a.getName().compareTo(b.getName()))));
    }
    
    public List<meal> Recherche(String rech) {

        ArrayList<meal> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM meal WHERE name LIKE '%"+rech+"%'";
            

            PreparedStatement pre = con.prepareStatement(requete);           
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                meal m = new meal();
                m.setId_meal(rs.getInt("id_meal"));
                m.setImage(rs.getString("image"));

                m.setName(rs.getString("name"));
                m.setType(rs.getString("type"));
                m.setRating(rs.getInt("rating"));
                list.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    
    public ObservableList<String> readAllMeals() throws SQLException {

        ObservableList<String> arr = FXCollections.observableArrayList();
        st = con.createStatement();
        ResultSet rs = st.executeQuery("select DISTINCT (name) from meal");
        while (rs.next()) {

            String name = rs.getString("name");
            
            arr.add(name);
        }
        return arr;
    }
   public int getIdMeal(String name) {
        int id = 0;
        try {
            PreparedStatement pre = con.prepareStatement("select * from meal where name=?");
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id_meal");
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return id;
    }

    
}
