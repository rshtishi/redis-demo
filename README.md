# redis-demo

A practical guide for accessing the Redis datastore.

## Topics

Redis is a very fast non-relational database that stores a mapping of keys to five different types of values(strings, lists, sets, hashes, sorted sets). In this demo
we will cover the following topics:

- configuring Redis
- accessing Redis with RedisTemplate
- accessing Redis with SpringData

### Configuring Redis

The following dependencies are needed to be added in the pom file:

```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis</artifactId>
		</dependency>

		<dependency>
			<groupId>redis.clients</groupId>
			<artifactId>jedis</artifactId>
			<type>jar</type>
		</dependency>
```

After that we need to configure the RedisTemplate:

```
	@Bean
	public JedisConnectionFactory jdedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(host);
		redisStandaloneConfiguration.setPort(port);
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
		return jedisConnectionFactory;
	}
	
	@Primary
	@Bean
	public RedisTemplate<String,Object> redisTemplate(){
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jdedisConnectionFactory());
		return redisTemplate;
	}
```

### Accessing Redis with RedisTemplate

Below is the implementation of the repository with RedisTemplate:

```
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
```

### Accessing Redis with SpringData

Below we are using Spring Data to generate automatically the implementation for the methods need to access Redis:

```
@Repository
public interface UserRepository extends CrudRepository<User, String> {
	
	List<User> findAll();
	
	Optional<User> findById(String id);

}
```
