///MODELO
package com.example.everardo.aplicacion;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by Everardo on 21/abr/2015.
 */
public class usuarios {

    //Declaracion de variables.
    private String idusuario;
    private String nombres;
    private String apellidos;
    private String tipo;
    private String nivel;
    private String fecha;
    private  String modulo;

    //Constructor
    public usuarios(String idusuario, String nombres, String apellidos, String tipo, String nivel, String fecha, String modulo) {
        this.idusuario = idusuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipo = tipo;
        this.nivel = nivel;
        this.fecha = fecha;
        this.modulo = modulo;
    }
    // Metodos Getter
    public String getIdusuario() {
        return idusuario;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNivel() {
        return nivel;
    }

    public String getFecha() {

        return fecha;
    }
    public String getModulo() {

        return modulo;
    }
    }
