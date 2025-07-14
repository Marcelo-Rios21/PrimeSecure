package primesecure.concurrent;

import primesecure.core.PrimesList;
import primesecure.model.CodigoMensaje;
import static primesecure.util.Utilidades.isPrime;

public class BufferPrimosSync {
    private final PrimesList listaCompartida;
    private final Object monitor = new Object();

    public BufferPrimosSync(PrimesList lista) {
        this.listaCompartida = lista;
    }

    public void agregarSiEsPrimo(int valor, String mensaje) throws InterruptedException {
        synchronized (monitor) {
            while (!isPrime(valor)) {
                System.out.println("Valor no primo. Esperando nuevo valor...");
                monitor.wait();
            }

            CodigoMensaje codigo = new CodigoMensaje(valor, mensaje);
            boolean agregado = listaCompartida.add(codigo);
            if (agregado) {
                System.out.println("Agregado (sincronizado): " + valor);
                monitor.notifyAll(); 
            }
        }
    }

    public CodigoMensaje obtenerUltimoDisponible() throws InterruptedException {
        synchronized (monitor) {
            while (listaCompartida.isEmpty()) {
                System.out.println("Lista vacia. Esperando nuevos codigos...");
                monitor.wait();
            }

            CodigoMensaje ultimo = listaCompartida.get(listaCompartida.size() - 1);
            System.out.println("Ultimo codigo disponible: " + ultimo);
            monitor.notifyAll();
            return ultimo;
        }
    }

    public void notificarNuevoValor() {
        synchronized (monitor) {
            monitor.notifyAll();
        }
    }
}
