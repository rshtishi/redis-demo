package com.github.rshtishi.demo.springdata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

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
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	private static String HASH_KEY = "User";

	@BeforeAll
	static void setup() {
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.del(HASH_KEY);
		jedis.flushAll();
	}

	@Test
	@Order(1)
	void testFindAll() {
		// setup
		// execute
		List<User> users = userRepository.findAll();
		// verify
		int expectedSize = 0;
		assertEquals(expectedSize, users.size());
	}

	@Test
	@Order(2)
	void testSave() {
		// setup
		User user = new User("1", "Rando", "rando@xyz.com");
		// execute
		userRepository.save(user);
		// verify
		List<User> users = userRepository.findAll();
		int expectedSize = 1;
		assertEquals(expectedSize, users.size());
	}

	@Test
	@Order(3)
	void testFindById() {
		// setup
		String id = "1";
		// execute
		Optional<User> optionalUser = userRepository.findById(id);
		// verify
		assertTrue(optionalUser.isPresent());
	}

	@Test
	@Order(4)
	void testDelete() {
		// setup
		String id = "1";
		// execute
		userRepository.deleteById(id);
		// verify
		List<User> users = userRepository.findAll();
		int expectedSize = 0;
		assertEquals(expectedSize, users.size());
	}

}
