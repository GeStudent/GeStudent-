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
public class cours {
   
    private String name;
    
      private int idcour;
    private String lesson;
    private int duration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdcour() {
        return idcour;
    }

    public void setIdcour(int idcour) {
        this.idcour = idcour;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public int getDuration() {
        return duration;
    }

  

    public void setDuration(int duration) {
        this.duration = duration;
    }
    

    public cours( String name,int idcour, String lesson,int duration) {
        this.name = name;
        this.idcour = idcour;
        this.lesson = lesson;
                this.duration = duration;

    }
       public cours( String name, String lesson,int duration) {
        this.name = name;
      
        this.lesson = lesson;
                this.duration = duration;

    }
 
 

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idcour;
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
        final cours other = (cours) obj;
        return this.idcour == other.idcour;
    }

    @Override
    public String toString() {
        return name ;
    }

}


