/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.entities;

/**
 *
 * @author ASUS
 */
public class Livre {
    private int id_livre ;
    private String name;
    private String image;
    private String author;
    private String url;
    private String categorie;
    private int quantite;
    
    public Livre() {
    }

    public Livre(String name, String image,String author, String url,String categorie, int quantite) {
        this.name = name;
        this.image = image;
        this.author = author;
        this.url = url;
        this.categorie = categorie;
        this.quantite = quantite;
    }

    public int getId_livre() {
        return id_livre;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id_livre;
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
        final Livre other = (Livre) obj;
        if (this.id_livre != other.id_livre) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id_livre + ", name=" + name + ", image=" + image + ", author=" + author + ", url=" + url + ", categorie=" + categorie + ", quantite=" + quantite + '}';
    }
    
    
    
}
