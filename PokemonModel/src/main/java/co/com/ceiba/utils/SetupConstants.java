package co.com.ceiba.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SetupConstants {

	private static Properties props;
	private static SetupConstants instance; 
	
	private SetupConstants(){
		
	}
	
	public static SetupConstants getInstance(){
		if(instance==null){
			instance = new SetupConstants();
			instance.loadConfiguration();
		}
		return instance;
	}
	
	private void loadConfiguration(){
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream("settings.properties");	
			props = new Properties();
			props.load(is);
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			System.exit(0);
		}
		
	}
	
	public  String getHost(){
		return props.getProperty("host");
	}
	
	public  String getPort(){
		return props.getProperty("port");
	}
	
}
