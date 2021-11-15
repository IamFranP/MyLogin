package com.example.mylogin;

public class Usuario {

    /*Francisco Miguel Penin Saldaña
     * Clase modelo de un usuario con nombre y contraseña
     * Version 1.0
     *14/10/2021
     */

    String nombre;
    String pass;
 
    public Usuario(String nombre, String pass) {
        this.nombre = nombre;
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
