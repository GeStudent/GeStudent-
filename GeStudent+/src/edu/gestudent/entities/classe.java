/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.entities;

import java.sql.Date;

/**
 *
 * @author CHIKHAOUI NOUHA
 */
public class classe {
   
   private String nameC;
 
    private int idclass;

    public classe() {
         //To change body of generated methods, choose Tools | Templates.
    }



  

    

   
    

    public classe( String nameC,int idclass) {
        this.nameC = nameC;
        this.idclass = idclass;
      

    }

   
    
  
       public classe( String nameC) {
        this.nameC = nameC;
      
       
    }

    public classe(String name, String nameC, int duration, String firstname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

   
 public String getNameC() {
        return nameC;
    }

    public int getIdclass() {
        return idclass;
    }

   
   

   

    public void setNameC(String nameC) {
        this.nameC = nameC;
    }
     public void setIdclass(int idclass) {
        this.idclass = idclass;
    }

  

    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idclass;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final classe other = (classe) obj;
        return this.idclass == other.idclass;
    }

    @Override
    public String toString() {
        return  nameC ;
    }

}


