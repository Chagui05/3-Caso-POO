package market.repository;

import java.util.Vector;

public interface IRepository<T> {
	public void save(T repo);
	public T findById(int id);
	public Vector<T> findAll();
	public void delete(int id);
}
