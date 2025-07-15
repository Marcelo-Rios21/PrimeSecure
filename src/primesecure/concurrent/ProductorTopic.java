package primesecure.concurrent;

public class ProductorTopic implements Runnable {
    private final Topic topic;
    private final int cantidad;

    public ProductorTopic(Topic topic, int cantidad) {
        this.topic = topic;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        for (int i = 1; i < cantidad; i++) {
            topic.publicar(i * 10);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }  
}
