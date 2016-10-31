package co.com.ceiba.rabbit.consumer;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.sun.corba.se.pept.broker.Broker;


public abstract class RabbitMQConsumer  {	
	/**
	 * 
	 */
	private String host;
	private String port;
	private String queue;
	/**
	 * 
	 */
	private DefaultConsumer consumer;
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
		consumer = new PokemonDefaultConsumer(channel);		
		channel.basicConsume(queue, true, consumer);		
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
