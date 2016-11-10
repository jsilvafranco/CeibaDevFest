package co.com.ceiba.rabbit.client;

import co.com.ceiba.rabbit.consumer.PokemonConsumerFactory;
import co.com.ceiba.utils.StringUtils;

public class StartSubscribers {

	public static void main(String[] args) {

		if (args.length > 0 && args[0].equals("fanout")) {
			for (int i = 0; i < StringUtils.QUEUE_POKEMONS.length; i++) {
				PokemonConsumerFactory.getInstance().createFanOutConsumer(StringUtils.QUEUE_POKEMONS[i]);
			}

		} else {
			for (int i = 0; i < StringUtils.QUEUE_POKEMONS.length; i++) {
				PokemonConsumerFactory.getInstance().createPokemonMaster(StringUtils.QUEUE_POKEMONS[i]);
			}
		}

	}

}
