package co.com.ceiba.rabbit.consumer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author jairsifr
 *
 */
public class PokemonConsumerFactory {
	
	private static int count = 0;
	
	private  static PokemonConsumerFactory instance; 
	private static Properties setupConstants;
	
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
			instance.loadConfiguration();
		}
		return instance;
	}
	/**
	 * 
	 * @return
	 */
	public  PokemonMaster createPokemonMaster(String queueName){
		count ++;
		return new PokemonMaster(setupConstants.getProperty("host"),
				setupConstants.getProperty("port"),
				queueName);
	}
	/**
	 * 
	 * @return
	 */
	public  int getCount(){
		return count;
	}
	

	
	private void loadConfiguration(){
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream("settings.properties");	
			setupConstants = new Properties();
			setupConstants.load(is);
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			System.exit(0);
		}
		
	}
}