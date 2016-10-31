package co.com.ceiba.rabbit.client;

import java.io.IOException;

import co.com.ceiba.rabbit.publisher.PokemonDispatcher;

public class StartPublisher {
	
	
	private static final int  NUMBER_OF_THREADS = 1;
	

	public static void main(String[] args) throws IOException,
			InterruptedException {
		for(int i=1; i<=NUMBER_OF_THREADS; i++){
			Thread t = new Thread(new PublisherThreadLauncher());
			t.setName("PokemonPublisherThread"+i);
			t.start();
		}
		
	}
	
	private static class PublisherThreadLauncher implements Runnable {

		@Override
		public void run() {
			PokemonDispatcher pd = new PokemonDispatcher();
		    long init = System.currentTimeMillis();
				try {
					pd.publishAPokemon(1000000);
			   long end  = System.currentTimeMillis();
			   System.out.println("tiempo total: "+ (end -init) + " ms");
				} catch (IOException e) {					
					e.printStackTrace();
				}
		}


		}
	
}
