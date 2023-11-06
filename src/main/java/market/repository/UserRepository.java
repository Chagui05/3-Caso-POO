package market.repository;


import java.io.IOException;
import java.util.Vector;

import com.fasterxml.jackson.databind.ObjectMapper;

import market.model.User;
import redis.clients.jedis.Jedis;

public class UserRepository implements IRepository<User>{
	
	private static volatile UserRepository instance;
	private ObjectMapper objectMapper;
	private Jedis jedis;
	
	private UserRepository() {
		jedis = ConnectDBRepository.getInstance().getJedis();
		objectMapper = new ObjectMapper();
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
            jedis.set("user:" + pUser.getId(), userJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(int id) {
    	String userJson = jedis.get("user:" + id);
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
        for (String key : jedis.keys("user:*")) {
            String userJson = jedis.get(key);
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
		jedis.del(key);
		
	}

}
