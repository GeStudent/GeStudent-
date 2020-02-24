/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.entities;

/**
 *
 * @author Ayadi
 */
public class Session {

    private static int idUser;

    public static void start(int currentUserID) {
        idUser = currentUserID;
    }

    public static int getCurrentSession() {
        if (idUser != -1) {
            return idUser;
        }
        return -1;

    }

    public static void close() throws Exception {
        if (idUser != -1) {
            idUser = -1;
        } else {
            throw new Exception("Session has not started yet!");
        }
    }

}
