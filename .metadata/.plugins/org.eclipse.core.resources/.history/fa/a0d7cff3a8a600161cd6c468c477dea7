package co.com.ceiba.rabbit.consumer;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import co.com.ceiba.rabbit.model.Pokemon;
import co.com.ceiba.rabbit.utils.SetupConstants;
/**
 * 
 * @author jairsifr
 *
 */
public class PokemonMaster extends RabbitMQConsumer implements Serializable {
	
	private List<Pokemon> pokeDesk;
	private String name;

	public PokemonMaster(String host, String port, String queue) {
		super(host, port, queue);
		pokeDesk= new ArrayList<Pokemon>();		
	}
	
	/**
	 * 
	 */
	@Override
	public void initialize() throws IOException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(SetupConstants.getInstance().getHost());
		factory.setPort(Integer.valueOf(SetupConstants.getInstance().getPort()).intValue());
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(queue, false, false, false, null);
		consumer = new PokemonDefaultConsumer(channel);
		channel.basicConsume(queue, true, consumer);
	}
	
	
	
	public void sayHello(){
		System.out.println("Hello I'm "+name +" and my gym is: " + getQueue());
	}
	
	public void listPokeDesk(){
		System.out.println("This is "+name+" PokeDesk!");
		for (Pokemon p : pokeDesk) {
			System.out.println(p.getName());
		}
	}

	/**
	 * UID
	 */
	private static final long serialVersionUID = 4531573881891635376L;

	public List<Pokemon> getPokeDesk() {
		return pokeDesk;
	}

	public void setPokeDesk(List<Pokemon> pokeDesk) {
		this.pokeDesk = pokeDesk;
	}

	public String getMasterName() {
		return name;
	}

	public void setMasterName(String name) {
		this.name = name;
	}
}
