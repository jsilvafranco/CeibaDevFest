package co.com.ceiba.rabbit.client;

import co.com.ceiba.rabbit.consumer.PokemonConsumerFactory;
import co.com.ceiba.rabbit.consumer.PokemonMaster;
import co.com.ceiba.rabbit.utils.StringUtils;

import com.rabbitmq.client.ShutdownSignalException;

public class StartSubscribers {

	public static void main(String[] args) {
		PokemonMaster pm = PokemonConsumerFactory.getInstance().createPokemonMaster(StringUtils.QUEUE_POKEMON1);
		PokemonMaster pm2 = PokemonConsumerFactory.getInstance().createPokemonMaster(StringUtils.QUEUE_POKEMON2);
		PokemonMaster pm3 = PokemonConsumerFactory.getInstance().createPokemonMaster(StringUtils.QUEUE_POKEMON3);
		try {
			pm.start();
			pm2.start();
			pm3.start();
		} catch (ShutdownSignalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}