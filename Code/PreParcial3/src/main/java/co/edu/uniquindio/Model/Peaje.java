package co.edu.uniquindio.Model;

import java.util.ArrayList;
import java.util.List;

public class Peaje {

    //Atributos
    private String nombre;
    private String departamento;
    private double valorTotalPeajes;
    private List<Vehiculo> listVehiculosPeaje = new ArrayList<>();

    //Constructor
    public Peaje(String nombre, String departamento) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.valorTotalPeajes = 0;
    }

    //ToString
    @Override
    public String toString() {
        return "Nombre del peaje: " + nombre + "\nDepartamento en el que está ubicado: " + departamento+"\n";
    }

    //getter's and setter's
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public double getValorTotalPeajes() {
        return valorTotalPeajes;
    }
    public void setValorTotalPeajes(double valorTotalPeajes) {
        this.valorTotalPeajes = valorTotalPeajes;
    }
    public List<Vehiculo> getListVehiculosPeaje() {
        return listVehiculosPeaje;
    }
    public void setListVehiculosPeaje(List<Vehiculo> listVehiculosPeaje) {
        this.listVehiculosPeaje = listVehiculosPeaje;
    }
}
