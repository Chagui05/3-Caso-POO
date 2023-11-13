package market.control;

import market.repository.IRepository;

public abstract class Control<T> {
	
	protected IRepository repository;
	
	public Control(IRepository repo) {
		this.repository = repo;
	}
	
	public void updateValues(T type) {
		repository.save(type);
	}
	
	abstract public void save(T type);
	abstract public void delete(T type);
//	abstract public void rate(T type, double pRating);TODO: potencialmente lo podemos ocupar

}
