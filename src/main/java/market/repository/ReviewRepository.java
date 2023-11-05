package market.repository;

import java.io.IOException;
import java.util.Vector;

import com.fasterxml.jackson.databind.ObjectMapper;



import market.model.Review;
import redis.clients.jedis.Jedis;

public class ReviewRepository implements IRepository<Review>{
	
	private static volatile ReviewRepository instance;
	private ObjectMapper objectMapper;
	private Jedis jedis;
	
	private ReviewRepository() {
		jedis = ConnectDBRepository.getInstance().getJedis();
		objectMapper = new ObjectMapper();
	}
	
	// constructor singleton
	public static ReviewRepository getInstance() {
		ReviewRepository result = instance;
		if(result == null) {
			synchronized(UserRepository.class) {
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
            jedis.set("review:" + pReview.getId(), reviewJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Review findById(int id) {
    	String reviewJson = jedis.get("review:" + id);
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
		for (String key : jedis.keys("review:*")) {
            String reviewJson = jedis.get(key);
            try {
                Review review = objectMapper.readValue(reviewJson, Review.class);
                reviews.add(review);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return reviews;
	}


}