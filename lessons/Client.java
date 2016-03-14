package ru.lesson.lessons;
/**
* This class describes clients
*/
public class Client{

	private final String name;
	private final Pet pet;
	
	public Client(String name, Pet pet){
		this.name = name;
		this.pet = pet;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Pet getPet(){
		return this.pet;
	}
	
	@Override
	public String toString(){
		return "Client "+this.getName()+". Client's pet is "+this.pet.toString();
	}

}
