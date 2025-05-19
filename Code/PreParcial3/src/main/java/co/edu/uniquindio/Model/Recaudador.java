package co.edu.uniquindio.Model;

public class Recaudador extends Persona{

    //Atributos
    private double dineroRecaudado;


    //Constructor
    public Recaudador(String nombre, String apellido, String id, double dineroRecaudado) {
        super(nombre, apellido, id);
        this.dineroRecaudado = dineroRecaudado;
    }

    //getter's and setter's
    public double getDineroRecaudado() {
        return dineroRecaudado;
    }

    public void setDineroRecaudado(double dineroRecaudado) {
        this.dineroRecaudado = dineroRecaudado;
    }
}
