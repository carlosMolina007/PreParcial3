package co.edu.uniquindio.Enums;

public enum TipoServicio {
    PUBLICO("Servicio público"),
    PARTICULAR("Servicio particular");

    private final String tipoServicio;

    TipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    @Override
    public String toString() {
        return tipoServicio;
    }
}
