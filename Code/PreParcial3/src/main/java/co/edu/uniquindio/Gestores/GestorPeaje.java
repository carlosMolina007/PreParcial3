package co.edu.uniquindio.Gestores;

import co.edu.uniquindio.Interfaces.ICRUDGenerico;
import co.edu.uniquindio.Model.Peaje;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GestorPeaje implements ICRUDGenerico<Peaje, String> {

    private List<Peaje> listPeajes = new ArrayList<>();

    @Override
    public boolean crear(Peaje newPeaje) {
        for (Peaje peaje : listPeajes) {
            if (peaje.getNombre().equalsIgnoreCase(newPeaje.getNombre())) {
                return false;
            }
        }
        listPeajes.add(newPeaje);
        return true;
    }

    @Override
    public Peaje buscar(String nombre) {
        for (Peaje peaje : listPeajes) {
            if (peaje.getNombre().equalsIgnoreCase(nombre)) {
                return peaje;
            }
        }
        return null;
    }

    @Override
    public boolean actualizar(Peaje peajeActualizar) {
        for (Peaje peajeExistente : listPeajes) {
            if (peajeExistente.getNombre().equalsIgnoreCase(peajeActualizar.getNombre())) {
                peajeExistente.setNombre(peajeActualizar.getNombre());
                peajeExistente.setDepartamento(peajeActualizar.getDepartamento());
                return true;

            }
        }
        return false;
    }

    @Override
    public boolean eliminar(String nombre) {
        for (int i = 0; i < listPeajes.size(); i++) {
            if (listPeajes.get(i).getNombre().equalsIgnoreCase(nombre)) {
                listPeajes.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Peaje> listar() {
        return Collections.unmodifiableList(listPeajes);
    }

    //getter's and setter's


    public List<Peaje> getListPeajes() {
        return listPeajes;
    }

    public void setListPeajes(List<Peaje> listPeajes) {
        this.listPeajes = listPeajes;
    }
}
