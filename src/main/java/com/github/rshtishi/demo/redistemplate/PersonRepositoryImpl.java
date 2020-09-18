package com.github.rshtishi.demo.redistemplate;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

	private RedisTemplate redisTemplate;
	private static String HASH_KEY = "Person";
	private HashOperations<String, String, Person> hashOperations;

	@Autowired
	public PersonRepositoryImpl(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void save(Person person) {
		hashOperations.put(HASH_KEY, person.getId(), person);
	}

	@Override
	public Person findById(String id) {
		return hashOperations.get(HASH_KEY, id);
	}

	@Override
	public Map<String, Person> findAll() {
		return hashOperations.entries(HASH_KEY);
	}

	@Override
	public void delete(String id) {
		hashOperations.delete(HASH_KEY, id);
	}

}
