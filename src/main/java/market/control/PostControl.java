package market.control;

import market.model.AverageVisitor;
import market.model.Post;
import market.model.Review;
import market.repository.PostRepository;

public class PostControl extends Control<Post> {

	public PostControl(PostRepository repo) {
		super(repo);
	}

	@Override
	public void save(Post pPost) {
		repository.save(pPost);
		System.out.println("se guardo: "+ pPost.getDescription());
	}

	@Override
	public void delete(Post pPost) {
		repository.delete(pPost.getId());
		System.out.println("se guardo: "+ pPost.getDescription());
	}

	@Override
	public void rate(Post pPost, double pRating) {
		pPost.getAllRatings().add(pRating);
		pPost.accept(new AverageVisitor());
		repository.save(pPost);	
	}
	
	public void addReview(Review pReview, Post pPost) {
		pPost.getReviews().add(pReview);
		repository.save(pPost);	
	}

}
