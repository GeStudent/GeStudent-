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
public class EventClient {

    private int id_event;
    private int id_event_client;

    private String nom;
    private String description;
    String date_reservation;

    public EventClient(int id_event_client, int id_event) {
        this.id_event_client = id_event_client;
        this.id_event = id_event;
    }

    public EventClient(int id_event, int id_event_client, String nom, String date_reservation) {
        this.id_event = id_event;
        this.id_event_client = id_event_client;
        this.nom = nom;
        this.date_reservation = date_reservation;
    }

    public EventClient() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getId_event_client() {
        return id_event_client;
    }

    public void setId_event_client(int id_event_client) {
        this.id_event_client = id_event_client;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(String date_reservation) {
        this.date_reservation = date_reservation;
    }



    @Override
    public String toString() {
        return "EventClient{"  + ", id_event=" + id_event + ", id_event_client=" + id_event_client + ", nom=" + nom + ", date_reservation=" + date_reservation + '}';
    }

}
