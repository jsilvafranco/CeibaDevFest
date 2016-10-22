package co.com.ceiba.rabbit.publisher;

import java.io.IOException;

import org.apache.commons.lang3.SerializationUtils;

import co.com.ceiba.rabbit.model.Pokemon;
import co.com.ceiba.rabbit.utils.StringUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class PokemonDispatcher {

	
	public  void publishAPokemon(int quantity) throws IOException{
		System.out.println("Publisher started. " + Thread.currentThread().getName());
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    channel.queueDeclare(StringUtils.QUEUE_POKEMON1, false, false, false, null);
	    channel.queueDeclare(StringUtils.QUEUE_POKEMON2, false, false, false, null);	
	    channel.queueDeclare(StringUtils.QUEUE_POKEMON3, false, false, false, null);	
	    for (int i = 0; i < quantity; i++) {	
	    	//first param is the exchange.
	    	String qName = StringUtils.getRandomQueue();
	      channel.basicPublish("",qName, null,SerializationUtils.serialize(getPokemon(qName,i)));	   
	    }
	    System.out.println("Publisher Done.");
	    
	}
	
	private static Pokemon getPokemon(String queueName, int i){
		Pokemon p = new Pokemon();
		p.setId(i);
		p.setName("poke"+i+"-"+queueName);
		p.setAge(String.valueOf(Math.random()*i));
		p.setWeight(String.valueOf(Math.random()*i));
		p.setPicUrl(i+".png");
		return p;
	}
	
	
	
}
