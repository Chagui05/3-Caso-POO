package market.model;

public interface IVisitor {
	public double visit(User user);
	public double visit(Review review);
	public double visit(Post post);

}
