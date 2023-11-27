package market.repository;

import java.io.IOException;
import java.util.Map;
import java.util.Vector;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import market.model.Review;
import market.model.User;
import redis.clients.jedis.Jedis;

public class ReviewRepository implements IRepository<Review>{
	
	private static volatile ReviewRepository instance;
	private ObjectMapper objectMapper;
	private Jedis jedis;
	
	private ReviewRepository() {
		jedis = ConnectDBRepository.getInstance().getJedis();
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
	}
	
	// constructor singleton
	public static ReviewRepository getInstance() {
		ReviewRepository result = instance;
		if(result == null) {
			synchronized(ReviewRepository.class) {
				result = instance;
				if(result == null) {
					instance = result = new ReviewRepository();
				}
			}
		}
		return result; 
	}
	
	@Override
    public void save(Review pReview) {
		try {
            String reviewJson = objectMapper.writeValueAsString(pReview);
            jedis.hset("reviewHash", "review:" + pReview.getId(), reviewJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	

    @Override
    public Review findById(int id) {
    	String reviewJson = jedis.hget("reviewHash","review:" + id);
        if (reviewJson != null) {
            try {
                return objectMapper.readValue(reviewJson, Review.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

	@Override
	public Vector<Review> findAll() {
		Vector<Review> reviews = new Vector<>();
		Map<String, String> reviewHash = jedis.hgetAll("reviewHash");
		
		for (Map.Entry<String, String> entry : reviewHash.entrySet()) {
            String reviewJson = entry.getValue();
            try {
                Review review = objectMapper.readValue(reviewJson, Review.class);
                reviews.add(review);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return reviews;
	}

	@Override
	public void delete(int id) {
		String key = "review:" + id;
		jedis.hdel("reviewHash", key);
	}


}