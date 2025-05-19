package co.edu.uniquindio.Model;

public class Moto extends Vehiculo {

    //Atributos
    private int cilindrada;

    //Constructor extendido de Vehiculo
    public Moto(String placa, int cantPeajesPagos, int cilindrada) {
        super(placa, cantPeajesPagos);
        this.cilindrada = cilindrada;
    }

    //Metodos heredados
    @Override
    public double calcularPrecioPeaje(){
        double precioTotal = 5000;
        if(getCilindrada() > 200){
            precioTotal += 2000;
        }
        return precioTotal;
    }

    @Override
    public String getDescripcion() {
        return "DATOS DEL VEHICULO\n =================================================\n" +
                "Tipo de vehiculo: Moto\nPlaca: "+placa+"\nCilindraje: "+cilindrada+"\nCantidad de peajes pagos: "+cantPeajesPagos;
    }

    @Override
    public String toString() {
        return "Datos de la moto:\nPlaca: "+placa+"\nCilindrada: "+cilindrada+"\nCantidad de peajes pagados:"+cantPeajesPagos+"\n";
    }


    //Getter's and setter's


    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }
}
