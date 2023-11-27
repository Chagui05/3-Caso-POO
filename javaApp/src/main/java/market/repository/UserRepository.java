package market.repository;


import java.io.IOException;
import java.util.Map;
import java.util.Vector;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import market.model.User;
import redis.clients.jedis.Jedis;

public class UserRepository implements IRepository<User>{
	
	private static volatile UserRepository instance;
	private ObjectMapper objectMapper;
	private Jedis jedis;
	
	private UserRepository() {
		jedis = ConnectDBRepository.getInstance().getJedis();
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
	}
	
	// constructor singleton
	public static UserRepository getInstance() {
		UserRepository result = instance;
		if(result == null) {
			synchronized(UserRepository.class) {
				result = instance;
				if(result == null) {
					instance = result = new UserRepository();
				}
			}
		}
		return result; 
	}
	
	@Override
    public void save(User pUser) {
		try {
            String userJson = objectMapper.writeValueAsString(pUser);
            jedis.hset("userHash", "user:" + pUser.getId(), userJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(int id) {
    	String userJson = jedis.hget("userHash", "user:"+id);
    	try {
            return objectMapper.readValue(userJson, User.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

	@Override
	public Vector<User> findAll() {
		Vector<User> users = new Vector<>();
		Map<String, String> userHash = jedis.hgetAll("userHash");
		
		for (Map.Entry<String, String> entry : userHash.entrySet()) {
            String userJson = entry.getValue();
            try {
                User user = objectMapper.readValue(userJson, User.class);
                users.add(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return users;
	}

	@Override
	public void delete(int id) {
		String key = "user:" + id;
		jedis.hdel("userHash", key);
	}
}
