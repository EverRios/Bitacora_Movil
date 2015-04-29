///MODELO
package com.example.everardo.aplicacion;

/**
 * Created by Everardo on 21/abr/2015.
 */
public class usuarios {

    private String idusuario ;
    private String nombres ;
    private String apellidos ;
    private String tipo;
    private String nivel ;
    private  String modulo ;

    public usuarios(String idusuario, String nombres, String apellidos, String tipo, String nivel, String modulo) {
        this.idusuario = idusuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipo = tipo;
        this.nivel = nivel;
        this.modulo = modulo;
    }

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

    public String getModulo() {
        return modulo;
    }


    }
