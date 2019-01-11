package com.example.ricar.tema4;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ricar.tema4.Model.Usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class ActivityRegistrar extends AppCompatActivity{
    Button registrar;
    Button cancelar;
    EditText email;
    EditText pass;
    EditText nombreUsuario;
    EditText nombre;
    EditText apellidos;
    EditText dirección;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        registrar = findViewById(R.id.registrar);
        cancelar = findViewById(R.id.cancelar);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        nombreUsuario = findViewById(R.id.nombreUsuario);
        nombre = findViewById(R.id.nombre);
        apellidos = findViewById(R.id.apellidos);
        dirección = findViewById(R.id.direccion);

        final Button cancelar = findViewById(R.id.cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                setResult(RESULT_CANCELED,i);
                finish();
            }
        });

        final Button aceptar = findViewById(R.id.registrar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreU = String.valueOf(nombreUsuario.getText());
                String nombreR = String.valueOf(nombre.getText());
                String ApellidosU = String.valueOf(apellidos.getText());
                String direccionU = String.valueOf(dirección.getText());
                String emailU = String.valueOf(email.getText());
                String passU = String.valueOf(pass.getText());

                if (nombreU.equals("") | nombreR.equals("") | ApellidosU.equals("") | emailU.equals("") | passU.equals("") | direccionU.equals("")){
                    Toast.makeText(v.getContext(), "Introduzca todos los campos necesarios",Toast.LENGTH_SHORT).show();
                }else{
                    Usuarios u=new Usuarios(nombreU,nombreR,ApellidosU,emailU,passU,direccionU);
                    Intent intent=new Intent();
                    Bundle b=new Bundle();
                    b.putParcelable("Usuario",u);
                    intent.putExtras(b);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
    }
}
