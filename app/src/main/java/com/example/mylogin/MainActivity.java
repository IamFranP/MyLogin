package com.example.mylogin;



import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/*Francisco Miguel Penin Saldaña
* Login con Java Actividad principal
* Version 1.0
*14/10/2021
*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Recupera los campos de texto
        EditText user = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText pass = (EditText) findViewById(R.id.editTextTextPassword);

        //Listener Para button Login
        Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.d("ONCLICK ACCEDER", "ACCEDER PULSADO");

                String[] archivos = fileList();
                String nombreFichero = "credenciales.txt";
                Fichero controlFichero = new Fichero();
                Usuario usuario = new Usuario(user.getText().toString(), pass.getText().toString());
                Log.d("usuario", "UsuarioDatos=" + pass.getText().toString() + " " + user.getText().toString());

                //Comprueba que el usuario no deja campos vacíos
                if (controlFichero.campoVacio(usuario)) {

                    Toast.makeText(getApplicationContext(), "Fallo al introducir datos vacios", Toast.LENGTH_SHORT).show();

                } else if (existeFichero(archivos, nombreFichero)) {


                    ArrayList listaUsuarios = controlFichero.leerFichero(getFilesDir() + "/" + "credenciales.txt");

                    //Si la pass y el usuario es correcto permite el acceso o lo cancela
                    if (controlFichero.usuarioRegistrado(listaUsuarios, usuario) ==true &&
                            controlFichero.comprobarPass(listaUsuarios, usuario) == true) {
                        Log.d("comprobarPass", "UsuarioDatos=" + pass.getText().toString() + " " + user.getText().toString());
                        Toast.makeText(getApplicationContext(), "El usuario se registro correctamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("comprobarPass", "UsuarioDatos=" + pass.getText().toString() + " " + user.getText().toString());
                        Toast.makeText(getApplicationContext(), "Error al acceder con el usuario", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        //Listener para button Sing In
        Button buttonSingIn = (Button) findViewById(R.id.buttonSingIn);
        buttonSingIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String[] archivos = fileList();//Recupera todos los archivos de la ruta del proyecto
                Usuario usuario = new Usuario(user.getText().toString(), pass.getText().toString());
                Log.d("usuario", "usuarioPass=" + pass.getText().toString());
                Fichero controlFichero = new Fichero();

                //Comprueba que los campos no estén vacíos
                if (controlFichero.campoVacio(usuario)) {
                    Toast.makeText(getApplicationContext(), "Fallo al introducir datos vacios", Toast.LENGTH_SHORT).show();
                } else if (existeFichero(archivos, "credenciales.txt")) {
                    //Si el usuario no existe  registra al usuario
                    if (!controlFichero.usuarioRegistrado(controlFichero.leerFichero(getFilesDir() + "/" + "credenciales.txt"), usuario)) {
                        Log.d("ONCLICK REGISTRAR", "Empieza a tirar ");
                        controlFichero.registrarUser(getFilesDir() + "/" + "credenciales.txt", usuario);
                        Toast.makeText(getApplicationContext(), "Usuario registrado correctamente", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "El usuario ya existe o campos incorrectos", Toast.LENGTH_LONG).show();
                    }
                    //Si el archivo donde guardamos los credenciales no existieran lo crea
                } else {
                    //Crear archivo credenciales
                    File file = new File(getFilesDir() + "/" + "credenciales.txt");
                    try {
                        file.createNewFile();

                        //grabar(binding.username.getText().toString(), binding.password.getText().toString());
                        //Esto de arriba es un método de como se hace

                        OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("credenciales.txt", Activity.MODE_PRIVATE));
                        archivo.write(user.getText().toString() + "\n" + pass.getText().toString() + "\n");
                        archivo.flush();
                        archivo.close();

                        Toast.makeText(getApplicationContext(), "Usuario registrado", Toast.LENGTH_LONG).show();

                    } catch (IOException e2) {
                        e2.printStackTrace();
                        Log.d("ONCLICK REGISTRAR", "IOException - AL CREAR FICHERO: " + e2.toString());
                    }
                }

            }
        });

        //Listeners para los registros con RRSS

    }//Aquí termina onCreate

    //Método que comprueba si el archivo existe y lo crea
    private boolean existeFichero(String[] archivos, String archBusca) {
        for (String archivo : archivos) {
            if (archBusca.equals(archivo))
                return true;
        }
        return false;
    }
}