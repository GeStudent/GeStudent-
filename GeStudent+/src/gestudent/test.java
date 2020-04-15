/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestudent;

import edu.gestudent.services.BCrypt;

/**
 *
 * @author Ayadi
 */
public class test {
    public static void main(String[] args) {
//              String passwordCrypted="$2a"+"ayadi".substring(3);
//              System.out.println("passss:"+passwordCrypted);
//              
     String hashed2 = BCrypt.hashpw("yassine", BCrypt.gensalt(13));
      hashed2 = "$2y$" + hashed2.substring(4);
      System.out.println("HASHED PASSWORD =" + hashed2);
      
             System.out.println(hashed2);

    }
}
