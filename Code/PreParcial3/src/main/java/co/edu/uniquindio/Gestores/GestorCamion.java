package co.edu.uniquindio.Gestores;

import co.edu.uniquindio.Interfaces.ICRUDGenerico;
import co.edu.uniquindio.Model.Camion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorCamion implements ICRUDGenerico<Camion, String> {

    private List<Camion> listCamiones = new ArrayList<>();

    //Metodos
    @Override
    public boolean crear(Camion newCamion) {
        for (Camion camion : listCamiones) {
            if (camion.getPlaca().equalsIgnoreCase(newCamion.getPlaca())) {
                return false;
            }
        }
        listCamiones.add(newCamion);
        return true;
    }

    @Override
    public Camion buscar(String placa){
        for (Camion camion : listCamiones) {
            if (camion.getPlaca().equalsIgnoreCase(placa)) {
                return camion;
            }
        }
        return null;
    }

    @Override
    public boolean actualizar(Camion camionActualizar){
        for (Camion camionExistente : listCamiones) {
            if (camionExistente.getPlaca().equalsIgnoreCase(camionActualizar.getPlaca())) {
                camionExistente.setCantPeajesPagos(camionActualizar.getCantPeajesPagos());
                camionExistente.setCantEjes(camionActualizar.getCantEjes());
                camionExistente.setCapacidadPesoTon(camionActualizar.getCapacidadPesoTon());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(String placaCamionEliminar){
        for (int i = 0; i <listCamiones.size(); i++) {
            if (listCamiones.get(i).getPlaca().equalsIgnoreCase(placaCamionEliminar)) {
                listCamiones.remove(listCamiones.get(i));
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Camion> listar(){
        return Collections.unmodifiableList(listCamiones);
    }

    //Getter's and setter's
    public List<Camion> getListCamiones() {
        return listCamiones;
    }

    public void setListCamiones(List<Camion> listCamiones) {
        this.listCamiones = listCamiones;
    }
}
