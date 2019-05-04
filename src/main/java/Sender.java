import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Sender {
    //load rabbitmq params from file, or use default values, any other needed params can be added

    private final static String QUEUE_NAME = BrokerProperty.load("queue", "test");
    private final static String EXCHANGE_NAME = BrokerProperty.load("exchange", "defaultExchange");
    private final static String ROUTING_KEY = BrokerProperty.load("routingKey", "");
    private final static String HOST_NAME = BrokerProperty.load("host", "localhost");
    private final static String EXCHANGE_TYPE = BrokerProperty.load("exchangeType", "direct");
    private final static String QUEUE_DURABILITY = BrokerProperty.load("queueDurability", "true");
    private final static String EXCHANGE_DURABILITY = BrokerProperty.load("exchangeDurability", "true");

    //takes an array of bytes, so any type of content can be sent
    public void send(byte[] message) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST_NAME);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, Boolean.parseBoolean(QUEUE_DURABILITY), false, false, null); //create a queue if it doesn't exist
        channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE, Boolean.parseBoolean(EXCHANGE_DURABILITY)); //create an exchange if it doesn't exist
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY); //bind a queue and an exchange
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message);//send a message to exchange
    }
}