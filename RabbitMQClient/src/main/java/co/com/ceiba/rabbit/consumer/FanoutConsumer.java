package co.com.ceiba.rabbit.consumer;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class FanoutConsumer extends RabbitMQConsumer {

	public FanoutConsumer(String host, String port, String queue) {
		super(host, port, queue);		
	}

	@Override
	void initialize() throws IOException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare("fanoutExchange", "fanout");
		//exclusive = true
		channel.queueDeclare(queue, false, false, false, null);
		consumer = new PokemonDefaultConsumer(channel);
		channel.basicConsume(queue, true, consumer);
	}
	
	

}
