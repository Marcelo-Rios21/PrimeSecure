package primesecure.core;

import java.util.List;
import primesecure.model.CodigoMensaje;
import static primesecure.util.Utilidades.isPrime;

public class GestorCodigos {
    private final PrimesList codigos;

    public GestorCodigos( ) {
        this.codigos = new PrimesList();
    }

    private int obtenerSiguientePrimo() {
        int ultimo = 1;
        if (!codigos.isEmpty()) {
            ultimo = codigos.get(codigos.size() - 1).getCodigoPrimo();
        }
        int candidato = ultimo + 1;
        while (!isPrime(candidato)) {
            candidato++;
        }
        return candidato;
    }


    public boolean agregarCodigo(String mensaje) {
        try {
            int siguiente = obtenerSiguientePrimo();
            CodigoMensaje codigo = new CodigoMensaje(siguiente, mensaje);
            return codigos.add(codigo);
        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo Agregar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCodigo(int numeroPrimo) {
        CodigoMensaje encontrado = buscarPorCodigo(numeroPrimo);
        if (encontrado != null) {
            return codigos.remove(encontrado);
        } else {
            System.out.println("Codigo no encontrado:" + numeroPrimo);
            return false;
        }
    }

    public CodigoMensaje buscarPorCodigo(int numeroPrimo) {
        for (CodigoMensaje cm : codigos) {
            if (cm.getCodigoPrimo() == numeroPrimo) {
                return cm;
            }
        }
        return null;
    }

    public void mostrarCodigos() {
        if (codigos.isEmpty()) {
            System.out.println("No hay codigos registrados.");
        } else {
            System.out.println("Lista de codigos registrados:");
            for (CodigoMensaje cm : codigos) {
                System.out.println(" - " + cm);
            }
        }
    }

    public int getCantidadCodigos() {
        return codigos.getPrimesCount();
    }

    public List<CodigoMensaje> getListaCompleta() {
        return codigos;
    }
}
