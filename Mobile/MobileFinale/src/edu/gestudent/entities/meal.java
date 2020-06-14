/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.entities;

import java.util.Objects;

/**
 *
 * @author user
 */
public class meal {
     private int id_meal;
     private String image;
    private String name;
    private String type;
    private int rating;

    public meal() {
    }
    

    public meal(int id_meal, String image, String name, String type, int rating) {
        this.id_meal = id_meal;
        this.image = image;
        this.name = name;
        this.type = type;
        this.rating = rating;
    }

    public meal(String image, String name, String type, int rating) {
        this.image = image;
        this.name = name;
        this.type = type;
        this.rating = rating;
    }

    public int getId_meal() {
        return id_meal;
    }

    public void setId_meal(int id_meal) {
        this.id_meal = id_meal;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id_meal;
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
        final meal other = (meal) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "meal{" + "id_meal=" + id_meal + ", image=" + image + ", name=" + name + ", type=" + type + ", rating=" + rating + '}';
    }
    
}