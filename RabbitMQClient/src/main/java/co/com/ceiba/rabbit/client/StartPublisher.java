package co.com.ceiba.rabbit.client;

import java.io.IOException;

import co.com.ceiba.rabbit.publisher.PokemonDispatcher;

public class StartPublisher {

	private static final int NUMBER_OF_THREADS = 2;
	private static final int NUMBER_OF_MESSAGES = 500000;

	public static void main(String[] args) throws IOException, InterruptedException {
		String type = "";
		if (args.length > 0) {
			type = args[0];
		}
		for (int i = 1; i <= NUMBER_OF_THREADS; i++) {
			Thread t = new Thread(new PublisherThreadLauncher(type));
			t.setName("PokemonPublisherThread" + i);
			t.start();
		}

	}

	private static class PublisherThreadLauncher implements Runnable {

		private String type;

		public PublisherThreadLauncher(String type) {
			this.type = type;
		}

		@Override
		public void run() {
			PokemonDispatcher pd = new PokemonDispatcher();
			long init = System.currentTimeMillis();
			try {
				if ("fanout".equals(type)) {
					pd.publishPokemonsFanOut(NUMBER_OF_MESSAGES);
				} else {
					pd.publishPokemons(NUMBER_OF_MESSAGES);
				}
				long end = System.currentTimeMillis();
				System.out.println(Thread.currentThread().getName() +"-> total messages sent:" + NUMBER_OF_MESSAGES);
				System.out.println("total time: " + (end - init) + " ms");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
