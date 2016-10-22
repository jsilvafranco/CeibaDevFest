package co.com.ceiba.rabbit.utils;

public  class StringUtils {
	
	
	public static final String QUEUE_POKEMON1 = "PokemonQ1";
	public static final String QUEUE_POKEMON2 = "PokemonQ2";
	public static final String QUEUE_POKEMON3 = "PokemonQ3";

	/**
	 * 
	 * @return
	 */
	public static final String getRandomQueue() {		
		
		 double var = Math.random();
		 if (var >= 0.7f) {
		 return QUEUE_POKEMON1;
		 }
		 if (var <= 0.4f) {
		 return QUEUE_POKEMON2;
		 } else {
		 return QUEUE_POKEMON3;
		 }

	}
}
