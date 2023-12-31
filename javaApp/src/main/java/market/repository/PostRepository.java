package market.repository;


import java.io.IOException;
import java.util.Map;
import java.util.Vector;

import com.fasterxml.jackson.databind.ObjectMapper;

import market.model.Post;
import redis.clients.jedis.Jedis;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class PostRepository implements IRepository<Post> {
	
	private static volatile PostRepository instance;
	private ObjectMapper objectMapper;
	private Jedis jedis;
	
	private PostRepository() {
		jedis = ConnectDBRepository.getInstance().getJedis();
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
	}
	
	//Constructor Singleton
	public static PostRepository getInstance() {
		PostRepository result = instance;
		if(result == null) {
			synchronized(PostRepository.class) {
				result = instance;
				if(result == null) {
					instance = result = new PostRepository();
				}
			}
		}
		return result; 
	}
	
	@Override
	public void save(Post pPost) {
		try {
            String postJson = objectMapper.writeValueAsString(pPost);
            jedis.hset("postHash","post:" + pPost.getId(), postJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	

	@Override
	public Post findById(int id) {
		String postJson = jedis.hget("postHash","post:" + id);
        if (postJson != null) {
            try {
                return objectMapper.readValue(postJson, Post.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
	}

	@Override
	public Vector<Post> findAll() {
		Vector<Post> posts = new Vector<>();
		Map<String, String> postHash = jedis.hgetAll("postHash");
		
		for (Map.Entry<String, String> entry : postHash.entrySet()) {
            String postJson = entry.getValue();
            try {
                Post post = objectMapper.readValue(postJson, Post.class);
                posts.add(post);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return posts;
	}

	@Override
	public void delete(int id) {
		String key = "post:" + id;
		jedis.hdel("postHash",key);
	}


}
