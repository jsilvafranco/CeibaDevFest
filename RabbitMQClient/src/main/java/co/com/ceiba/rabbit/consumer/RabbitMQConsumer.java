package co.com.ceiba.rabbit.consumer;

import java.io.IOException;

import org.apache.commons.lang3.SerializationUtils;

import co.com.ceiba.rabbit.model.Pokemon;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;


public abstract class RabbitMQConsumer  extends Thread {	
	/**
	 * 
	 */
	private String host;
	private String port;
	private String queue;
	/**
	 * 
	 */
	private QueueingConsumer consumer;
	/**
	 * 
	 * @param host
	 * @param port
	 * @param queue
	 */
	public RabbitMQConsumer(String host, String port, String queue) {
		super();
		this.host = host;
		this.port = port;
		this.queue = queue;
		try {
			initialize();
		} catch (IOException e) {
			System.out.print("Error:  cant register subscriber!");
		}
	}
	/**
	 * 
	 * @throws IOException
	 */
	private  void initialize() throws IOException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();		 
		channel.queueDeclare(queue, false, false, false, null);		
		consumer = new QueueingConsumer(channel);
		channel.basicConsume(queue, true, consumer);
	}
	/**
	 * 
	 * @throws ShutdownSignalException
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	private void startListening() throws ShutdownSignalException, InterruptedException, IOException{	
		System.out.println("Subscriber for queue: "+queue+ " listening started.");
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();			
				Pokemon message = (Pokemon) SerializationUtils.deserialize(delivery.getBody());
				if(message.getId() % 100000 == 0){
					System.out.println("Queue:"+queue+" :: Consumer Tag:" + consumer.getConsumerTag()+" "+message.getName());
				}
			}		
	}
	
	/**
	 * 
	 */
	@Override
	public void run(){
		try {
			startListening();
		} catch (ShutdownSignalException | InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public String getPort() {
		return port;
	}


	public void setPort(String port) {
		this.port = port;
	}


	public String getQueue() {
		return queue;
	}


	public void setQueue(String queue) {
		this.queue = queue;
	}

}
