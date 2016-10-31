package co.com.ceiba.rabbit.consumer;

import java.io.IOException;

import org.apache.commons.lang3.SerializationUtils;

import co.com.ceiba.rabbit.model.Pokemon;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class PokemonDefaultConsumer extends DefaultConsumer {

	public PokemonDefaultConsumer(Channel channel) {
		super(channel);
	}

	@Override
	public void handleDelivery(String consumerTag, Envelope envelope,
			AMQP.BasicProperties properties, byte[] body) throws IOException {
		Pokemon message = (Pokemon) SerializationUtils.deserialize(body);		
		if (message.getId() % 100000 == 0) {
			System.out.println("Queue:" + envelope.getRoutingKey()
					+ " :: Consumer Tag:" + consumerTag + " "
					+ message.getName());
		}
	}

}
