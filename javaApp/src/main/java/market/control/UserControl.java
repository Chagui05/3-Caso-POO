package market.control;

import java.util.Vector;

import market.model.AverageVisitor;
import market.model.User;
import market.repository.UserRepository;

public class UserControl extends Control<User>  {
	

	public UserControl(UserRepository userRep) {
		super(userRep);
		
	}

	@Override
	public void save(User pUser) {
		if(repository.findById(pUser.getId()) == null) {
			repository.save(pUser);
			System.out.println(pUser.getId()+" añadido");
		}	
	}

	@Override
	public void delete(User pUser) {
		repository.delete(pUser.getId());
		System.out.println(pUser.getId()+" eliminado");
		
	}

	@Override
	public void rate(User pUser, double pRating) {
		pUser.getAllRatings().add(pRating);
		pUser.accept(new AverageVisitor());
		repository.save(pUser);
	}
}
