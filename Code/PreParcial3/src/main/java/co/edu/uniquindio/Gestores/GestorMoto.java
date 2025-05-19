package co.edu.uniquindio.Gestores;

import co.edu.uniquindio.Interfaces.ICRUDGenerico;
import co.edu.uniquindio.Model.Moto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorMoto implements ICRUDGenerico<Moto, String> {

    private List<Moto> listMotos = new ArrayList<>();

    @Override
    public boolean crear(Moto newMoto){
        for (Moto moto : listMotos){
            if(moto.getPlaca().equals(newMoto.getPlaca())){
                return false;
            }
        }
        listMotos.add(newMoto);
        return true;
    }

    @Override
    public Moto buscar(String placa) {
        for (Moto moto : listMotos){
            if(moto.getPlaca().equals(placa)){
                return moto;
            }
        }
        return null;
    }

    @Override
    public boolean actualizar(Moto motoActualizar) {
        for (Moto motoExistente : listMotos){
            if(motoActualizar.getPlaca().equals(motoExistente.getPlaca())){
                motoExistente.setCantPeajesPagos(motoActualizar.getCantPeajesPagos());
                motoExistente.setCilindrada(motoActualizar.getCilindrada());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(String placa) {
        for (int i = 0; i < listMotos.size(); i++){
            if(listMotos.get(i).getPlaca().equals(placa)){
                listMotos.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Moto> listar() {
        return Collections.unmodifiableList(listMotos);
    }

    //Getter's and setter's


    public List<Moto> getListMotos() {
        return listMotos;
    }

    public void setListMotos(List<Moto> listMotos) {
        this.listMotos = listMotos;
    }
}
