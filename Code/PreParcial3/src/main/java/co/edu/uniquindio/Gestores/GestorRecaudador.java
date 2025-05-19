package co.edu.uniquindio.Gestores;

import co.edu.uniquindio.Interfaces.ICRUDGenerico;
import co.edu.uniquindio.Model.Recaudador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorRecaudador implements ICRUDGenerico<Recaudador, String> {

    private List<Recaudador> listRecaudadores = new ArrayList<>();

    @Override
    public boolean crear(Recaudador newRecaudador) {
        for (Recaudador recaudador : listRecaudadores) {
            if (recaudador.getId().equals(newRecaudador.getId())) {
                return false;
            }
        }
        listRecaudadores.add(newRecaudador);
        return true;
    }

    @Override
    public Recaudador buscar(String id) {
        for (Recaudador recaudador : listRecaudadores) {
            if (recaudador.getId().equals(id)) {
                return recaudador;
            }
        }
        return null;
    }

    @Override
    public boolean actualizar(Recaudador recaudadorActualizar) {
        for (Recaudador recaudadorExistente : listRecaudadores) {
            if (recaudadorActualizar.getId().equals(recaudadorExistente.getId())) {
                recaudadorExistente.setNombre(recaudadorActualizar.getNombre());
                recaudadorExistente.setApellido(recaudadorActualizar.getApellido());
                recaudadorExistente.setDineroRecaudado(recaudadorActualizar.getDineroRecaudado());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(String id) {
        for (int i = 0; i < listRecaudadores.size(); i++) {
            if (listRecaudadores.get(i).getId().equals(id)) {
                listRecaudadores.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Recaudador> listar() {
        return Collections.unmodifiableList(listRecaudadores);
    }


    //getter's and setter's

    public List<Recaudador> getListRecaudadores() {
        return listRecaudadores;
    }

    public void setListRecaudadores(List<Recaudador> listRecaudadores) {
        this.listRecaudadores = listRecaudadores;
    }
}
