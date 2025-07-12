package primesecure.concurrent;

import java.util.Random;
import primesecure.core.PrimesList;
import primesecure.model.CodigoMensaje;

public class PrimesThread implements Runnable {
    private final PrimesList lista;
    private final int ciclos;
    private final Random random;

    public PrimesThread(int ciclos, PrimesList lista) {
        this.ciclos = ciclos;
        this.lista = lista;
        this.random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < ciclos; i++) {
            int candidato = random.nextInt(10000);
            try {
                CodigoMensaje nuevo = new CodigoMensaje(candidato, "Generado por hilo" + Thread.currentThread().getName());
                lista.add(nuevo);
                System.out.println("[" + Thread.currentThread().getName() + "] Agregado: " + candidato);
            } catch (IllegalArgumentException e) {
                System.out.println("[" + Thread.currentThread().getName() + "] Ignorado (no primo): " + candidato);
            }
        } 
    }
}
