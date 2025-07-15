package primesecure.concurrent;

public class ConsumidorTopic implements TopicSubscriber {
    private final String nombre;

    public ConsumidorTopic(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void onMessage(int valor) {
        System.out.println(nombre + "recibio: " + valor);
    }
}
