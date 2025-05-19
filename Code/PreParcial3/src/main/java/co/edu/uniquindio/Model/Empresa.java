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


    //Atrubutos
    private String nombre;
    private String nit;
    private List<Vehiculo> listVehiculos = new ArrayList<>();
    private List<Persona> listPersonas = new ArrayList<>();

    //Constructor
    public Empresa(String nombre, String nit) {
        this.nombre = nombre;
        this.nit = nit;
    }

    //Metodos

    //Unifica la lista de vehiculos (Moto, Carro, Camion)
    public List<Vehiculo> unificarListaVehiculos() {
        listVehiculos.addAll(gCamion.getListCamiones());
        listVehiculos.addAll(gMoto.getListMotos());
        listVehiculos.addAll(gCarro.getListCarros());
        return listVehiculos;
    }

    //Unifica la lista de personas (Conductor, Recaudador)
    public List<Persona> unificarListaPersonas() {
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
            }
        }
        return false;
    }

    //Registra un vehiculo que pasa por un peaje
    public boolean registrarVehiculoPorPeaje(Vehiculo vehiculo, Peaje peaje) {
        for (Vehiculo vehiculoPeaje : peaje.getListVehiculosPeaje()){
            if (vehiculo.getPlaca().equals(vehiculoPeaje.getPlaca())){
                return false;
            }
        }
        peaje.getListVehiculosPeaje().add(vehiculo);
        return true;
    }

    //Muestra los vehiculos que han pasado por ese peaje
    public List<Vehiculo> mostrarVehiculosPorPeaje(Peaje peaje) {
        return peaje.getListVehiculosPeaje();
    }

    //Muestra el dinero total pagado por los vehiculos de una persona
    public double consultarDineroPagadoPeajes(Conductor conductor) {
        double valorTotalPagado = 0;
        List<Vehiculo> listVehiculos1 = unificarListaVehiculos();
        for (Vehiculo vehiculoSistema : listVehiculos1) {
            for (Vehiculo vehiculoConductor : conductor.getListVehiculosPropios()) {
                if (vehiculoSistema.getPlaca().equalsIgnoreCase(vehiculoConductor.getPlaca())){
                    valorTotalPagado += vehiculoSistema.calcularPrecioPeaje();
                }
            }
        }
        return valorTotalPagado;
    }

    //Retorna una lista con vehiculos de un mismo tipo; esto de los vehiculos de un conductor
    public List<Vehiculo> retornarVehiculosTipo(Conductor conductor, String tipoVehiculo) {
        List<Vehiculo> listVehiculosTipo = new ArrayList<>();
        for (Vehiculo vehiculoTipo : conductor.getListVehiculosPropios()) {
            if (tipoVehiculo.equalsIgnoreCase("Carro") && vehiculoTipo instanceof Carro){
                listVehiculosTipo.add(vehiculoTipo);
            }else if (tipoVehiculo.equalsIgnoreCase("Moto") && vehiculoTipo instanceof Moto){
                listVehiculosTipo.add(vehiculoTipo);
            }else if (tipoVehiculo.equalsIgnoreCase("Camion") && vehiculoTipo instanceof Camion){
                listVehiculosTipo.add(vehiculoTipo);
            }
        }
        return listVehiculosTipo;
    }

    //Calcular el valor del peaje de un vehículo X, actualiza el valor total recaudado y guarda el registro del paso del vehículo.
    public void calcularYActualizarDineroTotalPeaje(Vehiculo vehiculo, Peaje peaje) {
        double valorPeajeVehiculo = 0;
        //Verificamos que el vehiculo exista y calculamos el valor de su peaje y actualizamos el valor total recaudado
        List<Vehiculo> listVehiculosPeaje = unificarListaVehiculos();
        for (Vehiculo vehiculoPeaje : listVehiculosPeaje) {
            if (vehiculoPeaje.getPlaca().equalsIgnoreCase(vehiculo.getPlaca())){
                valorPeajeVehiculo = vehiculoPeaje.calcularPrecioPeaje();
                peaje.setValorTotalPeajes(peaje.getValorTotalPeajes() + valorPeajeVehiculo);
                vehiculo.setCantPeajesPagos(vehiculo.getCantPeajesPagos() + 1);
                peaje.getListVehiculosPeaje().add(vehiculo);
                break;
            }
        }
    }

    //Buscar recaudador por nombre y apellido
    public Recaudador buscarRecaudadorPorNombreYApellido(String nombre, String apellido){
        for (Recaudador recaudadorExistente : gRecaudador.getListRecaudadores()){
            if (recaudadorExistente.getNombre().equals(nombre) && recaudadorExistente.getApellido().equals(apellido)){
                return recaudadorExistente;
            }
        }
        return null;
    }

    //conductores que tienen al menos un camión con capacidad de carga mayor a 10 toneladas
    public List<Conductor> conductoresConCamionesCarga10T(Conductor conductor) {
        List<Conductor> listConductoresFiltrados = new ArrayList<>();
        for (Conductor conductorValidar : gConductor.getListConductor()){
            for (Vehiculo camionVerificar : conductor.getListVehiculosPropios()){
                if(camionVerificar instanceof Camion && ((Camion) camionVerificar).getCapacidadPesoTon() > 10){
                    listConductoresFiltrados.add(conductorValidar);
                }
            }
        }
        return listConductoresFiltrados;
    }

    //Metodo para seleccionar si un carro es de servicio público
    public TipoCarro tipoCombustibleCarro(int option){
        TipoCarro carroTipo = null;
        if (option == 1){
            carroTipo = TipoCarro.ELECTRICO;
        }else if (option == 2){
            carroTipo = TipoCarro.COMBUSTION;
        }
        return carroTipo;
    }

    //Metodo para seleccionar si un carro es de servicio público
    public TipoServicio tipoServicioCarro(int option){
        TipoServicio servicioCarro = null;
        if (option == 1){
            servicioCarro = TipoServicio.PARTICULAR;
        }else if (option == 2){
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

    public List<Vehiculo> getListVehiculos() {
        return listVehiculos;
    }

    public void setListVehiculos(List<Vehiculo> listVehiculos) {
        this.listVehiculos = listVehiculos;
    }

    public List<Persona> getListPersonas() {
        return listPersonas;
    }

    public void setListPersonas(List<Persona> listPersonas) {
        this.listPersonas = listPersonas;
    }
}