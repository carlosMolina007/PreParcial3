package co.edu.uniquindio.Gestores;

import co.edu.uniquindio.Interfaces.ICRUDGenerico;
import co.edu.uniquindio.Model.Camion;
import co.edu.uniquindio.Model.Conductor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorConductor implements ICRUDGenerico<Conductor, String> {

    private List<Conductor> listConductor = new ArrayList<>();

    public boolean crear(Conductor newConductor){
        for (Conductor conductor : listConductor){
            if(conductor.getId().equals(newConductor.getId())){
                return false;
            }
        }
        listConductor.add(newConductor);
        return true;
    }

    public Conductor buscar(String id){
        for (Conductor conductor : listConductor){
            if(conductor.getId().equals(id)){
                return conductor;
            }
        }
        return null;
    }

    public boolean actualizar(Conductor conductorActualizar){
        for (Conductor conductor : listConductor){
            if(conductor.getId().equals(conductorActualizar.getId())){
                conductor.setNombre(conductor.getId());
                conductorActualizar.setFechaNacimiento(conductor.getFechaNacimiento());
                conductor.setNombre(conductorActualizar.getNombre());
                conductor.setApellido(conductorActualizar.getApellido());
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(String id){
        for (int i = 0; i < listConductor.size(); i++){
            if(listConductor.get(i).getId().equals(id)){
                listConductor.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Conductor> listar(){
        return Collections.unmodifiableList(listConductor);
    }

    //getter's and setter's


    public List<Conductor> getListConductor() {
        return listConductor;
    }

    public void setListConductor(List<Conductor> listConductor) {
        this.listConductor = listConductor;
    }
}
