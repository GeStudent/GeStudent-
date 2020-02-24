/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

import edu.gestudent.entities.Menu;
import edu.gestudent.entities.MenuMeal;
import edu.gestudent.entities.meal;
import edu.gestudent.utils.DataBase;
import java.awt.SystemColor;
//import edu.projet.entitities.MenuMeal;
//import edu.projet.utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class MenuMealCrud {

    Connection con;
    Statement st;

    public MenuMealCrud() {
        con = DataBase.getInstance().getConnection();
    }

    public void ajouterMenuMeal(MenuMeal ml) {
        String reqeute = "insert into menumeal (id_menu,id_meal) values(?,?);";
        try {
            PreparedStatement pst = con.prepareStatement(reqeute);
            pst.setInt(1, ml.getId_menu());
            pst.setInt(2, ml.getId_meal());
            pst.executeUpdate();

            System.out.println("Meal added to menu");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public boolean deleteMenuMeal(MenuMeal ml) throws SQLException {

        String reqeute = "delete from menumeal  where (id_meal = ?) ;";
        try {
            PreparedStatement pst = con.prepareStatement(reqeute);
            pst.setInt(1, ml.getId_meal());
            if (pst.executeUpdate() != 0) {
                System.out.println("Meal deleted from the menu");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;

    }

    public ObservableList<MenuMeal> afficherMenuMeal(String menuName) {
        ObservableList<MenuMeal> lsp = FXCollections.observableArrayList();
        String req = "SELECT ml.id_meal, ml.id_menu, m.name as namemenu, m.description , me.name as namemeal , me.type FROM meal me , menu m JOIN menumeal ml WHERE ml.id_menu=m.id_menu AND ml.id_meal=me.id_meal AND m.name like ?";
        try {
            PreparedStatement st2 = con.prepareStatement(req);
            st2.setString(1, menuName);
            ResultSet rs = st2.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.toString());
                MenuMeal m = new MenuMeal();
                m.setId_meal(rs.getInt("id_meal"));
                m.setId_menu(rs.getInt("id_menu"));
                m.setNamemeal(rs.getString("namemeal"));
                m.setDescription(rs.getString("description"));
                m.setNamemenu(rs.getString("namemenu"));
                m.setTypemenumeal(rs.getString("type"));
                System.out.println(m);
                lsp.add(m);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lsp;
    }

//    public ObservableList<MenuMeal> afficherMenuMeal() throws SQLException {
//        ObservableList<MenuMeal> list = FXCollections.observableArrayList();
//
//        String req = "SELECT ml.id_meal, ml.id_menu, m.name , m.description , me.name , me.type FROM meal me , menu m JOIN menumeal ml WHERE ml.id_menu=m.id_menu AND ml.id_meal=me.id_meal AND m.name=?";
//
//        PreparedStatement st2 = con.prepareStatement(req);
//        //st2.setString(1, me);
//        ResultSet rs = st2.executeQuery();
//        while (rs.next()) {
//            MenuMeal m = new MenuMeal();
//            m.setId_meal(rs.getInt("id_meal"));
//            m.setId_menu(rs.getInt("id_menu"));
//            m.setNamemeal(rs.getString("name"));
//            m.setDescription(rs.getString("description"));
//
//            m.setNamemenu(rs.getString("name"));
//            m.setType(rs.getString("type"));
//            list.add(m);
//        }
//        return list;
//    }
//    public ObservableList<String> readAllMeals() throws SQLException {
//
//        ObservableList<String> arr = FXCollections.observableArrayList();
//        st = con.createStatement();
//        ResultSet rs = st.executeQuery("select DISTINCT (name) from meal");
//        while (rs.next()) {
//
//            String role = rs.getString("name");
//            arr.add(role);
//        }
//        return arr;
//    }
  

}
