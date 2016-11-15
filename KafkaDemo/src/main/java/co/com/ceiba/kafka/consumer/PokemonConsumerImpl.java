package co.com.ceiba.kafka.consumer;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;

import co.com.ceiba.kafka.producer.PokemonProducer;
import co.com.ceiba.utils.StringUtils;

public class PokemonConsumerImpl {
	private KafkaConsumer<String, String> consumer;

	public void declareConsumer() {
		consumer = new KafkaConsumer<String, String>(getProps());

		// Kafka Consumer subscribes list of topics here.
		consumer.subscribe(Arrays.asList(StringUtils.QUEUE_POKEMONS));
		consumer.commitAsync(new OffsetCommitCallback() {

			@Override
			public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
				if (!offsets.isEmpty()) {
					for (OffsetAndMetadata osm : offsets.values()) {
						System.out.println("process completed: offset=" + osm.offset() + "--->" + osm.metadata());
					}
				}

			}
		});
	}

	public void startConsuming() {
		System.out.println("Subscribed to topic ");
		boolean upToDate = false;
		try {
			System.out.println("Consumer Started.");
			long startTime = System.currentTimeMillis();
			while (upToDate == false) {
				
				ConsumerRecords<String, String> records = consumer.poll(100);
				System.out.println("new record arrival. total count: " + records.count());
				
				if (records.count() == 0) {
					upToDate = true;
					System.out.println(Thread.currentThread().getName()+" total Time: "+( System.currentTimeMillis() - startTime));
				} else {

					for (ConsumerRecord<String, String> record : records) {
						if(record.offset()%100000==0)
						System.out.println("Topic: " + record.topic() + "  Partition: " + record.partition() + " Offset " + record.offset() + ": " + record.value());
					}
				}
			}
		} catch (WakeupException e) {
			/**
			 * the consumer will block indefinitely until the next records can
			 * be returned. Instead of setting the flag in the previous example,
			 * the thread triggering the shutdown can then call
			 * consumer.wakeup() to interrupt an active poll, causing it to
			 * throw a WakeupException. This API is safe to use from another
			 * thread. Note that if there is no active poll in progress, the
			 * exception will be raised from the next call. In this example, we
			 * catch the exception to prevent it from being propagated.
			 */
			System.out.println("wakeup exception raised" + e.getMessage());
		} finally {		
			System.out.println("Consumer Done!");
		}

	}

	public void shutDown() {
		if (consumer != null) {
			System.out.println("Consumer Finished.");
			consumer.close();
			consumer.unsubscribe();
		}
	}

	private static Properties getProps() {
		Properties props = new Properties();

		props.put("bootstrap.servers",PokemonProducer.KAFKA_BROKER);
		props.put("group.id", UUID.randomUUID().toString());
		props.put("auto.offset.reset", "earliest");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		return props;
	}
}
