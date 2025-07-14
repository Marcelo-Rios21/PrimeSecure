package primesecure.concurrent;

public class ConsumidorSimple implements Runnable {
    private final BufferSimple buffer;

    public ConsumidorSimple(BufferSimple buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                buffer.consumir();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

/*Clase de prueba */
