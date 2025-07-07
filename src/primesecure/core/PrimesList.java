package primesecure.core;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import primesecure.model.CodigoMensaje;
import static primesecure.util.Utilidades.isPrime;

public class PrimesList extends ArrayList<CodigoMensaje>{

    private final ReentrantLock lock = new ReentrantLock(); //bloqueo para acceso concurrente

    private static final String ERROR_NO_PRIMO = "Error: solo se permiten numeros primos";

    public PrimesList() {
        super();
    }

    public PrimesList(int capacidadInicial) {
        super(capacidadInicial);
    }

    @Override
    public boolean add(CodigoMensaje codigo) {
        lock.lock(); //garantiza exclusion
        try {
            if (!isPrime(codigo.getCodigoPrimo())) {
                throw new IllegalArgumentException(ERROR_NO_PRIMO);
            }
            return super.add(codigo);
        } finally {
            lock.unlock(); //libera el bloque
        }
    }

    @Override
    public boolean remove(Object o) {
        lock.lock();
        try {
            if (o instanceof CodigoMensaje) {
                CodigoMensaje cm = (CodigoMensaje) o;
                if (isPrime(cm.getCodigoPrimo())) {
                    return super.remove(cm);
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
