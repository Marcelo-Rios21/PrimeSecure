package primesecure.concurrent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Topic {
    private final List<TopicSubscriber> suscriptores = new CopyOnWriteArrayList<>();

    public void suscribir(TopicSubscriber sub) {
        suscriptores.add(sub);
    }

    public void publicar(int valor) {
        for (TopicSubscriber sub : suscriptores) {
            sub.onMessage(valor);
        }
    }
}
