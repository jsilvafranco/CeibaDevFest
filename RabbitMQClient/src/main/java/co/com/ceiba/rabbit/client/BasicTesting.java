package co.com.ceiba.rabbit.client;

import co.com.ceiba.utils.StringUtils;

public class BasicTesting {

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(StringUtils.getRandomQueue());
		}

	}

}
