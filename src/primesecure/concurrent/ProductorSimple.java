package primesecure.concurrent;

public class ProductorSimple implements Runnable {
    private final BufferSimple buffer;

    public ProductorSimple(BufferSimple buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                buffer.producir(i);
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
/*Clase de prueba */