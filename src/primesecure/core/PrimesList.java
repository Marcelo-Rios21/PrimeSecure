package primesecure.core;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class PrimesList extends ArrayList<Integer>{

    private final ReentrantLock lock = new ReentrantLock(); //bloqueo para acceso concurrente

    private static final String ERROR_NO_PRIMO = "Error: solo se permiten numeros primos";

    public PrimesList() {
        super();
    }

    public PrimesList(int capacidadInicial) {
        super(capacidadInicial);
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        int raiz = (int) Math.sqrt(n);
        for (int i = 3; i <= raiz; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    @Override
    public boolean add(Integer n) {
        lock.lock(); //garantiza exclusion
        try {
            if (!isPrime(n)) {
                throw new IllegalArgumentException(ERROR_NO_PRIMO);
            }
            return super.add(n);
        } finally {
            lock.unlock(); //libera el bloque
        }
    }

    @Override
    public boolean remove(Object o) {
        lock.lock();
        try {
            if (o instanceof Integer) {
                Integer n = (Integer) o;
                if (isPrime(n)) {
                    return super.remove(n);
                }
            }
            return false; // si no es un numero o no primo, no remueve
        } finally {
            lock.unlock();
        }
    }  

    public int getPrimesCount() {
        lock.lock();
        try {
            return this.size();
        } finally {
            lock.unlock();
        }
    }
}
