package market.repository;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ConnectDBRepository {
	
	private static volatile ConnectDBRepository instance;
	private Jedis jedis;
	
	private ConnectDBRepository() {
		
		JedisPool pool = new JedisPool("localhost", 6379);
		try ( Jedis jedisPr = pool.getResource()) {
			jedis = jedisPr; 
		}
		
	}
	
	//Constructor Singleton
	public static ConnectDBRepository getInstance() {
		ConnectDBRepository result = instance;
		
		if(result == null) {
			synchronized(ConnectDBRepository.class) {
				result = instance;
				if(result == null) {
					instance = result = new ConnectDBRepository();
				}
			}
		}
		return result; 
	}
	
	public void addAndPrint(String pParameter) {
		jedis.set("foo", pParameter);
		System.out.println(jedis);
        System.out.println(jedis.get("foo")); // prints bar
	}

}
