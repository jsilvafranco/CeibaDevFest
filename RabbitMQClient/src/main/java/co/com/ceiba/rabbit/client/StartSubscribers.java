package co.com.ceiba.rabbit.client;

import co.com.ceiba.rabbit.consumer.PokemonConsumerFactory;
import co.com.ceiba.rabbit.utils.StringUtils;

public class StartSubscribers {

	public static void main(String[] args) {
		PokemonConsumerFactory.getInstance().createPokemonMaster(StringUtils.QUEUE_POKEMON1);
		PokemonConsumerFactory.getInstance().createPokemonMaster(StringUtils.QUEUE_POKEMON2);
		PokemonConsumerFactory.getInstance().createPokemonMaster(StringUtils.QUEUE_POKEMON3);
	}

}
