package primesecure.concurrent;

import java.util.LinkedList;
import java.util.Queue;

public class BufferSimple {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacidad;
    private final Object lock = new Object();

    public BufferSimple(int capacidad) {
        this.capacidad = capacidad;
    }

    public void producir(int valor) throws InterruptedException {
        synchronized (lock) {
            while (buffer.size() == capacidad) {
                System.out.println("Buffer lleno. Productor inactivo.");
                lock.wait();
            }
            buffer.add(valor);
            System.out.println("Productor agrego: " + valor);
            lock.notifyAll();
        }
    }

    public int consumir() throws InterruptedException {
        synchronized (lock) {
            while (buffer.isEmpty()) {
                System.out.println("Buffer vacio. Consumidor inactivo");
                lock.wait();
            }
            int valor = buffer.poll();
            System.out.println("Consumidor tomo: " + valor);
            lock.notifyAll();
            return valor;
        }
    }
}
/*Clase de prueba */