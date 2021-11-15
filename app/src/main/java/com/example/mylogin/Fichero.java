package com.example.mylogin;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Fichero {


    /*Francisco Miguel Penin Saldaña
     * Clase que controla el uso del fichero para guardar los credenciales en txt
     * Version 1.0
     *14/10/2021
     */

    //Lee el archivo y devuelve un Arraylist con los usuarios
    public ArrayList<Usuario> leerFichero(String ruta){

        Usuario user;
        ArrayList<Usuario> usuarios = new ArrayList<>();
        FileReader fr;
        int contador = 0;
        try {
            fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr);

            String linea;

            String[] arrayUser = new String[2];

            while ((linea = br.readLine()) != null) {

                // bucle que recorre el array que usamos para variables usuario
                for (int i = 0; i < 1; i++) {

                    arrayUser[contador] = linea;

                    contador++;

                }
                // if donde creamos el usuario
                if (contador == 2) {


                    String nombre = arrayUser[0];
                    String contrasenia = arrayUser[1];

                    user = new Usuario(nombre, contrasenia);
                    contador = 0;

                    usuarios.add(user);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;

    }

    //Comprueba si el usuario ya esta registrado
    public boolean usuarioRegistrado(ArrayList<Usuario> listaUsuarios, Usuario user)  {

        boolean bool = false;

        for (Usuario usuario : listaUsuarios) {

            if (usuario.getNombre().equals(user.getNombre())) {

                bool = true;

            }

        }

        return bool;

    }
    //Registra al usuario que le pasemos en la ruta que le demos por parámetro
    public void registrarUser(String ruta, Usuario user) {

        try  {

            FileWriter fichero = new FileWriter(ruta, true);
            PrintWriter pw = new PrintWriter(fichero);

            pw.println(user.getNombre());
            Log.d("Metodo registrar", "user.getPass=" + user.getPass());
            pw.println(user.getPass());


            pw.close();
            fichero.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("catch registrar", "error=" + e.toString());
        }

    }
    //A través de la lista de usuarios proporcionada comprueba que la contraseña es correcta para
    //el usuario que pasamos por parámetro
    public boolean comprobarPass(ArrayList<Usuario> listaUsuarios, Usuario user){
        boolean bool = false;

        for (Usuario usuario: listaUsuarios) {

            if(usuario.getNombre().equals(user.getPass())){
                bool = true;
            }
        }
        return bool;
    }
    //Comprueba que ningún campo esta vacío y devuelve true si algún campo se encuentra vacío
    public boolean campoVacio(Usuario user){
        boolean bool = false;

        if(user.getNombre().isEmpty() || user.getPass().isEmpty()){
            bool= true;
        }
        return bool;
    }
}
