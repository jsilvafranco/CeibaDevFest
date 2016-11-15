package co.com.ceiba.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import co.com.ceiba.utils.StringUtils;

public class PokemonProducer {

	//public static final String KAFKA_BROKER = "192.168.53.116:9092";
	public static final String KAFKA_BROKER = "localhost:9092,localhost:9003,localhost:9094";
	private static final int TOTAL_OF_MESSAGES = 333333;
	private static final int NUMBER_OF_THREADS = 1;

	public static void main(String[] args) {
		for (int i = 1; i <= NUMBER_OF_THREADS; i++) {
			Thread t = new Thread(new KafkaProducerThread());
			t.setName("KafkaPokemonPublisherThread" + i);
			t.start();
		}
	}
	
	
	private static class KafkaProducerThread implements Runnable {

		@Override
		public void run() {
			startKafkaProducer();
			
		}
		
	}

	private static void startKafkaProducer() {

		// create instance for properties to access producer configs
		Properties props = getBrokerProps();

		Producer<String, String> producer = new KafkaProducer<String, String>(props);

		long init = System.currentTimeMillis();
		try {
			int i = 0;
			for (i=0; i < TOTAL_OF_MESSAGES; i++) {
				producer.send(new ProducerRecord<String, String>(StringUtils.QUEUE_POKEMONS[0],StringUtils.getRandomPokemon()));
				producer.send(new ProducerRecord<String, String>(StringUtils.QUEUE_POKEMONS[1],StringUtils.getRandomPokemon()));
				producer.send(new ProducerRecord<String, String>(StringUtils.QUEUE_POKEMONS[2],StringUtils.getRandomPokemon()));
				if (i > 0 && i % 100000 == 0) {				
					System.out.println("10000 messages have been sent succesful ");
					
				}
			}
			producer.flush();
			long end = System.currentTimeMillis();
			System.out.println("total sent: "+i+" Total time: " + (end - init) + " ms");
		} finally {
			System.out.println("Producer "+Thread.currentThread().getName()+" Ended: ");
			producer.close();
		}
	}

	private static Properties getBrokerProps() {
		Properties props = new Properties();

		props.put("producer.type", "async");
		// Assign localhost id
		props.put("bootstrap.servers", KAFKA_BROKER);

		// Set acknowledgements for producer requests.
		props.put("acks", "1");

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
