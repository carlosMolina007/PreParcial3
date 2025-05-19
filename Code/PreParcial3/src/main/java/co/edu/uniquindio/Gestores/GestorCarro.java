package co.edu.uniquindio.Gestores;

import co.edu.uniquindio.Interfaces.ICRUDGenerico;
import co.edu.uniquindio.Model.Carro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorCarro implements ICRUDGenerico<Carro, String> {

    private List<Carro> listCarros = new ArrayList<>();

    @Override
    public boolean crear(Carro newCarro) {
        for (Carro carro : listCarros) {
            if (carro.getPlaca().equalsIgnoreCase(newCarro.getPlaca())) {
                return false;
            }
        }
        listCarros.add(newCarro);
        return true;
    }

    @Override
    public Carro buscar(String placa){
        for (Carro carro : listCarros) {
            if (carro.getPlaca().equalsIgnoreCase(placa)) {
                return carro;
            }
        }
        return null;
    }

    @Override
    public boolean actualizar(Carro carroActualizar){
        for (Carro carroExistente : listCarros) {
            if (carroExistente.getPlaca().equalsIgnoreCase(carroActualizar.getPlaca())) {
                carroExistente.setTipoCarro(carroActualizar.getTipoCarro());
                carroExistente.setServicioPublico(carroActualizar.getServicioPublico());
                carroExistente.setCantPeajesPagos(carroActualizar.getCantPeajesPagos());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(String placaCarroEliminar){
        for (int i = 0; i <listCarros.size(); i++) {
            if (listCarros.get(i).getPlaca().equalsIgnoreCase(placaCarroEliminar)) {
                listCarros.remove(listCarros.get(i));
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Carro> listar(){
        return Collections.unmodifiableList(listCarros);
    }

    //getter's and setter's

    public List<Carro> getListCarros() {
        return listCarros;
    }

    public void setListCarros(List<Carro> listCarros) {
        this.listCarros = listCarros;
    }
}
