package co.edu.uniquindio.Interfaces;

public interface ICRUDGenerico<T, ID> {

    boolean crear(T objeto);

    T buscar(ID id);

    boolean actualizar(T objeto);

    boolean eliminar(ID id);

    java.util.List<T> listar();
}
