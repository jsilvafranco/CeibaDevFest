package co.com.ceiba.rabbit.model;

import java.io.Serializable;

public class Pokemon implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5455326604299339204L;
	private String name;
	private String age;
	private String weight;
	private String mainAttack;
	private String picUrl;
	private int id;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getMainAttack() {
		return mainAttack;
	}
	public void setMainAttack(String mainAttack) {
		this.mainAttack = mainAttack;
	}
	public String getPicUrl() {
		return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/model/"+picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
