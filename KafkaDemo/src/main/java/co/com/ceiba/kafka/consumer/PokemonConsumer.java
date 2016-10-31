package co.com.ceiba.kafka.consumer;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import co.com.ceiba.kafka.utils.KafkaDemoUtils;

public class PokemonConsumer {
	public static void main(String[] args) throws Exception {
	     
	      //Kafka consumer configuration settings
	      String topicName = KafkaDemoUtils.TOPIC_NAME;	       
	     
	      KafkaConsumer<String, String> consumer = new KafkaConsumer
	         <String, String>(getProps());
	      
	      //Kafka Consumer subscribes list of topics here.
	      consumer.subscribe(Arrays.asList(topicName));
	      
	      //print the topic name
	      System.out.println("Subscribed to topic " + topicName);
	    
	      
	      try {
	    	  while (true) {
	    	    ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
	    	    for (ConsumerRecord<String, String> record : records)
	    	      System.out.println(record.offset() + ": " + record.value());
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
	    	  consumer.close();
	    	  System.out.println("Consumer Done!");
	    	}

	   }

	private static Properties getProps() {
		Properties props = new Properties();
	      
	      props.put("bootstrap.servers", "localhost:9092");
	      props.put("group.id", "pokemon-consumer-group");	    
	      props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	      props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		return props;
	}

}
