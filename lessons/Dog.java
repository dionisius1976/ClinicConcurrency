package ru.lesson.lessons;

/**
 * This class describes pet's type dogs.
 */

public class Dog implements Pet {

	private String name;
	
	public Dog (String name){
		this.name = name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString(){
		return "dog "+this.getName();
	}

}
