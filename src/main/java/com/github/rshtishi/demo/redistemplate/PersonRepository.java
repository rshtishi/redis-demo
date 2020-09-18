package com.github.rshtishi.demo.redistemplate;

import java.util.Map;

public interface PersonRepository {
	
	public void save(Person person);
	
	public Person findById(String id);
	
	public Map<String, Person> findAll();
	
	public void delete(String id);

}
