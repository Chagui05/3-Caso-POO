package market.model;

import java.util.Vector;

public class AverageVisitor implements IVisitor {
	
	public AverageVisitor() {
		
	}

	@Override
	public double visit(User user) {
		Vector<Double> allRatings = user.getAllRatings();
		double prom = 0;
		for(double num : allRatings) {
			prom+=num;
		}
		prom = prom/allRatings.size();
		return prom;
	}

	@Override
	public double visit(Review review) {
		Vector<Double> allRatings = review.getAllRatings();
		double prom = 0;
		for(double num : allRatings) {
			prom+=num;
		}
		prom = prom/allRatings.size();
		return prom;
	}

	@Override
	public double visit(Post post) {
		Vector<Double> allRatings = post.getAllRatings();
		double prom = 0;
		for(double num : allRatings) {
			prom+=num;
		}
		prom = prom/allRatings.size();
		return prom;
	}
	

}
