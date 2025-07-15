package primesecure.test;

import primesecure.concurrent.ConsumidorTopic;
import primesecure.concurrent.ProductorTopic;
import primesecure.concurrent.Topic;

public class TestTopicSystem {
    public static void main(String[] args) {
        Topic topic = new Topic();

        topic.suscribir(new ConsumidorTopic("Receptor A"));
        topic.suscribir(new ConsumidorTopic("Receptor B"));
        topic.suscribir(new ConsumidorTopic("Logger"));

        Thread productor = new Thread(new ProductorTopic(topic, 5));
        productor.start();
    }
}
