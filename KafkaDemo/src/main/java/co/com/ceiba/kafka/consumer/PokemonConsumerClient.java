package co.com.ceiba.kafka.consumer;


public class PokemonConsumerClient {
	
	
	  
	public static void main(String[] args) throws Exception {

		PokemonConsumerImpl pk = new PokemonConsumerImpl();
		pk.declareConsumer();
		pk.startConsuming();
		pk.shutDown();

	}
	
	

}
