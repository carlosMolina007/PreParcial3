package co.edu.uniquindio.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Conductor extends Persona{

    //Atributos propios
    private LocalDate fechaNacimiento;
    private List<Vehiculo> listVehiculosPropios = new ArrayList<>();

    //Constructor
    public Conductor(String nombre, String apellido, String id, LocalDate fechaNacimiento){
        super(nombre, apellido, id);
        this.fechaNacimiento = LocalDate.of(fechaNacimiento.getYear(), fechaNacimiento.getMonthValue(), fechaNacimiento.getDayOfMonth());
    }

    @Override
    public String toString() {
        return "\nNombre: "+nombre+"\nApellido: "+apellido+"\nDocumento de identidad: "+id+"\nFecha de nacimiento: "+fechaNacimiento+"\n";
    }

    //getter's and setter's

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Vehiculo> getListVehiculosPropios() {
        return listVehiculosPropios;
    }
    public void setListVehiculosPropios(List<Vehiculo> listVehiculosPropios) {
        this.listVehiculosPropios = listVehiculosPropios;
    }
}
