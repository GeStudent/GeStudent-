/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

import edu.gestudent.entities.Student;
import edu.gestudent.entities.user;
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
 * @author Ayadi
 */
public class ServicesUsers {

    protected Connection con;
    protected Statement ste;

    public ServicesUsers() {
        con = DataBase.getInstance().getConnection();

    }

    public String getName(int id) {
        String role = "";
        String role1 = "";
        try {
            PreparedStatement pre = con.prepareStatement("select firstname,lastname from fos_user where id=?");
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                role = rs.getString(1);
                role1 = rs.getString(2);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return role + " " + role1;
    }

    public String getMail(int id) {
        String role = "";
        try {
            PreparedStatement pre = con.prepareStatement("select email from fos_user where id=?");
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                role = rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return role;
    }

    public String getRole(String username) {
        String role = "";
        try {
            PreparedStatement pre = con.prepareStatement("select roles from fos_user where username=?");
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                role = rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return role;
    }

    public String getQRcode(String email) {
        String Qrcode = "";
        try {
            PreparedStatement pre = con.prepareStatement("select idcode from fos_user where email=?");
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Qrcode = rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return Qrcode;
    }

    public String getImage(int id) {
        String image = "";
        try {
            PreparedStatement pre = con.prepareStatement("select image from fos_user where id=?");
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                image = rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return image;
    }

    public String getUsername(int id) {
        String username = "";
        try {
            PreparedStatement pre = con.prepareStatement("select username from fos_user where id=?");
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                username = rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return username;
    }

    public int QrcodeisUsed(String Qr) {
        int enabled = 0;
        try {
            PreparedStatement pre = con.prepareStatement("select enabled from fos_user where idcode=?");
            pre.setString(1, Qr);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                enabled = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return enabled;
    }

    String idCode(user u) {
        int random = (int) (Math.random() * (199 - 100 + 1) + 100);
        String code = String.valueOf(random) + "GE";
        int value = 0;
        if (u.getGender() == "male") {
            code = code + "M";
        } else {
            code = code + "F";
        }
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("select NEXTVAL(seq_user);");
            while (rs.next()) {
                value = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        code = code + String.valueOf(value);
        return code;
    }

    public void ajouter(user u) {

        try {
            PreparedStatement pre = con.prepareStatement("INSERT INTO fos_user (firstname,lastname,email,birth_day,phone,pays,adress,gender,idCode,email_canonical)VALUES (?,?,?,?,?,?,?,?,?,?);");
            pre.setString(1, u.getFirstname());
            System.out.println(idCode(u));
            pre.setString(2, u.getLastname());
            pre.setString(3, u.getEmail());
            pre.setString(4, u.getBirthDay());
            pre.setInt(5, u.getPhone());
            pre.setString(6, u.getPays());
            pre.setString(7, u.getAdress());
            pre.setString(8, u.getGender());
            pre.setString(9, idCode(u));
            pre.setString(10, u.getEmail().toLowerCase());


            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean ajouterAccount(String username, String image, String password, String idcode) {
        try {
            PreparedStatement pre = con.prepareStatement("update fos_user set username=?,image=?,password=? ,username_canonical=? ,enabled=1 where idcode=?;");
            pre.setString(1, username);
            pre.setString(2, image);
            String hashed2 = BCrypt.hashpw(password, BCrypt.gensalt(13));
            hashed2 = "$2y$" + hashed2.substring(4);
            pre.setString(3, hashed2);
            pre.setString(5, idcode);
           pre.setString(4, username.toLowerCase());

            pre.executeUpdate();
            System.out.println("Account ADDED");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean delete(String idcode) {

        try {
            PreparedStatement pre = con.prepareStatement("Delete from fos_user where idcode=? ;");
            pre.setString(1, idcode);
            if (pre.executeUpdate() != 0) {
                System.out.println("user Deleted");
                return true;
            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
        System.out.println("id user not found!!!");
        return false;

    }

    public boolean updatepassword(int id, String password) {

        try {
            PreparedStatement pre = con.prepareStatement("update fos_user set password =? where id=? ;");
            String hashed2 = BCrypt.hashpw(password, BCrypt.gensalt(13));
            hashed2 = "$2y$" + hashed2.substring(4);

            pre.setString(1, hashed2);
            pre.setInt(2, id);

            if (pre.executeUpdate() != 0) {
                System.out.println("user's image is up to date");
                return true;
            }
            System.out.println("id user not found!!!");
        } catch (SQLException ex) {
            Logger.getLogger(ServicesUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public int changePwd(user u) {
        int usrExec = 0;

        PreparedStatement usrPs;
        try {

            String usrQry = "update fos_user set password = ? where username = ?";
            usrPs = con.prepareStatement(usrQry);
            usrPs.setString(1, u.getPassword());
            usrPs.setString(2, u.getUsername());
            usrExec = usrPs.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return usrExec;
    }

    public boolean updateimage(int id, String image) {

        try {
            PreparedStatement pre = con.prepareStatement("update fos_user set image =? where id=? ;");
            pre.setString(1, image);
            pre.setInt(2, id);

            if (pre.executeUpdate() != 0) {
                System.out.println("user's image is up to date");
                return true;
            }
            System.out.println("id user not found!!!");
        } catch (SQLException ex) {
            Logger.getLogger(ServicesUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean updateInfo(int id, String username, String firstname, String lastname, int phone, String date) {

        try {
            PreparedStatement pre = con.prepareStatement("update user set username =?,firstname=?,lastname=?,phone=?,birthDay=? where id=? ;");
            pre.setString(1, username);
            pre.setString(2, firstname);
            pre.setString(3, lastname);
            pre.setInt(4, phone);
            pre.setString(5, date);
            pre.setInt(6, id);

            if (pre.executeUpdate() != 0) {
                System.out.println("user is up to date");
                return true;
            }
            System.out.println("id user not found!!!");
        } catch (SQLException ex) {
            Logger.getLogger(ServicesUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public List<user> readAll() throws SQLException {

        List<user> lu = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select firstname,lastname,email,roles,birthDay,phone,pays,adress,gender from fos_user");
        while (rs.next()) {
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            String email = rs.getString("email");
            String roles = rs.getString("roles");
            String birthDay = rs.getString("birthDay");
            int phone = rs.getInt("phone");
            String pays = rs.getString("pays");
            String adress = rs.getString("adress");
            String gender = rs.getString("gender");
            user u = new user(firstname, lastname, email, roles, birthDay, phone, pays, adress, gender);
            lu.add(u);
        }
        return lu;
    }

    public boolean checkUser(String username, String password) {
        boolean verif = false;

        try {
            PreparedStatement pt = con.prepareStatement("SELECT password FROM fos_user where username=?");
            pt.setString(1, username);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                String crypted1 = "$2a" + rs.getString("password").substring(3);
                if (BCrypt.checkpw(password, crypted1)) {
                    System.out.println("connecte");
                    return true;
                } else {
                    System.out.println("nononoconnecte");
                }

            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
        return false;
    }

    public user findbyidcode(String idcode) {
        user u = null;
        try {

            PreparedStatement pre = con.prepareStatement("Select * from fos_user  WHERE idcode=? ");
            pre.setString(1, idcode);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String roles = rs.getString("roles");
                String birthDay = rs.getString("birthDay");
                int phone = rs.getInt("phone");
                String pays = rs.getString("pays");
                String adress = rs.getString("adress");
                String gender = rs.getString("gender");
                u = new user(firstname, lastname, email, roles, birthDay, phone, pays, adress, gender);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return u;
    }

    public user findbyid(int id) {
        user u = null;
        try {

            PreparedStatement pre = con.prepareStatement("Select * from fos_user  WHERE id=? ");
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String roles = rs.getString("roles");
                String birthDay = rs.getString("birth_day");
                int phone = rs.getInt("phone");
                String pays = rs.getString("pays");
                String adress = rs.getString("adress");
                String gender = rs.getString("gender");
                u = new user(username, firstname, lastname, email, roles, birthDay, phone, pays, adress, gender);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        return u;
    }

    public int login(user u) {
        int count = 0;

        PreparedStatement preparedStatement;
        try {
            PreparedStatement pre = con.prepareStatement("SELECT * FROM fos_user WHERE username = ?");
            pre.setString(1, u.getUsername());
            ResultSet rs = pre.executeQuery();
//            System.out.println(rs.getString("roles"));

            while (rs.next()) {
                String crypted1 = "$2a" + rs.getString("password").substring(3);
                if (BCrypt.checkpw(u.getPassword(), crypted1)) {
                    u.setId(rs.getInt("id"));
                    System.out.println("connnectee");
                    return 1;
                }
            }

            System.out.println(" non connnectee");

            return 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return count;
    }

    public int findUser(user u) {
        int count = 0;

        PreparedStatement loginPs;
        try {

            String loginQry = "select * from user where username = ?";
            loginPs = con.prepareStatement(loginQry);
            loginPs.setString(1, u.getUsername());
            ResultSet rs = loginPs.executeQuery();
            while (rs.next()) {
                count++;
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return count;
    }

    public String getPhoneByUser(String user) {
        String phone = "";
        try {
            String query = "select phone from user where username = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                phone = rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        //System.out.println(phone);

        return phone;
    }

    public user findUsertbyid(int id) {
        user u = null;
        try {

            PreparedStatement pre = con.prepareStatement("Select * from fos_user  WHERE id=? ");
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {

                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String roles = rs.getString("roles");
                String birthDay = rs.getString("birth_day");
                int phone = rs.getInt("phone");
                String image = rs.getString("image");
                String pays = rs.getString("pays");
                String adress = rs.getString("adress");
                String gender = rs.getString("gender");
                u = new user(id, username, lastname, firstname, image, email, "", roles, birthDay, phone, pays, adress, gender);
            }
            return u;
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return u;
    }
}
