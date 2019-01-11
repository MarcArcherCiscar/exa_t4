package com.example.ricar.tema4;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class mod_Perfil extends AppCompatActivity {

    private DatabaseReference bbdd;
    private FirebaseAuth mAuth;

    EditText modNombreU;
    EditText modNombreR;
    EditText modApellidos;
    EditText modDireccion;
    Button aceptarMod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod__perfil);
        modNombreU=findViewById(R.id.modNombreU);
        modNombreR=findViewById(R.id.modNombreR);
        modApellidos=findViewById(R.id.modApellidos);
        modDireccion=findViewById(R.id.modDireccion);
        aceptarMod=findViewById(R.id.aceptar);

        bbdd= FirebaseDatabase.getInstance().getReference("Usuarios");

        aceptarMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nombreU=String.valueOf(modNombreU.getText());
                final String nombreR=String.valueOf(modNombreR.getText());
                final String apellido=String.valueOf(modApellidos.getText());
                final String direccion=String.valueOf(modDireccion.getText());

                FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();

                String key="";

                if (user !=null){
                    key=user.getUid();
                }

                Query q=bbdd.orderByKey().equalTo(key);
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                            String key=datasnapshot.getKey();
                            if (!nombreU.equals("")){
                                bbdd.child(key).child("id").setValue(nombreU);
                            }
                            if (!nombreR.equals("")){
                                bbdd.child(key).child("nom").setValue(nombreR);
                            }
                            if (!apellido.equals("")){
                                bbdd.child(key).child("ap").setValue(apellido);
                            }
                            if (!direccion.equals("")){
                                bbdd.child(key).child("direccion").setValue(direccion);
                            }
                        }
                        Toast.makeText(getApplicationContext(), "Completado con exito",Toast.LENGTH_SHORT).show();
                        finish();


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}

