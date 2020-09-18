package com.github.rshtishi.demo.redistemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import redis.clients.jedis.Jedis;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonRepositoryImplTest {

	@Autowired
	private PersonRepositoryImpl personRepository;
	private static String HASH_KEY = "Person";
	
	@BeforeAll
	public static void init() {
		Jedis jedis = new Jedis("localhost",6379);
		jedis.del(HASH_KEY);
		jedis.flushAll();
	}

	@Test
	@Order(1)
	void testFindAll() {
		// setup
		// execute
		Map<String, Person> personMap = personRepository.findAll();
		// verify
		assertTrue(personMap.isEmpty());
	}

	@Test
	@Order(2)
	void testSave() {
		// setup
		Person person = new Person("1", "Rando Shtishi", 28);
		// execute
		personRepository.save(person);
		// verify
		Map<String, Person> personMap = personRepository.findAll();
		int expectedSize = 1;
		assertEquals(expectedSize,personMap.size());
	}
	
	@Test
	@Order(3)
	void testFindById() {
		//setup
		String id = "1";
		//execute
		Person person = personRepository.findById(id);
		//verify
		String expectedName = "Rando Shtishi";
		assertEquals(expectedName, person.getFullName());
	}
	
	void testDelete() {
		//setup
		String id = "1";
		//execute
		personRepository.delete(id);
		//verify
		Map<String, Person> personMap = personRepository.findAll();
		int expectedSize = 0;
		assertEquals(expectedSize,personMap.size());
	}

}
