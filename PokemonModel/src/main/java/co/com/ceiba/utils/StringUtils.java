package co.com.ceiba.utils;

import java.util.Random;

public  class StringUtils {
	
	
	public static final String[] QUEUE_POKEMONS = {"Europe","Asia","America"};
	public static final String[] POKEMONS_NAME_LIST={"Zubat","Rattata", "Pidgey", "Pikachu", "Bulbasaur", "Charmander", "Squirtle"};

	/**
	 * 
	 * @return
	 */
	public static final String getRandomQueue() {		
		return (QUEUE_POKEMONS[new Random().nextInt(QUEUE_POKEMONS.length)]);
	}
	public static final String getRandomPokemon() {		
		return (POKEMONS_NAME_LIST[new Random().nextInt(POKEMONS_NAME_LIST.length)]);
	}
}
