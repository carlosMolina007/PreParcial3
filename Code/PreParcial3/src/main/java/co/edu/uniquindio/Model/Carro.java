package co.edu.uniquindio.Model;

import co.edu.uniquindio.Enums.TipoCarro;
import co.edu.uniquindio.Enums.TipoServicio;

public class Carro extends Vehiculo {

    //Atributos propios
    private TipoCarro tipoCarro;
    private TipoServicio servicioPublico;


    //Constructor heredado
    public Carro(String placa, int cantPeajesPagos, TipoCarro tipoCarro, TipoServicio servicioPublico) {
        super(placa, cantPeajesPagos);
        this.tipoCarro = tipoCarro;
        this.servicioPublico = servicioPublico;
    }

    //Metodos heredados
    @Override
    public double calcularPrecioPeaje(){
        double precioTotal = 10000;
        if(getTipoCarro() == TipoCarro.ELECTRICO){
            precioTotal -= precioTotal*0.2;
        }else if(getServicioPublico() == TipoServicio.PUBLICO){
            precioTotal += precioTotal*0.15;
        }
        return precioTotal;
    }

    @Override
    public String getDescripcion() {
        return "DATOS DEL VEHICULO\n =================================================\n" +
                "Tipo de vehiculo: Carro\nPlaca: "+placa+"\nTipo de carro: "+tipoCarro.toString()+"\nVehiculo de: "+servicioPublico.toString()+"\nCantidade de peajes pagos: "+cantPeajesPagos;
    }

    @Override
    public String toString() {
        return "Datos del carro:\nPlaca: "+placa+"\nTipo de carro: "+tipoCarro.toString()+"\nVehiculo de: "+servicioPublico.toString()+"\nCantidad de peajes pagados: "+cantPeajesPagos+"\n";
    }

    //getter's and setter's


    public TipoCarro getTipoCarro() {
        return tipoCarro;
    }

    public void setTipoCarro(TipoCarro tipoCarro) {
        this.tipoCarro = tipoCarro;
    }

    public TipoServicio getServicioPublico() {
        return servicioPublico;
    }

    public void setServicioPublico(TipoServicio servicioPublico) {
        this.servicioPublico = servicioPublico;
    }
}
