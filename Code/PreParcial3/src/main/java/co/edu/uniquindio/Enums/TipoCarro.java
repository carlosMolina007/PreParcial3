package co.edu.uniquindio.Enums;

public enum TipoCarro {
    ELECTRICO("Eléctrico"),
    COMBUSTION("Combustión");

    private final String tipoCarro;

    TipoCarro(String tipoCarro) {
        this.tipoCarro = tipoCarro;
    }

    @Override
    public String toString() {
        return tipoCarro;
    }
}
