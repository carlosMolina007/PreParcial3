package co.edu.uniquindio.Model;

public class Camion extends Vehiculo {

    //Atributos propios
    private int cantEjes;
    private int capacidadPesoTon;

    //Constructor heredado
    public Camion(String placa, int cantPeajesPagos, int cantEjes, int capacidadPesoTon) {
        super(placa, cantPeajesPagos);
        this.cantEjes = cantEjes;
        this.capacidadPesoTon = capacidadPesoTon;
    }

    //Metodos heredados
    @Override
    public double calcularPrecioPeaje(){
        double valorTotal = 7000 * getCantEjes();
        if (getCapacidadPesoTon() > 10){
            valorTotal += (valorTotal * (getCantEjes() * 0.1));
        }
        return valorTotal;
    }

    @Override
    public String getDescripcion(){
        return "DATOS DEL VEHICULO\n =================================================\n" +
                "Tipo de vehiculo: Camión\nPlaca: "+placa+"\nCantidad de ejes: "+cantEjes+
                "\nCapacidad máxima en toneladas: "+capacidadPesoTon;
    }

    @Override
    public String toString(){
        return "Placa: "+placa+"\nCantidad de peajes pagos: "+cantPeajesPagos+"\nNumero de ejes: "+cantEjes+"\nPeso que soporta el camión: "+capacidadPesoTon+"T";
    }

    //getter's and setter's

    public int getCantEjes() {
        return cantEjes;
    }

    public void setCantEjes(int cantEjes) {
        this.cantEjes = cantEjes;
    }

    public int getCapacidadPesoTon() {
        return capacidadPesoTon;
    }

    public void setCapacidadPesoTon(int capacidadPesoTon) {
        this.capacidadPesoTon = capacidadPesoTon;
    }
}
