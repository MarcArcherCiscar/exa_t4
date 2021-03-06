package com.example.ricar.tema4.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuarios implements Parcelable {
    String id;
    String nom;
    String ap;
    String email;
    String pass;
    String direccion;

    public Usuarios(String id, String nom, String ap, String email, String pass,String direccion) {
        this.id = id;
        this.nom = nom;
        this.ap = ap;
        this.email = email;
        this.pass = pass;
        this.direccion=direccion;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    protected Usuarios(Parcel in) {
        id = in.readString();
        nom = in.readString();
        ap = in.readString();
        email = in.readString();
        pass = in.readString();
        direccion = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nom);
        dest.writeString(ap);
        dest.writeString(email);
        dest.writeString(pass);
        dest.writeString(direccion);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Usuarios> CREATOR = new Parcelable.Creator<Usuarios>() {
        @Override
        public Usuarios createFromParcel(Parcel in) {
            return new Usuarios(in);
        }

        @Override
        public Usuarios[] newArray(int size) {
            return new Usuarios[size];
        }
    };
}