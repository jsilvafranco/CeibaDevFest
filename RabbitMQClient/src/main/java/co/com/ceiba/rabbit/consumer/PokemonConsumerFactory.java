package co.com.ceiba.rabbit.consumer;

import co.com.ceiba.utils.SetupConstants;

/**
 * 
 * @author jairsifr
 *
 */
public class PokemonConsumerFactory {
	
	private static int count = 0;
	
	private  static PokemonConsumerFactory instance; 
	
	
	private PokemonConsumerFactory(){
		//private constructor.
	}
	
	/**
	 * 
	 * @return
	 */
	public static PokemonConsumerFactory getInstance(){
		if(instance == null){
			instance  = new PokemonConsumerFactory();			
		}
		return instance;
	}
	/**
	 * 
	 * @return
	 */
	public  PokemonMaster createPokemonMaster(String queueName){
		count ++;
		return new PokemonMaster(SetupConstants.getInstance().getHost(),
				SetupConstants.getInstance().getPort(),
				queueName);
	}
	
	public  FanoutConsumer createFanOutConsumer(String queueName){		
		return new FanoutConsumer(SetupConstants.getInstance().getHost(),
				SetupConstants.getInstance().getPort(),
				queueName);
	}
	/**
	 * 
	 * @return
	 */
	public  int getCount(){
		return count;
	}
	

	
	
}
