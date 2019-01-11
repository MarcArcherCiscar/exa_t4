package com.example.ricar.tema4;

import android.content.Intent;
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

public class anyadirProductosFavoritos extends AppCompatActivity {

    private DatabaseReference bbdd;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anyadir_productos_favoritos);
        mAuth = FirebaseAuth.getInstance();

        final EditText nombreFavorito=findViewById(R.id.nombreFavorito);
        bbdd= FirebaseDatabase.getInstance().getReference("Productos");

        final Button añadir=findViewById(R.id.btnAñadirFavorito);
        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nombre= String.valueOf(nombreFavorito.getText());

                if (nombre.equals("")){
                    Toast.makeText(getApplicationContext(),"Introduzca el nombre del producto del que quiere que se agreguen a sus favoritos",Toast.LENGTH_SHORT).show();
                }else {
                    Query q=bbdd.orderByChild("nombre").equalTo(nombre);
                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            bbdd= FirebaseDatabase.getInstance().getReference("Productos");
                            FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
                            user.getUid();
                            for (DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                                String key=datasnapshot.getKey();
                                DatabaseReference dbr=bbdd.child(key);
                                dbr.getKey();
                                if (dataSnapshot.getValue()==null){
                                    Toast.makeText(getApplicationContext(), "Error, compruebe que ha escrito correctamente el nombre",Toast.LENGTH_SHORT).show();
                                }else
                                    Toast.makeText(getApplicationContext(), "Se ha añadido a favoritos el producto: "+ nombre,Toast.LENGTH_SHORT).show();
                            }

                            finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }
}

