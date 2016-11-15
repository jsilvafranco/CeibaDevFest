package co.com.ceiba.rabbit.publisher;

import java.io.IOException;

import org.apache.commons.lang3.SerializationUtils;

import co.com.ceiba.pokemon.model.Pokemon;
import co.com.ceiba.utils.SetupConstants;
import co.com.ceiba.utils.StringUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class PokemonDispatcher {

	
	public  void publishPokemons(int quantity) throws IOException{
		System.out.println("Publisher started. " + Thread.currentThread().getName());
		Connection connection = createConnection();
	    Channel channel = createChannel(connection);	
	    for (int i = 0; i < quantity; i++) {	
	    	//first param is the exchange. "" means direct.
	    	String qName = StringUtils.getRandomQueue();	    	
	      channel.basicPublish("",qName, null,SerializationUtils.serialize(getPokemon(StringUtils.getRandomPokemon(),i)));	   
	    }
	    System.out.println("Publisher Done.");
	    channel.close();
	    connection.close();
	}
	
	public  void publishPokemonsFanOut(int quantity) throws IOException{
		System.out.println("Publisher started. " + Thread.currentThread().getName());
		Connection connection = createConnection();
	    Channel channel = createChannel(connection);
	    channel.exchangeDeclare("fanoutExchange", "fanout");
	    for (int i = 0; i < quantity; i++) {
	      channel.basicPublish("fanoutExchange","", null,SerializationUtils.serialize(getPokemon(StringUtils.getRandomPokemon(),i)));	   
	    }
	    System.out.println("Publisher Done.");
	    channel.close();
	    connection.close();
	}
	
	

	private Connection createConnection() throws IOException {
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("192.168.53.116");
	    factory.setPort(Integer.valueOf(SetupConstants.getInstance().getPort()).intValue());
	    Connection connection = factory.newConnection();
		return connection;
	}

	private Channel createChannel(Connection connection) throws IOException {
		Channel channel = connection.createChannel();
		for (int i = 0; i < StringUtils.QUEUE_POKEMONS.length; i++) {
			 channel.queueDeclare(StringUtils.QUEUE_POKEMONS[i], true, false, false, null);
		}	   
		return channel;
	}
	
	private static Pokemon getPokemon(String name, int i){
		Pokemon p = new Pokemon();
		p.setId(i);
		p.setName(name);
		p.setAge(String.valueOf(Math.random()*i));
		p.setWeight(String.valueOf(Math.random()*i));
		p.setPicUrl(i+".png");
		return p;
	}
	
	
	
}
