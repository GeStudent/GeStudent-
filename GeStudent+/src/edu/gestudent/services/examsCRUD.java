/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

import edu.gestudent.entities.exams;
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
 * @author Asus
 */
public class examsCRUD {
    Connection con;
     Statement ste;

    public examsCRUD() {
            con = DataBase.getInstance().getConnection();
    }
    public void ajoutex(exams e){
           String requete2= "INSERT INTO exams (nomex,dateex,duree)VALUES (?,?,?)";
         
        try {
             PreparedStatement pst = con.prepareStatement(requete2);
//            pre = con.prepareStatement("INSERT INTO behaviour (attendance,award)VALUES (?,?)");
            pst.setString(1, e.getNomex());
            pst.setString(2, e.getDateex());
            pst.setString(3, e.getDuree());
              pst.executeUpdate();
        } catch (SQLException ex) {
          System.out.println(ex.getMessage());
        }
           
    }
      public boolean updateex(int idexa, String dateex) throws SQLException {

        try {
            PreparedStatement pre = con.prepareStatement("update exams set dateex =? where idexa=? ;");
            pre.setString(1, dateex);
              pre.setInt(2, idexa);
            if (pre.executeUpdate() != 0) {
                System.out.println("exam date is updated");
                return true;
            }
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            System.out.println("id exam not found!!!");
            return false;
       

    }
      public boolean delete(exams e) throws SQLException {

        PreparedStatement pre = con.prepareStatement("Delete from exams where idexa=? ;");
try{
        pre.setInt(1, e.getIdexa());
        if (pre.executeUpdate() != 0) {
            System.out.println("exam Deleted");
            return true;
        }
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("id exam not found!!!");
        return false;

    }
     public List<exams> afficherex(){
        ArrayList<exams> per =new ArrayList();

        try {
                    String requete3 = "SELECT * FROM exams";
            PreparedStatement pst2 = con.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            
            while(rs.next()){
              exams e = new exams();
              e.setIdexa(rs.getInt("idexa"));
              e.setNomex(rs.getString(2));
              e.setDateex(rs.getString(3));
               e.setDuree(rs.getString(4));

            
              per.add(e);
            }
        } catch (SQLException ex) {
                  System.out.println(ex.getMessage());
        }
        return per;
    }
     
     
     public List<exams> afficherexClass(int idclass){
        ArrayList<exams> per =new ArrayList();

        try {
                    String requete3 = "SELECT exams.idexa, exams.nomex,exams.dateex,exams.duree,class.idclass,tc.idtea from exams,class,fos_user,tc where exams.idexa=tc.idexa and class.idclass=tc.idclas and tc.idtea=fos_user.id and class.idclass=?";
            PreparedStatement pst2 = con.prepareStatement(requete3);
            pst2.setInt(1, idclass);
            ResultSet rs = pst2.executeQuery();
            
            while(rs.next()){
              exams e = new exams();
              e.setIdexa(rs.getInt("idexa"));
              e.setNomex(rs.getString("nomex"));
              e.setDateex(rs.getString("dateex"));
               e.setDuree(rs.getString("duree"));

            
              per.add(e);
            }
        } catch (SQLException ex) {
                  System.out.println(ex.getMessage());
        }
        return per;
    }
     public List<exams> RechercheReclamation(String rech) {

        ArrayList<exams> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM exams WHERE nomex LIKE '%"+rech+"%'";
            

            PreparedStatement pre = con.prepareStatement(requete);           
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                exams t = new exams();
                t.setIdexa(rs.getInt("idexa"));
                t.setNomex(rs.getString("nomex"));
                t.setDateex(rs.getString("dateex"));
                t.setDuree(rs.getString("duree"));
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

//     public List<exams> rechrecherlivre(String Name)
//    {
//        try {
//            List<exams> exa=new ArrayList<>();
//                  String requete7 = "Select * from exams  WHERE '%+"nomex"+'%'";
//            PreparedStatement pst2 = con.prepareStatement(requete7);
//       
//            pst2.setString(1, nomex );
//            ResultSet rs = pre.executeQuery();
//            while (rs.next()) {
//                String name=rs.getString("name");
//                String image=rs.getString("image");
//                String author=rs.getString("author");
//                String url=rs.getString("url");
//                String categorie=rs.getString("categorie");
//                int quantite=rs.getInt("quantite");
//                exams e=new exams (name,image,author,url,categorie,quantite);
//                exa.add(l);
//            }
//            return liv;
//        } catch (SQLException ex) {
//            Logger.getLogger(LivreCrud.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
}
