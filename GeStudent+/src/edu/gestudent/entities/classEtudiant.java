/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.entities;

/**
 *
 * @author CHIKHAOUI NOUHA
 */
public class classEtudiant {

    private String namecl;
    private String firstname;
    private int idetudiant;

    private int idclass;

    public classEtudiant(int idclass, int idetudiant) {
        this.idetudiant = idetudiant;
        this.idclass = idclass;

    }

    public classEtudiant(String namecl, String firstname) {
        this.namecl = namecl;
        this.firstname = firstname;

    }

    public classEtudiant(int idetudiant) {
        this.idetudiant = idetudiant;

    }

    public String getNamecl() {
        return namecl;
    }

    public void setNamecl(String namecl) {
        this.namecl = namecl;
    }


    public int getidetudiant() {
        return idetudiant;
    }

    public int getidclass() {
        return idclass;
    }

    public void setidetudiant(int idetudiant) {
        this.idetudiant = idetudiant;
    }

    public void setIdclass(int idclass) {
        this.idclass = idclass;
    }

    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idetudiant;
        return hash;
    }

    
    public String getFirstname() {
        return firstname;

    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getIdetudiant() {
        return idetudiant;
    }

    /*public boolean equals(Object obj) {
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
    return this.idetudiant == other.idetudiant;
    }*/
    public void setIdetudiant(int idetudiant) {    
        this.idetudiant = idetudiant;
    }

    @Override
    public String toString() {
        return "classEtudiant{" + "namecl=" + namecl + ", firstname=" + firstname + '}';
    }

}
