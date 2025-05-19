package co.edu.uniquindio.Model;

public abstract class Vehiculo {
    protected String placa;
    protected int cantPeajesPagos;

    //Constructor padre
    public Vehiculo(String placa, int cantPeajesPagos) {
        this.placa = placa;
        this.cantPeajesPagos = cantPeajesPagos;
    }


    //Metodos abstractos
    public abstract double calcularPrecioPeaje();

    public abstract String getDescripcion();


    //Getter's and setter's
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public int getCantPeajesPagos() {
        return cantPeajesPagos;
    }
    public void setCantPeajesPagos(int cantPeajesPagos) {
        this.cantPeajesPagos = cantPeajesPagos;
    }
}
