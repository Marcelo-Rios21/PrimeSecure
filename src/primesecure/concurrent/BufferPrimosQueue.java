package primesecure.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class BufferPrimosQueue {
    private final BlockingQueue<Integer> cola;

    public BufferPrimosQueue(int capacidadMaxima) {
        this.cola = new LinkedBlockingDeque<>(capacidadMaxima);
    }

    public void publicar(int valor) throws InterruptedException {
        cola.put(valor);
        System.out.println("Publicado en cola: " + valor);
    }

    public int consumir() throws InterruptedException {
        int valor = cola.take();
        System.out.println("Consumido por cola: " + valor);
        return valor;
    }

    public int tamañoActual() {
        return cola.size();
    }
}
