/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

//import edu.projet.entitities.Menu;
//import edu.projet.utils.MyConnection;
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
import java.util.TreeSet;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class MenuCrud {

    Connection con;
    Statement st;

    public MenuCrud() {
        con = DataBase.getInstance().getConnection();
    }

    public void ajouterMenu(Menu m) {
        String reqeute = "insert into menu (name,description) values(?,?);";
        try {
            PreparedStatement pst = con.prepareStatement(reqeute);
//            pst.setInt(1, m.getId());
            pst.setString(1, m.getName());
            pst.setString(2, m.getDescription());
            pst.executeUpdate();

            System.out.println("Menu added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public boolean delete(Menu m) throws SQLException {

        String reqeute = "delete from menu  where (name = ?) ;";
        try {
            PreparedStatement pst = con.prepareStatement(reqeute);
            pst.setString(1, m.getName());
            // pst.setString(2, m.setDescription(reqeute));
            if (pst.executeUpdate() != 0) {
                System.out.println("Menu deleted");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;
//To change body of generated methods, choose Tools | Templates.
    }

    public boolean Update(String name, String description) throws SQLException {

        String reqeute = "UPDATE menu SET  description= ? where name = ? ;";
        try {
            PreparedStatement pst = con.prepareStatement(reqeute);
//            pst.setInt(1, m.getId());

            pst.setString(1, description);
            pst.setString(2, name);
            if (pst.executeUpdate() != 0) {
                System.out.println("Menu Updated");
            } else {
                System.out.println("non");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }

    public ObservableList<Menu> afficherMenu() {
        ObservableList<Menu> lsp =  FXCollections.observableArrayList();
        String req = "Select * from Menu";
        try {
            PreparedStatement st2 = con.prepareStatement(req);
            ResultSet rs = st2.executeQuery();
            while (rs.next()) {
                Menu m = new Menu();
                m.setId(rs.getInt("id_menu"));
                m.setName(rs.getString(2));
                m.setDescription(rs.getString("description"));
                lsp.add(m);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lsp;
    }

    
     public List<Menu> Rechercherrrr(String rech) {

        ArrayList<Menu> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM menu WHERE name LIKE '%"+rech+"%'";
            

            PreparedStatement pre = con.prepareStatement(requete);           
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Menu m = new Menu();
                m.setId(rs.getInt("id_menu"));
                m.setName(rs.getNString("name"));
                m.setDescription(rs.getString("description"));
                list.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    
    
//    public boolean rechercherMenu(String name) {
//
//        return afficherMenu().stream().anyMatch(e -> e.getName().equals(name));
//    }

    public TreeSet<Menu> trierEquipesParNom() {
        return afficherMenu().stream().collect(Collectors.toCollection(() -> new TreeSet<>((a, b) -> a.getName().compareTo(b.getName()))));
    }
    
    
     public ObservableList<String> readAllMenu() throws SQLException {

        ObservableList<String> arr = FXCollections.observableArrayList();
        st = con.createStatement();
        ResultSet rs = st.executeQuery("select DISTINCT (name) from menu");
        while (rs.next()) {

            String name = rs.getString("name");
            arr.add(name);
        }
        return arr;
    }
    
        public int getIdMenu(String name) {
        int id = 0;
        try {
            PreparedStatement pre = con.prepareStatement("select * from menu where name=?");
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id_menu");
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return id;
    }
}
