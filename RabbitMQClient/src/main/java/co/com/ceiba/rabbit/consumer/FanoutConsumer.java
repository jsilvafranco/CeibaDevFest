package co.com.ceiba.rabbit.consumer;

import java.io.IOException;

import co.com.ceiba.utils.SetupConstants;

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
		factory.setHost(SetupConstants.getInstance().getHost());
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare("fanoutExchange", "fanout");
		//exclusive = true
		channel.queueDeclare(queue, false, false, false, null);
		channel.queueBind(queue, "fanoutExchange","");
		consumer = new PokemonDefaultConsumer(channel);
		System.out.println("new Consumer created for consumer tag: "+consumer.getConsumerTag());
		channel.basicConsume(queue, true, consumer);
	}
	
	

}
