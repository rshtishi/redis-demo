package com.github.rshtishi.demo.springdata;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
	
	List<User> findAll();
	
	Optional<User> findById(String id);

}
