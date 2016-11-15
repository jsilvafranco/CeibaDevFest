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
			//instance.loadConfiguration();
			System.out.println("resources loaded");
		}
		return instance;
	}
	
	private void loadConfiguration(){
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream("settings.properties");	
			props = new Properties();
			props.load(is);
		} catch (FileNotFoundException e) {
			System.out.println("error loading properties");
			e.printStackTrace();
		} catch (IOException e) {
			System.exit(0);
		}
		
	}
	
	public  String getHost(){
		return "192.168.53.117";
	}
	
	public  String getPort(){
		return "5672";
	}
	
}
