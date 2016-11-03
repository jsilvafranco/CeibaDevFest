package co.com.ceiba.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import co.com.ceiba.kafka.utils.KafkaDemoUtils;

public class PokemonProducer {

	public static void main(String[] args) {

		// Assign topicName to string variable
		startKafkaProducer();
	}

	private static void startKafkaProducer() {
		String topicName = KafkaDemoUtils.TOPIC_NAME;

		// create instance for properties to access producer configs
		Properties props = getBrokerProps();

		Producer<String, String> producer = new KafkaProducer<String, String>(props);

		long init = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			producer.send(new ProducerRecord<String, String>(topicName, "K" + Integer.toString(i), "V-" + "Pokemon-" + Integer.toString(i)));
			if (i > 0 && i % 1000 == 0) {
				System.out.println("A thousand messages have been sent succesful ");
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("tiempo total: " + (end - init) + " ms");
		producer.close();
	}

	private static Properties getBrokerProps() {
		Properties props = new Properties();

		props.put("producer.type", "async");
		// Assign localhost id
		props.put("bootstrap.servers", "localhost:9092");

		// Set acknowledgements for producer requests.
		props.put("acks", "all");

		// If the request fails, the producer can automatically retry,
		props.put("retries", Integer.valueOf(0));

		// Specify buffer size in config
		props.put("batch.size", Integer.valueOf(16384));

		// Reduce the no of requests less than 0
		props.put("linger.ms", Integer.valueOf(1));

		// The buffer.memory controls the total amount of memory available to
		// the producer for buffering.
		props.put("buffer.memory", Long.valueOf(33554432));

		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		return props;
	}
}
