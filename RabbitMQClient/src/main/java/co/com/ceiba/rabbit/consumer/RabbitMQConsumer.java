package co.com.ceiba.rabbit.consumer;

import java.io.IOException;

import com.rabbitmq.client.DefaultConsumer;


public abstract class RabbitMQConsumer  {	
	/**
	 * 
	 */
	protected String host;
	protected String port;
	protected String queue;
	/**
	 * 
	 */
	protected DefaultConsumer consumer;
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
	 abstract  void initialize() throws IOException;

	
	
	
	
	
	

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
