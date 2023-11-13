package market.control;


import market.model.AverageVisitor;
import market.model.Review;
import market.repository.ReviewRepository;

public class ReviewControl extends Control<Review> {

	public ReviewControl(ReviewRepository repo) {
		super(repo);
	}

	@Override
	public void save(Review pReview) {
		if(repository.findById(pReview.getId()) == null)
		{
			repository.save(pReview);
			System.out.println("se guardo: "+ pReview.getComment());
		}
	}

	@Override
	public void delete(Review pReview) {
		repository.delete(pReview.getId());
		System.out.println("se guardo: "+ pReview.getComment());
	}

	public void rate(Review pReview, double pRating) {
		pReview.getAllRatings().add(pRating);
		pReview.accept(new AverageVisitor());
		repository.save(pReview);
	}
	
	public void likeReview(Review pReview) {
		int likes = pReview.getLikes();
		pReview.setLikes(likes++);
		repository.save(pReview);
	}

}
