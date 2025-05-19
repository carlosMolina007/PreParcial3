package co.edu.uniquindio.Model;

import co.edu.uniquindio.Enums.TipoCarro;
import co.edu.uniquindio.Enums.TipoServicio;
import co.edu.uniquindio.Gestores.*;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

    GestorCamion gCamion = new GestorCamion();
    GestorCarro gCarro = new GestorCarro();
    GestorMoto gMoto = new GestorMoto();
    GestorRecaudador gRecaudador = new GestorRecaudador();
    GestorConductor gConductor = new GestorConductor();
    GestorPeaje gPeaje = new GestorPeaje();
    //Peaje peaje;


    //Atrubutos
    private String nombre;
    private String nit;

    //Constructor
    public Empresa(String nombre, String nit) {
        this.nombre = nombre;
        this.nit = nit;
    }

    //Metodos

    //Unifica la lista de vehiculos (Moto, Carro, Camion)
    public List<Vehiculo> unificarListaVehiculos() {
        List<Vehiculo> listVehiculos = new ArrayList<>();
        if (gCarro != null && gCarro.getListCarros() != null) {
            listVehiculos.addAll(gCarro.getListCarros());
        }

        if (gMoto != null && gMoto.getListMotos() != null) {
            listVehiculos.addAll(gMoto.getListMotos());
        }

        if (gCamion != null && gCamion.getListCamiones() != null) {
            listVehiculos.addAll(gCamion.getListCamiones());
        }
        //listVehiculos.addAll(gCamion.getListCamiones());
        //listVehiculos.addAll(gMoto.getListMotos());
        //listVehiculos.addAll(gCarro.getListCarros());
        return listVehiculos;
    }

    //Unifica la lista de personas (Conductor, Recaudador)
    public List<Persona> unificarListaPersonas() {
        List<Persona> listPersonas = new ArrayList<>();
        listPersonas.addAll(gRecaudador.getListRecaudadores());
        listPersonas.addAll(gConductor.getListConductor());
        return listPersonas;
    }

    //Asigna un vehiculo a un conductor
    public boolean asignarVehiculosConductor(String idConductor, String placaVehiculoAsignar) {
        List<Vehiculo> listVehiculosExistentes = unificarListaVehiculos();
        for (Vehiculo vehiculoExistente : listVehiculosExistentes) {
            if (vehiculoExistente.getPlaca().equals(placaVehiculoAsignar)) {
                for (Conductor conductorExistente : gConductor.getListConductor()) {
                    if (conductorExistente.getId().equals(idConductor)) {
                        conductorExistente.getListVehiculosPropios().add(vehiculoExistente);
                        return true;
                    }
                }
                break;
            }
        }
        return false;
    }


    //Registra un vehiculo que pasa por un peaje
    public boolean registrarVehiculoPorPeaje(String placaVehiculoRPeaje, String nombrePeaje) {
        List<Vehiculo> listVehiculosRegistrar = unificarListaVehiculos();
        for (Peaje peajebuscar : gPeaje.getListPeajes()) {
            if (nombrePeaje.equalsIgnoreCase(peajebuscar.getNombre())) {
                Peaje peajeEncontrado = peajebuscar;
                for (Vehiculo vehiculoPeaje : listVehiculosRegistrar) {
                    if (placaVehiculoRPeaje.equals(vehiculoPeaje.getPlaca())) {
                        peajeEncontrado.getListVehiculosPeaje().add(vehiculoPeaje);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    //Muestra los vehiculos que han pasado por ese peaje
    public List<Vehiculo> mostrarVehiculosPorPeaje(String nombrePeaje) {
        for (Peaje peajeBuscar : gPeaje.getListPeajes()) {
            if (peajeBuscar.getNombre().equals(nombrePeaje)) {
                Peaje peaje = peajeBuscar;
                return peaje.getListVehiculosPeaje();
            }
        }
        return new ArrayList<>();
    }

    //Muestra el dinero total pagado por los vehiculos de una persona
    public String consultarDineroPagadoPeajes(String idConductor) {
        double valorSubTotal = 0;
        int contadorPeajes = 0;
        double valorTotal = 0;
        List<Vehiculo> listVehiculos1 = unificarListaVehiculos();
        for (Conductor conductorB : gConductor.getListConductor()) {
            if (conductorB.getId().equals(idConductor)) {
                Conductor conductor = conductorB;
                for (Vehiculo vehiculoSistema : listVehiculos1) {
                    for (Vehiculo vehiculoConductor : conductor.getListVehiculosPropios()) {
                        if (vehiculoSistema.getPlaca().equalsIgnoreCase(vehiculoConductor.getPlaca())) {
                            valorSubTotal += vehiculoSistema.calcularPrecioPeaje();
                            contadorPeajes += vehiculoSistema.cantPeajesPagos;
                        }
                    }
                }
            }
        }
        valorTotal = valorSubTotal * contadorPeajes;
        return "DINERO TOTAL PAGADO EN PEAJES POR SUS VEHICULOS:\n\nValor subTotal: "+valorSubTotal+"\n"+"Cantidad total de peajes pagos: "+contadorPeajes+
                "\n\n====>>> VALOR TOTAL : "+valorTotal+"<<<====";
    }

    //Retorna una lista con vehiculos de un mismo tipo; esto de los vehiculos de un conductor
    public List<Vehiculo> retornarVehiculosTipo(String idConductor, String tipoVehiculo) {
        List<Vehiculo> listVehiculosTipo = new ArrayList<>();
        for(Conductor conductor : gConductor.getListConductor()) {
            if(conductor.getId().equalsIgnoreCase(idConductor)) {
                for (Vehiculo vehiculoTipo : conductor.getListVehiculosPropios()) {
                    if (tipoVehiculo.equalsIgnoreCase("Carro") && vehiculoTipo instanceof Carro) {
                        listVehiculosTipo.add(vehiculoTipo);
                    } else if (tipoVehiculo.equalsIgnoreCase("Moto") && vehiculoTipo instanceof Moto) {
                        listVehiculosTipo.add(vehiculoTipo);
                    } else if (tipoVehiculo.equalsIgnoreCase("Camión") && vehiculoTipo instanceof Camion) {
                        listVehiculosTipo.add(vehiculoTipo);
                    }
                }
            }
        }

        return listVehiculosTipo;
    }

    //Calcular el valor del peaje de un vehículo X, actualiza el valor total recaudado y guarda el registro del paso del vehículo.
    public boolean calcularYActualizarDineroTotalPeaje(String placaVehiculo, String nombrePeaje) {
        double valorPeajeVehiculo = 0;
        //Verificamos que el vehiculo exista y calculamos el valor de su peaje y actualizamos el valor total recaudado
        List<Vehiculo> listVehiculosPeaje = unificarListaVehiculos();
        for (Peaje peajeBuscar : gPeaje.getListPeajes()){
            if(peajeBuscar.getNombre().equalsIgnoreCase(nombrePeaje)){
                Peaje peaje = peajeBuscar;
                for (Vehiculo vehiculoPeaje : listVehiculosPeaje) {
                    if (vehiculoPeaje.getPlaca().equalsIgnoreCase(placaVehiculo)){
                        Vehiculo vehiculo = vehiculoPeaje;
                        valorPeajeVehiculo = vehiculoPeaje.calcularPrecioPeaje();
                        peaje.setValorTotalPeajes(peaje.getValorTotalPeajes() + valorPeajeVehiculo);
                        vehiculo.setCantPeajesPagos(vehiculo.getCantPeajesPagos() + 1);
                        peaje.getListVehiculosPeaje().add(vehiculo);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Buscar recaudador por nombre y apellido
    public Recaudador buscarRecaudadorPorNombreYApellido(String nombre, String apellido) {
        for (Recaudador recaudadorExistente : gRecaudador.getListRecaudadores()) {
            if (recaudadorExistente.getNombre().equalsIgnoreCase(nombre) && recaudadorExistente.getApellido().equalsIgnoreCase(apellido)) {
                return recaudadorExistente;
            }
        }
        return null;
    }

    //conductores que tienen al menos un camión con capacidad de carga mayor a 10 toneladas
    public List<Conductor> conductoresConCamionesCarga10T() {
        List<Conductor> listConductoresFiltrados = new ArrayList<>();
        for (Conductor conductorValidar : gConductor.getListConductor()) {
            for (Vehiculo camionVerificar : conductorValidar.getListVehiculosPropios()) {
                if (camionVerificar instanceof Camion && ((Camion) camionVerificar).getCapacidadPesoTon() > 10) {
                    listConductoresFiltrados.add(conductorValidar);
                }
            }
        }
        return listConductoresFiltrados;
    }

    //Metodo para seleccionar si un carro es de servicio público
    public TipoCarro tipoCombustibleCarro(int option) {
        TipoCarro carroTipo = null;
        if (option == 1) {
            carroTipo = TipoCarro.ELECTRICO;
        } else if (option == 2) {
            carroTipo = TipoCarro.COMBUSTION;
        }
        return carroTipo;
    }

    //Metodo para seleccionar si un carro es de servicio público
    public TipoServicio tipoServicioCarro(int option) {
        TipoServicio servicioCarro = null;
        if (option == 1) {
            servicioCarro = TipoServicio.PARTICULAR;
        } else if (option == 2) {
            servicioCarro = TipoServicio.PUBLICO;
        }
        return servicioCarro;
    }

    //getter's and setter's
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public GestorCamion getgCamion() {
        return gCamion;
    }

    public GestorCarro getgCarro() {
        return gCarro;
    }

    public GestorMoto getgMoto() {
        return gMoto;
    }

    public GestorRecaudador getgRecaudador() {
        return gRecaudador;
    }

    public GestorConductor getgConductor() {
        return gConductor;
    }

    public GestorPeaje getgPeaje() {
        return gPeaje;
    }
}