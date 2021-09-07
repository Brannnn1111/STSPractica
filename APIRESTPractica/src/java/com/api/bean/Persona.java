/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.bean;

/**
 *
 * @author BRANDON RODRIGUEZ
 */
public class Persona {
    private String   _cedula;
    private String   _nombre;
    private Telefono _telefono;

    public String getCedula() {
        return _cedula;
    }

    public void setCedula(String _cedula) {
        this._cedula = _cedula;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public Telefono getTelefono() {
        return _telefono;
    }

    public void setTelefono(Telefono _telefono) {
        this._telefono = _telefono;
    }
    
    
    
}
