package primesecure.model;

public class CodigoMensaje {
    private final int codigoPrimo;
    private final String mensaje;

    public CodigoMensaje(int codigoPrimo, String mensaje) {
        this.codigoPrimo = codigoPrimo;
        this.mensaje = mensaje;
    }

    public int getCodigoPrimo() {
        return codigoPrimo;
    }

    public String getMensaje() {
        return mensaje;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigoPrimo + " | Mensaje: " + mensaje;
    }

}
