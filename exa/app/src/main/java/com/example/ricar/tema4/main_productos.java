package com.example.ricar.tema4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ricar.tema4.Model.Producto;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class main_productos extends AppCompatActivity implements View.OnClickListener{
    private DatabaseReference bbdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_productos);

        final Button añadir =findViewById(R.id.añadirProductos);
        añadir.setOnClickListener(this);
        final Button modificar=findViewById(R.id.button);
        modificar.setOnClickListener(this);
        final Button modificarProductes=findViewById(R.id.modificarProductos);
        modificarProductes.setOnClickListener(this);
        final Button eliminarProductes=findViewById(R.id.eliminarProductos);
        eliminarProductes.setOnClickListener(this);
        final Button productosFavoritos=findViewById(R.id.PorductosFavoritos);
        productosFavoritos.setOnClickListener(this);
        final Button añadirFavoritos=findViewById(R.id.añadirFavoritos);
        añadirFavoritos.setOnClickListener(this);
        bbdd= FirebaseDatabase.getInstance().getReference("Productos");
    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.añadirProductos){
            Intent i=new Intent(this, anyadirProductos.class);
            startActivityForResult(i,1);
        }
        if (v.getId()==R.id.button){
            Intent i =new Intent(this, mod_Perfil.class);
            startActivity(i);
        }
        if (v.getId()==R.id.modificarProductos){
            Intent i=new Intent(this,modificarProductos.class);
            startActivity(i);
        }
        if (v.getId()==R.id.eliminarProductos){
            Intent i=new Intent(this,eliminarProductos.class);
            startActivity(i);
        }
        if (v.getId()==R.id.PorductosFavoritos){
            Intent i=new Intent(this,productosFavoritos.class);
            startActivity(i);
        }
        if (v.getId()==R.id.añadirFavoritos){
            Intent i=new Intent(this,anyadirProductosFavoritos.class);
            startActivity(i);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if (resultCode==RESULT_OK){
                Bundle b=data.getExtras();
                final Producto p=b.getParcelable("producto");
                String key=bbdd.push().getKey();
                bbdd.child(key).setValue(p);

            }
        }
    }
}
